package com.example.fooddelivery.ui.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.R
import com.example.fooddelivery.data.Restaurant

class RestaurantAdapter(
    private var items: List<Restaurant>,
    private val onItemClick: (Restaurant) -> Unit
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.restaurantName)
        private val cuisine: TextView = itemView.findViewById(R.id.restaurantCuisine)
        private val info: TextView = itemView.findViewById(R.id.restaurantInfo)
        private val orderButton: Button = itemView.findViewById(R.id.orderButton)

        fun bind(restaurant: Restaurant, onClick: (Restaurant) -> Unit) {
            name.text = restaurant.name
            cuisine.text = restaurant.cuisine
            info.text = "${restaurant.deliveryTime} • ${restaurant.deliveryFee} • ${restaurant.rating}★"
            itemView.setOnClickListener { onClick(restaurant) }
            orderButton.setOnClickListener { onClick(restaurant) }
        }
    }

    fun updateData(data: List<Restaurant>) {
        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount(): Int = items.size
}
