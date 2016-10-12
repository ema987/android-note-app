package com.w3ma.noteapp.network.model.user;

import java.util.Date;

/**
 * Created by Emanuele on 21/07/2016.
 */
public class UserInfo {

    private String name;
    private String avatarLink;
    private Date lastLoginDate;

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

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(final Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
