package com.example.signupandlogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.signupandlogin.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private  val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    private  val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.notRegisteredButton.setOnClickListener{
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        binding.signInButton.setOnClickListener {
            val email = binding.signInEmail.text.toString()
            val password = binding.signInPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                        if (it.isSuccessful){
                            Toast.makeText(this,"Successfully Logged In",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,Home::class.java))
                        }
                        else
                        Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
                    }

            }else
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()



        }
    }
}