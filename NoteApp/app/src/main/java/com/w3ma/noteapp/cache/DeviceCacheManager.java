package com.w3ma.noteapp.cache;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.common.reflect.TypeToken;
import com.w3ma.noteapp.business.util.Constants;
import com.w3ma.noteapp.business.util.GsonUtil;
import com.w3ma.noteapp.datasource.local.db.Note;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emanuele on 05/10/2016.
 */

public class DeviceCacheManager {

    public Map<Long, NoteWithTime> noteWithTimeSparseArray;
    protected Context context;
    protected GsonUtil gsonUtil;

    public DeviceCacheManager(final Context context, final GsonUtil gsonUtil) {
        this.context = context;
        this.gsonUtil = gsonUtil;

        final File cacheNoteMapFile = new File(context.getExternalCacheDir() + Constants.CACHE_NOTE_MAP_FILE);
        if (cacheNoteMapFile.exists()) {
            try {
                final BufferedReader reader = new BufferedReader(new FileReader(cacheNoteMapFile));
                final StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                reader.close();
                noteWithTimeSparseArray = gsonUtil.toObject(stringBuilder.toString(), new TypeToken<Map<Long, NoteWithTime>>() {
                }.getType());
            } catch (final IOException e) {
                //nothing
            }
        }
    }

    public long getNoteLastServerHitTime(final Long id) {
        if (noteWithTimeSparseArray != null) {
            final NoteWithTime noteWithTime = noteWithTimeSparseArray.get(id);
            if (noteWithTime != null) {
                return noteWithTime.getLastRefreshTime();
            }
        }
        return 0;
    }

    @SuppressLint("all")
    public void setNoteLastServerHitTime(final Note note) {
        if (noteWithTimeSparseArray == null) {
            noteWithTimeSparseArray = new HashMap<>();
        }
        final NoteWithTime noteWithTime = new NoteWithTime(note);
        noteWithTime.setLastRefreshTime(System.currentTimeMillis());
        noteWithTimeSparseArray.put(note.getId(), noteWithTime);
        Writer writer = null;
        try {
            writer = new FileWriter(context.getExternalCacheDir() + Constants.CACHE_NOTE_MAP_FILE);
            gsonUtil.getGson().toJson(noteWithTimeSparseArray, writer);
        } catch (final IOException e) {
            //nothing
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (final IOException e) {
                    //nothing
                }
            }
        }
    }
}
