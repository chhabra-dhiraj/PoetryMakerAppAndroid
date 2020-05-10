package com.dhirajchhabra.poetrymaker.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.dhirajchhabra.poetrymaker.MyApplication;
import com.dhirajchhabra.poetrymaker.models.Poetry;
import com.dhirajchhabra.poetrymaker.models.User;
import com.dhirajchhabra.poetrymaker.viewmodels.UserViewModel;
import com.dhirajchhabra.poetrymaker.views.adapters.PoetriesRecyclerViewAdapter;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.dhirajchhabra.poetrymaker.R;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView poetryRv;
    private ProgressBar progressBar;
    private FloatingActionButton addPoetryFab;
    private ArrayList<Poetry> poetries = new ArrayList<>();
    private MaterialTextView noPoetryTv;
    private PoetriesRecyclerViewAdapter poetriesRecyclerViewAdapter;
    private UserViewModel userViewModel;

    private CircleImageView userIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        poetryRv = findViewById(R.id.poetryRv);
        progressBar = findViewById(R.id.progressBar);
        addPoetryFab = findViewById(R.id.addPoetryFab);
        noPoetryTv = findViewById(R.id.noPoetryTv);
        userIv = findViewById(R.id.userIv);

        poetryRv.setVisibility(View.GONE);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        poetryRv.setLayoutManager(layoutManager);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.initialize();

        Log.e("TAG", "onCreate: " + MyApplication.getUserId());

        userViewModel.makeNetworkCallForGettingUser(MyApplication.getUserId()).observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    progressBar.setVisibility(View.GONE);
                    Picasso.get().load(user.getImageUrl()).into(userIv);
                    if (user.getPoetries().size() == 0) {
                        noPoetryTv.setVisibility(View.VISIBLE);
                    } else {
                        poetryRv.setVisibility(View.VISIBLE);
                        poetries = user.getPoetries();
                        // specify an adapter (see also next example)
                        poetriesRecyclerViewAdapter = new PoetriesRecyclerViewAdapter(poetries, MainActivity.this);
                        poetryRv.setAdapter(poetriesRecyclerViewAdapter);
                    }
                }
            }
        });


        addPoetryFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PoetryCreatingActivity.class);
                startActivityForResult(intent, PoetryCreatingActivity.RESULT_CODE);
                noPoetryTv.setVisibility(View.GONE);
                poetryRv.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        progressBar.setVisibility(View.VISIBLE);

        if (requestCode == PoetryCreatingActivity.RESULT_CODE) {
            userViewModel.makeNetworkCallForGettingUser(MyApplication.getUserId());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutUs:
                return true;
            case R.id.logout:
                signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        //
                        // ...

                        userViewModel.clearCurrentData();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

        // [END auth_fui_signout]
    }
}
