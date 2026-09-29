package com.example.fooddelivery.ui.restaurant

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R
import com.example.fooddelivery.data.Restaurant

class RestaurantDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)

        val restaurant = intent.getSerializableExtra("restaurant") as? Restaurant

        if (restaurant == null) {
            Toast.makeText(this, "Restaurant not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        findViewById<TextView>(R.id.restaurantName).text = restaurant.name
        findViewById<TextView>(R.id.restaurantCuisine).text = restaurant.cuisine
        findViewById<TextView>(R.id.restaurantInfo).text = "${restaurant.deliveryTime} • ${restaurant.deliveryFee} • ${restaurant.rating}★"

        findViewById<Button>(R.id.orderButton).setOnClickListener {
            Toast.makeText(this, "Order placed for ${restaurant.name}", Toast.LENGTH_SHORT).show()
        }
    }
}
