package com.ahmet.mybank.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmet.mybank.R
import com.ahmet.mybank.adapter.HomeAdaptor
import com.ahmet.mybank.databinding.FragmentHomeBinding
import com.ahmet.mybank.models.Bank
import com.ahmet.mybank.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adaptorHome: HomeAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.dataError
        return view
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.editText.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (p0 != null) {
                    searchdata(p0.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {


            }
        })



        viewModel.getData()

        initRecycler()

        swipeRefresh()

        observeData()

    }

    private fun searchdata(deger:CharSequence) {
        val searchQuery = "%$deger%"
        viewModel.getSearchData(searchQuery)
        adaptorHome.submit=viewModel.getSearchData(binding.editText.text.toString())
    }


    private fun observeData() {

        viewModel.banks.observe(viewLifecycleOwner, Observer {
            binding.recyclerviewHome.visibility = View.VISIBLE
            adaptorHome.submit = it
        })

        viewModel.dataLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading.let {
                if (it) {
                    binding.dataLoading.visibility = View.VISIBLE
                } else {
                    binding.dataLoading.visibility = View.GONE
                }
            }
        })

        viewModel.dataError.observe(viewLifecycleOwner, Observer { error ->
            error.let {
                if (it) {
                    binding.dataError.visibility = View.VISIBLE
                } else {
                    binding.dataError.visibility = View.GONE
                }
            }
        })
    }

    private fun swipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {

            binding.apply {
                recyclerviewHome.visibility = View.GONE
                dataError.visibility = View.GONE
                dataLoading.visibility = View.VISIBLE
                swipeRefreshLayout.isRefreshing = false
            }
            viewModel.getData()
        }

    }

    private fun initRecycler() {

        binding.recyclerviewHome.layoutManager = LinearLayoutManager(requireContext())
        adaptorHome = HomeAdaptor()
        binding.recyclerviewHome.adapter = adaptorHome
    }


}