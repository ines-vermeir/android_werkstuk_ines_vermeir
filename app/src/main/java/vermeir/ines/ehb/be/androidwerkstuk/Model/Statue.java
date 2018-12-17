package vermeir.ines.ehb.be.androidwerkstuk.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Ines on 10/11/2018.
 */

@Entity
public class Statue  {
    @PrimaryKey
    @NonNull
    private int id;
    private String name;
    private String description;
    @Ignore
    private LatLng latLng;
    private Double lat;
    private Double lng;
    @Ignore
    private List<Question> questions;
    private Boolean complete;

    public Statue(){

    }

    @Ignore
    public Statue(@NonNull int id, String name, String description, LatLng latLng, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.latLng = latLng;
        this.lat = latLng.latitude;
        this.lng = latLng.longitude;
        this.questions = questions;
        this.complete = false;
    }


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
    public int getId() {
        return id;
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



    public Boolean isComplete() {
        return complete;
    }

    public void setId(int id) {
        this.id = id;
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



    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

 /*   public static final Creator<Statue> CREATOR = new Creator<Statue>() {
        @Override
        public Statue createFromParcel(Parcel in) {
            return new Statue(in);
        }

        @Override
        public Statue[] newArray(int size) {
            return new Statue[size];
        }
    };

    /* Parcelling part
    public Statue(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
        this.description =  in.readString();
        this.questions = in.readList();

    }
*//*


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeList(questions);
    }*/
}
