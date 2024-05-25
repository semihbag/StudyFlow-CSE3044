package com.example.studyflow.view.planningview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.studyflow.R
import com.example.studyflow.databinding.PlanningDialogBinding
import com.example.studyflow.interfaces.planning.PlanningDialogFragmentClickListener
import com.example.studyflow.model.Planning
import com.example.studyflow.view.tagview.TagBottomSheetDialogFragment
import com.example.studyflow.viewmodel.planning.PlanningViewModel

class PlanningDialogFragment : DialogFragment() ,PlanningDialogFragmentClickListener{
    private lateinit var viewModel: PlanningViewModel
    private lateinit var tagBottomSheetDialogFragment: TagBottomSheetDialogFragment
    private lateinit var planningFragment: PlanningFragment
    //bu date bilgisini çekebilmek için


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View= inflater.inflate(R.layout.planning_dialog, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txt= view.findViewById<TextView>(R.id.txtAddedPlanning)
        val button= view.findViewById<Button>(R.id.saveAddedPlanning)
        //enter bastı mı diye dinlememize gerek yok butona basmalı save butonuna

    }

    //şu an save tıkladığında aldığı plan objesini kaydediyor olmasını temenni ediyoruz
    override fun clickSavedPlanningButton(view: View) {
        val binding = DataBindingUtil.findBinding<PlanningDialogBinding>(view)
        binding?.let {
            val planningTxt =it.txtAddedPlanning.text.toString()
            //girilen txtyi aldıgımızı düsünüyorum
            val planning =Planning(planningTxt, tagBottomSheetDialogFragment.selectedTagUuid, planningFragment.date)
            //viewmodel kullanarak dbye kaydet store metodu yazılmalı
            viewModel.storePlanningToDB(planning)
            binding.txtAddedPlanning.text.clear()
            tagBottomSheetDialogFragment.updateSelectedTagUuid(0)

        }
    }

}