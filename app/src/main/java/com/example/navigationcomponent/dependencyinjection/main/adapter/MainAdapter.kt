package com.example.navigationcomponent.dependencyinjection.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.navigationcomponent.R
import com.example.navigationcomponent.dependencyinjection.data.model.User


class MainAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)  {
        var textView: TextView?=null
        var textViewEmail:TextView?=null
        var imageView:ImageView?=null
        fun bind(user: User) {
           textView=itemView.findViewById(R.id.textViewUserName)
            textView?.text = user.name
            textViewEmail=itemView.findViewById(R.id.textViewUserEmail)
            textViewEmail?.text=user.email
            imageView=itemView.findViewById(R.id.imageViewAvatar)

           /* Glide.with(imageView)
                .load(user.avatar)
                .into(imageView)*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )


    override fun onBindViewHolder(holder: MainAdapter.DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addData(list: List<User>) {
        users.addAll(list)
    }

    override fun getItemCount(): Int {
        val list:List<User>
        list=ArrayList<User>()
        return list.size
    }


}