package com.dhirajchhabra.poetrymaker.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dhirajchhabra.poetrymaker.R;
import com.dhirajchhabra.poetrymaker.models.Poetry;
import com.dhirajchhabra.poetrymaker.views.activities.MainActivity;
import com.dhirajchhabra.poetrymaker.views.activities.PoetryCreatingActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PoetriesRecyclerViewAdapter extends RecyclerView.Adapter<PoetriesRecyclerViewAdapter.MyViewHolder> {

    private static ArrayList<Poetry> mDataset;
    private Context mContext;
    private static int position;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView titleText;
        TextView genreText;
        ImageView cvFab;
        public MyViewHolder(View v) {
            super(v);
            titleText = v.findViewById(R.id.titleText);
            genreText = v.findViewById(R.id.genreText);
            cvFab = v.findViewById(R.id.cvFab);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PoetriesRecyclerViewAdapter(ArrayList<Poetry> myDataset, Context context) {
        mDataset = myDataset;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PoetriesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poetry_rv_item, parent, false);

        final MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.titleText.setText(mDataset.get(position).getTitle());
        holder.genreText.setText(mDataset.get(position).getGenre());
        holder.cvFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PoetriesRecyclerViewAdapter.this.position = position;
                Intent intent = new Intent(mContext, PoetryCreatingActivity.class);
                intent.putExtra("currentPoetryData", mDataset.get(position));
                Log.e("TAG", "onClick: " + mDataset.get(position).getGenre());
                ((MainActivity) mContext).startActivityForResult(intent, PoetryCreatingActivity.RESULT_CODE);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static Poetry getDataSet() {
        return mDataset.get(position);
    }
}
