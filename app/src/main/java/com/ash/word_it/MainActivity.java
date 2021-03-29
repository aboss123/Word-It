package com.ash.word_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.ash.word_it.fragments.ProgressFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


class SATWord {

    SATWord(String word, WordType type, String definition) {
        this.word = word;
        this.type = type;
        this.definition = definition;
    }

    enum WordType {
        Verb,
        Noun,
        Adjective,
        Adverb
    }

    private String word;
    private WordType type;
    private String definition;

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setType(WordType type) {
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public WordType getType() {
        return type;
    }

    @NonNull
    @Override
    public String toString() {
        return "[" + word + ", " + type.toString() + ", " + definition + "]";
    }
}




public class MainActivity extends AppCompatActivity {

    static public class SATQuestion {
        private boolean type;
        private SATWord word;
        private ArrayList<String> words;


        SATQuestion(boolean type, SATWord word, ArrayList<String> words) {
            this.type = type;
            this.word = word;
            this.words = words;
        }


        public ArrayList<String> render() {
            String s;
            if (type) s = "What part of speech is the word: " + word.getWord();
            else s = "What word is the definition: " + word.getDefinition();
            ArrayList<String> questions_and_answers = new ArrayList<>();
            questions_and_answers.add(s);
            questions_and_answers.addAll(words);
            if (type) {
                questions_and_answers.add(word.getType().toString());
            } else {
                questions_and_answers.add(word.getWord());
            }
            return questions_and_answers;
        }
    }

    // Used in Quizzing list
    public static ArrayList<SATWord> words = new ArrayList<>();
    private JSONObject root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_quiz, R.id.navigation_progress)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        // Read in the direct file asset
        try {
            InputStream is = getAssets().open("vocab.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            root = new JSONObject(new String(buffer));
        } catch (IOException | JSONException e) {
            System.out.println("DSDSDSDSDSDDS");
            e.printStackTrace();
        }

        try {
            JSONArray results = root.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                String def = results.getJSONObject(i).getString("definition");
                String type = results.getJSONObject(i).getString("type");
                String word = results.getJSONObject(i).getString("word");
                if (type.equalsIgnoreCase(".n"))
                    words.add(new SATWord(word, SATWord.WordType.Noun, def));
                else if (type.equalsIgnoreCase("v."))
                    words.add(new SATWord(word, SATWord.WordType.Verb, def));
                else if (type.equalsIgnoreCase("adj."))
                    words.add(new SATWord(word, SATWord.WordType.Adjective, def));
                else if (type.equalsIgnoreCase("adv.")) words.add(new SATWord(word, SATWord.WordType.Adverb, def));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static String user_uuid() {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        assert currentFirebaseUser != null;
        return currentFirebaseUser.getUid();
    }

    public static SATQuestion new_question() {
        Random random = new Random();
        ArrayList<String> w = new ArrayList<>();
        SATWord word;
        boolean x = (random.ints(1, 3).findFirst().getAsInt() == 1);
        int idx = random.ints(1, words.size()).findFirst().getAsInt();
        word = words.get(idx);
        if (!x) {
            for (int i = 0; i < 3; i++) {
                int index = random.ints(1, words.size()).findFirst().getAsInt();
                String val = words.get(index).getWord();
                w.add(val);
            }
            w.add(word.getWord());
            Collections.shuffle(w);
            w.add(word.getWord());
        }
        else {
            w.add("Noun");
            w.add("Verb");
            w.add("Adverb");
            w.add("Adjective");
            Collections.shuffle(w);
        }
        return new SATQuestion(x, word, w);
    }
}
