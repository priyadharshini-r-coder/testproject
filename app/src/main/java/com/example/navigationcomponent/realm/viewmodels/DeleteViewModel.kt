package com.example.navigationcomponent.realm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.navigationcomponent.realm.model.VisitInfo

class DeleteViewModel:ViewModel() {
    private val db = Realm.getDefaultInstance()

    private val _visitInfo = MutableLiveData<VisitInfo>()
    val visitInfo: LiveData<Int> = Transformations.map(_visitInfo) {
        it.visitCount
    }

    fun deleteViewCount(count: Int) {
        val visitInfo = db.where(VisitInfo::class.java).findFirst()
        if (visitInfo != null) {
            db.beginTransaction()
            visitInfo.apply
            {

            }
            visitInfo.apply {
                visitCount = if (visitCount.minus(count) >= 0)
                    visitCount.minus(count)
                else
                    0
                _visitInfo.postValue(this)
            }
            db.commitTransaction()
        }
    }
}