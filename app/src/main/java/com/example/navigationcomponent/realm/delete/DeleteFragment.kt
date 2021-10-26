package com.example.navigationcomponent.realm.delete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.navigationcomponent.R
import com.example.navigationcomponent.databinding.FragmentDeleteBinding
import com.example.navigationcomponent.realm.hideKeyboard
import com.example.navigationcomponent.realm.viewmodels.DeleteViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DeleteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeleteFragment : Fragment() {
    private val deleteViewModel =
        ViewModelProvider.NewInstanceFactory().create(DeleteViewModel::class.java)

    private var _binding: FragmentDeleteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btAddConfirm.setOnClickListener {
            hideKeyboard()
            deleteViewModel.deleteViewCount(binding.etViewCount.text.toString().toInt())
        }

        deleteViewModel.visitInfo.observe(viewLifecycleOwner) {
            binding.tvViewCount.text = resources.getString(R.string.update_view_count, it)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}