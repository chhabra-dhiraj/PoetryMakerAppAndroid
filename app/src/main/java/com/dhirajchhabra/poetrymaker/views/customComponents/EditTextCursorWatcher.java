package com.dhirajchhabra.poetrymaker.views.customComponents;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatEditText;

import com.dhirajchhabra.poetrymaker.views.interfaces.OnCursorPositionChangedListener;

public class EditTextCursorWatcher extends AppCompatEditText {

    private OnCursorPositionChangedListener cursorPositionChangedListener;
    private String lastWordBeforeSpace;
    private int indexForAfterWord, indexForBeforeWord;
    final long delay = 1000; // 1 seconds after user stops typing
    private long last_text_edit_time = 0;

    final Handler handler = new Handler();

    public EditTextCursorWatcher(Context context) {
        super(context);
    }

    public EditTextCursorWatcher(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextCursorWatcher(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void addCursorPositionChangedListener(OnCursorPositionChangedListener cursorPositionChangedListener) {
        this.cursorPositionChangedListener = cursorPositionChangedListener;
    }

    public void removeCursorPositionChangedListener() {
        this.cursorPositionChangedListener = null;
    }

    public String getLastWordBeforeSpace() {
        return lastWordBeforeSpace;
    }

    public int getIndexForAfterWord() {
        return indexForAfterWord;
    }

    public int getIndexForBeforeWord() {
        return indexForBeforeWord;
    }

    final Runnable input_finish_checker = new Runnable() {
        public void run() {
            if (System.currentTimeMillis() > (last_text_edit_time + delay - 500)) {
                //halt detected
                Log.e("TAG", "run: " + "typing stopped");
                if(cursorPositionChangedListener!=null && lastWordBeforeSpace!=null)
                    cursorPositionChangedListener.onChangeDetected(lastWordBeforeSpace, indexForAfterWord, indexForBeforeWord);
            }
        }
    };

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        Log.e("TAG", "isCursorPositionChanged: " + selEnd + ". " + selStart);
        //always reset the attributes after cursor position changes
        lastWordBeforeSpace = null;
        indexForBeforeWord = -1;
        indexForAfterWord = -1;
        if (selStart == selEnd && getText() != null) {
            last_text_edit_time = System.currentTimeMillis();
            Log.e("TAG", "onCursorChanged: true" + selEnd + ". " + selStart);
            String text = getText().toString();
            //proceed only if the last character is a space
            if (selStart > 1 && text.charAt(selStart - 1) == ' ') {
                //proceed only if the word preceding the space is not another space
                if (text.charAt(selStart - 2) != ' ') {
                    //excluding the last space from the text
                    text = text.substring(0, selStart - 1);
                    String delimiter = " ";
                    int lastDelimiterPosition = text.lastIndexOf(delimiter);

                    lastWordBeforeSpace = lastDelimiterPosition == -1 ? text :
                            text.substring(lastDelimiterPosition + delimiter.length());
                    indexForBeforeWord = lastDelimiterPosition == -1 ? 0 : lastDelimiterPosition;
                    indexForAfterWord = selStart;

                    handler.removeCallbacks(input_finish_checker);

                    handler.postDelayed(input_finish_checker, delay);
                    Log.e("TAG", "onSelectionChanged: space after word - " + lastWordBeforeSpace);
                }
            }
        }
    }

}
