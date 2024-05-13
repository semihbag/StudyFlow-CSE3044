package com.example.studyflow.adapter.flasmind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.databinding.CardRowBinding
import com.example.studyflow.model.Card

class CardRecyclerAdapter (private val cardList : ArrayList<Card>) : RecyclerView.Adapter<CardRecyclerAdapter.CardViewHolder>() {
    class CardViewHolder(var view : CardRowBinding) : RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CardRowBinding>(inflater, R.layout.card_row, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.view.card = cardList[position]
    }

    fun updateCardList(newList : List<Card>) {
        cardList.clear()
        cardList.addAll(newList)
        notifyDataSetChanged()
    }
}