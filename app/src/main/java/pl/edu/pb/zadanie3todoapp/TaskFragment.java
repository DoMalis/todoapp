package pl.edu.pb.zadanie3todoapp;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class TaskFragment extends Fragment {
    private static final String KEY_EXTRA_TASK_NAME = "extra_task_name";

    private static final String ARG_TASK_ID = "task_id";

    private EditText nameField;
    private Button addButton;
    private CheckBox doneCheckBox;
    private Task task;

    public static TaskFragment newInstance(UUID taksId){
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_TASK_ID, taksId);
        TaskFragment taskFragment=new TaskFragment();
        taskFragment.setArguments(bundle);
        return taskFragment;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        nameField = view.findViewById(R.id.task_name);
        doneCheckBox = view.findViewById(R.id.task_done);
        addButton = view.findViewById(R.id.task_date);

        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
task.setName(charSequence.toString());
                Intent resultIntent = new Intent();
                resultIntent.putExtra(KEY_EXTRA_TASK_NAME, task.getName()); // KEY_EXTRA_TASK_NAME to klucz
                getActivity().setResult(RESULT_OK, resultIntent);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
addButton.setText(task.getDate().toString());
addButton.setEnabled(false);


        doneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Aktualizuj stan zakończenia w obiekcie Task w czasie rzeczywistym
                task.setDone(isChecked);
            }
        });
        nameField.setText(task.getName());
        addButton.setText(task.getDate().toString());
        doneCheckBox.setChecked(task.isDone());
        return view;
    }


    public TaskFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
UUID taskId=(UUID) getArguments().getSerializable(ARG_TASK_ID);
        // Inicjalizacja obiektu Task
        task = TaskStorage.getInstance().getTask(taskId);

    }


}
