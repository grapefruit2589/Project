// PlannerAdapter.kt
package com.example.travelplanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlannerAdapter(private val planners: List<Planner>) :
    RecyclerView.Adapter<PlannerAdapter.PlannerViewHolder>() {

    class PlannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val travelPeriod: TextView = itemView.findViewById(R.id.travelPeriod)
        val accommodation: TextView = itemView.findViewById(R.id.accommodation)
        val destination: TextView = itemView.findViewById(R.id.destination)
        val companions: TextView = itemView.findViewById(R.id.companions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlannerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.planner_item, parent, false)
        return PlannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlannerViewHolder, position: Int) {
        val planner = planners[position]
        holder.travelPeriod.text = planner.travelPeriod
        holder.accommodation.text = planner.accommodation
        holder.destination.text = planner.destination
        holder.companions.text = planner.companions.toString()
    }

    override fun getItemCount(): Int {
        return planners.size
    }
}
