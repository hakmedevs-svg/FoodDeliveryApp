package com.example.fooddelivery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantAdapter(
    private var restaurants: List<Restaurant>,
    private val onRestaurantClick: (Restaurant) -> Unit
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.restaurantTitle)
        private val cuisineText: TextView = itemView.findViewById(R.id.restaurantCuisine)
        private val ratingText: TextView = itemView.findViewById(R.id.restaurantRating)
        private val infoText: TextView = itemView.findViewById(R.id.restaurantInfo)

        fun bind(restaurant: Restaurant, onClick: (Restaurant) -> Unit) {
            titleText.text = restaurant.name
            cuisineText.text = restaurant.cuisine
            ratingText.text = "★ ${restaurant.rating}"
            infoText.text = "${restaurant.deliveryTime} • ${restaurant.deliveryFee}"
            itemView.setOnClickListener { onClick(restaurant) }
        }
    }

    fun updateData(newRestaurants: List<Restaurant>) {
        restaurants = newRestaurants
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurants[position], onRestaurantClick)
    }

    override fun getItemCount(): Int = restaurants.size
}
