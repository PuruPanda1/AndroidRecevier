package com.system.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.system.R
import com.system.data.model.Order
import com.system.databinding.CartItemRowBinding
import com.system.utilities.TimeAgo

class CartAdapter : ListAdapter<Order, CartAdapter.CartViewHolder>(COMPARATOR) {
    class CartViewHolder(val binding: CartItemRowBinding) : ViewHolder(binding.root) {
        fun bind(order: Order) {
            val timestamp = order._id.timestamp.toLong()
            binding.descriptionShowRC.text = order.name
            binding.amountShowRC.text = binding.descriptionShowRC.context.resources.getString(
                R.string.amount,
                order.price.toString()
            )
            binding.idNumber.text = TimeAgo.getTimeAgo(timestamp)

            Log.d("CHECKINGDEV", "bind: ${order._id.timestamp}")
            binding.quantityRC.text = binding.descriptionShowRC.context.resources.getString(
                R.string.quantity,
                order.qunatity.toString()
            )
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            CartItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}