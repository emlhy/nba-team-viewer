package com.example.nbateamviewer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateamviewer.R
import com.example.nbateamviewer.data.PlayerEntry

class PlayerAdapter : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    private val playerList = ArrayList<PlayerEntry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayerAdapter.ViewHolder, position: Int) {
        val player = playerList[position]
        val firstName = player.firstName
        val lastName = player.lastName
        val pos = player.position
        val number = player.number

        holder.firstName.text = firstName
        holder.lastName.text = lastName
        holder.position.text = pos
        holder.number.text = number.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var firstName: TextView = itemView.findViewById(R.id.firstName)
        var lastName: TextView = itemView.findViewById(R.id.lastName)
        var position: TextView = itemView.findViewById(R.id.position)
        var number: TextView = itemView.findViewById(R.id.number)
    }

    fun setPlayerList(childrenList: List<PlayerEntry>) {
        this.playerList.clear()
        this.playerList.addAll(childrenList)
    }

    fun getPlayerList() : List<PlayerEntry> {
        return this.playerList
    }
}