package com.bionickhand.kotlinprofifirstapp.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bionickhand.kotlinprofifirstapp.R

class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view){
    val textViewName = view.findViewById<TextView>(R.id.textViewName)
    val textViewCount = view.findViewById<TextView>(R.id.textViewCount)
}