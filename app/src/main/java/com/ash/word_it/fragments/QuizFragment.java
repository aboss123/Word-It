package com.ash.word_it.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ash.word_it.Auth;
import com.ash.word_it.MainActivity;
import com.ash.word_it.MainActivity.SATQuestion;
import com.ash.word_it.R;
import com.ash.word_it.external.AnimationFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;


public class QuizFragment extends Fragment {

    public static final String SHARED_PREF = "prefs";
    public static final String SCORES = "score";
    public static final String QUESTIONS = "questions";

    private Button o1, o2, o3, o4, quit;
    private String question, op1, op2, op3, op4;
    private String selected, answer;

    private TextView aa, qq;


    public static ArrayList<Integer> scores;
    public static ArrayList<Integer> questions;


    public int question_c;
    public int correct_answers;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // The main Quiz View
        final View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        final ViewAnimator animator = view.findViewById(R.id.quiz_flipper);

        o1 = view.findViewById(R.id.o1);
        o2 = view.findViewById(R.id.o2);
        o3 = view.findViewById(R.id.o3);
        o4 = view.findViewById(R.id.o4);
        quit = view.findViewById(R.id.quit);

        qq = QuestionFragment.question;
        aa = AnswerFragment.answer;

        load_data();

        update_question();

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_data();
            }
        });

        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = o1.getText().toString().toLowerCase();
                if (answer.equalsIgnoreCase(selected)) {
                    o1.setBackgroundResource(R.drawable.button4);
                    Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
                    Handler handler  = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear_all();
                        }
                    }, 1000);
                    correct_answers++;
                    update_question();
                }
                else {
                    o1.setBackgroundResource(R.drawable.button3);
                    AnimationFactory.flipTransition(animator, AnimationFactory.FlipDirection.LEFT_RIGHT, 1000);
                    Toast.makeText(getActivity(), "Incorrect!", Toast.LENGTH_SHORT).show();
                    Handler handler  = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AnimationFactory.flipTransition(animator, AnimationFactory.FlipDirection.RIGHT_LEFT, 1000);
                        }
                    }, 1000);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear_all();
                            update_question();
                        }
                    }, 2000);
                }
                question_c++;
            }
        });

        o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = o2.getText().toString().toLowerCase();
                if (answer.equalsIgnoreCase(selected)) {
                    o2.setBackgroundResource(R.drawable.button4);
                    Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
                    Handler handler  = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear_all();
                        }
                    }, 1000);
                    correct_answers++;
                    update_question();
                }
                else {
                    o2.setBackgroundResource(R.drawable.button3);
                    AnimationFactory.flipTransition(animator, AnimationFactory.FlipDirection.LEFT_RIGHT, 1000);
                    Toast.makeText(getActivity(), "Incorrect!", Toast.LENGTH_SHORT).show();
                    Handler handler  = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AnimationFactory.flipTransition(animator, AnimationFactory.FlipDirection.RIGHT_LEFT, 1000);
                        }
                    }, 1000);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear_all();
                            update_question();
                        }
                    }, 2000);
                }
                question_c++;
            }
        });

        o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = o3.getText().toString().toLowerCase();
                if (answer.equalsIgnoreCase(selected)) {
                    o3.setBackgroundResource(R.drawable.button4);
                    Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
                    Handler handler  = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear_all();
                        }
                    }, 1000);
                    correct_answers++;
                    update_question();
                }
                else {
                    o3.setBackgroundResource(R.drawable.button3);
                    AnimationFactory.flipTransition(animator, AnimationFactory.FlipDirection.LEFT_RIGHT, 1000);
                    Toast.makeText(getActivity(), "Incorrect!", Toast.LENGTH_SHORT).show();
                    Handler handler  = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AnimationFactory.flipTransition(animator, AnimationFactory.FlipDirection.RIGHT_LEFT, 1000);
                        }
                    }, 1000);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear_all();
                            update_question();
                        }
                    }, 2000);
                }
                question_c++;
            }
        });

        o4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = o4.getText().toString().toLowerCase();
                if (answer.equalsIgnoreCase(selected)) {
                    o4.setBackgroundResource(R.drawable.button4);
                    Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
                    Handler handler  = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear_all();
                        }
                    }, 1000);
                    correct_answers++;
                    update_question();
                }
                else {
                    o4.setBackgroundResource(R.drawable.button3);
                    AnimationFactory.flipTransition(animator, AnimationFactory.FlipDirection.LEFT_RIGHT, 1000);
                    Toast.makeText(getActivity(), "Incorrect!", Toast.LENGTH_SHORT).show();
                    Handler handler  = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AnimationFactory.flipTransition(animator, AnimationFactory.FlipDirection.RIGHT_LEFT, 1000);
                        }
                    }, 1000);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clear_all();
                            update_question();
                        }
                    }, 2000);
                }
                question_c++;
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        save_data();
        super.onDestroy();
        System.out.println("SAVVVVVVVVVVVVVED");
    }

    private void save_data() {
        SharedPreferences preferences = requireActivity().getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        int score = (int)(((double)correct_answers / question_c) * 100);

        if (scores.isEmpty() || questions.isEmpty()) System.out.println("EMMMMMMPTY");

        scores.add(score);
        questions.add(question_c);

        if (question_c == 0) return;

        String text = "";


        int i = 0 , j = 0;

       text = text.concat(score + "," + question_c);
        System.out.println("TEXT: " + text);


        String p = preferences.getString(MainActivity.user_uuid(), null);

        if (p != null && p.contains(text))  return;
        else if (p != null) text = p.concat("," + text);
        editor.putString(MainActivity.user_uuid(), text);

        editor.apply();
        editor.commit();
    }

    private void load_data() {
        scores = new ArrayList<>();
        questions = new ArrayList<>();

        SharedPreferences preferences = requireActivity().getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String p = preferences.getString(MainActivity.user_uuid(), null);

        if (p == null) {
            System.out.println("HEEEEEEEEEEEEEEERE");
            return;
        }


        String[] points = p.split(",");

        int i = 0, j = 0;
        for (; i < points.length; ++i, ++j) {
            int v = Integer.parseInt(points[i]);
            if (i % 2 == 0) questions.add(v);
            else scores.add(v);
        }
    }

    private void clear_all() {
        o1.setBackgroundResource(R.drawable.button2);
        o2.setBackgroundResource(R.drawable.button2);
        o3.setBackgroundResource(R.drawable.button2);
        o4.setBackgroundResource(R.drawable.button2);
    }

    private void update_question() {
        SATQuestion q = MainActivity.new_question();
        ArrayList<String> keys = q.render();

        question = keys.get(0);
        op1      = keys.get(1);
        op2      = keys.get(2);
        op3      = keys.get(3);
        op4      = keys.get(4);
        answer   = keys.get(5).toLowerCase();

        o1.setText(op1);
        o2.setText(op2);
        o3.setText(op3);
        o4.setText(op4);

        qq.setText(question);
        aa.setText(answer);
    }
}
