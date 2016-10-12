package com.w3ma.noteapp.notedetail;

/**
 * Created by Emanuele on 25/07/2016.
 */
public class NoteDetailViewModel {

    private Long id;
    private String title;
    private String content;
    private String creationDate;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final String creationDate) {
        this.creationDate = creationDate;
    }
}
