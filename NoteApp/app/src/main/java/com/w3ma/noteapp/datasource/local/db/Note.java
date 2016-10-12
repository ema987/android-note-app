package com.w3ma.noteapp.datasource.local.db;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

import java.util.Date;

/**
 * Entity mapped to table "NOTE".
 */
public class Note {

    private Long id;
    /** Not-null value. */
    private String title;
    private String content;
    private java.util.Date creationDate;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Note() {
    }

    public Note(Long id) {
        this.id = id;
    }

    public Note(Long id, String title, String content, java.util.Date creationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.util.Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(java.util.Date creationDate) {
        this.creationDate = creationDate;
    }

    // KEEP METHODS - put your custom methods here

    public static final class Builder {
        private String title;
        private String content;
        private Date creationDate;

        private Builder() {
        }

        public Builder title(final String title) {
            this.title = title;
            return this;
        }

        public Builder content(final String content) {
            this.content = content;
            return this;
        }

        public Builder creationDate(final Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Note build() {
            return new Note(this);
        }
    }

    public Note(Builder builder) {
        title = builder.title;
        content = builder.content;
        creationDate = builder.creationDate;
    }
    // KEEP METHODS END

}