package com.example.crudapproomdatabase.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudapproomdatabase.Adapter.TaskAdapter;
import com.example.crudapproomdatabase.Model.DatabaseClient;
import com.example.crudapproomdatabase.Model.Task;
import com.example.crudapproomdatabase.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddTask;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAddTask = findViewById(R.id.floating_button_add);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });


        getTasks();
    }

    private void getTasks() {

        class GetTasks extends AsyncTask<Void, Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDAO()
                        .getAll();
                System.out.println("tasks : "+taskList);
                return taskList;
            }


            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);

                TaskAdapter taskAdapter=new TaskAdapter(MainActivity.this,tasks);
                recyclerView.setAdapter(taskAdapter);
            }
        }
        GetTasks getTasks=new GetTasks();
        getTasks.execute();


    }

}