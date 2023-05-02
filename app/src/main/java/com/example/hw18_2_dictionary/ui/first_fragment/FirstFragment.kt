package com.example.hw18_2_dictionary.ui.first_fragment

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.hw18_2_dictionary.R
import com.example.hw18_2_dictionary.databinding.FragmentFirstBinding
import com.example.hw18_2_dictionary.model.ui.WordData
import com.example.hw18_2_dictionary.ui.LanguageStateEnum
import com.example.hw18_2_dictionary.ui.LanguageSystem
import com.example.hw18_2_dictionary.ui.dialog.AddDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {
    private lateinit var binding: FragmentFirstBinding
    private val viewModel: WordViewModel by viewModels()
    private lateinit var adapter: WordListAdapter
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!

        adapter = WordListAdapter(viewModel.languageState, { word ->
            shareText(word)
        }) { word ->
            showBottomSheetDialogEdit(word.id)
        }
        binding.recyclerViewFirstFragment.adapter = adapter
        navController = findNavController()
        setAdapter()
        setActionBar()
        binding.floatingActionButtonFirst.setOnClickListener {
            showBottomSheetDialogAdd()
        }

    }

    private fun setAdapter() {
        viewModel.wordList.observe(viewLifecycleOwner) { words ->
            adapter.submitList(words)
//            binding.countText.text = words.size.toString()
        }

        viewModel.languageState.observe(viewLifecycleOwner) {
            adapter.notifyDataSetChanged()
        }
    }

    private fun setActionBar() {
        val menuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                viewModel.countWord.observe(viewLifecycleOwner) { count ->
                    menu.findItem(R.id.itemCount)?.title = count.toString()
                }

                menuInflater.inflate(R.menu.menu, menu)
                val searchItem = menu.findItem(R.id.search_item)
                val searchView = searchItem.actionView as SearchView
                searchView.isSubmitButtonEnabled = true
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        newText?.let { viewModel.searchWord(it) }
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.action_pr_language -> changeLanguage(LanguageStateEnum.PERSIAN)
                    R.id.action_en_language -> changeLanguage(LanguageStateEnum.ENGLISH)
                    R.id.action_fr_language -> changeLanguage(LanguageStateEnum.FRENCH)
                    R.id.action_ar_language -> changeLanguage(LanguageStateEnum.ARABIC)
                    R.id.action_pr_language_app -> setLocale(LanguageSystem.PERSIAN)
                    R.id.action_en_language_app -> setLocale(LanguageSystem.ENGLISH)
                }
                return true
            }
        })
    }


    private fun changeLanguage(languageState: LanguageStateEnum) {
        viewModel.changeLanguage(languageState)
    }

    private fun showBottomSheetDialogAdd() {
        val add = AddDialog()
        add.show(childFragmentManager, "kir")
    }


    private fun showBottomSheetDialogEdit(id: Int) {
        navController.navigate(FirstFragmentDirections.actionMainFragmentToEditBottomSheetDialog(id))
    }

    private fun shareText(word: WordData) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT, """Persian word is: ${word.persianWord}
                    | English word is: ${word.englishWord} 
                    | French word is: ${word.frenchWord} 
                    | Arabic word is: ${word.arabicWord}""".trimMargin()
            )
            type = "text/plain"
        }
        val intent = Intent.createChooser(sendIntent, null)
        startActivity(intent)
    }

    private fun setLocale(languageSystem: LanguageSystem) {
        val language = when (languageSystem) {
            LanguageSystem.PERSIAN -> {
                viewModel.changeLanguage(LanguageStateEnum.PERSIAN)
                "fa"
            }
            LanguageSystem.ENGLISH -> {
                viewModel.changeLanguage(LanguageStateEnum.ENGLISH)
                "en"
            }
        }
        //کد برای تغییر زبان-مناسب اندروید 12 ب بالا
//        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(language)
//        AppCompatDelegate.setApplicationLocales(appLocale)
    }

}