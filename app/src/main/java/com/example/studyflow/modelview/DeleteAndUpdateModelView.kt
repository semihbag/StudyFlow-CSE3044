package com.example.studyflow.modelview

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyflow.R
import com.example.studyflow.model.ToDoPlan

class DeleteAndUpdateModelView: ViewModel() {

    var toDoPlan = MutableLiveData<ToDoPlan>()

    fun initializePlan(view: View, id: Int) {

        if (id != -1) {
            view.context?.let {
                val database = it.openOrCreateDatabase("StudyFlow", Context.MODE_PRIVATE,null)
                val cursor = database.rawQuery("SELECT * FROM toDoPlans WHERE id = $id",null)
                val planIndex = cursor.getColumnIndex("toDoPlan")
                val checkedIndex = cursor.getColumnIndex("is_checked")
                val indicesIndex = cursor.getColumnIndex("id")

                while (cursor.moveToNext()) {
                    toDoPlan.value = ToDoPlan(cursor.getInt(indicesIndex),cursor.getString(planIndex),cursor.getInt(checkedIndex))
                }
                cursor.close()
            }
        }
    }

    fun updatePlan(view: View, id: Int) {

        view.context?.let {
            val database = view.context.openOrCreateDatabase("StudyFlow", Context.MODE_PRIVATE,null)
            toDoPlan.value?.let {
                it.plan = view.findViewById<EditText>(R.id.toDoPlanUD).text.toString()
                if (it.id != -1) {
                    val sqlString = "UPDATE toDoPlans SET toDoPlan = ? WHERE id = ?"
                    val statement = database.compileStatement(sqlString)
                    statement.bindString(1,it.plan)
                    statement.bindString(2,it.id.toString())
                    statement.execute()
                    Toast.makeText(view.context,"The Update Operation is successful", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(view.context,"The Update Operation is not successful", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun deletePlan(view: View, id: Int) {
        view.context?.let {
            val database = view.context.openOrCreateDatabase("StudyFlow", Context.MODE_PRIVATE,null)
            toDoPlan.value?.let {
                if (it.id != -1) {
                    val builder = AlertDialog.Builder(view.context)
                    builder.setTitle("Delete " + it.plan + " ?")
                    builder.setMessage("Are you sure want to delete " + it.plan + " ?")
                    builder.setPositiveButton("Yes") { _, _ ->
                        val sqlString = "DELETE FROM toDoPlans  WHERE id = ?"
                        val statement = database.compileStatement(sqlString)
                        statement.bindString(1,it.id.toString())
                        statement.execute()
                        Toast.makeText(view.context,"The Delete Operation is successful",Toast.LENGTH_SHORT).show()
                    }
                    builder.setNegativeButton("No") { _, _ ->
                    }
                    builder.show()
                }
                else {
                    Toast.makeText(view.context,"The Delete Operation is not successful",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}