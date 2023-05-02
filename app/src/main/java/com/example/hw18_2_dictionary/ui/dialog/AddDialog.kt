package com.example.hw18_2_dictionary.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.hw18_2_dictionary.databinding.DailogAddBinding
import com.example.hw18_2_dictionary.others.checkWordsValid
import com.example.hw18_2_dictionary.ui.first_fragment.WordViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddDialog : BottomSheetDialogFragment() {
    private lateinit var binding: DailogAddBinding
    private val viewModel: WordViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DailogAddBinding.inflate(inflater)
        binding.apply {

            buttonCrate.setOnClickListener {
                if (checkWordsValid(
                        tvPersian.text.toString(),
                        tvEngland.text.toString(),
                        tvFrance.text.toString(),
                        tvArabic.text.toString()
                    )
                ) {
                    viewModel.getInsert(
                        tvPersian.text.toString(),
                        tvEngland.text.toString(),
                        tvFrance.text.toString(),
                        tvArabic.text.toString()
                    )
//                    viewModel.checkCount()
                    dismiss()
                } else {
                    Toast.makeText(requireContext(), "Please enter valid data", Toast.LENGTH_SHORT)
                        .show()
                }

            }
            buttonCancel.setOnClickListener {
                dismiss()
            }
        }
        return binding.root
    }
}