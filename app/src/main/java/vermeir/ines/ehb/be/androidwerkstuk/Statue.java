package vermeir.ines.ehb.be.androidwerkstuk;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by Ines on 10/11/2018.
 */

class Statue implements Parcelable {
    private String id;
    private String name;
    private String description;
    private LatLng latLng;
    private HashMap<String,String> questions;
    private boolean complete;

    public Statue(String id, String name, String description, double lat, double lng) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.latLng = new LatLng(lat, lng);
        complete = false;
        questions = new HashMap<>();
    }

    public static final Creator<Statue> CREATOR = new Creator<Statue>() {
        @Override
        public Statue createFromParcel(Parcel in) {
            return new Statue(in);
        }

        @Override
        public Statue[] newArray(int size) {
            return new Statue[size];
        }
    };

    public void addQuestion(String question, String answer){
        questions.put(question, answer);
        return;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }


    public HashMap<String, String> getQuestion() {
        return questions;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setQuestion(HashMap<String, String> question) {
        this.questions = question;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Parcelling part
    public Statue(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
        this.description =  in.readString();
        this.questions = in.readHashMap(getClass().getClassLoader());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeMap(questions);
    }
}
