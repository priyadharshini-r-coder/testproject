package com.example.navigationcomponent.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.navigationcomponent.R
import com.example.navigationcomponent.databinding.ActivityCoroutinesRetrofitBinding
import com.example.navigationcomponent.viewmodel.CoroutinesRetrofitViewModel

import java.util.ArrayList
import java.util.LinkedHashMap

class CoroutinesRetrofitActivity : AppCompatActivity() {
    var  binding:ActivityCoroutinesRetrofitBinding?=null
    var b1:Button?=null
    protected var viewModel:CoroutinesRetrofitViewModel? = null
    private val TAG = CoroutinesRetrofitActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoroutinesRetrofitViewModel::class.java)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_coroutines_retrofit)
        binding?.button?.setOnClickListener {
            setCountryCode()
        }
    }

    private fun setCountryCode() {

        val countryCode = ArrayList<String>()
        val providerMap = LinkedHashMap<String, String?>()
        //        countryCode.add(getString(R.string.select_provider_types));
        viewModel?.getCountry()?.observe(this, {
            val response= it.body()

            Log.d(TAG, "country code data" + response?.getStatus() + response)
            if (response?.getStatus() != null && response.getStatus()!!) {
                if (response.getCountryCodeResponseList() != null && response.getCountryCodeResponseList()!!.isNotEmpty()) {
                   Log.d(TAG,response.toString());
                }
            } else {

            }
        })
    }
}