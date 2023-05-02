package com.example.hw18_2_dictionary.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.hw18_2_dictionary.databinding.DialogEditBinding
import com.example.hw18_2_dictionary.model.ui.WordData
import com.example.hw18_2_dictionary.ui.first_fragment.WordViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditBottomSheetDialog : BottomSheetDialogFragment() {
    private lateinit var binding: DialogEditBinding
    private val viewModel: WordViewModel by viewModels()
    private val args: EditBottomSheetDialogArgs by navArgs()
    private lateinit var word: WordData
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogEditBinding.inflate(inflater)
        getWordAndSet(args.wordId)
        viewModel.word.observe(viewLifecycleOwner) { word ->
            setWordOnView(word)
        }
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        binding.buttonEdit.setOnClickListener {
            editWord()
            viewModel.update(word)
            dismiss()
        }
        binding.buttonDelete.setOnClickListener {
            viewModel.delete(word)
//            viewModel.checkCount()
            dismiss()
        }
        return binding.root
    }

    private fun getWordAndSet(id: Int) {
        viewModel.getWord(id)
    }

    private fun setWordOnView(word: WordData) {
        this.word = word
        binding.apply {
            tvPersian.setText(word.persianWord)
            tvEngland.setText(word.englishWord)
            tvFrance.setText(word.frenchWord)
            tvArabic.setText(word.arabicWord)
        }
    }

    private fun editWord() {
        binding.apply {
            word.persianWord = tvPersian.text.toString()
            word.englishWord = tvEngland.text.toString()
            word.frenchWord = tvFrance.text.toString()
            word.arabicWord = tvArabic.text.toString()
        }
    }
}