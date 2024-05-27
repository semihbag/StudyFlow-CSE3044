package com.example.studyflow.view.analysisview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentAnalysisBinding
import com.example.studyflow.databinding.FragmentPomodoroBinding
import com.example.studyflow.databinding.TagBottomSheetDialogRowBinding
import com.example.studyflow.interfaces.analysis.AnalysisFragmentClickListener
import com.example.studyflow.interfaces.tag.TagBottomSheetDialogClickListener
import com.example.studyflow.model.Pomodoro
import com.example.studyflow.view.tagview.TagBottomSheetDialogFragment
import com.example.studyflow.viewmodel.analysis.AnalysisViewModel
import com.example.studyflow.viewmodel.pomodoro.PomodoroViewModel

class AnalysisFragment : Fragment(), TagBottomSheetDialogClickListener, AnalysisFragmentClickListener {


    private lateinit var viewModel: AnalysisViewModel
    private lateinit var tagBottomSheetDialogFragment: TagBottomSheetDialogFragment
    private var allPomodorosOfTag: List<Pomodoro> = mutableListOf()

    private lateinit var selectedTag: com.example.studyflow.model.Tag
    private var tagID = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAnalysisBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_analysis, container, false
        )
        binding.listener = this
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    fun observeLiveData() {
        viewModel.pomodoroItems.observe(viewLifecycleOwner, Observer { pomodoros ->
            allPomodorosOfTag = pomodoros
            calculateTotalTime()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[AnalysisViewModel::class.java]
        tagBottomSheetDialogFragment = TagBottomSheetDialogFragment(this)
    }


    private fun calculateTotalTime() {
        var totalTime: Long = 0
        for (pomodoro in allPomodorosOfTag) {
            totalTime += (pomodoro.endTime - pomodoro.startTime)
        }

        view?.let {
            val binding = DataBindingUtil.findBinding<FragmentAnalysisBinding>(it)
            binding?.let { bind ->
                bind.TotalMinText.text = (totalTime / 60000).toString()
                bind.TotalSecText.text = (totalTime % 60000 / 1000).toString()
            }
        }
    }

    override fun onTag(view: View) {
        tagBottomSheetDialogFragment.show(requireActivity().supportFragmentManager, "a")

    }

    override fun clickSelectTag(view: View) {
        val binding = DataBindingUtil.findBinding<TagBottomSheetDialogRowBinding>(view)
        binding?.let {
            it.tag?.let {
                selectedTag = it

                val myView = getView()
                myView?.let{
                    val binding2 = DataBindingUtil.findBinding<FragmentAnalysisBinding>(it)
                    binding2?.let{
                        // change the buttons (stop and resume will be visible)
                        binding2.tagNameOnAnalysis.text = selectedTag.tagName
                        tagID = selectedTag.uuid
                    }
                }
            }
        }
        tagBottomSheetDialogFragment.dismiss()
        if (tagID != -1) {
            viewModel.findAllPomodorosOfSpesificTag(tagID)
            observeLiveData()
        }
    }
}