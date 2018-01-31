package com.fajar.projectkamus.Model;

import android.os.Parcel;
import android.os.Parcelable;



public class KamusIndoModel implements Parcelable {
    private int id;
    private String words;
    private String details;

    public KamusIndoModel(){

    }

    public KamusIndoModel(int id, String words, String details) {
        this.id = id;
        this.words = words;
        this.details = details;
    }

    public KamusIndoModel(String words, String details) {
        this.words = words;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.words);
        dest.writeString(this.details);
    }

    protected KamusIndoModel(Parcel in) {
        this.id = in.readInt();
        this.words = in.readString();
        this.details = in.readString();
    }

    public static final Parcelable.Creator<KamusIndoModel> CREATOR = new Parcelable.Creator<KamusIndoModel>() {
        @Override
        public KamusIndoModel createFromParcel(Parcel source) {
            return new KamusIndoModel(source);
        }

        @Override
        public KamusIndoModel[] newArray(int size) {
            return new KamusIndoModel[size];
        }
    };
}
