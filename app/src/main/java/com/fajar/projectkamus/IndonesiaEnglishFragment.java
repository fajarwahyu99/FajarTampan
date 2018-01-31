package com.fajar.projectkamus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fajar.projectkamus.Adapter.KamusIndoAdapter;
import com.fajar.projectkamus.Database.KamusIndonesiaEnglishHelper;
import com.fajar.projectkamus.Model.KamusIndoModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class IndonesiaEnglishFragment extends Fragment {


    public IndonesiaEnglishFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerViewIndonesiaEnglish;
    SearchView searchViewIndonesiaEnglish;
    KamusIndonesiaEnglishHelper kamusIndonesiaEnglishHelper;
    KamusIndoAdapter kamusAdapter;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_indonesia_english, container, false);

        searchViewIndonesiaEnglish = view.findViewById(R.id.search_word_indonesia_english);
        searchViewIndonesiaEnglish.setFocusable(false);

        rootView = view.findViewById(R.id.root_layout_indo);

        recyclerViewIndonesiaEnglish = view.findViewById(R.id.recycler_indonesia_english);

        searchViewIndonesiaEnglish.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                kamusIndonesiaEnglishHelper = new KamusIndonesiaEnglishHelper(getContext());
                kamusAdapter = new KamusIndoAdapter(getContext());

                recyclerViewIndonesiaEnglish.setLayoutManager(new LinearLayoutManager(getContext()));

                recyclerViewIndonesiaEnglish.setAdapter(kamusAdapter);

                kamusIndonesiaEnglishHelper.open();

                ArrayList<KamusIndoModel> kamusIndoModels = kamusIndonesiaEnglishHelper.getDataByWordIndo(newText);

                kamusIndonesiaEnglishHelper.closeIndo();

                kamusAdapter.addItem(kamusIndoModels);

                return true;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        searchViewIndonesiaEnglish.setQuery("", false);
        rootView.requestFocus();
    }
}
