package com.example.converter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.converter.databinding.FragmentMainBinding
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider as ViewModelProvider

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var activityCallback: MainActivity
    private lateinit var binding: FragmentMainBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityCallback = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )

        viewModel = ViewModelProvider(activityCallback).get(MainViewModel::class.java)
        //binding.category.setOnItemSelectedListener { categoryClicked() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        val context: Context = requireContext()
        ArrayAdapter.createFromResource(
            context,
            R.array.categories,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.category.adapter = adapter

        }
        viewModel.str_from.observe(viewLifecycleOwner, Observer{ str -> setInputText(str) })
        Log.i(this.id.toString() ,"---------------------------------fragment")
        //viewModel.str_from1.observe(viewLifecycleOwner, Observer { str -> setInputText(str) })
    }

    fun categoryClicked() {
        //viewModel.categoryClicked(binding.category.get() )
    }

    fun getInputText(): String {
        return binding.textInput.text.toString()
    }

    fun setInputText(str: String) {
        binding.textInput.text = str
        //binding.textInput.text = "aaaaaaaaaaaaaaaaaa"
    }
}