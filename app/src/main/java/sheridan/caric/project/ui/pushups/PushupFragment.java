package sheridan.caric.project.ui.pushups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sheridan.caric.project.R;
import sheridan.caric.project.db.Pushup;
import sheridan.caric.project.ui.PushupListAdapter;

public class PushupFragment extends Fragment {

    private PushupViewModel pushupViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        View view = getLayoutInflater().inflate();

        pushupViewModel =
                ViewModelProviders.of(this).get(PushupViewModel.class);

        View view = inflater.inflate(R.layout.fragment_pushup, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        final PushupListAdapter adapter = new PushupListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        pushupViewModel.getAllPushups().observe(this, new Observer<List<Pushup>>() {
            @Override
            public void onChanged(@Nullable final List<Pushup> pushups) {
                // Update the cached copy of the words in the adapter.
                adapter.setPushups(pushups);
            }
        });
        //final TextView textView = view.findViewById(R.id.text_home);
//        pushupViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return view;
    }
}