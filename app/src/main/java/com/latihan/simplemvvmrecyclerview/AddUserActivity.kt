package com.latihan.simplemvvmrecyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.latihan.simplemvvmrecyclerview.databinding.ActivityAddUserBinding

class AddUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddUserBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSave.setOnClickListener {
            val newUser = User(id = 0, name =  binding.etName.text.toString(), email = binding.etEmail.text.toString())
            viewModel.addUser(newUser)
            finish()
        }
    }
}