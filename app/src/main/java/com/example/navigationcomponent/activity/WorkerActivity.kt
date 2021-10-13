package com.example.navigationcomponent.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*

import com.example.navigationcomponent.R
import com.example.navigationcomponent.workmanger.CompressingWorker
import com.example.navigationcomponent.workmanger.DownloadingWorker
import com.example.navigationcomponent.workmanger.FilteringWorker
import com.example.navigationcomponent.workmanger.UploadWorker
import java.util.concurrent.TimeUnit

class WorkerActivity : AppCompatActivity() {
    var textView:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker)
        textView=findViewById(R.id.textView)
    }

    companion object{
        const val KEY_COUNT_VALUE = "key_count"
    }



    private fun setOneTimeWorkRequest() {

        val workManager = WorkManager.getInstance(applicationContext)

        val data: Data = Data.Builder()
            .putInt(KEY_COUNT_VALUE,125)
            .build()
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        val filteringRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java)
            .build()
        val compressingRequest= OneTimeWorkRequest.Builder(CompressingWorker::class.java)
            .build()
        val downloadingWorker= OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
            .build()
        val paralleWorks = mutableListOf<OneTimeWorkRequest>()
        paralleWorks.add(downloadingWorker)
        paralleWorks.add(filteringRequest)
        workManager
            .beginWith(paralleWorks)
            .then(compressingRequest)
            .then(uploadRequest)
            .enqueue()
         workManager.getWorkInfoByIdLiveData(uploadRequest.id).observe(this, Observer {
             textView?.text  = it.state.name
             if(it.state.isFinished){
                 val data = it.outputData
                 val message = data.getString(UploadWorker.KEY_WORKER)
                 Toast.makeText(applicationContext,message, Toast.LENGTH_LONG).show()
             }
         })



    }

    private fun setPeriodicWorkRequest(){
        val periodicWorkRequest = PeriodicWorkRequest
            .Builder(DownloadingWorker::class.java,16, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
    }
}