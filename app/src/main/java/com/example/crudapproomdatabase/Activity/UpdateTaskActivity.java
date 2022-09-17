package com.example.crudapproomdatabase.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.crudapproomdatabase.Model.Task;
import com.example.crudapproomdatabase.R;
import com.example.crudapproomdatabase.databinding.ActivityAddTaskBinding;
import com.example.crudapproomdatabase.databinding.ActivityUpdateTaskBinding;

public class UpdateTaskActivity extends AppCompatActivity {

    ActivityUpdateTaskBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // view binding
        binding= ActivityUpdateTaskBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        Task task=(Task) getIntent().getSerializableExtra("task");

        loadTask(task);

        binding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                updateTask(task);
            }
        });

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTaskActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTask(task);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });
    }

    private void loadTask(Task task) {
    }

    private void deleteTask(Task task) {
    }

    private void updateTask(Task task) {
    }
}