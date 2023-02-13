package com.example.classwork5

import android.app.Application
import com.example.classwork5.repository.Repository

class App: Application() {

    val repository: Repository by lazy {
        Repository()
    }
}