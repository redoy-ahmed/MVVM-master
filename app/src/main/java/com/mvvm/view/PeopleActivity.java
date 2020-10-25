package com.mvvm.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mvvm.R;
import com.mvvm.databinding.PeopleActivityBinding;
import com.mvvm.viewmodel.PeopleViewModel;

import java.util.Observable;
import java.util.Observer;

public class PeopleActivity extends AppCompatActivity implements Observer {

    private PeopleViewModel peopleViewModel;

    private PeopleActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(binding.toolbar);
        setupListPeopleView(binding.recyclerPeople);
        setupObserver(peopleViewModel);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.people_activity);
        peopleViewModel = new PeopleViewModel(this);
        binding.setMainViewModel(peopleViewModel);
    }

    private void setupListPeopleView(RecyclerView recyclerPeople) {
        PeopleAdapter adapter = new PeopleAdapter();
        recyclerPeople.setAdapter(adapter);
        recyclerPeople.setHasFixedSize(true);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        peopleViewModel.reset();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof PeopleViewModel) {
            PeopleAdapter peopleAdapter = (PeopleAdapter) binding.recyclerPeople.getAdapter();
            PeopleViewModel peopleViewModel = (PeopleViewModel) observable;
            if (peopleAdapter != null) {
                peopleAdapter.setPeopleList(peopleViewModel.getPeopleList());
            }
        }
    }
}
