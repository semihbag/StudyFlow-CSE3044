package com.example.studyflow.adapter.tag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyflow.R
import com.example.studyflow.databinding.TagRowBinding
import com.example.studyflow.interfaces.tag.TagFragmentClickListener
import com.example.studyflow.interfaces.tag.TagRecyclerAdapterClickListener
import com.example.studyflow.model.Tag

class TagRecyclerAdapter(private val tagList: ArrayList<Tag>, private val listenerFragment : TagFragmentClickListener) : RecyclerView.Adapter<TagRecyclerAdapter.TagViewHolder>(), TagRecyclerAdapterClickListener {

    // create class
    class TagViewHolder(var view: TagRowBinding) : RecyclerView.ViewHolder(view.root) {
    }

    // override functions (came from RecyclerView.Adapter)
    // bu fonksiyon aslında RecyclerView.Adapter classsının fonksiyonu ve onu override etmemiz lazım
    // mantık olarak bu bir holder ise biizm yazdığımız holder layoutuna (tagı tutan xml - front) göre uyumlu hale getirmek lazım
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        // bu databinding olmadan önce kullanılan çağırma yöntemi idi
        //val view = inflater.inflate(R.layout.tag_row,parent,false)
        val view = DataBindingUtil.inflate<TagRowBinding>(inflater, R.layout.tag_row, parent, false)        // burda yazdığım layout ile bağlantısını kuruyorum artık holder neye benzeyeceğini biliyor
        return TagViewHolder(view)
    }

    // sanırım bunu arkaplanda otomatik kendi kullanıyor 
    override fun getItemCount(): Int {
        return tagList.size
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        // holder aslında bi TagViewHolder yani tek bir tagı tutan container adının holder olmasına gerek yok ama mantıklı olan bu
        // ayrıca bu TagViewHolder i da yukarda kendim tanımladım
        holder.view.tag = tagList[position]
        holder.view.listenerAdapter = this
        holder.view.listenerFragment = listenerFragment
        holder.view.tagTittle.isSelected = true


        // yukadaki listener aslında bir interface. bu sınıf o interfaceden extend edildiği için this yazabilirim. üzerinde tıklama işini yapacak
        // clickTag fonksiyonu da zaten bunun için overrride edildi (aşağıda)


    }

    fun updateTagList(newTagList: List<Tag>) {
        tagList.clear()
        tagList.addAll(newTagList)
        notifyDataSetChanged()
    }

    override fun clickTag(view: View) {
        val binding = DataBindingUtil.findBinding<TagRowBinding>(view)
        binding?.let {
            it.gridLayout.visibility = if (it.gridLayout.visibility == View.GONE) View.VISIBLE else View.GONE
        }
    }
}