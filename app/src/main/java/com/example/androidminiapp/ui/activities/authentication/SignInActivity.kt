package com.example.androidminiapp.ui.activities.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codingstuff.loginandsignup.SignUpActivity
import com.example.androidminiapp.MainActivity
import com.example.androidminiapp.databinding.ActivitySignInBinding
import com.example.androidminiapp.db.room.RegisterDatabase
import com.example.androidminiapp.db.room.RegisterRepository
import com.example.androidminiapp.viewModel.SignInViewModel
import com.example.androidminiapp.viewModelFactory.SignInViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity(), Observable {
    private lateinit var signInViewModel: SignInViewModel
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = requireNotNull(this.application)

        val dao  = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = SignInViewModelFactory(repository,application)

        signInViewModel = ViewModelProvider(this,factory).get(SignInViewModel::class.java)

        binding.lifecycleOwner = this
        binding.mySignInViewModel = signInViewModel


//        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
//
//        // observe variable
        signInViewModel.goToHomePage.observe(this, Observer {
            if (it) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        })
//
//
        binding.button.setOnClickListener {
            signInViewModel.signIn(this)
        }
    }

//    override fun onStart() {
//        super.onStart()
//
//        if (firebaseAuth.currentUser != null) {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}