package com.example.notesapp.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNoteListBinding
import com.example.notesapp.presentation.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteListFragment : Fragment(R.layout.fragment_note_list) {
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NoteViewModel by viewModels()
    private val notesAdapter by lazy { NotesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupNavController()
        observeData()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = notesAdapter
    }

    private fun setupNavController() {
        binding.createNoteButton.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.noteList.collect {
                notesAdapter.setData(it)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            if (message != null) {
                binding.errorMessage.isVisible = true
                binding.errorMessage.text = message.asString(requireContext())
            } else {
                binding.errorMessage.isVisible = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}