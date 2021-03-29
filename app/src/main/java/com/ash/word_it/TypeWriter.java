package com.ash.word_it;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

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

    private Runnable ccadr = new Runnable() {
        @Override
        public void run() {
            setText(text.subSequence(0, ++idx));

            if (idx < text.length())
                handler.postDelayed(ccadr, delay);
        }
    };

    public void animate(CharSequence txt, long d, Runnable runnable) {
        text = txt;
        idx  = 0;
        delay = d;
        setText("");

        handler.removeCallbacks(ccadr);
        handler.postDelayed(ccadr, delay);
        handler.postDelayed(runnable, 2150);
    }

}
