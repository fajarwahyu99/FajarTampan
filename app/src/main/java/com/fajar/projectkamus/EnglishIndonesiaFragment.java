package com.fajar.projectkamus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fajar.projectkamus.Adapter.KamusAdapter;
import com.fajar.projectkamus.Database.KamusEnglishIndonesiaHelper;
import com.fajar.projectkamus.Model.KamusModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnglishIndonesiaFragment extends Fragment {


    public EnglishIndonesiaFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerViewEnglishIndonesia;
    SearchView searchViewEnglishIndonesia;
    KamusEnglishIndonesiaHelper kamusEnglishIndonesiaHelper;
    KamusAdapter kamusAdapter;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_english_indonesia, container, false);

        searchViewEnglishIndonesia = view.findViewById(R.id.search_word_english_indonesia);
        searchViewEnglishIndonesia.setFocusable(false);
        rootView = view.findViewById(R.id.root_layout);

        recyclerViewEnglishIndonesia = view.findViewById(R.id.recycler_english_indonesia);

        searchViewEnglishIndonesia.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                kamusEnglishIndonesiaHelper = new KamusEnglishIndonesiaHelper(getContext());
                kamusAdapter = new KamusAdapter(getContext());

                recyclerViewEnglishIndonesia.setLayoutManager(new LinearLayoutManager(getContext()));

                recyclerViewEnglishIndonesia.setAdapter(kamusAdapter);

                kamusEnglishIndonesiaHelper.open();

                ArrayList<KamusModel> kamusModels = kamusEnglishIndonesiaHelper.getDataByWord(newText);

                kamusEnglishIndonesiaHelper.close();

                kamusAdapter.addItem(kamusModels);

                return true;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        searchViewEnglishIndonesia.setQuery("",false);
        rootView.requestFocus();
    }
}
