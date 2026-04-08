package com.example.studentproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentproject.model.Student

class DetailViewModel: ViewModel() {
    val studentLD = MutableLiveData<Student>()
    val errorLD = MutableLiveData<Boolean>()
    val TAG: String= "Vollet Tag"
    var queue : RequestQueue?= null

    fun fetch(id: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMW"
        errorLD.value = false

        val stringRequest = StringRequest(Request.Method.GET, url,
            {
                val sType = object: TypeToken<List<Student>>(){}.type
                val result = Gson().fromJson<List<Student>>(it, sType) a ArrayList
                val student = result.find{it.id == id} as Student
                studentLD.value - student
            },
            {
                Log.d("volley_status", it.message.toString())
                errorLD.value = true
            })
        //        val student1 = Student(
//            "16055", "Nonie", "1998/03/28", "5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff"
//        )
        studentLD.value = student


    }
    }