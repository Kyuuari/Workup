package sheridan.caric.project.ui.pushups;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import sheridan.caric.project.R;
import sheridan.caric.project.db.Pushup;
import sheridan.caric.project.ui.pushups.PushupViewModel;

public class PushupsEditDialog extends DialogFragment {

    private PushupViewModel pushupViewModel;
    private EditText input;
    private long aLong;
    private AlertDialog a;
    private Pushup p;

    public PushupsEditDialog(Pushup pushup) {
        p = pushup;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        pushupViewModel =
                ViewModelProviders.of(this).get(PushupViewModel.class);
        LayoutInflater inflater = requireActivity().getLayoutInflater();


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(inflater.inflate(R.layout.dialog_edit_pushup, null));

        builder.setPositiveButton("OK", (dialog, which) -> {

            try {
                input = a.findViewById(R.id.Amount);
                aLong = Long.valueOf(input.getText().toString());

                long id = p.getId();
                Pushup updatePushup = new Pushup(aLong);
                updatePushup.setId(id);

                pushupViewModel.update(updatePushup);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Unable to make changes", Toast.LENGTH_SHORT).show();
            }

        });

        builder.setNegativeButton("Delete", (dialog, which) -> {
            pushupViewModel.deleteByPushup(p);
            Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
        });

        a = builder.create();

        return a;
        //dialog.show();
    }
}
