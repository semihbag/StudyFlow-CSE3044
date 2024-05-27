package com.example.studyflow.adapter.analysis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.adapter.tag.TagRecyclerAdapter
import com.example.studyflow.databinding.AnalysisRowBinding
import com.example.studyflow.databinding.FragmentAnalysisBinding
import com.example.studyflow.databinding.TagRowBinding
import com.example.studyflow.model.Card
import com.example.studyflow.model.Pomodoro
import java.text.SimpleDateFormat
import java.util.Date

class AnalysisDetailAdapter(private val pomodoroList: ArrayList<Pomodoro>):
    RecyclerView.Adapter<AnalysisDetailAdapter.AnalysisDetailViewHolder>(){

    class AnalysisDetailViewHolder(var view: AnalysisRowBinding ): RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisDetailAdapter.AnalysisDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        // bu databinding olmadan önce kullanılan çağırma yöntemi idi
        //val view = inflater.inflate(R.layout.tag_row,parent,false)
        val view = DataBindingUtil.inflate<AnalysisRowBinding>(inflater, R.layout.analysis_row, parent, false)        // burda yazdığım layout ile bağlantısını kuruyorum artık holder neye benzeyeceğini biliyor
        return AnalysisDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnalysisDetailViewHolder, position: Int) {
        val date_format = SimpleDateFormat("yyyy-MM-dd HH:mm")
        holder.view.date.text =  date_format.format(Date(pomodoroList[position].startTime)).toString()
        holder.view.enteredTime.text = pomodoroList[position].enteredTime.toString()
        holder.view.pomodoroTime.text = pomodoroList[position].pomodoroTime.toString()
        holder.view.inactiveTime.text = pomodoroList[position].inactiveTime.toString()
        if ( 1 == pomodoroList[position].isFinished){
            holder.view.isCompleted.setBackgroundResource(R.drawable.icon_done2)
        }
        else {
            holder.view.isCompleted.setBackgroundResource(R.drawable.icon_done)
        }
    }

    // sanırım bunu arkaplanda otomatik kendi kullanıyor
    override fun getItemCount(): Int {
        return pomodoroList.size
    }

}