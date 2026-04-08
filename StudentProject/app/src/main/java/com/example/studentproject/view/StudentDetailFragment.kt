package com.example.studentproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.studentproject.R
import com.example.studentproject.databinding.FragmentStudentDetailBinding
import com.example.studentproject.databinding.FragmentStudentListBinding
import com.example.studentproject.viewmodel.DetailViewModel
import com.example.studentproject.viewmodel.ListViewModel
import java.util.Observer


class StudentDetailFragment : Fragment() {
    private lateinit var binding: FragmentStudentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        get student from argument
        val student = StudentDetailFragmentArgs.fromBundle(requireArguments()).student

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(student.id)

        observeViewModel()
    }

    fun observeViewModel() {
//        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
//            binding.txtName.setText(it.name)
//            binding.txtNrp.setText(it.id)
//            binding.txtBod.setText(it.bod)
//            binding.txtPhone.setText(it.phone)
//        })
    }


}