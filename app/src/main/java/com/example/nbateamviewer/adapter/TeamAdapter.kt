package com.example.nbateamviewer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateamviewer.R
import com.example.nbateamviewer.data.TeamEntry

class TeamAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    private val teamList = ArrayList<TeamEntry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: TeamAdapter.ViewHolder, position: Int) {
        val team = teamList[position]
        val name = team.fullName
        val wins = team.wins
        val losses = team.losses

        holder.name.text = name
        holder.wins.text = wins.toString()
        holder.losses.text = losses.toString()
    }

    class ViewHolder(itemView: View,
                     private var onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var name: TextView = itemView.findViewById(R.id.teamName)
        var wins: TextView = itemView.findViewById(R.id.wins)
        var losses: TextView = itemView.findViewById(R.id.losses)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(adapterPosition)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setTeamList(childrenList: List<TeamEntry>) {
        this.teamList.clear()
        this.teamList.addAll(childrenList)
    }

    fun getTeamList() : List<TeamEntry> {
        return this.teamList
    }
}