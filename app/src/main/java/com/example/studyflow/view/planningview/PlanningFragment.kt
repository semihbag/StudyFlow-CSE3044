package com.example.studyflow.view.planningview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentPlanningBinding
import com.example.studyflow.interfaces.planning.PlanningFragmentClickListener
import com.example.studyflow.model.Planning
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Calendar
import java.util.Date


class PlanningFragment : Fragment(), PlanningFragmentClickListener {

    private lateinit var binding: FragmentPlanningBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planning, container, false)
        binding.planningAddButton = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDay = dayOfMonth.toLong()
            val selectedMonth = (month + 1).toLong()
            val selectedYear = year.toLong()
            val selectedDateLong = selectedYear * 10000 + selectedMonth * 100 + selectedDay

            println("Seçilen tarih: Gün: $selectedDay, Ay: $selectedMonth, Yıl: $selectedYear")
            println("Seçilen tarih (long): $selectedDateLong")

            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)

        }
    }
    override fun clickAddPlanningButton(view: View) {
        println("aagirdkclick")
        val showAddPlanningPage = PlanningDialogFragment()
        showAddPlanningPage.show((activity as AppCompatActivity).supportFragmentManager, "showAddPlanningPage")


    }
}
