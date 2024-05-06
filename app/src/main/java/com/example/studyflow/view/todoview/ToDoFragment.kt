package com.example.studyflow.view.todoview

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.adapter.todo.ToDoMainRecyclerAdapter
import com.example.studyflow.adapter.todo.ToDoSelectTagRecyclerAdapter
import com.example.studyflow.databinding.FragmentToDoBinding
import com.example.studyflow.databinding.ToDoSelectTagRowBinding
import com.example.studyflow.interfaces.todo.ToDoFragmentClickListener
import com.example.studyflow.model.Tag
import com.example.studyflow.model.ToDo
import com.example.studyflow.model.ToDoMainRecyclerItem
import com.example.studyflow.viewmodel.todo.ToDoViewModel


class ToDoFragment : Fragment(), ToDoFragmentClickListener {
    private lateinit var viewModel: ToDoViewModel
    private val recyclerSelecetTagAdapter = ToDoSelectTagRecyclerAdapter(ArrayList<Tag>(), this)
    private lateinit var recyclerToDoMainAdapter : ToDoMainRecyclerAdapter
    private var selectedTagId = 0
    private lateinit var selectedTagBinding : ToDoSelectTagRowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentToDoBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_to_do, container, false
        )

        binding.listener = this
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        viewModel.loadSelectTagFromDB()
        viewModel.setToDoMainRecyclerItemList()

        val selectTagRecyclerView = view.findViewById<RecyclerView>(R.id.to_do_select_tag_recyclerview)
        selectTagRecyclerView.layoutManager = LinearLayoutManager(context)
        selectTagRecyclerView.adapter = recyclerSelecetTagAdapter

        val toDoMainRecyclerView = view.findViewById<RecyclerView>(R.id.to_do_main_recycler)
        toDoMainRecyclerView.layoutManager = LinearLayoutManager(context)
        recyclerToDoMainAdapter = ToDoMainRecyclerAdapter(ArrayList<ToDoMainRecyclerItem>(),requireContext())
        toDoMainRecyclerView.adapter = recyclerToDoMainAdapter

        observeLiveData()

        val binding = DataBindingUtil.findBinding<FragmentToDoBinding>(view)
        binding?.let {
            it.editTextAddToDo.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    val toDo = ToDo(it.editTextAddToDo.text.toString(), selectedTagId, false)
                    viewModel.storeToDoToDB(toDo)
                    binding.editTextAddToDo.text.clear()
                    selectedTagId = 0
                    if (::selectedTagBinding.isInitialized) {
                        selectedTagBinding.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"))
                    }
                    it.selecTagForToDoList.visibility = View.GONE
                    return@setOnKeyListener true
                }
                false
            }
        }





    }

    fun observeLiveData() {
        viewModel.mutableSelectTagList.observe(viewLifecycleOwner, Observer { tags ->
            tags.let {
                view?.findViewById<RecyclerView>(R.id.to_do_select_tag_recyclerview)?.visibility =
                    View.VISIBLE
                recyclerSelecetTagAdapter.updateSelectTagList(tags)
            }
        })

        viewModel.mutableToDoList.observe(viewLifecycleOwner, Observer { todos ->
            todos.let {
                viewModel.setToDoMainRecyclerItemList()
            }
        })

        viewModel.mutableToDoMainRecyclerItem.observe(viewLifecycleOwner, Observer { items ->
            items.let {
                view?.findViewById<RecyclerView>(R.id.to_do_main_recycler)?.visibility = View.VISIBLE
                recyclerToDoMainAdapter.updateToDoMainRecyclerItemList(ArrayList(items))
            }
        })
    }






    override fun clickSelectTag(view: View) {
        val binding = DataBindingUtil.findBinding<ToDoSelectTagRowBinding>(view)
        binding?.let {
            val tag = it.tag
            tag?.let {
                if (::selectedTagBinding.isInitialized) {
                    selectedTagBinding.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"))
                }
                selectedTagBinding = binding
                selectedTagBinding.cardView.setCardBackgroundColor(Color.parseColor("#E3D8D8"))
                selectedTagId = it.uuid
            }
        }
    }

    override fun clickAddToDo(view: View) {
        val binding = DataBindingUtil.findBinding<FragmentToDoBinding>(view)
        binding?.let {
            val toDoText = it.editTextAddToDo.text.toString()
            val toDo = ToDo(toDoText,selectedTagId,false)
            viewModel.storeToDoToDB(toDo)
            binding.editTextAddToDo.text.clear()
            selectedTagId = 0
            if (::selectedTagBinding.isInitialized) {
                selectedTagBinding.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"))
            }
            it.selecTagForToDoList.visibility = View.GONE
        }
    }

    override fun clickShowTagList(view: View) {
        val binding = DataBindingUtil.findBinding<FragmentToDoBinding>(view)
        binding?.let {
            it.selecTagForToDoList.visibility =
                if (it.selecTagForToDoList.visibility == View.GONE) View.VISIBLE else View.GONE
        }
    }
}