package com.example.studentproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentproject.R
import com.example.studentproject.databinding.FragmentStudentListBinding
import com.example.studentproject.viewmodel.ListViewModel
import androidx.lifecycle.ViewModel
import com.example.studentproject.view.StudentListAdapter

class StudentListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var studentListAdapter: StudentListAdapter

    private lateinit var binding: FragmentStudentListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.recViewStudent.layoutManager = LinearLayoutManager(context)
        binding.recViewStudent.adapter = studentListAdapter

        observeViewModel()

//        swipe refresh handler
        binding.swipeRefresh.setOnRefreshListener {
//            binding.recViewStudent.visibility = View.GONE
//            binding.txtError.visibility = View.GONE
//            binding.progressLoad.visibility = View.VISIBLE
//            3 Line code diatas tidak perlu karena sudah ada di dalam fungsi refresh di bawah
            viewModel.refresh()
        }

    }

    fun observeViewModel() {
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateStudentList(it)
            binding.swipeRefresh.isRefreshing = false

        })

//        observe error LD
        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recViewStudent.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recViewStudent.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })

    }


}
