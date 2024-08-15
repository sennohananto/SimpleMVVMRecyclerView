package com.latihan.simplemvvmrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(var users: ArrayList<User>, private val onUpdateClick: (User) -> Unit,
                  private val onDeleteClick: (User) -> Unit) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position], onUpdateClick, onDeleteClick)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun updateUsers(newUsers: ArrayList<User>) {
        users = newUsers
        notifyDataSetChanged()
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        val btnUpdate: Button = itemView.findViewById(R.id.btnUpdate)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

        fun bind(user: User, onUpdateClick: (User) -> Unit, onDeleteClick: (User) -> Unit) {
            tvName.text = user.name
            tvEmail.text = user.email

            btnUpdate.setOnClickListener { onUpdateClick(user) }
            btnDelete.setOnClickListener { onDeleteClick(user) }
        }
    }
}