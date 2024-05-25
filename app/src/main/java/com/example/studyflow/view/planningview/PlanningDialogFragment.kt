package com.example.studyflow.view.planningview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.findFragment
import com.example.studyflow.R
import com.example.studyflow.interfaces.planning.PlanningDialogFragmentClickListener
import com.example.studyflow.interfaces.planning.PlanningFragmentClickListener
import com.example.studyflow.view.tagview.TagBottomSheetDialogFragment
import com.example.studyflow.viewmodel.todo.ToDoViewModel

class PlanningDialogFragment : DialogFragment() ,PlanningDialogFragmentClickListener{
    private lateinit var viewModel: ToDoViewModel
    private lateinit var tagBottomSheetDialogFragment: TagBottomSheetDialogFragment


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
        button.setOnClickListener {
            // EditText'ten girilen metni alma
            val inputText = txt.text.toString()
            // Konsola metni yazdırma
            println(inputText)
        }

    }

    override fun clickSavedPlanningButton(view: View) {
        val binding = DataBindingUtil.findBinding<FragmentPlanningDialogBinding>(view)


    }

}