package com.w3ma.noteapp.notelist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.w3ma.noteapp.R;
import com.w3ma.noteapp.datasource.local.db.Note;
import com.w3ma.noteapp.ui.util.RecyclerViewOnClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Emanuele on 21/07/2016.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private final List<Note> noteList;
    private RecyclerViewOnClickListener<Note> recyclerViewOnClickListener;

    public NoteAdapter(final List<Note> noteList) {
        this.noteList = noteList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Note note = noteList.get(position);
        holder.title.setText(note.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                recyclerViewOnClickListener.onClick(note);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (noteList != null) {
            return noteList.size();
        }
        return 0;
    }

    public void setRecyclerViewOnClickListener(final RecyclerViewOnClickListener<Note> recyclerViewOnClickListener) {
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.note_title_text_view)
        TextView title;

        public ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
