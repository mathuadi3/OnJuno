package com.adhish.onjuno.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adhish.onjuno.databinding.ItemHoldingsBinding
import com.adhish.onjuno.model.YourCryptoHolding
import com.adhish.onjuno.util.FromScreen
import com.bumptech.glide.Glide


class YourHoldingsAdapter(
    val fromScreen: FromScreen,
    val onClick: (String) -> Unit
) :
    ListAdapter<YourCryptoHolding, YourHoldingsAdapter.HoldingViewHolder>(DiffCallBack()) {
    class DiffCallBack : DiffUtil.ItemCallback<YourCryptoHolding>() {
        override fun areItemsTheSame(
            oldItem: YourCryptoHolding,
            newItem: YourCryptoHolding
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: YourCryptoHolding,
            newItem: YourCryptoHolding
        ): Boolean {
            return oldItem == newItem
        }
    }

    class HoldingViewHolder(val binding: ItemHoldingsBinding) :
        RecyclerView.ViewHolder(binding.root) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoldingViewHolder {
        return HoldingViewHolder(
            ItemHoldingsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: HoldingViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            tvCoinText.text = item.title
            Glide.with(root.context)
                .load(item.logo)
                .centerCrop()
                .into(ivIcon)
        }
    }

}