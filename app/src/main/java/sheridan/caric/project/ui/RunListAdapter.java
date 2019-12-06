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
import sheridan.caric.project.db.Run;

public class RunListAdapter extends RecyclerView.Adapter<RunListAdapter.RunViewHolder> {

    private final LayoutInflater mInflater;
    private List<Run> mRuns;
    private View itemView;
    private View.OnClickListener mOnItemClickListener;

    public RunListAdapter(Context context){mInflater=LayoutInflater.from(context);}

    @Override
    public RunListAdapter.RunViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = mInflater.inflate(R.layout.run_item, parent, false);
//        itemView.setOnClickListener(v->openDialog());
        return new RunListAdapter.RunViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RunListAdapter.RunViewHolder holder, int position) {
        if (mRuns != null) {
            Run current = mRuns.get(position);
            holder.runItemView.setText(String.valueOf(current.getAmount()));
            holder.runItemView.setId(position);

            SimpleDateFormat df = new SimpleDateFormat("MMM d h:mm a");
            String formattedDate = df.format(current.getDate());
            holder.dateItemView.setText(formattedDate);

            //bind holder with run
            holder.setRun(current);
        } else {
            // Covers the case of data not being ready yet.
            holder.runItemView.setText("No Runs Set");
        }
    }


    public void setRuns(List<Run> runs){
        mRuns = runs;
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }



    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mRuns != null)
            return mRuns.size();
        else return 0;
    }

    public class RunViewHolder extends RecyclerView.ViewHolder {
        private final TextView runItemView;
        private final TextView dateItemView;
        private Run run;

        private RunViewHolder(View itemView) {
            super(itemView);
            runItemView = itemView.findViewById(R.id.textView);
            dateItemView= itemView.findViewById(R.id.textView2);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }


        public void setRun(Run run) {
            this.run = run;
        }

        public Run getRun(){return this.run;}

    }
}
