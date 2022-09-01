package com.example.androidminiapp.viewModelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidminiapp.db.room.RegisterRepository
import com.example.androidminiapp.viewModel.SignInViewModel
import javax.security.auth.login.LoginException

class SignInViewModelFactory(
    private val repository: RegisterRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(repository,application) as T
        }
        throw IllegalAccessException("Unknown View Model Class")
    }
}