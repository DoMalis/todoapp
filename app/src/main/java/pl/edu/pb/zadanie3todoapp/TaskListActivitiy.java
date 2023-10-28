package pl.edu.pb.zadanie3todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;

import android.os.Bundle;

public class TaskListActivitiy extends SingleFragmentAbstracct {

    public static final String KEY_EXTRA_TASK_NAME = "extra_task_name";
    public static final int REQUEST_UPDATE_TASK = 1; // Define an appropriate request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_UPDATE_TASK) {
                if (data != null && data.hasExtra(KEY_EXTRA_TASK_NAME)) {
                    String updatedTaskName = data.getStringExtra(KEY_EXTRA_TASK_NAME);
                    // Tutaj zaktualizuj widok zadania na liście z wykorzystaniem nowej nazwy
                }
            }
        }
    }


        @Override
        protected Fragment createFragment() {
        return new TaskListFragment();
    }
}