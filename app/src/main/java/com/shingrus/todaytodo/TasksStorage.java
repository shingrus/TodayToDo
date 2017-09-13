package com.shingrus.todaytodo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shingrus on 13/09/2017.
 */

public class TasksStorage {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ToDo.db";
    private static final String TASK_TABLE_NAME = "Task";
    public static final String TASK_COLUMN_ID = "_id";
    public static final String TASK_COLUMN_TITLE = "title";
    public static final String TASK_COLUMN_DATE_INSERTED = "inserted_at";
    public static final String TASK_COLUMN_STATUS = "status";
//    public static final String TASK_COLUMN_URL = "url";

    private TaskDBHelper taskDBHelper ;
    private static TasksStorage sInstance;


    private static final String CREATE_TASK_TABLE = "CREATE TABLE '" + TASK_TABLE_NAME + "' (" +
            "'_id' INTEGER PRIMARY KEY AUTOINCREMENT," +
            "'title' TEXT , " +
            "'inserted_at' INTEGER default (strftime('%s','now'))," +
            "'status' INTEGER default 0," +
            ")";


    private static final String INSERT_TASK_STMNT = "INSERT INTO " + TASK_TABLE_NAME + " (" +
            TASK_COLUMN_TITLE + ") VALUES(?)";

    private TasksStorage(Context ctx) {
        taskDBHelper = new TaskDBHelper(ctx);
    }


    public static synchronized TasksStorage getInstance() {
        return sInstance;
    }

    public static synchronized TasksStorage getInstance(Context context) {
        if (sInstance == null && context != null) {
            sInstance = new TasksStorage(context);
        }
        return sInstance;
    }

    class TaskDBHelper extends SQLiteOpenHelper {
        public TaskDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TASK_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            //Do nothing for now
        }
    }

}
