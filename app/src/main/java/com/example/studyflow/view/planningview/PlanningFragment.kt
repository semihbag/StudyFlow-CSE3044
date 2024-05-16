package com.example.studyflow.view.planningview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import android.view.ViewGroup
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentPlanningBinding
import com.example.studyflow.interfaces.planning.PlanningFragmentClickListener
import com.example.studyflow.model.Planning
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Calendar
import java.util.Date


class PlanningFragment : Fragment() ,PlanningFragmentClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentPlanningBinding= DataBindingUtil.inflate(
            inflater, R.layout.fragment_planning,container,false
        )
        binding.planningAddButton=this
        binding.lifecycleOwner=viewLifecycleOwner

        binding.planningAddButton.runCatching {
            var dialog= PlanningDialogFragment()
            dialog.show(parentFragmentManager, "PlanningDialogFragment")

        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DataBindingUtil.findBinding<FragmentPlanningBinding>(view)
        binding?.let { fragmentBinding ->
            fragmentBinding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                // Tarih bileşenlerini al
                val selectedDay = dayOfMonth.toLong()
                val selectedMonth = (month + 1).toLong() // Aylar 0 tabanlı olduğu için 1 ekliyoruz
                val selectedYear = year.toLong()

                // Bu bileşenleri birleştirerek tek bir long değere dönüştürmek
                val selectedDateLong = selectedYear * 10000 + selectedMonth * 100 + selectedDay

                println("Seçilen tarih: Gün: $selectedDay, Ay: $selectedMonth, Yıl: $selectedYear")
                println("Seçilen tarih (long): $selectedDateLong")

                // Eğer isterseniz milisaniye olarak da alabilirsiniz
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                val selectedDateInMillis = calendar.timeInMillis

                println("Seçilen tarih (millis): $selectedDateInMillis")
            }
        }
    }


    //BU METODU YAZARKEN KTÜPHANENİN METOTLARINI KULLAN VE DENE BAKALIM OLUYOR MU


    override fun clickAddPlanningButton(view: View) {
        val binding = DataBindingUtil.findBinding<FragmentPlanningBinding>(view)
        binding?.let {
            println("aaa")

        }
    }


}