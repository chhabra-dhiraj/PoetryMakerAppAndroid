package com.dhirajchhabra.poetrymaker.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dhirajchhabra.poetrymaker.MyApplication;
import com.dhirajchhabra.poetrymaker.R;
import com.dhirajchhabra.poetrymaker.models.Poem;
import com.dhirajchhabra.poetrymaker.models.Poetry;
import com.dhirajchhabra.poetrymaker.models.SentenceDetails;
import com.dhirajchhabra.poetrymaker.viewmodels.PoemViewModel;
import com.dhirajchhabra.poetrymaker.viewmodels.PoetryViewModel;
import com.dhirajchhabra.poetrymaker.views.adapters.PoetriesRecyclerViewAdapter;
import com.dhirajchhabra.poetrymaker.views.customComponents.EditTextCursorWatcher;
import com.dhirajchhabra.poetrymaker.views.interfaces.OnCursorPositionChangedListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class PoetryCreatingActivity extends AppCompatActivity implements OnCursorPositionChangedListener {

    public static final int RESULT_CODE = 10;

    private RadioButton spinnerGenre;
    private TextInputEditText titleEt;
    private ExtendedFloatingActionButton saveButton;
    private RadioButton mythRb, natureRb, loveRb;
    private ChipGroup afterWordsPoetryChipGroup;
    private ChipGroup beforeWordsPoetryChipGroup;
    private Chip chip1, chip2, chip3, chip4, chip5, chip6, chip7, chip8, chip9, chip10;
    ArrayList<SentenceDetails> afterWordsList, beforeWordsList;
    //    private ProgressBar fetchingSuggestionsProgressBar;
    private EditTextCursorWatcher poemWritingEditText;
    private String selectedGenre, lastWordBeforeSpace;
    private int indexForAfterWord, indexForBeforeWord;
    private ProgressBar progressBarWords;

    final long delay = 1000; // 1 seconds after user stops typing
    private long last_text_edit_time = 0;

    private ArrayList<Chip> chipsBefore = new ArrayList<>();


    private ArrayList<Chip> chipsAfter = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poetry_creating);

        final Poetry poetry = getIntent().getParcelableExtra("currentPoetryData");

        mythRb = findViewById(R.id.mythRb);
        natureRb = findViewById(R.id.natureRb);
        loveRb = findViewById(R.id.loveRb);

        titleEt = findViewById(R.id.titleEt);

        poemWritingEditText = findViewById(R.id.edit_text_write_poem_pwa);

        saveButton = findViewById(R.id.saveButton);

        progressBarWords = findViewById(R.id.progressBarWords);

        Poetry poem = null;

        if (poetry != null) {

            poem = PoetriesRecyclerViewAdapter.getDataSet();
            Log.e("TAG", "onCreate: " + poetry);
            titleEt.setText(poem.getTitle());

            if ("myth".equals(poem.getGenre())) {
                mythRb.setChecked(true);
            } else if ("nature".equals(poem.getGenre())) {
                natureRb.setChecked(true);
            } else if ("love".equals(poem.getGenre())) {
                loveRb.setChecked(true);
            }

            poemWritingEditText.setText(poem.getBody());
        }

        final Poetry finalPoem = poem;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButton.setEnabled(false);
                PoetryViewModel poetryViewModel = ViewModelProviders.of(PoetryCreatingActivity.this).get(PoetryViewModel.class);
                poetryViewModel.initialize();
                if (finalPoem != null) {
                    poetryViewModel.makeNetworkCallForUpdatingPoetry(finalPoem.getPoetryId(), finalPoem.getTitle(), finalPoem.getGenre(), finalPoem.getBody()).observe(PoetryCreatingActivity.this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if (s != null) {
                                saveButton.setEnabled(true);
                                Toast.makeText(PoetryCreatingActivity.this, "Poem Details updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    poetryViewModel.makeNetworkCallForCreatingPoetry(MyApplication.getUserId(), titleEt.getText().toString(), selectedGenre, poemWritingEditText.getText().toString(), null, null, null, null, null, null).observe(PoetryCreatingActivity.this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if (s != null) {
                                saveButton.setEnabled(true);
                                Toast.makeText(PoetryCreatingActivity.this, "New Poem Created", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        afterWordsList = new ArrayList<>();
        beforeWordsList = new ArrayList<>();

        afterWordsPoetryChipGroup = findViewById(R.id.chipGroup2);
        beforeWordsPoetryChipGroup = findViewById(R.id.chipGroup1);

        beforeWordsPoetryChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip chip = group.findViewById(checkedId);
                if (chip != null) {
                    String clickedWord = chip.getText().toString();
                    clickedOnBeforeWord(clickedWord);
                }
            }
        });

        afterWordsPoetryChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip chip = group.findViewById(checkedId);
                if (chip != null) {
                    String clickedWord = chip.getText().toString();
                    clickedOnAfterWord(clickedWord);
                }
            }
        });

        chip1 = findViewById(R.id.chip1);
        chip2 = findViewById(R.id.chip2);
        chip3 = findViewById(R.id.chip3);
        chip4 = findViewById(R.id.chip4);
        chip5 = findViewById(R.id.chip5);
        chip6 = findViewById(R.id.chip6);
        chip7 = findViewById(R.id.chip7);
        chip8 = findViewById(R.id.chip8);
        chip9 = findViewById(R.id.chip9);
        chip10 = findViewById(R.id.chip10);

        chipsBefore.add(chip1);
        chipsBefore.add(chip2);
        chipsBefore.add(chip3);
        chipsBefore.add(chip4);
        chipsBefore.add(chip5);

        chipsAfter.add(chip6);
        chipsAfter.add(chip7);
        chipsAfter.add(chip8);
        chipsAfter.add(chip9);
        chipsAfter.add(chip10);

        poemWritingEditText.addCursorPositionChangedListener(this);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.mythRb:
                if (checked) {
                    selectedGenre = "myth";
                }
                // Pirates are the best
                break;
            case R.id.natureRb:
                if (checked) {
                    selectedGenre = "nature";
                }
                // Pirates are the best
                break;
            case R.id.loveRb:
                if (checked) {
                    selectedGenre = "love";
                }
                // Pirates are the best
                break;
        }
    }

    // This method is called when a halt is detected in typing.
