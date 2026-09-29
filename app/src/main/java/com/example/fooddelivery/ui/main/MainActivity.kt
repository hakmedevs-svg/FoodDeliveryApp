package com.example.fooddelivery.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.R
import com.example.fooddelivery.data.PreferenceManager
import com.example.fooddelivery.data.Restaurant
import com.example.fooddelivery.ui.restaurant.RestaurantAdapter
import com.example.fooddelivery.ui.restaurant.RestaurantDetailActivity
import com.example.fooddelivery.ui.settings.SettingsActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var adapter: RestaurantAdapter
    private lateinit var locationText: TextView
    private lateinit var prefs: PreferenceManager

    private val restaurants = listOf(
        Restaurant(1, "Baghdad Grill", "Arabic", 4.9, "20-30 min", "Free over 20K", "Karrada, Baghdad", "Fresh grilled meals and fast delivery.", 33.3152, 44.3661),
        Restaurant(2, "Basra Feast", "Seafood", 4.8, "25-35 min", "4,000 IQD", "Al-Ashar, Basra", "Seafood specials and family meals.", 30.5369, 47.8203),
        Restaurant(3, "Erbil Bites", "Fast Food", 4.7, "15-25 min", "3,500 IQD", "Center, Erbil", "Fast and reliable door-to-door eating.", 36.1911, 44.0092),
        Restaurant(4, "Mosul Pizza", "Pizza", 4.6, "30-40 min", "5,000 IQD", "Old City, Mosul", "Tasty pizza and rich crusts.", 36.3350, 43.1189)
    )

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        val allowed = result[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            result[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (allowed) {
            getCurrentLocation()
        } else {
            Toast.makeText(this, "Location disabled. Showing default order list.", Toast.LENGTH_SHORT).show()
            render(restaurants)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = PreferenceManager(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationText = findViewById(R.id.locationText)

        val recyclerView = findViewById<RecyclerView>(R.id.restaurantsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RestaurantAdapter(restaurants) { restaurant ->
            val intent = Intent(this, RestaurantDetailActivity::class.java)
            intent.putExtra("restaurant", restaurant)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.locationButton).setOnClickListener {
            requestLocationPermission()
        }

        findViewById<ImageButton>(R.id.settingsButton).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        render(restaurants)
    }

    private fun requestLocationPermission() {
        val fineGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        if (fineGranted == PackageManager.PERMISSION_GRANTED || coarseGranted == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation()
        } else {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                val sorted = restaurants.sortedBy { restaurant ->
                    distance(location.latitude, location.longitude, restaurant.latitude, restaurant.longitude)
                }
                locationText.text = "Current location: ${location.latitude}, ${location.longitude}"
                render(sorted)
            } else {
                locationText.text = "Location unavailable"
                render(restaurants)
            }
        }
    }

    private fun render(list: List<Restaurant>) {
        adapter.updateData(list)
    }

    private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val earthRadius = 6371.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
            cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
            sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1.0 - a))
        return earthRadius * c
    }
}
