package com.example.studyflow.adapter.analysis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.adapter.tag.TagRecyclerAdapter
import com.example.studyflow.databinding.FragmentAnalysisBinding
import com.example.studyflow.databinding.TagRowBinding
import com.example.studyflow.model.Card
import com.example.studyflow.model.Pomodoro

class AnalysisDetailAdapter(private val pomodoroList: ArrayList<Pomodoro>):
    RecyclerView.Adapter<AnalysisDetailAdapter.AnalysisDetailViewHolder>(){

    class AnalysisDetailViewHolder(var view: FragmentAnalysisBinding ): RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisDetailAdapter.AnalysisDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        // bu databinding olmadan önce kullanılan çağırma yöntemi idi
        //val view = inflater.inflate(R.layout.tag_row,parent,false)
        val view = DataBindingUtil.inflate<FragmentAnalysisBinding>(inflater, R.layout.analysis_row, parent, false)        // burda yazdığım layout ile bağlantısını kuruyorum artık holder neye benzeyeceğini biliyor
        return AnalysisDetailAdapter.AnalysisDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnalysisDetailViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    // sanırım bunu arkaplanda otomatik kendi kullanıyor
    override fun getItemCount(): Int {
        return pomodoroList.size
    }

    override fun onBindViewHolder(holder: TagRecyclerAdapter.TagViewHolder, position: Int) {
        // holder aslında bi TagViewHolder yani tek bir tagı tutan container adının holder olmasına gerek yok ama mantıklı olan bu
        // ayrıca bu TagViewHolder i da yukarda kendim tanımladım
        holder.view.tag = tagList[position]
        holder.view.listenerAdapter = this
        holder.view.listenerFragment = listenerFragment
        holder.view.tagTittle.isSelected = true

        // yukadaki listener aslında bir interface. bu sınıf o interfaceden extend edildiği için this yazabilirim. üzerinde tıklama işini yapacak
        // clickTag fonksiyonu da zaten bunun için overrride edildi (aşağıda)


    }
}