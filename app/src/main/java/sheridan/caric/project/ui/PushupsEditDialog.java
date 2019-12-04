package sheridan.caric.project.ui;

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
    private long p;

    public PushupsEditDialog(){
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        pushupViewModel =
                ViewModelProviders.of(this).get(PushupViewModel.class);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        Toast.makeText(getContext(),"hello world",Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(inflater.inflate(R.layout.dialog_edit_pushup,null));

        builder.setPositiveButton("OK", (dialog, which) -> {
            input = a.findViewById(R.id.Amount);
            aLong = Long.valueOf(input.getText().toString());
            pushupViewModel.insert(new Pushup(aLong));
        });

        builder.setNegativeButton("Delete All",(dialog, which) -> {
          // pushupViewModel.deleteById();
           Toast.makeText(getContext(),"Deleted",Toast.LENGTH_SHORT).show();
        });

        a = builder.create();

        return a;
        //dialog.show();
    }
}
