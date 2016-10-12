package com.w3ma.noteapp.ui.customview.userinfoviewheader;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.w3ma.noteapp.R;
import com.w3ma.noteapp.config.NoteListApplication;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Emanuele on 26/07/2016.
 */
public class UserInfoHeaderView extends FrameLayout implements UserInfoHeaderContract.View {

    @Bind(R.id.user_name_text_view)
    TextView userNameTextView;
    @Bind(R.id.last_login_text_view)
    TextView lastLoginTextView;
    @Bind(R.id.user_avatar_image_view)
    ImageView userAvatarImageView;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    UserInfoHeaderContract.Presenter userInfoHeaderPresenter;

    private UserInfoViewModel userInfoViewModel;

    public UserInfoHeaderView(final Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public UserInfoHeaderView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public UserInfoHeaderView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UserInfoHeaderView(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Initialise views.
     */
    private void init(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_user_info_header, this);
        ButterKnife.bind(this);
        setCustomAttributes(context, attrs, defStyleAttr, defStyleRes);
        DaggerUserInfoHeaderComponent.builder().applicationComponent(((NoteListApplication) context.getApplicationContext()).getApplicationComponent())
                .userInfoHeaderModule(new UserInfoHeaderModule(this)).build().inject(this);
        userInfoHeaderPresenter.onResume();
    }

    private void setCustomAttributes(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        if (attrs != null) {
            final TypedArray typedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.UserInfoHeaderView, defStyleAttr, defStyleRes);

            final boolean lastLoginDateVisible = typedArray.getBoolean(R.styleable.UserInfoHeaderView_lastLoginDateVisible, true);
            if (!lastLoginDateVisible) {
                lastLoginTextView.setVisibility(View.GONE);
            }

            typedArray.recycle();
        }
    }

    private void updateView() {
        userNameTextView.setText(userInfoViewModel.getName());
        lastLoginTextView.setText(userInfoViewModel.getLastLoginDate());
        Picasso.with(getContext()).load(userInfoViewModel.getAvatarLink()).into(userAvatarImageView);
    }

    @Override
    public void showUserInfo(final UserInfoViewModel userInfoViewModel) {
        this.userInfoViewModel = userInfoViewModel;
        updateView();
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

    }
}
