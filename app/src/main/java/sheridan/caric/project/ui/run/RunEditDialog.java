package sheridan.caric.project.ui.run;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import sheridan.caric.project.R;
import sheridan.caric.project.db.Pushup;
import sheridan.caric.project.db.Run;
import sheridan.caric.project.ui.pushups.PushupViewModel;

public class RunEditDialog extends DialogFragment {

    private RunViewModel runViewModel;
    private EditText input;
    private long aLong;
    private AlertDialog a;
    private Run r;

    public RunEditDialog(Run run) {
        r = run;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        runViewModel =
                ViewModelProviders.of(this).get(RunViewModel.class);
        LayoutInflater inflater = requireActivity().getLayoutInflater();


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(inflater.inflate(R.layout.dialog_edit_run, null));

        builder.setPositiveButton("OK", (dialog, which) -> {

            try {
                input = a.findViewById(R.id.Amount);
                aLong = Long.valueOf(input.getText().toString());

                long id = r.getId();
                Run updateRun = new Run(aLong);
                updateRun.setId(id);

                runViewModel.update(updateRun);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Unable to make changes", Toast.LENGTH_SHORT).show();
            }

        });

        builder.setNegativeButton("Delete", (dialog, which) -> {
            runViewModel.deleteByRun(r);
            Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
        });

        a = builder.create();

        return a;
    }
}
