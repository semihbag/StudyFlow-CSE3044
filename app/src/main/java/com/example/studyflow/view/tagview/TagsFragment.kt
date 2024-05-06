package com.example.studyflow.view.tagview

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.adapter.tag.TagRecyclerAdapter
import com.example.studyflow.databinding.FragmentTagsBinding
import com.example.studyflow.databinding.TagRowBinding
import com.example.studyflow.interfaces.tag.TagFragmentClickListener
import com.example.studyflow.model.Tag
import com.example.studyflow.viewmodel.tag.TagViewModel

class TagsFragment : Fragment(), TagFragmentClickListener {
    // fragmana viewmodel ve adapterleri ekledim. arkadaki işleri bu ikisi yapıyor çünkü
    // viewmodel henüz oluşturmadım ama adapter oluşturdum ilk olarak da adaptere boş array verdim
    private lateinit var viewModel : TagViewModel
    private val recyclerAdapter = TagRecyclerAdapter(ArrayList<Tag>(), this)

    // bunu ellemeye gerek yok
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentTagsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_tags, container, false)

        // Viewmodel'i bağlama işlemi
        binding.listener = this
        // LiveData'yi gözlemleme işlemi
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }


    // hemen yukarda xml ile bağlantı kurduk görüntü taslağımız hazır peki içinde ne göstericez o hazırlıkları yapıyoruz burda da
    // adı üzerinde onViewCreted (görünüm oluştuktan sonra)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // burada kendi yazdığımız viewmodel classını bağladık bu sayede fonksiyonlarını çağırabileceğiz en yukarda tanımlamıştık sadece
        viewModel = ViewModelProvider(this).get(TagViewModel::class.java)

        // tagları çağırdım vievmodel classtan, çünkü db işlerini bu yapıyor
        viewModel.loadTagsFromDB()

        // recyclerı birbirine bağlayacağız
        // yukarda bi adapter oluşturduk ama oluşturduğumuz gibi kalmıştı
        // şimdi onu ilgili adapter ile bağlayacağız ki bu da recycleview in adapteri
        val recyclerView = view.findViewById<RecyclerView>(R.id.tagsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerAdapter

        // her şeyi birbirine bağladık şimdi de bakalım gözlemleyelim neler  oluyor neler bitiyor
        observeLiveData()

        // burada eğer textte enter bastıysa ekleme yapıyoruz
        val binding = DataBindingUtil.findBinding<FragmentTagsBinding>(view)
        binding?.let {
            it.editTextAddTag.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    val tag = Tag(binding.editTextAddTag.text.toString())
                    viewModel.storeTagToDB(tag)
                    binding.editTextAddTag.text.clear()
                    return@setOnKeyListener true
                }
                false
            }
        }
    }

    // burada gözlenebilir verilei gözleme fonksiyonlarını yazdım
    // buradad viewmodelin tag arrayiini gözlemliyorum zaten onu da orada mutable olarak tanımladım
    //  taglarde bi değişiklik olur olmaz update edeceğiz her birine bi tetikleyici kurduk yani
    fun observeLiveData() {
        viewModel.mutableTags.observe(viewLifecycleOwner, Observer { tags ->
            tags.let {
                view?.findViewById<RecyclerView>(R.id.tagsRecyclerView)?.visibility = View.VISIBLE
                recyclerAdapter.updateTagList(tags)
            }
        })
    }


    override fun clickAddTag(view: View) {
        val binding = DataBindingUtil.findBinding<FragmentTagsBinding>(view)
        binding?.let {
            val tag = Tag(binding.editTextAddTag.text.toString())
            viewModel.storeTagToDB(tag)
            binding.editTextAddTag.text.clear()
        }
    }

    override fun clickDeleteTag(view: View) {
        val binding = DataBindingUtil.findBinding<TagRowBinding>(view)
        binding?.let {
            viewModel.deleteTagFromDB(it.tagUuid.text.toString().toInt())
        }
    }
}