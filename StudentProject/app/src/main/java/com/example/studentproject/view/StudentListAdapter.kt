package com.example.studentproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.studentproject.databinding.StudentCardItemBinding
import com.example.studentproject.model.Student

class StudentListAdapter(val studentList:ArrayList<Student>)
    : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder {
        val binding = StudentCardItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)

    }

    override fun onBindViewHolder(
        holder: StudentViewHolder,
        position: Int
    ) {
        holder.binding.txtNrp.text = studentList[position].id
        holder.binding.txtNrp.text = studentList[position].name
        holder.binding.btnDetail.setOnClickListener {
            val student = studentList[position]
            val action = StudentListFragmentDirections.actionStudentDetailFragment(student)
            it.findNavController().navigate(action)

        }
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }


    override fun getItemCount() = studentList.size

    class StudentViewHolder(var binding: StudentCardItemBinding)
        :RecyclerView.ViewHolder(binding.root)


}


