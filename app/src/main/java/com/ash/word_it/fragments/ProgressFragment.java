package com.ash.word_it.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ash.word_it.MainActivity;
import com.ash.word_it.R;
import com.ash.word_it.ScoreAdapter;
import com.ash.word_it.ScoreItem;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.ash.word_it.fragments.QuizFragment.SHARED_PREF;
import static com.ash.word_it.fragments.QuizFragment.questions;
import static com.ash.word_it.fragments.QuizFragment.scores;

public class ProgressFragment extends Fragment {

    private ArrayList<ScoreItem> items = new ArrayList<>();
    private ScoreAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        scores = new ArrayList<>();
        questions = new ArrayList<>();

        SharedPreferences preferences = requireActivity().getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String pref = preferences.getString(MainActivity.user_uuid(), null);

        if (pref == null) {
            return view;
        }

        String[] pp = pref.split(",");

        System.out.println("TEEEEEEEST: " + pp);

        if (pp.length != 2)
        {
            for (int i = 0; i < pp.length; i++) {
                items.add(new ScoreItem(Integer.parseInt(pp[i]), Integer.parseInt(pp[i + 1])));
                i++;
            }
        } else {
            items.add(new ScoreItem(Integer.parseInt(pp[0]), Integer.parseInt(pp[1])));
        }

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new ScoreAdapter(items);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
