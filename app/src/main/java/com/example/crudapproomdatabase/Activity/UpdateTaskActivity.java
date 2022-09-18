package com.example.crudapproomdatabase.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudapproomdatabase.Model.DatabaseClient;
import com.example.crudapproomdatabase.Model.Task;
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

        binding.editTextTask.setText(task.getTask());
        binding.editTextDesc.setText(task.getDesc());
        binding.editTextFinishBy.setText(task.getFinishBy());
        binding.checkBoxFinished.setChecked(task.isFinished());

    }

    private void deleteTask(Task task) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDAO()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this, MainActivity.class));
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();
    }

    private void updateTask(Task task) {
        String sTask = binding.editTextTask.getText().toString().trim();
        String sDesc = binding.editTextDesc.getText().toString().trim();
        String sFinishBy = binding.editTextFinishBy.getText().toString().trim();

        if (sTask.isEmpty()){
            binding.editTextTask.setError("Task Required");
            binding.editTextTask.requestFocus();
            return;
        }
        if (sDesc.isEmpty()){
            binding.editTextDesc.setError("Description Required");
            binding.editTextDesc.requestFocus();
            return;
        }
        if (sFinishBy.isEmpty()){
            binding.editTextFinishBy.setError("Finish By  Required");
            binding.editTextFinishBy.requestFocus();
            return;
        }
        class UpdateTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                Task task1=new Task();
                task1.setTask(sTask);
                task1.setDesc(sDesc);
                task1.setFinishBy(sFinishBy);
                task1.setFinished(binding.checkBoxFinished.isChecked());
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this, MainActivity.class));
            }
        }
        UpdateTask ut = new UpdateTask();
        ut.execute();
    }
}