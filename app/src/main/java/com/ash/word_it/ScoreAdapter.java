package com.ash.word_it;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {
    private ArrayList<ScoreItem> items;


    public static class ScoreViewHolder extends RecyclerView.ViewHolder {
        private TextView line1, line2;


        ScoreViewHolder(View item) {
            super(item);

            line1 = item.findViewById(R.id.l1);
            line2 = item.findViewById(R.id.l2);
        }
    }

    public ScoreAdapter(ArrayList<ScoreItem> list) {
        items = list;
    }


    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int view_type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_list, parent, false);
        return new ScoreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        ScoreItem item = items.get(position);

        holder.line1.setText("Score Was: " + item.getScore_percentage() + "%");
        holder.line2.setText("Per " + item.getQuestion_count() + " questions");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
