package com.example.studyflow.viewmodel.tagViewModel

import android.app.Application
import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.studyflow.R
import com.example.studyflow.model.Tag
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.view.tagview.TagsFragment
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

/*      class TagViewModel : ViewModel(){ ==
        Bu ilk hali yani bu class bir view model

        ama coroutines scope interface özellikleri lazım bize

        class TagViewModel : ViewModel(), CoroutineScope{

        bu şekilde yazarak bu interface i de eklemiş oluruz ama bunun fonksiyonlarını tek tek override etmek lazım her sınıf için uğraşamayız
        bu yüzde bu fonksiyonları ovverride etmiş olan bir viewmodel class oluşturacağız bu classtan extend edeceğiz
        hem coroutines scope fonklarına ulaşacağız hem de bir viewmodel olacağız


*/

class TagViewModel(application: Application) : BaseViewModel(application) {
    val mutableTags = MutableLiveData<List<Tag>>()

    fun loadTagsFromDB() {

        launch {
            val dao = StudyFlowDB(getApplication()).tagDao()
            val currentTags = dao.getAllTag()
            mutableTags.value = currentTags
        }
    }

    fun storeTagToDB(tag: Tag) {
        // burada aslında direkt db ye veri gömebiliriz
        // ama coroutines sınıfları göz önünde bulundurmak lazım
        // şuanda bu class bir viewmodel class o yüzden coroutines funclarına ulaşamıyorum extend etmek lazım
        // onun yerine hem vievmodel hem de coroutines class özelliklerini taşıyan base bir class oluşturacağım
        // çünkü db ile etkileşimi sadece bu class yapmıcak. her seferinde tekrar tekrar yazmamak için bir süper class oluşturacağım


        launch {
            val dao = StudyFlowDB(getApplication()).tagDao()
            val id = dao.insertTag(tag)
            tag.uuid = id.toInt()
            val currentTags = mutableTags.value?.toMutableList() ?: mutableListOf()
            currentTags.add(tag)
            mutableTags.value = currentTags
        }
    }
}