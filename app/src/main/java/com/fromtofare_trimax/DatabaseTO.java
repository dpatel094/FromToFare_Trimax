package com.fromtofare_trimax;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTO extends SQLiteOpenHelper {
    private static final String DBName = "FareTable";
    private static final int Dbversion = 1;
    private static final String TABLE_NAME_Station = "StationName";
    private static final String TABLE_NAME_Station_Fare = "StationFare";
    private static final String IDs = "Ids";
    private static final String Station = "Station_Name";
    private static final String TicketFare = "Station_Fare";


    public DatabaseTO(Context context) {
        super(context, DBName, null, Dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String executeTb = "CREATE TABLE " + TABLE_NAME_Station + " ("
                + IDs + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Station + " TEXT)";

        db.execSQL(executeTb);

        String executeTbFare = "CREATE TABLE " + TABLE_NAME_Station_Fare + " ("
                + IDs + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TicketFare + " INTEGER)";

        db.execSQL(executeTbFare);
    }

    public void AddBaseFare(Integer fare) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TicketFare, fare);

        db.insert(TABLE_NAME_Station_Fare, null, values);
        db.close();
    }

    public void AddStation(String StationName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Station, StationName);

        db.insert(TABLE_NAME_Station, null, values);
        db.close();
    }

    public long numberofstation() {
        SQLiteDatabase db = this.getWritableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME_Station);
        db.close();
        return count;
    }

    public Integer getbasefare() {
        Integer basefare = 0;
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_Station_Fare;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            Log.i("Nodata", "Nodate");
        }
        while (cursor.moveToNext()) {
            basefare = Integer.parseInt(cursor.getString(1));
        }
        return basefare;
    }

    public List<StationFareModel> getStation() {
        List<StationFareModel> stationFareModels = new ArrayList<StationFareModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME_Station;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            Log.i("Nodata", "Nodate");
        }
        while (cursor.moveToNext()) {
            StationFareModel stationFareModel = new StationFareModel();
            stationFareModel.setStationName(cursor.getString(1));
            stationFareModel.setiDs(Integer.parseInt(cursor.getString(0)));
            stationFareModels.add(stationFareModel);
        }

        return stationFareModels;
    }

    public StationFareModel getfare(int fareId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_Station, new String[]{IDs,
                        Station, TicketFare}, IDs + "=?",
                new String[]{String.valueOf(fareId)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        StationFareModel stationFareModel = new StationFareModel();
        stationFareModel.setStationName(cursor.getString(1));
        stationFareModel.setiDs(Integer.parseInt(cursor.getString(0)));
        //stationFareModels.add(stationFareModel);

        return stationFareModel;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_Station);
        onCreate(db);
    }
}
