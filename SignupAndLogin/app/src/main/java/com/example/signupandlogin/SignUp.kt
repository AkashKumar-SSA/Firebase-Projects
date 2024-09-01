package com.example.signupandlogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.signupandlogin.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firebaseAuth = FirebaseAuth.getInstance()
        binding.notRegisteredButton.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.signUpButton.setOnClickListener{
            val email = binding.signUpEmail.text.toString()
            val password1 = binding.signUpPassword1.text.toString()
            val password2 = binding.signUpPassword2.text.toString()

            if (email.isNotEmpty() && password1.isNotEmpty() && password2.isNotEmpty()) {
                if (password1 == password2){
                    firebaseAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this,"Sign up successful",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            binding.signUpEmail.error = it.exception.toString()
                        }
                    }
                }else
                    Toast.makeText(this,"Passwords do not match",Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"Empty fields are not allowed",Toast.LENGTH_SHORT).show()
        }
    }
}