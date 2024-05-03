package com.example.studyflow.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/*  bu sınıfı coroutines scope özelliklerinden faydalanmak için yazdık
    aynı zamanda bu sınıf bir viewmodel sınıfı

    neden ViewModel extend etmedik de AndroidViewModel extend ettik peki?
    aslında AndroidViewModel da ViewModelin bir üst sınıfı
    biz database e uygulamaını sadece tek bir fragmentinden ulaşmıyoruz her fragmentinden ulaşılabilir olması daha mantıklı
    o yüzden bize application contex lazım
    bu yüzde AndroidViewModel sınıfından extend ettik her yerde ulaşabilmek için

    AndroidViewModel extend ettikten sonra ",CoroutineScope" yazdık o interface i de ekledik onun abstract fonklarnı da geliştireceğiz

*/

open class BaseViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}