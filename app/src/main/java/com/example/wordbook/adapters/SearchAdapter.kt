package com.example.wordbook.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wordbook.MainActivity.Companion.TAG
import com.example.wordbook.R
import com.example.wordbook.databinding.SearchItemBinding
import com.example.wordbook.model.Word

class SearchAdapter(val initialData: List<String>? = null) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private var data: List<String> = listOf()

    init {
        if(initialData != null){
            data = initialData
        }
    }

    fun updateData(updatedData: List<String>){
        Log.d(TAG,"Updated data submitted")
        data = updatedData
        notifyDataSetChanged()
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.searchText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = SearchItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(item.root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = data[position].toString()
    }
}