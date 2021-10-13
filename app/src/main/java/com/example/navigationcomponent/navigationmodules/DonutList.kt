package com.example.navigationcomponent.navigationmodules

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponent.adapter.DonutListAdapter
import com.example.navigationcomponent.database.SnackDatabase
import com.example.navigationcomponent.databinding.FragmentDonutListBinding
import com.example.navigationcomponent.factory.DonutViewModelFactory
import com.example.navigationcomponent.viewmodel.DonutListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DonutList.newInstance] factory method to
 * create an instance of this fragment.
 */
class DonutList : Fragment() {
    private lateinit var donutListViewModel: DonutListViewModel

    private val adapter = DonutListAdapter(
        onEdit = { donut ->
            findNavController().navigate(
                DonutListDirections.actionDonutListToDonutEntryDialogFragment(donut.id)
            )
        },
        onDelete = { donut ->
            NotificationManagerCompat.from(requireContext()).cancel(donut.id.toInt())
            donutListViewModel.delete(donut)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding =FragmentDonutListBinding.bind(view)
        val donutDao = SnackDatabase.getDatabase(requireContext()).donutDao()
        donutListViewModel = ViewModelProvider(
            this, DonutViewModelFactory(
                donutDao,
                UserPreferencesRepository.getInstance(requireContext())
            )
        ).get(DonutListViewModel::class.java)

        donutListViewModel.donuts.observe(viewLifecycleOwner) { donuts ->
            adapter.submitList(donuts)
        }

        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener { fabView ->
            fabView.findNavController().navigate(
                DonutListDirections.actionDonutListToDonutEntryDialogFragment()
            )
        }

        donutListViewModel.isFirstRun().observe(viewLifecycleOwner) { selection ->
            if (selection == UserPreferencesRepository.Selection.NOT_SELECTED) {
                val navController = findNavController()
                navController.navigate(DonutListDirections.actionDonutListToSelectionFragment())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentDonutListBinding.inflate(inflater, container, false).root
    }
}