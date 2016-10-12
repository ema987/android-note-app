package com.w3ma.noteapp.cache;

import com.w3ma.noteapp.datasource.local.db.Note;

/**
 * Created by Emanuele on 05/10/2016.
 */

public class NoteWithTime extends Note {

    private long lastRefreshTime;

    public long getLastRefreshTime() {
        return lastRefreshTime;
    }

    public void setLastRefreshTime(final long lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }

    public NoteWithTime(Note note) {
        setId(note.getId());
        setTitle(note.getTitle());
        setContent(note.getContent());
        setCreationDate(note.getCreationDate());
    }
}
