package com.latihan.simplemvvmrecyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.latihan.simplemvvmrecyclerview.databinding.ActivityUpdateUserBinding

class UpdateUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateUserBinding
    private val viewModel: UserViewModel by viewModels()
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userId = intent.getIntExtra("USER_ID", -1)
        binding.etName.setText(intent.getStringExtra("USER_NAME"))
        binding.etEmail.setText(intent.getStringExtra("USER_EMAIL"))

        binding.btnSave.setOnClickListener {
            val updatedUser = User(id = userId, name = binding.etName.text.toString(), email = binding.etEmail.text.toString())
            viewModel.updateUser(userId, updatedUser)
            finish()
        }
    }
}