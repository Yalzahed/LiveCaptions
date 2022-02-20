package com.example.cameraappviews

import androidx.recyclerview.widget.RecyclerView
import com.example.cameraappviews.databinding.CardCellBinding
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.io.IOException

class CardViewHolder(
    private val cardCellBinding: CardCellBinding
) : RecyclerView.ViewHolder(cardCellBinding.root) {
    fun bindCollectible(collectible: Collectibles) {

        if (collectible.discovered.value == true)
            cardCellBinding.cover.setImageResource(collectible.collectible)
        else
            cardCellBinding.cover.setImageResource(R.drawable.question_mark)

        cardCellBinding.title.text = collectible.name
        cardCellBinding.location.text = collectible.location
    }
}