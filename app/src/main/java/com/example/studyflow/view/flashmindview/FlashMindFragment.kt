package com.example.studyflow.view.flashmindview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.adapter.flasmind.FlashMindTagRecyclerAdapter
import com.example.studyflow.databinding.FragmentFlashMindBinding
import com.example.studyflow.model.Tag
import com.example.studyflow.viewmodel.flashmind.FlashMindViewModel
import com.example.studyflow.viewmodel.tag.TagViewModel

class FlashMindFragment : Fragment() {
    private lateinit var viewModel : FlashMindViewModel
    private val recyclerAdapter = FlashMindTagRecyclerAdapter(ArrayList<Tag>())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentFlashMindBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_flash_mind, container,false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FlashMindViewModel::class.java)

        // burda db load fonk gelcek

        val recyclerView = view.findViewById<RecyclerView>(R.id.flash_mind_tag_row_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerAdapter

        

    }

}