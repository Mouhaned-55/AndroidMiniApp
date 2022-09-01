package com.codingstuff.loginandsignup

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidminiapp.databinding.ActivitySignUpBinding
import com.example.androidminiapp.db.room.RegisterDatabase
import com.example.androidminiapp.db.room.RegisterRepository
import com.example.androidminiapp.ui.activities.authentication.SignInActivity
import com.example.androidminiapp.viewModel.SignUpViewModel
import com.example.androidminiapp.viewModelFactory.SignUpViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity(), Observable {

    private lateinit var  signUpViewModel : SignUpViewModel
    private lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = requireNotNull(this.application)

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = SignUpViewModelFactory(repository,application)

        signUpViewModel = ViewModelProvider(this,factory).get(SignUpViewModel::class.java)


        binding.lifecycleOwner = this
        binding.signUpViewModel = signUpViewModel


        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        //observe variable
        signUpViewModel.goToSignIn.observe(this, Observer {
            if (it) {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
        })



        binding.button.setOnClickListener {
            signUpViewModel.checkInputs(this)

        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}