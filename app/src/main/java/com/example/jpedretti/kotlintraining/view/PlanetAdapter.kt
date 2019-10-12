package com.example.jpedretti.kotlintraining.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jpedretti.kotlintraining.R
import com.example.jpedretti.kotlintraining.provider.responseModels.PlanetResult

class PlanetsViewHolder(val view: View) : RecyclerView.ViewHolder(view)

class PlanetAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var planets = listOf<PlanetResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.planet_item_view, parent, false)
        return PlanetsViewHolder(view)
    }

    override fun getItemCount() = planets.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = planets[position]
        holder.itemView.let {
            it.findViewById<TextView>(R.id.name).text = item.name
            it.findViewById<TextView>(R.id.orbital_period).text = item.orbitalPeriod
            it.findViewById<TextView>(R.id.rotation_period).text = item.rotationPeriod
        }
    }

    fun setPlanets(planets: List<PlanetResult>) {
        this.planets = planets.map { it }
        notifyDataSetChanged()
    }
}