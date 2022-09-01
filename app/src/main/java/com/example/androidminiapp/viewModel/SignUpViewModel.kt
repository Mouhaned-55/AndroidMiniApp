package com.example.androidminiapp.viewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidminiapp.db.room.RegisterRepository
import com.example.androidminiapp.db.room.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class SignUpViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {
//    private lateinit var firebaseAuth: FirebaseAuth


    private var userdata: String? = null

    @Bindable
    val userEmail = MutableLiveData("")

    @Bindable
    val userPassword = MutableLiveData("")

    @Bindable
    val userPasswordConfirm = MutableLiveData("")

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    var goToSignIn = MutableLiveData<Boolean>(false)


    fun checkInputs(context: Context) {
        if (userEmail.value!!.isNotEmpty() && userPassword.value!!.isNotEmpty() && userPasswordConfirm.value!!.isNotEmpty()) {
            uiScope.launch {


                if (userPassword.value!! == userPasswordConfirm.value!!) {
                    val userEmails = repository.getEmail(userEmail.value!!)
                    if (userEmails != null) {
                        Toast.makeText(context, "Invalid Account", Toast.LENGTH_SHORT).show()
                    } else {
                        val email = userEmail.value!!
                        val password = userPassword.value!!
                        val passwordConfirm = userPasswordConfirm.value!!
                        insert(User(0, email, password, passwordConfirm))
                        userEmail.value = null
                        userPassword.value = null
                        userPasswordConfirm.value = null
                        goToSignIn.value = true
                    }
                } else {
                    Toast.makeText(context, "Passwords are not matching", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insert(user: User): Job = viewModelScope.launch {
        repository.insert(user)
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}