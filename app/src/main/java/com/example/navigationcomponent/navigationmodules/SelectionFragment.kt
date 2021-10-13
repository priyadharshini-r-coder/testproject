package com.example.navigationcomponent.navigationmodules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.navigationcomponent.databinding.FragmentSelectionBinding

class SelectionFragment:Fragment() {
   /* override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentSelectionBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentSelectionBinding.bind(view)

        val selectionViewModel: SelectionViewModel by viewModels {
            SelectionViewModelFactory(UserPreferencesRepository.getInstance(requireContext()))
        }

        selectionViewModel.checkCoffeeTrackerEnabled().observe(viewLifecycleOwner) { selection ->
            if (selection == UserPreferencesRepository.Selection.DONUT_AND_COFFEE) {
                binding.checkBox.isChecked = true
            }
        }

        binding.button.setOnClickListener { button ->
            val coffeeSelected = binding.checkBox.isChecked
           // selectionViewModel.saveCoffeeTrackerSelection(coffeeSelected)

            button.findNavController().navigate(
                SelectionFragmentDirections.actionSelectionFragmentToDonutList()
            )
        }
    }*/
}