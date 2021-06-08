package com.ash.word_it;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

// https://www.youtube.com/watch?v=4JUWmX1lK4o --- Based on Typewriter tutorial
// Author based: Sylvain Saurel
public class TypeWriter extends androidx.appcompat.widget.AppCompatTextView {

    private CharSequence text;
    private int idx;
    private long delay = 100;

    private Handler handler = new Handler();


    public TypeWriter(Context context) {
        super(context);
    }

    public TypeWriter(Context context, AttributeSet attr) {
        super(context, attr);
    }

    private Runnable writer = new Runnable() {
        @Override
        public void run() {
            setText(text.subSequence(0, ++idx));

            if (idx < text.length())
                handler.postDelayed(writer, delay);
        }
    };

    public void animate(CharSequence txt, long d, Runnable runnable) {
        text = txt;
        idx  = 0;
        delay = d;
        setText("");

        handler.removeCallbacks(writer);
        handler.postDelayed(writer, delay);
        handler.postDelayed(runnable, 2150);
    }

}
