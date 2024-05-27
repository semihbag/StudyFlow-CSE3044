package com.example.studyflow.view.planningview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyflow.R
import com.example.studyflow.adapter.planning.PlanningRecyclerAdapter
import com.example.studyflow.databinding.FragmentPlanningBinding
import com.example.studyflow.databinding.TagBottomSheetDialogRowBinding
import com.example.studyflow.interfaces.planning.PlanningFragmentClickListener
import com.example.studyflow.interfaces.tag.TagBottomSheetDialogClickListener
import com.example.studyflow.model.Plan
import com.example.studyflow.model.Tag
import com.example.studyflow.view.tagview.TagBottomSheetDialogFragment
import com.example.studyflow.viewmodel.planning.PlanningViewModel


class PlanningFragment : Fragment(), PlanningFragmentClickListener,
    TagBottomSheetDialogClickListener {
    private lateinit var viewmodel : PlanningViewModel
    private lateinit var binding : FragmentPlanningBinding
    private val recyclerAdapter = PlanningRecyclerAdapter(ArrayList<Plan>())
    private lateinit var tagBottomSheetDialogFragment: TagBottomSheetDialogFragment
    private lateinit var selectedTag : Tag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planning, container,false)

        binding.listener = this
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(this).get(PlanningViewModel::class.java)
        binding.planingRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.planingRecyclerView.adapter = recyclerAdapter

        tagBottomSheetDialogFragment = TagBottomSheetDialogFragment(this)

        binding.calender.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Calendar sınıfı ile seçilen tarihi ayarla
            val calendar = binding.calender.getInstance()
            calendar.set(year, month, dayOfMonth, 0, 0, 0)
            calendar.set(binding.calender.MILLISECOND, 0)

            // Tarihi long olarak al
            val selectedDateInMillis: Long = calendar.timeInMillis

            // Burada long olarak alınan tarihi kullanabilirsiniz
            println("Seçilen Tarih (Millis): $selectedDateInMillis")
        }
    }





    override fun clickSelectTag(view: View) {
        val binding1 = DataBindingUtil.findBinding<TagBottomSheetDialogRowBinding>(view)
        binding1?.let { it ->
            it.tag?.let {
                selectedTag = it
                binding.tag = it
            }
        }
        tagBottomSheetDialogFragment.dismiss()    }

    override fun clickShowTagList(view: View) {
        tagBottomSheetDialogFragment.show(requireActivity().supportFragmentManager, "a")
    }

    override fun clickAddPlan(view: View) {
        TODO("Not yet implemented")
    }
}