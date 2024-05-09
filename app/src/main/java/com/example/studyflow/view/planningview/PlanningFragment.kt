package com.example.studyflow.view.planningview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studyflow.R
import com.example.studyflow.interfaces.planning.PlanningFragmentClickListener

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planning, container, false)
    }

    //BU METODU YAZARKEN KTÜPHANENİN METOTLARINI KULLAN VE DENE BAKALIM OLUYOR MU
    override fun clickCalenderDay(view: View) {
        TODO("Not yet implemented")
    }

    override fun clickAddPlanningButton(view: View) {
        TODO("Not yet implemented")
    }


}