package com.example.notesapp.presentation.detail_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentDetailNoteBinding
import com.example.notesapp.presentation.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailNoteFragment : Fragment() {
    private var _binding: FragmentDetailNoteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NoteViewModel by viewModels()
    private val args: DetailNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailNoteBinding.inflate(inflater, container, false)

        val note = args.detailNote
        binding.nameDetail.text = note.name
        binding.noteDetail.text = note.note

        binding.editButton.setOnClickListener {
            val action = DetailNoteFragmentDirections.actionDetailNoteFragmentToEditNoteFragment(note)
            findNavController().navigate(action)
        }

        binding.deleteButton.setOnClickListener {
            findNavController().navigate(R.id.action_detailNoteFragment_to_noteListFragment)
            viewModel.deleteNote(note)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}