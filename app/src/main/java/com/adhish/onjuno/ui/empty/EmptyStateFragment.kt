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
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.adhish.onjuno.databinding.FragmentEmptyStateBinding
import com.adhish.onjuno.model.ResponseModel
import com.adhish.onjuno.ui.HomeViewModel
import com.adhish.onjuno.ui.YourHoldingsAdapter
import com.adhish.onjuno.util.FromScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmptyStateFragment : Fragment() {

    private var _binding: FragmentEmptyStateBinding? = null
    private val binding get() = _binding
    private val viewModel by activityViewModels<HomeViewModel>()
    private lateinit var holdingsAdapter: YourHoldingsAdapter

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
        with(viewModel){
            emptyStateData.observe(viewLifecycleOwner){
                setupData(it)
            }

        }
    }

    private fun setUpViews() {
        binding?.apply {
            holdingsAdapter = YourHoldingsAdapter(FromScreen.EMPTY){
                //Add click implementation
            }
            layoutCryptoHolding.rvHolding.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = holdingsAdapter
                addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
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
        }
    }
}