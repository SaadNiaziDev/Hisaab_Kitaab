package com.example.hisaab_kitaab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "khata";
    private static final int DB_VERSION = 1;
    private static final String PARENT_TABLE = "users";
    private static final String CHILD_TABLE = "records";
    private static final String ID_COL = "id";

    private static final String NAME_COL = "username";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";


    private static final String RECIPIENT_COL = "recipient";
    private static final String PARENT_COL = "parentID";
    private static final String AMOUNT_COL = "amount";
    private static final String DATE_COL = "date";
    private static final String TRANSACTION_COL = "type";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + PARENT_TABLE + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";
        String query2 = "CREATE TABLE " + CHILD_TABLE + " ("
                + RECIPIENT_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + AMOUNT_COL + " TEXT,"
                + TRANSACTION_COL + "TEXT,"
                + PARENT_COL +" TEXT,"
                + "FOREIGN KEY("+ PARENT_COL + ")" + "REFERENCES " +PARENT_TABLE+ "("+EMAIL_COL+")"+");";

        db.execSQL(query2);
    }

    public void registerUser(String username, String password, String email) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(NAME_COL, username);
        values.put(PASSWORD_COL, password);
        values.put(EMAIL_COL,email);
        db.insert(PARENT_TABLE, null, values);

        db.close();
    }

    public void addRecord(String recipient, String date, double amount, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RECIPIENT_COL,recipient);
        values.put(DATE_COL,date);
        values.put(AMOUNT_COL,amount);
        values.put(TRANSACTION_COL,type);
        db.insert(CHILD_TABLE, null, values);

        db.close();
    }


    public ArrayList<KhataModel> readRecords() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + CHILD_TABLE, null);

        ArrayList<KhataModel> courseModalArrayList = new ArrayList<>();

        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(new KhataModel(cursorCourses.getString(1),
                        cursorCourses.getString(3),
                        cursorCourses.getString(2),
                        cursorCourses.getString(4),
                        cursorCourses.getString(5)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return courseModalArrayList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + PARENT_TABLE);
        onCreate(db);
    }
}

