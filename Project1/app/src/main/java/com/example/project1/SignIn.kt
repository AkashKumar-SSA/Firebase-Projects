package com.example.project1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project1.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firebaseAuth = FirebaseAuth.getInstance()
        binding.notRegistered.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.signInButton.setOnClickListener{
            val email = binding.personSignInEmail.text.toString()
            val password = binding.personSignInPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            Toast.makeText(this, "Sign In Successful", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, Home::class.java)
                            startActivity(intent)
                        }else
                            Toast.makeText(this,"Failed to Sign in! Try Again", Toast.LENGTH_LONG).show()
                    }
            }else
                Toast.makeText(this,"Fields cannot be empty", Toast.LENGTH_SHORT).show()

        }
    }
}