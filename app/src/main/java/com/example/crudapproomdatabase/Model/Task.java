package com.example.crudapproomdatabase.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// this is our table

@Entity(tableName = "task")
public class Task implements Serializable {

    // these variables are columns in Room db

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="task")
    private String task;

    @ColumnInfo(name="desc")
    private String desc;

    @ColumnInfo(name="finish_by")
    private String finishBy;

    @ColumnInfo(name="finished")
    private boolean finished;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFinishBy() {
        return finishBy;
    }

    public void setFinishBy(String finishBy) {
        this.finishBy = finishBy;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
