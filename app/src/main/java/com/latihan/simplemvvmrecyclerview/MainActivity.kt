package com.latihan.simplemvvmrecyclerview

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.latihan.simplemvvmrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: UserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter(ArrayList<User>(),{ user ->
            // Handle update click
            val intent = Intent(this, UpdateUserActivity::class.java).apply {
                putExtra("USER_ID", user.id)
                putExtra("USER_NAME", user.name)
                putExtra("USER_EMAIL", user.email)
            }
            startActivity(intent)
        },{ user ->
            showDeleteConfirmationDialog(user)
        })
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = userAdapter

        viewModel.users.observe(this, Observer { users ->
            userAdapter.users.clear()
            userAdapter.users.addAll(users)
            userAdapter.notifyDataSetChanged()
        })

        viewModel.operationResult.observe(this, Observer { result ->
            // Display the result (Toast, Snackbar, etc.)
            Snackbar.make(this,binding.root,"Result : $result",Snackbar.LENGTH_LONG).show()
        })

        viewModel.fetchUsers()

        binding.btnAddUser.setOnClickListener {
            // Open AddUserActivity
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }


    }

    private fun showDeleteConfirmationDialog(user: User) {
        AlertDialog.Builder(this).apply {
            setTitle("Delete User")
            setMessage("Are you sure you want to delete this user?")
            setPositiveButton("Yes") { _, _ ->
                viewModel.deleteUser(user.id)
            }
            setNegativeButton("No", null)
        }.show()
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchUsers()
    }
}