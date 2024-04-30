package com.example.studyflow.view.toDoView

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.studyflow.R
import androidx.navigation.Navigation



class DeleteAndUpdatePlanFragment : Fragment() {

    private lateinit var planText: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_and_update_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        planText = view.findViewById(R.id.toDoPlanUD)
        updateButton = view.findViewById(R.id.updateButton)
        deleteButton = view.findViewById(R.id.deleteButton)
        deleteButton.visibility = View.VISIBLE
        updateButton.visibility = View.VISIBLE
        var index = -1

        arguments?.let {
            planText.setText(DeleteAndUpdatePlanFragmentArgs.fromBundle(it).plan)
            index = DeleteAndUpdatePlanFragmentArgs.fromBundle(it).index
        }

        updateButton.setOnClickListener {
            val database = it.context.openOrCreateDatabase("StudyFlow", Context.MODE_PRIVATE,null)
            if (index != -1) {
                val sqlString = "UPDATE toDoPlans SET toDoPlan = ? WHERE id = ?"
                val statement = database.compileStatement(sqlString)
                statement.bindString(1,planText.text.toString())
                statement.bindString(2,index.toString())
                statement.execute()
                Toast.makeText(it.context,"The Update Operation is successful",Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(it.context,"The Update Operation is not successful",Toast.LENGTH_SHORT).show()
            }

        }
        deleteButton.setOnClickListener {
            val database = it.context.openOrCreateDatabase("StudyFlow", Context.MODE_PRIVATE,null)
            if (index != -1) {
                val builder = AlertDialog.Builder(it.context)
                builder.setTitle("Delete " + planText.text.toString() + " ?")
                builder.setMessage("Are you sure want to delete " + planText.text.toString() + " ?")
                builder.setPositiveButton("Yes") { _, _ ->
                    val sqlString = "DELETE FROM toDoPlans  WHERE id = ?"
                    val statement = database.compileStatement(sqlString)
                    statement.bindString(1,index.toString())
                    statement.execute()
                    deleteButton.visibility = View.INVISIBLE
                    updateButton.visibility = View.INVISIBLE
                    Toast.makeText(it.context,"The Delete Operation is successful",Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No") { _, _ ->
                }
                builder.show()
            }
            else {
                Toast.makeText(it.context,"The Delete Operation is not successful",Toast.LENGTH_SHORT).show()
            }
        }
    }
}