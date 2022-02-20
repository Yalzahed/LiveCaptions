package com.example.cameraappviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cameraappviews.databinding.CardCellBinding

class CardAdapter(private var collectibles: List<Collectibles>):
    RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CardCellBinding.inflate(from, parent, false)
        return CardViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindCollectible(collectibles[position])

    }

    override fun getItemCount(): Int = collectibles.size;
}