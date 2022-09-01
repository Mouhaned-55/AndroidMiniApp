package com.example.androidminiapp.viewModelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidminiapp.db.room.RegisterRepository
import com.example.androidminiapp.viewModel.SignUpViewModel

class SignUpViewModelFactory(
    private val repository: RegisterRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(repository,application) as T
        }
        throw IllegalAccessException("Unknown View Model Class")
    }
}