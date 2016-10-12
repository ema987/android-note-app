package com.w3ma.noteappgreendaogenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerator {

    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");
    private static final int DB_VERSION = 1;
    private static final String DB_MODEL_PACKAGE = "com.w3ma.noteapp.datasource.local.db";
    private static final String NOTE_ENTITY_NAME = "Note";
    private static final String NOTE_TITLE_FIELD = "title";
    private static final String NOTE_CONTENT_FIELD = "content";
    private static final String NOTE_CREATION_DATE_FIELD = "creationDate";

    public static void main(final String[] args) {
        final Schema schema = new Schema(DB_VERSION, DB_MODEL_PACKAGE);
        schema.enableKeepSectionsByDefault();

        addNoteTable(schema);

        try {
            new DaoGenerator().generateAll(schema, PROJECT_DIR + "/app/src/main/java");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private static Entity addNoteTable(final Schema schema) {
        final Entity noteEntity = schema.addEntity(NOTE_ENTITY_NAME);
        noteEntity.addIdProperty().primaryKey().autoincrement();
        noteEntity.addStringProperty(NOTE_TITLE_FIELD).notNull();
        noteEntity.addStringProperty(NOTE_CONTENT_FIELD);
        noteEntity.addDateProperty(NOTE_CREATION_DATE_FIELD);
        return noteEntity;
    }

}
