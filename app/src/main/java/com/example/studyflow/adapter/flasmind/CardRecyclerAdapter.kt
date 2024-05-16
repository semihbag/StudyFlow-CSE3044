package com.example.studyflow.adapter.flasmind

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.databinding.CardRowBinding
import com.example.studyflow.model.Card
import java.util.Date
import java.util.Locale

class CardRecyclerAdapter(private val cardList: ArrayList<Card>) :
    RecyclerView.Adapter<CardRecyclerAdapter.CardViewHolder>() {
    class CardViewHolder(var view: CardRowBinding) : RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<CardRowBinding>(inflater, R.layout.card_row, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardList[position]
        holder.view.card = card

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val dateString = sdf.format(Date(card.createDate))

        holder.view.textViewCardDate.text = dateString
        holder.view.cardTitle.isSelected = true
    }


    fun updateCardList(newList: List<Card>) {
        cardList.clear()
        cardList.addAll(newList)
        notifyDataSetChanged()
    }
}