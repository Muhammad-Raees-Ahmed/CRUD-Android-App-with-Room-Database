package com.example.crudapproomdatabase.Model;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {

    private Context context;
    private static DatabaseClient databaseClientInstance;


    //our app database object
    private AppDatabase appDatabase;

    public DatabaseClient(Context context) {
        this.context = context;

//        creating the app database with Room database builder
//        MyToDos is the name of the database
        appDatabase =Room.databaseBuilder(context,AppDatabase.class,"MyToDos").build();
    }
    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (databaseClientInstance == null) {
            databaseClientInstance = new DatabaseClient(mCtx);
        }
        return databaseClientInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }


}

