package com.example.crudapproomdatabase.Model;



import androidx.room.Database;
import androidx.room.RoomDatabase;

// adding annotation for our database entities and db version.
@Database(entities = {Task.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDAO taskDAO();
}
