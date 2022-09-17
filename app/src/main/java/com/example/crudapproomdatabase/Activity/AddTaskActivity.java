package com.example.crudapproomdatabase.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudapproomdatabase.Model.DatabaseClient;
import com.example.crudapproomdatabase.Model.Task;
import com.example.crudapproomdatabase.R;
import com.example.crudapproomdatabase.databinding.ActivityAddTaskBinding;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTask, editTextDesc, editTextFinishBy;
    ActivityAddTaskBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // view binding
        binding=ActivityAddTaskBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });

    }

    private void saveTask() {
        String sTask=binding.editTextTask.getText().toString().trim();
        String sDesc=binding.editTextDesc.getText().toString().trim();
        String sFinishBy=binding.editTextFinishBy.getText().toString().trim();

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
        class  SaveTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Task task=new Task();
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(false);

//adding to database
                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDAO()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        }
        SaveTask st = new SaveTask();
        st.execute();
    }



}