package com.ahmet.mybank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.mybank.databinding.RowHomeBinding
import com.ahmet.mybank.models.Bank
import com.ahmet.mybank.ui.HomeFragmentDirections

class HomeAdaptor () : RecyclerView.Adapter<HomeAdaptor.ItemHolder>() {

    class ItemHolder(val binding:RowHomeBinding):RecyclerView.ViewHolder(binding.root)

    private val diffUtil= object: DiffUtil.ItemCallback<Bank>(){
        override fun areItemsTheSame(oldItem: Bank, newItem: Bank): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Bank, newItem: Bank): Boolean {
            return oldItem == newItem
        }
    }
    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)
    var submit:List<Bank>
    get() = recyclerListDiffer.currentList
    set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding=RowHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        val list=submit[position]

        holder.binding.apply {
            if (list.dc_BANKA_SUBE == "") {
                rowBankaSube.text="---- " + list.dc_SEHIR
            } else {
                rowBankaSube.text=list.dc_BANKA_SUBE
            }

        }
        holder.itemView.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToDetailsFragment(list)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return submit.size
    }

}