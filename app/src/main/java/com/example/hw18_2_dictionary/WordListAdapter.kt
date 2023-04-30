package com.example.hw18_2_dictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw18_2_dictionary.data.WordDatabase
import java.util.*

class WordListAdapter(
    val longClickTask: (task: WordDatabase) -> Boolean,
    val clickTask: (task: WordDatabase, position: Int) -> Unit,
) :
    ListAdapter<WordDatabase, WordListAdapter.ViewHolder>(DiffCallBack()) {

    class DiffCallBack : DiffUtil.ItemCallback<WordDatabase>() {
        override fun areItemsTheSame(oldItem: WordDatabase, newItem: WordDatabase): Boolean {
            return oldItem.wordDao() == newItem.wordDao()
        }

        override fun areContentsTheSame(oldItem: WordDatabase, newItem: WordDatabase): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_first, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}