package com.example.coffee.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.coffee.R
import com.example.coffee.databinding.FragmentCoffeeEntryDialogBinding
import com.example.core.model.Coffee
import com.example.coffee.viewmodel.CoffeeEntryViewModel
import com.example.coffee.viewmodel.CoffeeViewModelfactory
import com.example.navigationcomponent.database.SnackDatabase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CoffeeEntryDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoffeeEntryDialogFragment : BottomSheetDialogFragment() {

    private enum class EditingState {
        NEW_COFFEE,
        EXISTING_COFFEE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCoffeeEntryDialogBinding.inflate(inflater, container, false).root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val coffeeDao = SnackDatabase.getDatabase(requireContext()).coffeeDao()

        val coffeeEntryViewModel: CoffeeEntryViewModel by viewModels {
            CoffeeViewModelfactory(coffeeDao)
        }

        val binding = FragmentCoffeeEntryDialogBinding.bind(view)

        var coffee: Coffee? = null
        val args: CoffeeEntryDialogFragmentArgs by navArgs()
        val editingState =
            if (args.itemId > 0) {
                EditingState.EXISTING_COFFEE
            } else {
                EditingState.NEW_COFFEE
            }

        // If we arrived here with an itemId of >= 0, then we are editing an existing item
        if (editingState == EditingState.EXISTING_COFFEE) {
            // Request to edit an existing item, whose id was passed in as an argument.
            // Retrieve that item and populate the UI with its details
            coffeeEntryViewModel.get(args.itemId).observe(viewLifecycleOwner) { coffeeItem ->
                binding.apply {
                    name.setText(coffeeItem.name)
                    description.setText(coffeeItem.description)
                    ratingBar.rating = coffeeItem.rating.toFloat()
                }

                coffee = coffeeItem
            }
        }

        // When the user clicks the Done button, use the data here to either update
        // an existing item or create a new one
        binding.doneButton.setOnClickListener {
            // Grab these now since the Fragment may go away before the setupNotification
            // lambda below is called
            val context = requireContext().applicationContext
            val navController = findNavController()

            coffeeEntryViewModel.addData(
                coffee?.id ?: 0,
                binding.name.text.toString(),
                binding.description.text.toString(),
                binding.ratingBar.rating.toInt()
            ) { actualId ->
                val arg = CoffeeEntryDialogFragmentArgs(actualId).toBundle()
                val pendingIntent = navController
                    .createDeepLink()
                    .setDestination(R.id.coffeeEntryDialogFragment)
                    .setArguments(arg)
                    .createPendingIntent()

            }
            dismiss()
        }

        // User clicked the Cancel button; just exit the dialog without saving the data
        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }
}