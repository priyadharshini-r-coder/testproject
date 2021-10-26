package com.example.navigationcomponent.realm.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.navGraphViewModels
import com.example.navigationcomponent.R
import com.example.navigationcomponent.databinding.FragmentHomeFrgamentBinding
import com.example.navigationcomponent.realm.viewmodels.HomeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFrgament.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFrgament : Fragment() {

    private val homeViewModel: HomeViewModel by navGraphViewModels(R.id.mobile_navigation)

    private var _binding:FragmentHomeFrgamentBinding ?=null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeFrgamentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })

        homeViewModel.visitInfo.observe(viewLifecycleOwner, {
            binding.textHomeSubtitle.text = "You have visited this page $it times"
        })

        binding.btRefreshCount.setOnClickListener {
            homeViewModel.onRefreshCount()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}