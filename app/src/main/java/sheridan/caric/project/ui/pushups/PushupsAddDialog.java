package sheridan.caric.project.ui.pushups;

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

public class PushupsAddDialog extends DialogFragment {
    private PushupViewModel pushupViewModel;
    private EditText input;
    private long aLong;
    private AlertDialog a;
    private Pushup p;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        pushupViewModel =
                ViewModelProviders.of(this).get(PushupViewModel.class);
        LayoutInflater inflater = requireActivity().getLayoutInflater();


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(inflater.inflate(R.layout.dialog_add_pushup, null));

        builder.setPositiveButton("OK", (dialog, which) -> {

            try {
                input = a.findViewById(R.id.Amount);
                aLong = Long.valueOf(input.getText().toString());
                pushupViewModel.insert(new Pushup(aLong));
            } catch (Exception e) {
                Toast.makeText(getContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
            }


        });

        a = builder.create();

        return a;

    }
}
