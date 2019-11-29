package sheridan.caric.project.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import sheridan.caric.project.R;
import sheridan.caric.project.db.Pushup;

public class PushupListAdapter extends RecyclerView.Adapter<PushupListAdapter.PushupViewHolder> {

    private final LayoutInflater mInflater;
    private List<Pushup> mPushups; // Cached copy of words

    public PushupListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public PushupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new PushupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PushupViewHolder holder, int position) {
        if (mPushups != null) {
            Pushup current = mPushups.get(position);
            holder.wordItemView.setText(String.valueOf(current.getAmount()));
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    public void setPushups(List<Pushup> pushups){
        mPushups = pushups;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPushups != null)
            return mPushups.size();
        else return 0;
    }

    class PushupViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private PushupViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
