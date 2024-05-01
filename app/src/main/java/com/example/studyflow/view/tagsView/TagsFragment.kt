package com.example.studyflow.view.tagsView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.adaptor.TagRecyclerAdapter
import com.example.studyflow.model.Tag
import com.example.studyflow.viewmodel.tagViewModel.TagListViewModel

class TagsFragment : Fragment() {
    private lateinit var viewModel : TagListViewModel
    private val recyclerAdapter = TagRecyclerAdapter(ArrayList<Tag>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tags, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // burada kendi yazdığımız viewmodel classını bağladık bu sayede fonksiyonlarını çağırabileceğiz
        viewModel = ViewModelProvider(this).get(TagListViewModel::class.java)
        viewModel.getTagsFromDB()

        // recyclerı birbirine bağlayacağız
        val recyclerView = view.findViewById<RecyclerView>(R.id.tagsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerAdapter

    }

    // burada gözlenebilir verilei gözleme fonksiyonlarını yazdım
    fun observeLiveData() {
        viewModel.tags.observe(this, Observer { tags ->
            tags.let {
                view?.findViewById<RecyclerView>(R.id.tagsRecyclerView)?.visibility = View.VISIBLE
                recyclerAdapter.updateTagList(tags)
            }
        })
    }
}