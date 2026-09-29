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
import com.example.fooddelivery.data.local.PreferenceManager
import com.example.fooddelivery.data.model.Restaurant
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
    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var locationText: TextView
    private lateinit var preferenceManager: PreferenceManager

    private val currentRestaurants = mutableListOf(
        Restaurant(
            id = 1,
            name = "Burger House",
            cuisine = "برجر - ساندويتشات",
            rating = 4.8,
            deliveryTime = "25 - 35 دقيقة",
            deliveryFee = "10 ريال",
            address = "شارع الملك فهد، الرياض",
            description = "أشهى البرجر الطازج مع بطاطس مقلية وباستا خاصة.",
            latitude = 24.7136,
            longitude = 46.6753
        ),
        Restaurant(
            id = 2,
            name = "Sushi Spot",
            cuisine = "سوشي - أسماك",
            rating = 4.9,
            deliveryTime = "20 - 30 دقيقة",
            deliveryFee = "مجاني فوق 50 ريال",
            address = "حي النخيل، الرياض",
            description = "تجربة سوشي فاخرة مع مكونات طازجة يومياً.",
            latitude = 24.7382,
            longitude = 46.7045
        ),
        Restaurant(
            id = 3,
            name = "Al Shawarma",
            cuisine = "شاورما - عربي",
            rating = 4.7,
            deliveryTime = "15 - 25 دقيقة",
            deliveryFee = "8 ريال",
            address = "شارع التحلية، الرياض",
            description = "شاورما مشوية بطعم أصيل مع صلصات منزلية.",
            latitude = 24.6877,
            longitude = 46.6721
        ),
        Restaurant(
            id = 4,
            name = "Pizza Corner",
            cuisine = "بيتزا - إيطالي",
            rating = 4.6,
            deliveryTime = "30 - 40 دقيقة",
            deliveryFee = "12 ريال",
            address = "حي المرسيدس، الرياض",
            description = "بيتزا خبزها طريقة وقطعها كبيرة وممتازة.",
            latitude = 24.7003,
            longitude = 46.7152
        )
    )

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (granted) {
            getCurrentLocation()
        } else {
            Toast.makeText(this, "تم رفض صلاحية الموقع", Toast.LENGTH_SHORT).show()
            renderRestaurants(currentRestaurants)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferenceManager = PreferenceManager(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationText = findViewById(R.id.locationText)

        val recyclerView: RecyclerView = findViewById(R.id.restaurantsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        restaurantAdapter = RestaurantAdapter(currentRestaurants) { restaurant ->
            val intent = Intent(this, RestaurantDetailActivity::class.java)
            intent.putExtra("restaurant", restaurant)
            startActivity(intent)
        }
        recyclerView.adapter = restaurantAdapter

        val locationButton: Button = findViewById(R.id.locationButton)
        locationButton.setOnClickListener {
            requestLocationPermission()
        }

        val settingsButton: ImageButton = findViewById(R.id.settingsButton)
        settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        renderRestaurants(currentRestaurants)
        updateGovernorateDisplay()
    }

    private fun updateGovernorateDisplay() {
        val governorate = preferenceManager.getSelectedGovernorate()
        if (governorate.isNotEmpty()) {
            locationText.text = "محافظة: $governorate"
        }
    }

    private fun requestLocationPermission() {
        val hasPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            getCurrentLocation()
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun getCurrentLocation() {
        locationText.text = "جاري تحديد موقعك..."

        try {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        val sortedRestaurants = currentRestaurants.sortedBy { restaurant ->
                            calculateDistance(location.latitude, location.longitude, restaurant.latitude, restaurant.longitude)
                        }
                        renderRestaurants(sortedRestaurants)
                        locationText.text = "موقعك: ${location.latitude}, ${location.longitude}"
                    } else {
                        Toast.makeText(this, "تعذر الحصول على الموقع الحالي", Toast.LENGTH_SHORT).show()
                        renderRestaurants(currentRestaurants)
                    }
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "فشل في قراءة الموقع", Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderRestaurants(restaurants: List<Restaurant>) {
        restaurantAdapter.updateData(restaurants)
    }

    private fun calculateDistance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        val radius = 6371.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return radius * c
    }
}
