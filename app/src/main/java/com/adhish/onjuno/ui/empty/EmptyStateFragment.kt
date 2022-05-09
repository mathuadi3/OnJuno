package com.adhish.onjuno.ui.empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adhish.onjuno.databinding.FragmentEmptyStateBinding
import com.adhish.onjuno.model.AllTransaction
import com.adhish.onjuno.model.ResponseModel
import com.adhish.onjuno.ui.adapter.CryptoPricesAdapter
import com.adhish.onjuno.ui.HomeViewModel
import com.adhish.onjuno.ui.adapter.RecentTransactionAdapter
import com.adhish.onjuno.ui.adapter.YourHoldingsAdapter
import com.adhish.onjuno.util.FromScreen
import com.adhish.onjuno.util.hide
import com.adhish.onjuno.util.show
import com.adhish.onjuno.util.toTransaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmptyStateFragment : Fragment() {

    private var _binding: FragmentEmptyStateBinding? = null
    private val binding get() = _binding
    private val viewModel by activityViewModels<HomeViewModel>()
    private lateinit var holdingsAdapter: YourHoldingsAdapter
    private lateinit var pricesAdapter: CryptoPricesAdapter
    private lateinit var transactionAdapter: RecentTransactionAdapter
    private val transactions = arrayListOf<AllTransaction>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmptyStateBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getEmptyStateData()
        setUpViews()
        with(viewModel) {
            emptyStateData.observe(viewLifecycleOwner) {
                setupData(it)
            }

            isLoading.observe(viewLifecycleOwner) {
                binding?.progressBar?.apply { if (it) show() else hide() }
            }
        }
    }

    private fun setUpViews() {
        binding?.apply {
            holdingsAdapter = YourHoldingsAdapter(FromScreen.EMPTY) { holding ->
                //Add click implementation
                viewModel.addTransaction(holding)
                updateTransactionList()

            }
            layoutCryptoHolding.rvHolding.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = holdingsAdapter
                addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            }

            pricesAdapter = CryptoPricesAdapter()

            layoutPrices.rvPrices.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                adapter = pricesAdapter
            }

            transactionAdapter = RecentTransactionAdapter()
            layoutTransaction.rvTransactions.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = transactionAdapter
            }
        }
    }

    private fun updateTransactionList() {
        val transactionList = arrayListOf<AllTransaction>()
        transactionList.addAll(viewModel.holdingsList.map { it.toTransaction() })
        transactionList.addAll(transactions)
        transactionAdapter.submitList(transactionList)
    }

    private fun setupData(response: ResponseModel) {
        binding?.apply {
            layoutCryptoBalance.apply {
                tvCoinText.text = response.cryptoBalance.title
                tvSubtitle.text = response.cryptoBalance.subtitle
            }
            holdingsAdapter.submitList(response.yourCryptoHoldings)
            pricesAdapter.submitList(response.cryptoPrices)
            transactions.addAll(response.allTransactions)
            transactionAdapter.submitList(transactions)
        }
    }
}