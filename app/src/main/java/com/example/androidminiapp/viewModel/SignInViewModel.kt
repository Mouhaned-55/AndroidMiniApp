package com.example.androidminiapp.viewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidminiapp.db.room.RegisterRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignInViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {

    val users = repository.users


    @Bindable
    val userEmail = MutableLiveData("")

    @Bindable
    val userPassword = MutableLiveData("")

    var goToHomePage = MutableLiveData<Boolean>(false)

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun signIn(context: Context) {
        if (userEmail.value!!.isNotEmpty() && userPassword.value!!.isNotEmpty()) {
            uiScope.launch {
                val userEmails = repository.getEmail(userEmail.value!!)
                if (userEmails != null) {
                    if (userEmails.password == userPassword.value) {
                        userEmail.value = null
                        userPassword.value = null
                        goToHomePage.value = true

                    }

                } else {
                    Toast.makeText(context, "Password is incorrect", Toast.LENGTH_SHORT).show()

                }
            }
        } else {
            Toast.makeText(context, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}