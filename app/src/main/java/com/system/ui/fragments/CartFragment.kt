package com.system.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.system.databinding.FragmentCartBinding
import com.system.ui.RealmViewModel
import com.system.ui.adapters.CartAdapter


class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RealmViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCartBinding.inflate(layoutInflater, container, false)

        initUI()


//        binding.submitBtn.setOnClickListener {
//            if (binding.orderName.text.isNotBlank()) {
//                viewModel.insertOrder(order = Order().apply {
//                    name = binding.orderName.text.toString()
//                })
//            }
//        }
        return binding.root
    }

    private fun initUI() {
        val cartAdapter = CartAdapter()
        binding.cartRC.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRC.adapter = cartAdapter

        viewModel.data.observe(viewLifecycleOwner) {
            cartAdapter.submitList(it)
        }

    }

}