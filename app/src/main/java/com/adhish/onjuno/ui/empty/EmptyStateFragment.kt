package com.adhish.onjuno.ui.empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adhish.onjuno.databinding.FragmentEmptyStateBinding
import com.adhish.onjuno.databinding.FragmentHomeBinding


class EmptyStateFragment : Fragment() {

    private var _binding: FragmentEmptyStateBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmptyStateBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}