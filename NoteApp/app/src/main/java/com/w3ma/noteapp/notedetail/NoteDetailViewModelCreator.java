package com.w3ma.noteapp.notedetail;


import com.w3ma.noteapp.datasource.local.db.Note;

import java.text.DateFormat;

/**
 * Created by Emanuele on 25/07/2016.
 */
public class NoteDetailViewModelCreator {

    private final DateFormat dateFormat = DateFormat.getDateTimeInstance();

    public NoteDetailViewModel createViewModel(final Note note) {
        final NoteDetailViewModel noteDetailViewModel = new NoteDetailViewModel();
        noteDetailViewModel.setId(note.getId());
        noteDetailViewModel.setContent(note.getContent());
        noteDetailViewModel.setTitle(note.getTitle());
        if (note.getCreationDate() != null) {
            noteDetailViewModel.setCreationDate(dateFormat.format(note.getCreationDate()));
        }
        return noteDetailViewModel;
    }

}
