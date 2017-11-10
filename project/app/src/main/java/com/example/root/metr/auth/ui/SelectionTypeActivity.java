package com.example.root.metr.auth.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.root.metr.R;
import com.example.root.metr.databinding.ActivitySelectionTypeBinding;


public class SelectionTypeActivity extends AppCompatActivity {

    ActivitySelectionTypeBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_selection_type);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setSupportActionBar(binding.toolbar);
    }

    public void clickPhisical(View view){

    }

    public void clickJuridical(View view){

    }
}
