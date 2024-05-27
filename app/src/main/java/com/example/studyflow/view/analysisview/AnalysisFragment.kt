package com.example.studyflow.view.analysisview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.adapter.analysis.AnalysisDetailAdapter
import com.example.studyflow.adapter.tag.TagRecyclerAdapter
import com.example.studyflow.databinding.FragmentAnalysisBinding
import com.example.studyflow.databinding.FragmentPomodoroBinding
import com.example.studyflow.databinding.TagBottomSheetDialogRowBinding
import com.example.studyflow.interfaces.analysis.AnalysisFragmentClickListener
import com.example.studyflow.interfaces.tag.TagBottomSheetDialogClickListener
import com.example.studyflow.model.Pomodoro
import com.example.studyflow.model.Tag
import com.example.studyflow.view.tagview.TagBottomSheetDialogFragment
import com.example.studyflow.viewmodel.pomodoro.PomodoroViewModel

class AnalysisFragment : Fragment(), TagBottomSheetDialogClickListener, AnalysisFragmentClickListener {


    // private lateinit var viewModel: Analysis
    private lateinit var tagBottomSheetDialogFragment: TagBottomSheetDialogFragment

    private lateinit var selectedTag: com.example.studyflow.model.Tag
    private var tagID = -1

    // For AnalysisDetailAdapter
    private val recyclerAdapter = AnalysisDetailAdapter(ArrayList<Pomodoro>())


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tagBottomSheetDialogFragment = TagBottomSheetDialogFragment(this)

        // recyclerı birbirine bağlayacağız
        // yukarda bi adapter oluşturduk ama oluşturduğumuz gibi kalmıştı
        // şimdi onu ilgili adapter ile bağlayacağız ki bu da recycleview in adapteri
        val recyclerView = view.findViewById<RecyclerView>(R.id.analysisRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerAdapter
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
    }
}