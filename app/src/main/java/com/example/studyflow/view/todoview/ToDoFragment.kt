package com.example.studyflow.view.todoview

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
import com.example.studyflow.databinding.FragmentToDoBinding
import com.example.studyflow.databinding.ToDoRowBinding
import com.example.studyflow.interfaces.todo.ToDoFragmentClickListener
import com.example.studyflow.model.ToDo
import com.example.studyflow.model.ToDoMainRecyclerItem
import com.example.studyflow.view.tagview.TagBottomSheetDialogFragment
import com.example.studyflow.viewmodel.todo.ToDoViewModel


class ToDoFragment : Fragment(), ToDoFragmentClickListener {
    private lateinit var viewModel: ToDoViewModel
    private lateinit var recyclerToDoMainAdapter: ToDoMainRecyclerAdapter
    private lateinit var tagBottomSheetDialogFragment: TagBottomSheetDialogFragment

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



        val toDoMainRecyclerView = view.findViewById<RecyclerView>(R.id.to_do_main_recycler)
        toDoMainRecyclerView.layoutManager = LinearLayoutManager(context)
        recyclerToDoMainAdapter =
            ToDoMainRecyclerAdapter(ArrayList<ToDoMainRecyclerItem>(), requireContext(), this)
        toDoMainRecyclerView.adapter = recyclerToDoMainAdapter

        tagBottomSheetDialogFragment = TagBottomSheetDialogFragment()


        observeLiveData()


        // burayı enter bastı mı diye dinliyorum
        val binding = DataBindingUtil.findBinding<FragmentToDoBinding>(view)
        binding?.let {
            it.editTextAddToDo.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    val toDo = ToDo(it.editTextAddToDo.text.toString(), tagBottomSheetDialogFragment.selectedTagUuid, false)
                    viewModel.storeToDoToDB(toDo)
                    binding.editTextAddToDo.text.clear()
                    tagBottomSheetDialogFragment.updateSelectedTagUuid(0)
                    return@setOnKeyListener true
                }
                false
            }
        }


    }

    fun observeLiveData() {
        viewModel.mutableSelectTagList.observe(viewLifecycleOwner, Observer { tags ->
            tags.let {
                view?.findViewById<RecyclerView>(R.id.tag_bottom_sheet_dialog_recyclerview)?.visibility =
                    View.VISIBLE
                tagBottomSheetDialogFragment.updateAdapterList(tags)
            }
        })

        viewModel.mutableToDoList.observe(viewLifecycleOwner, Observer { todos ->
            todos.let {
                viewModel.setToDoMainRecyclerItemList()
            }
        })

        viewModel.mutableToDoMainRecyclerItem.observe(viewLifecycleOwner, Observer { items ->
            items.let {
                view?.findViewById<RecyclerView>(R.id.to_do_main_recycler)?.visibility =
                    View.VISIBLE
                recyclerToDoMainAdapter.updateToDoMainRecyclerItemList(ArrayList(items))
            }
        })
    }


    override fun clickAddToDo(view: View) {
        val binding = DataBindingUtil.findBinding<FragmentToDoBinding>(view)
        binding?.let {
            val toDoText = it.editTextAddToDo.text.toString()
            val toDo = ToDo(toDoText, tagBottomSheetDialogFragment.selectedTagUuid, false)
            viewModel.storeToDoToDB(toDo)
            binding.editTextAddToDo.text.clear()
            tagBottomSheetDialogFragment.updateSelectedTagUuid(0)
        }
    }


    override fun clickShowTagList(view: View) {
        tagBottomSheetDialogFragment.show(childFragmentManager, tagBottomSheetDialogFragment.tag)
    }

    override fun clickDone(view: View) {
        val binding = DataBindingUtil.findBinding<ToDoRowBinding>(view)
        binding?.let {
            it.toDo?.let {
                it.done = binding.checkBox.isChecked
                viewModel.updateToDo(it)
            }
        }
    }
}