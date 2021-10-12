package com.example.navigationcomponent.dependencyinjection.main.view

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationcomponent.R
import com.example.navigationcomponent.databinding.DepencyInjectionBinding
import com.example.navigationcomponent.dependencyinjection.data.model.User
import com.example.navigationcomponent.dependencyinjection.main.adapter.MainAdapter
import com.example.navigationcomponent.dependencyinjection.main.viewmodel.DependencyViewModel
import com.example.navigationcomponent.dependencyinjection.utils.Status

class DependencyInjectionActivity : AppCompatActivity() {
    private val mainViewModel : DependencyViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    private lateinit var binding:DepencyInjectionBinding

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding=DataBindingUtil.setContentView(this,R.layout.activity_dependency_injection)
        setupUI()
        setupObserver()

    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
       binding. recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.users.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                   binding. progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                   binding. recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                   binding. progressBar.visibility = View.VISIBLE
                   binding. recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}