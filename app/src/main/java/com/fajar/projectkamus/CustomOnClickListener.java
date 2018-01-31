package com.fajar.projectkamus;

import android.view.View;



public class CustomOnClickListener implements View.OnClickListener {
    private int position;
    private OnItemClickCallBack onItemClickCallBack;

    public CustomOnClickListener(int position, OnItemClickCallBack onItemClickCallBack) {
        this.position = position;
        this.onItemClickCallBack = onItemClickCallBack;
    }

    @Override
    public void onClick(View view) {
        onItemClickCallBack.onItemClicked(view, position);
    }

    private interface OnItemClickCallBack {
        void onItemClicked(View view, int position);
    }
}
