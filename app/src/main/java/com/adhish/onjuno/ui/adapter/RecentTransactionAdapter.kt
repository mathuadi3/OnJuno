package com.adhish.onjuno.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adhish.onjuno.databinding.ItemTransactionBinding
import com.adhish.onjuno.model.AllTransaction
import com.adhish.onjuno.util.getSVGFromUrl


class RecentTransactionAdapter() :
    ListAdapter<AllTransaction, RecentTransactionAdapter.HoldingViewHolder>(DiffCallBack()) {
    class DiffCallBack : DiffUtil.ItemCallback<AllTransaction>() {
        override fun areItemsTheSame(
            oldItem: AllTransaction,
            newItem: AllTransaction
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AllTransaction,
            newItem: AllTransaction
        ): Boolean {
            return oldItem == newItem
        }
    }

    class HoldingViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoldingViewHolder {
        return HoldingViewHolder(
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: HoldingViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            tvCoinText.text = item.title
            val price = "$${item.txnAmount}"
            tvCoinPrice.text = price
            tvSubtitle.text = item.txnTime
            tvReturnText.text = item.txnSubAmount
            ivIcon.getSVGFromUrl(item.txnLogo)
        }
    }

}