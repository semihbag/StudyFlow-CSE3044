package com.example.studyflow.viewmodel.pomodoro

import android.app.Application
import android.os.CountDownTimer
import android.renderscript.ScriptGroup.Binding
import android.view.View
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat.FocusDirection
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.studyflow.R
import com.example.studyflow.databinding.FragmentPomodoroBinding
import com.example.studyflow.model.Pomodoro
import com.example.studyflow.service.StudyFlowDB
import com.example.studyflow.view.FocusFragment
import com.example.studyflow.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*
open class PomodoroViewModel(application: Application) : BaseViewModel(application) {


    var pomodoroID = MutableLiveData<Int>()


    // Insert new Pomodoro Item to the Database
    fun insertPomodoro(pomodoro: Pomodoro){
        launch {
            val dao = StudyFlowDB(getApplication()).pomodoroDao()
            val id = dao.insertPomodoro(pomodoro)
            if (pomodoro.tagId != -1){
                dao.increasePomodorCount(pomodoro.tagId)
                dao.increaseFocusedMinute(pomodoro.pomodoroTime.toInt() / 60000, pomodoro.tagId)
                dao.increaseOutOfFocusedMinute(pomodoro.inactiveTime.toInt() / 60000, pomodoro.tagId)
                if (pomodoro.isFinished == 0){
                    dao.increaseStopCount(pomodoro.tagId)
                }
            }
            pomodoro.uuid = id.toInt()
            pomodoroID.value = pomodoro.uuid
        }
    }

    fun updatePomodoro(breakTimeInMills: Long, pomodoro_id: Int) {
        launch {
            val dao = StudyFlowDB(getApplication()).pomodoroDao()
            dao.updateBreakTime(breakTimeInMills,pomodoro_id)
        }
    }
}