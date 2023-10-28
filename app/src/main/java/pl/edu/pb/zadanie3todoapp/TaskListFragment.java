package pl.edu.pb.zadanie3todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskListFragment extends Fragment {
    public static final String KEY_EXTRA_TASK_ID = "extra_task_id";

    private RecyclerView recyclerView;
    private TaskAdapter adapter;

    private void upadateView(){
        TaskStorage taskStorage = TaskStorage.getInstance();
        List<Task> tasks=taskStorage.getTasks();

        if(adapter==null){
        adapter=new TaskAdapter(tasks);
        recyclerView.setAdapter(adapter);}
        else {  adapter.notifyDataSetChanged();
        }
        }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        // Pobierz odwołanie do RecyclerView
        recyclerView = view.findViewById(R.id.task_recycler_view);

        // Tutaj możesz dodać ustawienia RecyclerView, takie jak LayoutManager, Adapter, itp.
recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView taskNameTextView;
        private TextView taskDateTextView;
        private Task task; // Store the current task associated with this holder


        @Override
        public void onClick(View view) {
            // Handle the item click event here
            Intent intent=new Intent(getActivity(),MainActivity.class);
            intent.putExtra(KEY_EXTRA_TASK_ID, task.getId());
            startActivity(intent);
        }
        public TaskHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_task,parent,false));

            taskNameTextView = itemView.findViewById(R.id.task_item_name);
            taskDateTextView = itemView.findViewById(R.id.task_item_date);

            // Set the click listener for the entire item view
            itemView.setOnClickListener(this);
        }
        public void bind(Task task) {
            // Bind the task's data to the view elements
            this.task = task; // Store the task associated with this holder
            taskNameTextView.setText(task.getName());
            taskDateTextView.setText(task.getDate().toString());
        }
    }
@Override
public void onResume(){
        super.onResume();
        upadateView();
}
    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder>{
        private List<Task> tasks;
@Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position){
    Task task=tasks.get(position);
    holder.bind(task);
}
        public TaskAdapter(List<Task> tasks){
            this.tasks=tasks;
        }
        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater,parent);
        }

        @Override
        public int getItemCount(){
            return tasks.size();
        }

    }
}
