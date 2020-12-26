package com.example.napoleonit.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.napoleonit.R
import com.example.napoleonit.data.entity.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cart_item.*

class CartAdapter(
    private val onDeleteClick: (Product) -> Unit
) : ListAdapter<Product, CartAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        )
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        holder.tvCartItemTitle.text = product.name
        holder.tvCartItemPrice.text = product.price.toString()
        holder.tvCartItemDiscount.text = product.discount.toString()
        holder.tvCartItemDiscountPrice.text = product.calcDiscountPrice().toString()
        holder.deleteIv.setOnClickListener { onDeleteClick(product) }
    }
}

