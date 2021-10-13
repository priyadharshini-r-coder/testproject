package com.example.coffee.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.coffee.adapter.CoffeeListAdapter
import com.example.coffee.databinding.FragmentCoffeeListBinding
import com.example.coffee.viewmodel.CoffeeListViewModel
import com.example.coffee.viewmodel.CoffeeViewModelfactory
import com.example.navigationcomponent.database.SnackDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CoffeeList.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoffeeList : Fragment() {


        private lateinit var coffeeListViewModel: CoffeeListViewModel

        private val adapter = CoffeeListAdapter(
            onEdit = { coffee ->
                findNavController().navigate(
                    CoffeeListDirections.actionCoffeeListToCoffeeEntryDialogFragment(coffee.id)
                )
            },
            onDelete = { coffee ->
                NotificationManagerCompat.from(requireContext()).cancel(coffee.id.toInt())
                coffeeListViewModel.delete(coffee)
            }
        )

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return FragmentCoffeeListBinding.inflate(inflater, container, false).root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            val binding = FragmentCoffeeListBinding.bind(view)
            val coffeeDao = SnackDatabase.getDatabase(requireContext()).coffeeDao()

            coffeeListViewModel = ViewModelProvider(this, CoffeeViewModelfactory(coffeeDao))
                .get(CoffeeListViewModel::class.java)

            coffeeListViewModel.coffeeList.observe(viewLifecycleOwner) { coffees ->
                adapter.submitList(coffees)
            }

            binding.recyclerView.adapter = adapter

            binding.fab.setOnClickListener { fabView ->
                fabView.findNavController().navigate(
                    CoffeeListDirections.actionCoffeeListToCoffeeEntryDialogFragment()
                )
            }
        }
}