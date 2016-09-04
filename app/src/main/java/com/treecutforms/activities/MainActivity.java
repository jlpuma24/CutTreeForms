package com.treecutforms.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.treecutforms.R;
import com.treecutforms.adapters.FormItemAdapter;
import com.treecutforms.databinding.ActivityMainBinding;
import com.treecutforms.models.Form;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private FormItemAdapter adapter = new FormItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupToolbar();
        setupFloatingButton();
        setupAdapter();
        setupRecyclerView();
    }

    /**
     * Setup activity main toolbar
     */
    private void setupToolbar() {
        binding.toolbar.setTitle(getString(R.string.activity_main_toolbar_title));
        binding.toolbar.setTitleTextColor(Color.WHITE);
    }

    private void setupFloatingButton() {
        binding.fabNewForm.setOnClickListener(this);
    }

    /**
     * Setup adapter items
     */
    private void setupAdapter() {
        // TODO get real data from local database
        ArrayList<Form> forms = new ArrayList<>();
        forms.add(new Form());
        forms.add(new Form());
        forms.add(new Form());
        forms.add(new Form());
        forms.add(new Form());
        forms.add(new Form());
        forms.add(new Form());
        forms.add(new Form());
        forms.add(new Form());
        forms.add(new Form());
        forms.add(new Form());
        forms.add(new Form());
        adapter.setItems(forms);
    }

    /**
     * Setup Categories RecyclerView
     */
    private void setupRecyclerView() {
        binding.rvFormList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvFormList.setHasFixedSize(true);
        binding.rvFormList.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_new_form:
                launchFormActivity();
                break;
        }
    }

    private void launchFormActivity() {
        startActivity(new Intent(this, FormActivity.class));
    }
}
