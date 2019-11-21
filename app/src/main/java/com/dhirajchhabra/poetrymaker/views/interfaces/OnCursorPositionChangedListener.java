package com.dhirajchhabra.poetrymaker.views.interfaces;

public interface OnCursorPositionChangedListener {

    public void onChangeDetected(String lastWordBeforeSpace, int indexForAfterWord, int indexForBeforeWord);

}
