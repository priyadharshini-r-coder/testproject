package com.example.navigationcomponent.navigationmodules

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.navigationcomponent.R
import com.example.navigationcomponent.database.SnackDatabase
import com.example.navigationcomponent.databinding.FragmentDonutEntryDialogBinding
import com.example.navigationcomponent.factory.DonutViewModelFactory
import com.example.navigationcomponent.model.Donut
import com.example.navigationcomponent.viewmodel.DonutEntryViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DonutEntryDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DonutEntryDialogFragment : BottomSheetDialogFragment() {
    private enum class EditingState {
        NEW_DONUT,
        EXISTING_DONUT
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentDonutEntryDialogBinding.inflate(inflater, container, false).root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val donutDao = SnackDatabase.getDatabase(requireContext()).donutDao()

        val donutEntryViewModel: DonutEntryViewModel by viewModels {
            DonutViewModelFactory(donutDao, UserPreferencesRepository.getInstance(requireContext()))
        }

        val binding = FragmentDonutEntryDialogBinding.bind(view)

        var donut: Donut? = null
        val args: DonutEntryDialogFragmentArgs by navArgs()
        val editingState =
            if (args.itemId > 0) {
                EditingState.EXISTING_DONUT
            } else {
                EditingState.NEW_DONUT
            }

        // If we arrived here with an itemId of >= 0, then we are editing an existing item
        if (editingState == EditingState.EXISTING_DONUT) {
            // Request to edit an existing item, whose id was passed in as an argument.
            // Retrieve that item and populate the UI with its details
            donutEntryViewModel.get(args.itemId).observe(viewLifecycleOwner) { donutItem ->
                binding.apply {
                    name.setText(donutItem.name)
                    description.setText(donutItem.description)
                    ratingBar.rating = donutItem.rating.toFloat()
                }
                donut = donutItem
            }
        }

        // When the user clicks the Done button, use the data here to either update
        // an existing item or create a new one
        binding.doneButton.setOnClickListener {
            // Grab these now since the Fragment may go away before the setupNotification
            // lambda below is called
            val context = requireContext().applicationContext
            val navController = findNavController()

            donutEntryViewModel.addData(
                donut?.id ?: 0,
                binding.name.text.toString(),
                binding.description.text.toString(),
                binding.ratingBar.rating.toInt()
            ) { actualId ->
                val arg = DonutEntryDialogFragmentArgs(actualId).toBundle()
                val pendingIntent = navController
                    .createDeepLink()
                    .setDestination(R.id.donutEntryDialogFragment)
                    .setArguments(arg)
                    .createPendingIntent()

                Notifier.postNotification(actualId, context, pendingIntent)
            }
            dismiss()
        }

        // User clicked the Cancel button; just exit the dialog without saving the data
        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }

}