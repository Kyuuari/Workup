package sheridan.caric.project.ui.run;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sheridan.caric.project.R;
import sheridan.caric.project.db.Pushup;
import sheridan.caric.project.db.Run;
import sheridan.caric.project.ui.RunListAdapter;

public class RunFragment extends Fragment {

    private RunViewModel runViewModel;
    private RecyclerView recyclerView;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            RunListAdapter.RunViewHolder viewHolder = (RunListAdapter.RunViewHolder) v.getTag();
            long position = viewHolder.getAdapterPosition();
            long a = viewHolder.getRun().getId();
            Run r = viewHolder.getRun();

            openEditDialog(r);
            //pushupViewModel.deleteById();
            //viewHolder.getItemId();
            //viewHolder.getItemViewType();
            //viewHolder.itemView;
            //TestItem thisItem = mTestItemList.get(position)
            //Toast.makeText(getContext(), "You Clicked: " + position, Toast.LENGTH_SHORT).show();

        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        runViewModel =
                ViewModelProviders.of(this).get(RunViewModel.class);
        View view = inflater.inflate(R.layout.fragment_run, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);

        final RunListAdapter adapter = new RunListAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(onItemClickListener);

        runViewModel.getAllRuns().observe(this, new Observer<List<Run>>() {
            @Override
            public void onChanged(@Nullable final List<Run> runs) {
                // Update the cached copy of the words in the adapter.
                adapter.setRuns(runs);
            }
        });


        return view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                openAddDialog();
                return true;
            case R.id.menu_deleteAll:
                runViewModel.deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openEditDialog(Run run) {
        FragmentManager manager = ((AppCompatActivity) this.getContext()).getSupportFragmentManager();
        DialogFragment frag = new RunEditDialog(run);
        frag.show(manager, "Add");
    }


    public void openAddDialog() {
        FragmentManager manager = ((AppCompatActivity) this.getContext()).getSupportFragmentManager();
        DialogFragment frag = new RunAddDialog();
        frag.show(manager, "Add");
    }
}