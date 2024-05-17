package com.example.studyflow.view.flashmindview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.adapter.flasmind.CardRecyclerAdapter
import com.example.studyflow.databinding.FragmentCardBinding
import com.example.studyflow.interfaces.flashmind.CardFragmentClickListener
import com.example.studyflow.model.Card
import com.example.studyflow.model.Tag
import com.example.studyflow.viewmodel.flashmind.CardViewModel


class CardFragment : Fragment(), CardFragmentClickListener {
    private lateinit var viewModel: CardViewModel
    private val recyclerAdapter = CardRecyclerAdapter(ArrayList<Card>())
    private lateinit var tag : Tag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tag = CardFragmentArgs.fromBundle(it).tag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCardBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_card, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.listener = this
        binding.tag = tag
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CardViewModel::class.java)
        viewModel.loadCardsFromDB(this.tag)

        val  recyclerView = view.findViewById<RecyclerView>(R.id.cardRecyclerView)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.adapter = recyclerAdapter

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.mutableCard.observe(viewLifecycleOwner, Observer { cards ->
            cards.let {
                view?.findViewById<RecyclerView>(R.id.cardRecyclerView)?.visibility = View.VISIBLE
                recyclerAdapter.updateCardList(cards)
            }
        })
    }

    override fun clickAddCard(view: View) {
        val action = CardFragmentDirections.actionCardFragmentToCardCreateFragment(tag,null)
        Navigation.findNavController(view).navigate(action)
    }

}