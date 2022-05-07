package com.adhish.onjuno.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adhish.onjuno.databinding.FragmentEmptyStateBinding
import com.adhish.onjuno.databinding.FragmentHomeBinding
import com.adhish.onjuno.databinding.FragmentValueStateBinding


class ValueStateFragment : Fragment() {

    private var _binding: FragmentValueStateBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentValueStateBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}