package com.ash.word_it.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.ash.word_it.Login;
import com.ash.word_it.MainActivity;
import com.ash.word_it.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static android.content.Context.MODE_PRIVATE;
import static com.ash.word_it.fragments.QuizFragment.QUESTIONS;
import static com.ash.word_it.fragments.QuizFragment.SCORES;
import static com.ash.word_it.fragments.QuizFragment.SHARED_PREF;
import static com.ash.word_it.fragments.QuizFragment.questions;
import static com.ash.word_it.fragments.QuizFragment.scores;

public class HomeFragment extends Fragment {

    private GraphView                   graph;
    private LineGraphSeries<DataPoint>  series;
    private TextView sign_out;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //save_data();

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        graph = view.findViewById(R.id.graph);

        sign_out = view.findViewById(R.id.sign_out);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(requireActivity().getApplicationContext(), Login.class));
                requireActivity().finish();
            }
        });

        load_data();

        //if (questions.isEmpty() || scores.isEmpty())  return view;

        ArrayList<DataPoint> points = new ArrayList<>();

        scores = new ArrayList<>();
        questions = new ArrayList<>();

        SharedPreferences preferences = requireActivity().getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String pref = preferences.getString(MainActivity.user_uuid(), null);

        if (pref == null) {
            return view;
        }

        String[] pp = pref.split(",");

        if (pp.length != 2)
        {
            for (int i = 0; i < pp.length; i++) {
                points.add(new DataPoint(Integer.parseInt(pp[i]), Integer.parseInt(pp[i + 1])));
                i++;
            }
        } else {
            points.add(new DataPoint(Integer.parseInt(pp[0]), Integer.parseInt(pp[1])));
        }

        DataPoint[] po = new DataPoint[points.size()];
        po = points.toArray(po);

        Arrays.sort(po, new Comparator<DataPoint>() {
            @Override
            public int compare(DataPoint dataPoint, DataPoint t1) {
                return (int)(dataPoint.getX() - t1.getX());
            }
        });

        graph.getViewport().setMinX(0);

        //DataPoint[] dd = { new DataPoint(50, 2), new DataPoint(100, 4) };

        for (int i = 0; i < po.length; i++) {
            System.out.println(po[i].toString());
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(po);
        graph.addSeries(series);

        return view;
    }

    private void load_data() {
        scores = new ArrayList<>();
        questions = new ArrayList<>();

        SharedPreferences preferences = requireActivity().getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String p = preferences.getString(MainActivity.user_uuid(), null);

        if (p == null) return;

        String[] points = p.split(",");

        int i = 0, j = 0;
        for (; i < points.length; ++i, ++j) {
            int v = Integer.parseInt(points[i]);
            if (i % 2 == 0) questions.add(v);
            else scores.add(v);
        }
        System.out.println("QUESTIONS: " + questions);
        System.out.println("SCORES: " + scores);
    }
}
