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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.adapter.planner.PlanningRecyclerAdapter
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
    private lateinit var planningRecyclerAdapter: PlanningRecyclerAdapter
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

        //recycler ile adapteri bağlayalm bakalm
        val recyclerView =view.findViewById<RecyclerView>(R.id.planningRecyclerView)
        recyclerView.layoutManager =LinearLayoutManager(context)
        recyclerView.adapter =planningRecyclerAdapter


        observeLiveData()

        val calendarView: CalendarView = view.findViewById(R.id.calendarView)

        // CalendarView'den seçilen tarihi dinleyici ile al
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedCalendar = Calendar.getInstance()
            selectedCalendar.set(year, month, dayOfMonth)
            date = selectedCalendar.timeInMillis
        }
        //default olarak date anlık günü alır model sınıfında



    }



    fun observeLiveData(){
        viewModel.mutableSelectPlanningDay.observe(viewLifecycleOwner,{plannings->
            plannings.let {
                view?.findViewById<RecyclerView>(R.id.planning_recycler_row)?.visibility =View.VISIBLE
                planningRecyclerAdapter.updatePlanningList(plannings)
            }
        })
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
