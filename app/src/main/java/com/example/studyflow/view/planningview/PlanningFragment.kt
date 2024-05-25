package com.example.studyflow.view.planningview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentPlanningBinding
import com.example.studyflow.interfaces.planning.PlanningFragmentClickListener
import com.example.studyflow.model.Planning
import com.example.studyflow.view.tagview.TagBottomSheetDialogFragment
import com.example.studyflow.viewmodel.planning.PlanningViewModel
import com.example.studyflow.viewmodel.todo.ToDoViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Calendar
import java.util.Date


class PlanningFragment : Fragment(), PlanningFragmentClickListener {
    private lateinit var viewModel: PlanningViewModel // load fonksiyonlarını kullancan
    private lateinit var binding: FragmentPlanningBinding
    private lateinit var tagBottomSheetDialogFragment: TagBottomSheetDialogFragment
    var date : Long=0

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

        viewModel =ViewModelProvider(this).get(PlanningViewModel::class.java)
        viewModel.loadPlanningFromDB(date)
        //burada datei gönderme sebebimiz buna göre bir yükleme yapılacak olması
        //RECYCLER İÇİN METOT YAZILIYOR EKLENECEK



        // fragment_date.xml dosyasındaki CalendarView'i bul
        val calendarView: CalendarView = view.findViewById(R.id.calendarView)

        // CalendarView'e tarih değişikliği dinleyicisi ekle
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Seçilen tarihi yyyyMMdd formatında Long olarak ayarla
            val formattedDate = String.format("%04d%02d%02d", year, month + 1, dayOfMonth)
            date = formattedDate.toLong()

        }


        // Kullanıcı hiçbir güne tıklamazsa, varsayılan olarak bugünün tarihini al
        if (date == 0L) {
            val todayCalendar = Calendar.getInstance()
            val year = todayCalendar.get(Calendar.YEAR)
            val month = todayCalendar.get(Calendar.MONTH) + 1 // Ayı 1-12 aralığına getir
            val dayOfMonth = todayCalendar.get(Calendar.DAY_OF_MONTH)

            // Bugünün tarihini yyyyMMdd formatında Long olarak ayarla
            val formattedToday = String.format("%04d%02d%02d", year, month, dayOfMonth)
            date = formattedToday.toLong()
        }


    }
    override fun clickAddPlanningButton(view: View) {
        println("aagirdkclick")
        val showAddPlanningPage = PlanningDialogFragment()
        showAddPlanningPage.show((activity as AppCompatActivity).supportFragmentManager, "showAddPlanningPage")
    }

    override fun clickShowTagListButton(view: View) {
        tagBottomSheetDialogFragment.show(childFragmentManager, tagBottomSheetDialogFragment.tag)
    }
}
