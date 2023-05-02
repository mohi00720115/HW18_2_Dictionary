package com.example.hw18_2_dictionary.ui.first_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw18_2_dictionary.databinding.ItemRecyclerFirstBinding
import com.example.hw18_2_dictionary.model.ui.WordData
import com.example.hw18_2_dictionary.ui.LanguageStateEnum

typealias Click = (word: WordData) -> Unit
typealias Share = (word: WordData) -> Unit

class WordListAdapter(
    private val languageState: LiveData<LanguageStateEnum>,
    private val shear: Share,
    private val click: Click
) :
    ListAdapter<WordData, WordListAdapter.ViewHolder>(DiffCallBack()) {

    class DiffCallBack : DiffUtil.ItemCallback<WordData>() {
        override fun areItemsTheSame(oldItem: WordData, newItem: WordData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WordData, newItem: WordData): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(private val binding: ItemRecyclerFirstBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(word: WordData) {
            with(binding) {
                tvPersian.text = word.persianWord
                tvEngland.text = word.englishWord
                tvFrance.text = word.frenchWord
                tvArabic.text = word.arabicWord
                bindLanguage(word)
            }
        }

        init {
            binding.root.setOnClickListener {
                click(getItem(adapterPosition))
            }
            binding.btnShare.setOnClickListener {
                shear(getItem(adapterPosition))
            }
        }

        private fun bindLanguage(word: WordData) {
            with(binding) {
                val wordMap = mapOf(
                    LanguageStateEnum.PERSIAN to word.persianWord,
                    LanguageStateEnum.ENGLISH to word.englishWord,
                    LanguageStateEnum.FRENCH to word.frenchWord,
                    LanguageStateEnum.ARABIC to word.arabicWord
                )
                val selectedWord = wordMap[languageState.value] ?: word.persianWord
                tvMainWord.text = selectedWord
                tvPersian.isVisible = languageState.value != LanguageStateEnum.PERSIAN
                tvEngland.isVisible = languageState.value != LanguageStateEnum.ENGLISH
                tvFrance.isVisible = languageState.value != LanguageStateEnum.FRENCH
                tvArabic.isVisible = languageState.value != LanguageStateEnum.ARABIC
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecyclerFirstBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}