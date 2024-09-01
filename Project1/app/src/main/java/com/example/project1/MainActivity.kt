package com.example.project1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project1.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        val database = Firebase.database
//        val myRef = database.getReference("message")
//        val nameRef = database.getReference("name")

        val intent = Intent(this,SignIn::class.java)
        binding.alreadyRegistered.setOnClickListener{
            startActivity(intent)
        }
        firebaseAuth = FirebaseAuth.getInstance()
        val name = binding.personName.text.toString()
        binding.signUpButton.setOnClickListener{
            val email = binding.personEmail.text.toString()
            val password1 = binding.personPassword1.text.toString()
            val password2 = binding.personPassword2.text.toString()

            if (email.isNotEmpty() && password1.isNotEmpty() && password2.isNotEmpty()){
                if (password1 == password2){
                    firebaseAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener {
                            if (it.isSuccessful){
                                Toast.makeText(this,"Successfully Registered",Toast.LENGTH_SHORT).show()
                                startActivity(intent)
                            }
                        }
                }else Toast.makeText(this,"Passwords do not match",Toast.LENGTH_SHORT).show()

            }else Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()

        }







    }
}