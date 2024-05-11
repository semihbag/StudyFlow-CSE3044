package com.example.studyflow.view.flashmindview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
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
        viewModel.loadFlashMindTagsFromDB()

        val recyclerView = view.findViewById<RecyclerView>(R.id.flash_mind_tag_row_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerAdapter

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.RIGHT) {
                        // sağa kaydım
                } else if (direction == ItemTouchHelper.LEFT) {
                    // sola kaydım
                }
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

                    // right background
                    val backgroundRight = ColorDrawable(Color.BLUE)
                    backgroundRight.setBounds(viewHolder.itemView.right + dX.toInt(), viewHolder.itemView.top, viewHolder.itemView.right, viewHolder.itemView.bottom)
                    backgroundRight.draw(c)

                    // Exercise text
                    val textPaintRight = Paint().apply {
                        color = Color.WHITE
                        textSize = 40f
                        textAlign = Paint.Align.CENTER
                    }
                    val textRight = "EXERCISE"
                    c.drawText(textRight, viewHolder.itemView.right.toFloat() + dX / 2, viewHolder.itemView.bottom.toFloat() - viewHolder.itemView.height / 2, textPaintRight)


                    // left background
                    val backgroundLeft = ColorDrawable(Color.RED)
                    backgroundLeft.setBounds(viewHolder.itemView.left + dX.toInt(), viewHolder.itemView.top, viewHolder.itemView.left, viewHolder.itemView.bottom)
                    backgroundLeft.draw(c)

                    // Exercise text
                    val textPaintLeft = Paint().apply {
                        color = Color.WHITE
                        textSize = 40f
                        textAlign = Paint.Align.CENTER
                    }
                    val textLeft = "MY CARDS"
                    c.drawText(textLeft, viewHolder.itemView.left.toFloat() + dX / 2, viewHolder.itemView.bottom.toFloat() - viewHolder.itemView.height / 2, textPaintLeft)

                }
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.mutableFlashMindTags.observe(viewLifecycleOwner, Observer { tags ->
            tags.let {
                view?.findViewById<RecyclerView>(R.id.flash_mind_tag_row_recyclerview)?.visibility = View.VISIBLE
                recyclerAdapter.updateFlashMindTagList(tags)
            }
        })
    }
}