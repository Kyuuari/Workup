package sheridan.caric.project.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import sheridan.caric.project.R;
import sheridan.caric.project.db.Pushup;

public class PushupListAdapter extends RecyclerView.Adapter<PushupListAdapter.PushupViewHolder> {

    private final LayoutInflater mInflater;
    private List<Pushup> mPushups;
    private View itemView;
    private View.OnClickListener mOnItemClickListener;


    public PushupListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public PushupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = mInflater.inflate(R.layout.pushup_item, parent, false);
//        itemView.setOnClickListener(v->openDialog());
        return new PushupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PushupViewHolder holder, int position) {
        if (mPushups != null) {
            Pushup current = mPushups.get(position);
            holder.pushupsItemView.setText(String.valueOf(current.getAmount()));
            holder.pushupsItemView.setId(position);

            SimpleDateFormat df = new SimpleDateFormat("MMM d h:mm a");
            String formattedDate = df.format(current.getDate());

            holder.pushupsDate.setText(formattedDate);
            //bind holder with pushup
            holder.setPushup(current);
        } else {
            // Covers the case of data not being ready yet.
            holder.pushupsItemView.setText("No Pushups Set");
        }
    }

    public void setPushups(List<Pushup> pushups){
        mPushups = pushups;
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }


    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPushups != null)
            return mPushups.size();
        else return 0;
    }

    public class PushupViewHolder extends RecyclerView.ViewHolder {
        private final TextView pushupsItemView;
        private final TextView pushupsDate;
        private Pushup pushup;

        private PushupViewHolder(View itemView) {
            super(itemView);
            pushupsItemView = itemView.findViewById(R.id.textView);
            pushupsDate = itemView.findViewById(R.id.textView2);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public void setPushup(Pushup pushup) {
            this.pushup = pushup;
        }

        public Pushup getPushup(){return this.pushup;}
    }



}
