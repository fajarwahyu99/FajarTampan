package com.fajar.projectkamus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fajar.projectkamus.DetailsActivity;
import com.fajar.projectkamus.Model.KamusIndoModel;
import com.fajar.projectkamus.R;

import java.util.ArrayList;



public class KamusIndoAdapter extends RecyclerView.Adapter<KamusIndoAdapter.KamusIndoHolder> {

    private ArrayList<KamusIndoModel> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public KamusIndoAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public KamusIndoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);

        return new KamusIndoHolder(view);
    }

    public void addItem(ArrayList<KamusIndoModel> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(KamusIndoHolder holder, int position) {
        final String words = mData.get(position).getWords();
        final String details_words = mData.get(position).getDetails();

        holder.textViewWord.setText(words);
        holder.textViewWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(DetailsActivity.EXTRA_WORDS, words);
                intent.putExtra(DetailsActivity.EXTRA_DETAILS, details_words);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class KamusIndoHolder extends RecyclerView.ViewHolder{
        private TextView textViewWord;
        public KamusIndoHolder(View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.txt_words);
        }
    }
}
