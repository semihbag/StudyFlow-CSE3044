package com.example.studyflow.view.flashmindview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyflow.R

/**
 * A simple [Fragment] subclass.
 * Use the [FlashMindFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FlashMindFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flash_mind, container, false)
    }


}