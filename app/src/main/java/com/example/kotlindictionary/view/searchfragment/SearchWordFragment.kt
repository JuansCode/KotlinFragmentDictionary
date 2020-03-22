package com.example.kotlindictionary.view.searchfragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.kotlindictionary.R
import com.example.kotlindictionary.databinding.FragmentSearchWordBinding
import com.example.kotlindictionary.util.showLoadingDialog
import com.example.kotlindictionary.util.viewModel
import com.example.kotlindictionary.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_search_word.*


/**
 * A simple [Fragment] subclass.
 */
class SearchWordFragment: Fragment() {

    private lateinit var binding : FragmentSearchWordBinding
    private val urbanModel : MainViewModel by viewModel {
        MainViewModel()
    }

    private val dialog: AlertDialog by showLoadingDialog {
        cancelable = false
        setMessage("Loading data...")
        setRetryVisibility()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_word, container, false)
        binding.apply {
            viewModel = urbanModel
            lifecycleOwner = this@SearchWordFragment
        }
        urbanModel.dataLoading.observe(viewLifecycleOwner, Observer {aBoolean->
            if(aBoolean !!){
                dialog.show()
            }else{
                dialog.dismiss()
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        iv_search.setOnClickListener {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().windowToken, 0)
            val term: String = et_search_text.text.toString()
            urbanModel.loadData(term)
            iv_sort.visibility = View.VISIBLE
        }
        iv_sort.setOnClickListener {
            urbanModel.sortList()
        }
    }
}
