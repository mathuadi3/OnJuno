package com.adhish.onjuno.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adhish.onjuno.databinding.ItemPriceBinding
import com.adhish.onjuno.model.CryptoPrice
import com.adhish.onjuno.util.getSVGFromUrl


class CryptoPricesAdapter :
    ListAdapter<CryptoPrice, CryptoPricesAdapter.HoldingViewHolder>(DiffCallBack()) {
    //To update list changes
    class DiffCallBack : DiffUtil.ItemCallback<CryptoPrice>() {
        override fun areItemsTheSame(
            oldItem: CryptoPrice,
            newItem: CryptoPrice
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CryptoPrice,
            newItem: CryptoPrice
        ): Boolean {
            return oldItem == newItem
        }
    }

    class HoldingViewHolder(val binding: ItemPriceBinding) :
        RecyclerView.ViewHolder(binding.root) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoldingViewHolder {
        return HoldingViewHolder(
            ItemPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: HoldingViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            tvCoinText.text = item.title
            val price = "$${item.currentPriceInUsd}"
            tvCoinPrice.text = price
            ivIcon.getSVGFromUrl(item.logo)
        }
    }

}