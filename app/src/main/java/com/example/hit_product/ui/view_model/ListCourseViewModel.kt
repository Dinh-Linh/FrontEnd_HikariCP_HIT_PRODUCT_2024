package com.example.hit_product.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.CourseRegistration
import com.example.hit_product.data.repository.ListCourseRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient

class ListCourseViewModel : BaseViewModel() {
    private val listCourseRepo = ListCourseRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )

    private val _listCourse = MutableLiveData<List<CourseRegistration>>()
    val listCourse : LiveData<List<CourseRegistration>> get() = _listCourse
    fun getAllCourse(token : String){
        executeTask(
            request = {
                listCourseRepo.getAllCourse(token)
            },
            onSuccess = {
                _listCourse.value = it
                Log.d("All Course: ", it.toString())
            },
            onError = {
                Log.e("Error: ", "${error(it)}")
            }
        )
    }
}