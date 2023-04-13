package com.system.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.system.R
import com.system.data.model.Order
import com.system.databinding.FragmentHomeBinding
import com.system.ui.RealmViewModel

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RealmViewModel>()

    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        binding.insertBtn.setOnClickListener { 
            if(binding.orderName.text!!.isEmpty() || binding.orderPrice.text!!.isEmpty() || binding.orderQuantity.text!!.isEmpty()){
                Toast.makeText(requireContext(), "Fields cant be empty", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.insertOrder(order = Order().apply {
                    name = binding.orderName.text.toString()
                    price = binding.orderPrice.text.toString().toInt()
                    qunatity = binding.orderQuantity.text.toString().toInt()
                })
                Toast.makeText(requireContext(), "Element inserted!", Toast.LENGTH_SHORT).show()
            }
        }
        
        return binding.root
        
    }

}