package com.example.wordbook.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wordbook.MainActivity.Companion.TAG
import com.example.wordbook.R
import com.example.wordbook.databinding.SearchItemBinding
import com.example.wordbook.model.SearchItemModel
import com.example.wordbook.model.Word

class SearchAdapter(val initialData: List<SearchItemModel>? = null) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private var data: List<SearchItemModel> = listOf()

    private val diffUtil = object : DiffUtil.ItemCallback<SearchItemModel>(){
        override fun areItemsTheSame(oldItem: SearchItemModel, newItem: SearchItemModel): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchItemModel,
            newItem: SearchItemModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    init {
        if(initialData != null){
            updateData(initialData)
        }
    }

    fun updateData(updatedData: List<SearchItemModel>){
        Log.d(TAG,"Updated data submitted")
        data = updatedData
        asyncListDiffer.submitList(data)
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.searchText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = SearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(item.root)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = asyncListDiffer.currentList[position].text.toString()
    }
}