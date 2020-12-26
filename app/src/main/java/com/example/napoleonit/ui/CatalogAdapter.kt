package com.example.napoleonit.ui

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.napoleonit.R
import com.example.napoleonit.domain.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.catalog_item.*

class CatalogAdapter(
    private val onProductClick: (Product) -> Unit
) : ListAdapter<Product, CatalogAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.catalog_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        holder.containerView.setOnClickListener { onProductClick(product) }
        holder.productTitle.text = product.name
        Glide.with(holder.itemView.context).load(product.img).into(holder.productImg)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer
}

class CharacterItemDecoration(
    private val offset: Int = 0
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams

        outRect.top = offset
        if (layoutParams.spanIndex % 2 == 0) {
            outRect.left = offset
            outRect.right = offset / 2
        } else {
            outRect.left = offset / 2
            outRect.right = offset
        }

        super.getItemOffsets(outRect, view, parent, state)
    }
}