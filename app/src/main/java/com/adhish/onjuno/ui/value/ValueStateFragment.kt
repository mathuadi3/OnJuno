package com.adhish.onjuno.ui.value

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adhish.onjuno.databinding.FragmentValueStateBinding
import com.adhish.onjuno.model.ResponseModel
import com.adhish.onjuno.ui.adapter.CryptoPricesAdapter
import com.adhish.onjuno.ui.HomeViewModel
import com.adhish.onjuno.ui.adapter.RecentTransactionAdapter
import com.adhish.onjuno.ui.adapter.YourHoldingsAdapter
import com.adhish.onjuno.util.FromScreen
import com.adhish.onjuno.util.hide
import com.adhish.onjuno.util.show


class ValueStateFragment : Fragment() {

    private var _binding: FragmentValueStateBinding? = null
    private val binding get() = _binding
    private val viewModel by activityViewModels<HomeViewModel>()
    private lateinit var holdingsAdapter: YourHoldingsAdapter
    private lateinit var pricesAdapter: CryptoPricesAdapter
    private lateinit var transactionAdapter: RecentTransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentValueStateBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getValueStateData()
        setUpViews()
        with(viewModel) {
            valueStateData.observe(viewLifecycleOwner) {
                setupData(it)
            }

            isLoading.observe(viewLifecycleOwner) {
                binding?.progressBar?.apply { if (it) show() else hide() }
            }
        }
    }

    private fun setUpViews() {
        binding?.apply {
            holdingsAdapter = YourHoldingsAdapter(FromScreen.VALUE) {
                //Add click implementation
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

    private fun setupData(response: ResponseModel) {
        binding?.apply {
            layoutCryptoBalance.apply {
                tvCoinText.text = response.cryptoBalance.title
                tvSubtitle.text = response.cryptoBalance.subtitle
            }
            holdingsAdapter.submitList(response.yourCryptoHoldings)
            pricesAdapter.submitList(response.cryptoPrices)
            transactionAdapter.submitList(response.allTransactions)
        }
    }
}