package com.treecutforms.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.treecutforms.R;
import com.treecutforms.adapters.FormItemAdapter;
import com.treecutforms.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FormItemAdapter adapter = new FormItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupAdapter();
        setupRecyclerView();
    }

    /**
     * Setup adapter items
     */
    private void setupAdapter() {
        // TODO get real data from local database
//        ArrayList<Form> forms = new ArrayList<>();
//        forms.add(new Form());
//        forms.add(new Form());
//        forms.add(new Form());
//        forms.add(new Form());
//        forms.add(new Form());
//        forms.add(new Form());
//        adapter.setItems(forms);
    }

    /**
     * Setup Categories RecyclerView
     */
    private void setupRecyclerView() {
        binding.rvFormList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvFormList.setHasFixedSize(true);
        binding.rvFormList.setAdapter(adapter);
    }

}