// It makes the Api call to fetch suggestions
    private void onHaltDetected() {
        Log.e("TAG", "onHaltDetected: Word is - " + lastWordBeforeSpace);

        PoemViewModel poemViewModel = ViewModelProviders.of(this).get(PoemViewModel.class);
        poemViewModel.initialize();

        poemViewModel.makeNetworkCallForPoem(selectedGenre, lastWordBeforeSpace).observe(this, new Observer<Poem>() {
            @Override
            public void onChanged(Poem poem) {
                if (poem != null) {
                    // do anything with response

//                    afterWordsList.clear();
                    beforeWordsList.clear();
//                    afterWordsList.addAll(poem.getAfterWords());
                    beforeWordsList.addAll(poem.getSentences());

                    int afterSize = 5;
                    if (afterWordsList.size() < 5) {
                        afterSize = afterWordsList.size();
                    }

                    int beforeSize = 5;
                    if (beforeWordsList.size() < 5) {
                        beforeSize = beforeWordsList.size();
                    }

                    for (int i = 0; i < beforeSize - 1; i++) {
                        chipsBefore.get(i).setText(beforeWordsList.get(i).getSentence());
                    }

                    for (int i = 0; i < afterSize - 1; i++) {
                        chipsAfter.get(i).setText(afterWordsList.get(i).getSentence());
                    }

                    progressBarWords.setVisibility(View.GONE);
                    beforeWordsPoetryChipGroup.setVisibility(View.VISIBLE);
//                    afterWordsPoetryChipGroup.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    @Override
    public void onChangeDetected(String lastWordBeforeSpace, int indexForAfterWord, int indexForBeforeWord) {
        this.lastWordBeforeSpace = lastWordBeforeSpace;
        this.indexForAfterWord = indexForAfterWord;
        this.indexForBeforeWord = indexForBeforeWord;

        this.progressBarWords.setVisibility(View.VISIBLE);
        this.beforeWordsPoetryChipGroup.setVisibility(View.INVISIBLE);
        this.afterWordsPoetryChipGroup.setVisibility(View.INVISIBLE);

        Log.e("TAG", "onChangeDetected: " + lastWordBeforeSpace + " " + indexForAfterWord + " " + indexForBeforeWord);

        onHaltDetected();

    }

    public void clickedOnAfterWord(String clickedWord) {
        //insert the word with a space at the after index
        poemWritingEditText.getText().insert(indexForAfterWord, clickedWord + " ");
    }

    public void clickedOnBeforeWord(String clickedWord) {
        //insert the word with a space at the before index
        String wordToBeInserted;
        if (indexForBeforeWord == 0)
            wordToBeInserted = clickedWord + " ";
        else
            wordToBeInserted = " " + clickedWord;
        poemWritingEditText.getText().insert(indexForBeforeWord, wordToBeInserted);
    }

}
