package sheridan.caric.project.ui.pushups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
import sheridan.caric.project.ui.PushupListAdapter;

public class PushupFragment extends Fragment {

    private PushupViewModel pushupViewModel;
    private List<Pushup> mPushups;
    private RecyclerView recyclerView;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                PushupListAdapter.PushupViewHolder viewHolder = (PushupListAdapter.PushupViewHolder) v.getTag();
                long position = viewHolder.getAdapterPosition();
                long a = viewHolder.getPushup().getId();
                Pushup p = viewHolder.getPushup();

                openEditDialog(p);
                //pushupViewModel.deleteByPushup(p);
                //pushupViewModel.deleteById();
                //viewHolder.getItemId();
                //viewHolder.getItemId();
                //viewHolder.getItemViewType();
                //viewHolder.itemView;
                //TestItem thisItem = mTestItemList.get(position)
               //Toast.makeText(getContext(), "You Clicked: " + a, Toast.LENGTH_SHORT).show();

        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        pushupViewModel =
                ViewModelProviders.of(this).get(PushupViewModel.class);

        View view = inflater.inflate(R.layout.fragment_pushup, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);

        final PushupListAdapter adapter = new PushupListAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(onItemClickListener);

        pushupViewModel.getAllPushups().observe(this, new Observer<List<Pushup>>() {
            @Override
            public void onChanged(@Nullable final List<Pushup> pushups) {
                // Update the cached copy of the words in the adapter.
                adapter.setPushups(pushups);
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
                pushupViewModel.deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openEditDialog(Pushup pushup){
        FragmentManager manager = ((AppCompatActivity)this.getContext()).getSupportFragmentManager();
        DialogFragment frag = new PushupsEditDialog(pushup);
        frag.show(manager,"Edit");
    }


    public void openAddDialog(){
        FragmentManager manager = ((AppCompatActivity)this.getContext()).getSupportFragmentManager();
        DialogFragment frag = new PushupsAddDialog();
        frag.show(manager,"Add");
    }




};
