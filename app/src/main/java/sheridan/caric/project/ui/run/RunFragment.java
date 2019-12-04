package sheridan.caric.project.ui.run;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import sheridan.caric.project.R;

public class RunFragment extends Fragment {

    private RunViewModel runViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        runViewModel =
                ViewModelProviders.of(this).get(RunViewModel.class);
        View view = inflater.inflate(R.layout.fragment_run, container, false);
        return view;
    }
}