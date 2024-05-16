package com.example.studyflow.view.input_output_edit

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentEditImageBinding
import com.example.studyflow.enums.EditInputType
import com.example.studyflow.interfaces.input_output_edit.EditImageFragmentClickListener
import com.example.studyflow.model.Card
import com.example.studyflow.model.Tag


class EditImageFragment : Fragment(), EditImageFragmentClickListener {
    private lateinit var binding: FragmentEditImageBinding
    private lateinit var tag: Tag
    private lateinit var card: Card
    private lateinit var editType: EditInputType

    // resmi ekrana gömmeyi burda yaptım
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { imageUri ->
            binding.imageView.setImageURI(imageUri)

            // card içindeki path güncelledim
            when (editType) {
                EditInputType.IMAGE_FRONT -> card.imagePathFront = imageUri.toString()
                EditInputType.IMAGE_BACK -> card.imagePathBack = imageUri.toString()
                else -> println("Unknown type")
            }
        }
    }

    // izin istedim
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // İzin verildi
                openGallery()
            } else {
                // İzin verilmedi
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            this.editType = EditTextFragmentArgs.fromBundle(it).editType
            this.tag = EditTextFragmentArgs.fromBundle(it).tag
            EditTextFragmentArgs.fromBundle(it).card?.let { card ->
                this.card = card
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_image, container, false)
        binding.listener = this

        when (editType) {
            EditInputType.IMAGE_FRONT -> binding.textViewHint.hint = "Select Front Image"
            EditInputType.IMAGE_BACK -> binding.textViewHint.hint = "Select Back Image"
            else -> println("Unknown type")
        }
        return binding.root
    }


    override fun clickDone(view: View) {
        val action = EditImageFragmentDirections.actionEditImageFragmentToCardCreateFragment(this.tag, this.card)
        Navigation.findNavController(view).navigate(action)
    }


    override fun clickSelectImage(view: View) {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED -> {
                openGallery()
            }
            else -> {
                // İzin iste
                requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
            }
        }
    }

    private fun openGallery() {
        getContent.launch("image/*")
    }
}