package com.adhish.onjuno.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adhish.onjuno.databinding.ItemHoldingsBinding
import com.adhish.onjuno.model.YourCryptoHolding
import com.adhish.onjuno.util.*


class YourHoldingsAdapter(
    private val fromScreen: FromScreen,
    private val onClick: (YourCryptoHolding) -> Unit
) :
    ListAdapter<YourCryptoHolding, YourHoldingsAdapter.HoldingViewHolder>(DiffCallBack()) {
    //To update list changes
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
            if (fromScreen == FromScreen.EMPTY) {
                btnBuy.show()
                btnDeposit.show()
                tvCoinPrice.hide()
                tvSubtitle.hide()
                btnBuy.setSafeOnClickListener { onClick.invoke(item) }
            } else {
                btnBuy.hide()
                btnDeposit.hide()
                tvCoinPrice.show()
                tvSubtitle.show()
                val price = "$${item.currentBalInUsd}"
                tvCoinPrice.text = price
                tvSubtitle.text = item.currentBalInToken
            }

            tvCoinText.text = item.title
            ivIcon.getSVGFromUrl(item.logo)
        }
    }

}