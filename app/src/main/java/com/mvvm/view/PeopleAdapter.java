package com.mvvm.view;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mvvm.R;
import com.mvvm.databinding.ItemPeopleBinding;
import com.mvvm.model.People;
import com.mvvm.viewmodel.ItemPeopleViewModel;

import java.util.Collections;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder> {

    private List<People> peopleList;

    PeopleAdapter() {
        this.peopleList = Collections.emptyList();
    }

    @NonNull
    @Override
    public PeopleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPeopleBinding itemPeopleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people, parent, false);
        return new PeopleAdapterViewHolder(itemPeopleBinding);
    }

    @Override
    public void onBindViewHolder(PeopleAdapterViewHolder holder, int position) {
        holder.bindPeople(peopleList.get(position));
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    void setPeopleList(List<People> peopleList) {
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }

    static class PeopleAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemPeopleBinding binding;

        PeopleAdapterViewHolder(ItemPeopleBinding binding) {
            super(binding.itemPeople);
            this.binding = binding;
        }

        void bindPeople(People people) {
            if (binding.getPeopleViewModel() == null) {
                binding.setPeopleViewModel(new ItemPeopleViewModel(itemView.getContext(), people));
            } else {
                binding.getPeopleViewModel().setPeople(people);
            }
        }
    }
}
