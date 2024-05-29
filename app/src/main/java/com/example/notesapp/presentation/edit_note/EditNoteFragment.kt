package com.example.notesapp.presentation.edit_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.data.model.Note
import com.example.notesapp.databinding.FragmentEditNoteBinding
import com.example.notesapp.presentation.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NoteViewModel by viewModels()
    private val args: EditNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)

        binding.name.setText(args.editNote.name)
        binding.note.setText(args.editNote.note)

        binding.saveButton.setOnClickListener {
            val formattedDate = getCurrentFormattedDate()

            val note = Note(
                0,
                binding.nameInput.editText?.text.toString(),
                binding.noteInput.editText?.text.toString(),
                formattedDate
            )

            findNavController().navigate(R.id.action_editNoteFragment_to_noteListFragment)

            viewModel.updateNote(note)
        }

        return binding.root
    }

    private fun getCurrentFormattedDate(): String {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return dateFormat.format(currentDate)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}