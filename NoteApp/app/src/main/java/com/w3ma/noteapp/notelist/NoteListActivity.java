package com.w3ma.noteapp.notelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.w3ma.noteapp.R;
import com.w3ma.noteapp.business.util.Constants;
import com.w3ma.noteapp.business.util.GsonUtil;
import com.w3ma.noteapp.config.NoteListApplication;
import com.w3ma.noteapp.datasource.local.db.Note;
import com.w3ma.noteapp.notedetail.NoteDetailActivity;
import com.w3ma.noteapp.notedetail.NoteDetailFragment;
import com.w3ma.noteapp.ui.customview.userinfoviewheader.UserInfoHeaderView;
import com.w3ma.noteapp.ui.util.RecyclerViewOnClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * An activity representing a list of Notes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link NoteDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class NoteListActivity extends AppCompatActivity implements NoteListContract.View, NoteDetailFragment.FragmentCallbacks {

    @Inject
    protected NoteListContract.Presenter noteListPresenter;
    @Inject
    protected GsonUtil gsonUtil;
    @Bind(R.id.note_list)
    RecyclerView noteListRecyclerView;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.user_info_header_view)
    UserInfoHeaderView userInfoHeaderView;
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        ButterKnife.bind(this);
        DaggerNoteListComponent.builder().applicationComponent(((NoteListApplication) getApplication()).getApplicationComponent())
                .noteListModule(new NoteListModule(this)).build().inject(this);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.note_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        noteListPresenter.onCreate();

    }

    @Override
    protected void onResume() {
        super.onResume();
        noteListPresenter.onResume();
    }

    @Override
    public void setNoteList(final List<Note> noteList) {
        noteListRecyclerView.setAdapter(new NoteAdapter(noteList));
        noteListRecyclerView.getAdapter().notifyDataSetChanged();
        ((NoteAdapter) noteListRecyclerView.getAdapter()).setRecyclerViewOnClickListener(new RecyclerViewOnClickListener<Note>() {
            @Override
            public void onClick(final Note note) {
                noteListPresenter.onListItemClick(note);
            }
        });
    }

    @Override
    public void showNoteDetails(final Note note) {
        final Bundle arguments = new Bundle();
        arguments.putLong(Constants.KEY_NOTE_ID, note.getId());
        if (mTwoPane) {
            final NoteDetailFragment fragment = new NoteDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.note_detail_container, fragment)
                    .commit();
        } else {
            final Intent intent = new Intent(NoteListActivity.this, NoteDetailActivity.class);
            intent.putExtras(arguments);
            startActivity(intent);
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(final String message) {
        Toast.makeText(NoteListActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCustomTitle(final String title) {
        //do nothing
    }
}
