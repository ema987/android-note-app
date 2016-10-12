package com.w3ma.noteapp.ui.customview.userinfoviewheader;

/**
 * Created by Emanuele on 26/07/2016.
 */
public class UserInfoViewModel {

    private String name;
    private String avatarLink;
    private String lastLoginDate;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(final String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(final String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
