package com.w3ma.noteapp.notedetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.w3ma.noteapp.R;
import com.w3ma.noteapp.business.util.Constants;
import com.w3ma.noteapp.config.NoteListApplication;
import com.w3ma.noteapp.notelist.NoteListActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A fragment representing a single Note detail screen.
 * This fragment is either contained in a {@link NoteListActivity}
 * in two-pane mode (on tablets) or a {@link NoteDetailActivity}
 * on handsets.
 */
public class NoteDetailFragment extends Fragment implements NoteDetailContract.View {

    @Bind(R.id.note_title_text_view)
    TextView noteTitleTextView;
    @Bind(R.id.note_content_text_view)
    TextView noteContentTextView;
    @Bind(R.id.creation_date_text_view)
    TextView creationDateTextView;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Inject
    NoteDetailContract.Presenter noteDetailPresenter;
    private FragmentCallbacks activity;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteDetailFragment() {
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        try {
            activity = (FragmentCallbacks) context; //this is to show how to use fragment callbacks, to set the title we could have just used the activity method
        } catch (final ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentCallbacks interface");
        }
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerNoteDetailComponent.builder().applicationComponent(((NoteListApplication) getActivity().getApplication()).getApplicationComponent())
                .noteDetailModule(new NoteDetailModule(this)).build().inject(this);

        noteDetailPresenter.onCreate();

        if (getArguments().containsKey(Constants.KEY_NOTE_ID)) {
            final Long noteId = getArguments().getLong(Constants.KEY_NOTE_ID);
            noteDetailPresenter.setNote(noteId);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_note_details, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        noteDetailPresenter.onResume();
    }

    @Override
    public void showDetails(final NoteDetailViewModel noteDetailViewModel) {
        noteTitleTextView.setText(noteDetailViewModel.getTitle());
        noteContentTextView.setText(noteDetailViewModel.getContent());
        creationDateTextView.setText(noteDetailViewModel.getCreationDate());
    }

    @Override
    public void setTitle(final String name) {
        activity.setCustomTitle(name);
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public interface FragmentCallbacks {

        void setCustomTitle(String title);
    }
}
