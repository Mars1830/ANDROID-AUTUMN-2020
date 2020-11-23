package com.example.converter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.converter.databinding.FragmentMainBinding
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.properties.Delegates
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
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )
        viewModel = ViewModelProvider(activityCallback).get(MainViewModel::class.java)
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
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.category.adapter = adapter
        }

        binding.category.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedIndex: Int, selectedId: Long
            ) { categorySelected() }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.sourceUnit.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedIndex: Int, selectedId: Long
            ) { sourceUnitSelected() }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.destUnit.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedIndex: Int, selectedId: Long
            ) { destUnitSelected() }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        viewModel.sourceStr.observe(viewLifecycleOwner, { calculateResult() })
    }

    private fun calculateResult() {
        setInputText()
        val result = viewModel.calculateResult().toString()
        setOutputText(result)
    }

    private fun categorySelected() {
        viewModel.category = binding.category.selectedItem.toString()
        updateUnits()
        calculateResult()
    }

    private fun sourceUnitSelected() {
        viewModel.sourceUnit = binding.sourceUnit.selectedItem.toString()
        calculateResult()
    }

    private fun destUnitSelected() {
        viewModel.destUnit = binding.destUnit.selectedItem.toString()
        calculateResult()
    }

    private fun updateUnits() {
        val units = when (viewModel.category) {
            "Distance" -> R.array.distanceUnits
            "Temperature" -> R.array.tempUnits
            "Weight" -> R.array.weightUnits
            else -> throw Exception("Resource not found")
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            units,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.sourceUnit.adapter = adapter
            binding.destUnit.adapter = adapter
        }
    }

    private fun setInputText() {
        binding.textInput.text = viewModel.sourceStr.value
    }

    private fun setOutputText(str: String) {
        binding.textOutput.text = str
    }
}