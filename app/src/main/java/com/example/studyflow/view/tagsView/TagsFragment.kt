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
    // fragmana viewmodel ve adapterleri ekledim. arkadaki işleri bu ikisi yapıyor çünkü
    // viewmodel henüz oluşturmadım ama adapter oluşturdum ilk olarak da adaptere boş array verdim
    private lateinit var viewModel : TagListViewModel
    private val recyclerAdapter = TagRecyclerAdapter(ArrayList<Tag>())

    // bunu ellemeye gerek yok
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        // layout (xml)ile fragman bağlantısını yaptım yukarda da viewmodel adapter bağlamıştım. şimdi her şey bağlandı
        // adı üzerinde onCreateView(görünüm oluşurken yani) ilk başta bi xml ile bağlantı kurmak lazım
        return inflater.inflate(R.layout.fragment_tags, container, false)
    }

    // hemen yukarda xml ile bağlantı kurduk görüntü taslağımız hazır peki içinde ne göstericez o hazırlıkları yapıyoruz burda da
    // adı üzerinde onViewCreted (görünüm oluştuktan sonra)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // burada kendi yazdığımız viewmodel classını bağladık bu sayede fonksiyonlarını çağırabileceğiz en yukarda tanımlamıştık sadece
        viewModel = ViewModelProvider(this).get(TagListViewModel::class.java)
        // tagları çağırdım vievmodel classtan, çünkü db işlerini bu yapıyor
        viewModel.getTagsFromDB()

        // recyclerı birbirine bağlayacağız
        // yukarda bi adapter oluşturduk ama oluşturduğumuz gibi kalmıştı
        // şimdi onu ilgili adapter ile bağlayacağız ki bu da recycleview in adapteri
        val recyclerView = view.findViewById<RecyclerView>(R.id.tagsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerAdapter

        // her şeyi birbirine bağladık şimdi de bakalım gözlemleyelim neler  oluyor neler bitiyor
        observeLiveData()
    }

    // burada gözlenebilir verilei gözleme fonksiyonlarını yazdım
    // buradad viewmodelin tag arrayiini gözlemliyorum zaten onu da orada mutable olarak tanımladım
    //  taglarde bi değişiklik olur olmaz update edeceğiz her birine bi tetikleyici kurduk yani
    fun observeLiveData() {
        viewModel.tags.observe(viewLifecycleOwner, Observer { tags ->
            tags.let {
                view?.findViewById<RecyclerView>(R.id.tagsRecyclerView)?.visibility = View.VISIBLE
                recyclerAdapter.updateTagList(tags)
            }
        })
    }
}