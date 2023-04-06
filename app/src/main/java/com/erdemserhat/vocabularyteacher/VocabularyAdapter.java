package com.erdemserhat.vocabularyteacher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erdemserhat.vocabularyteacher.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.VocabularyHolder> {

    private ArrayList <Vocabulary> vocabularyArrayList;

    public VocabularyAdapter(ArrayList <Vocabulary> vocabularyArrayList) {
        this.vocabularyArrayList=vocabularyArrayList;
    }

    @NonNull
    @Override
    public VocabularyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new VocabularyHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyHolder holder, int position) {
        holder.binding.vocabularyName.setText(vocabularyArrayList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),VocabularyShower.class);
                intent.putExtra("vocabulary", vocabularyArrayList.get(position));
                holder.itemView.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return vocabularyArrayList.size();
    }

    public class VocabularyHolder extends RecyclerView.ViewHolder {
        private RecyclerRowBinding binding;

        public VocabularyHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }


}
