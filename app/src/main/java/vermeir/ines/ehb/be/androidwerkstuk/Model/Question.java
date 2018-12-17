package vermeir.ines.ehb.be.androidwerkstuk.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.HashMap;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Ines on 7/12/2018.
 */


@Entity(foreignKeys = @ForeignKey(entity = Statue.class,parentColumns = "id", childColumns = "statue_id", onDelete = CASCADE), indices = {@Index("statue_id")})
public class Question {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo(name = "statue_id")
    private int statueId;
    private String question;
    @TypeConverters(answerConverter.class)
    private HashMap<String,Boolean> answers;

    public Question() {
    }

    @Ignore
    public Question( int statueId, String question, HashMap<String, Boolean> answers) {

        this.statueId = statueId;
        this.question = question;
        this.answers = answers;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public int getStatueId() {
        return statueId;
    }

    public String getQuestion() {
        return question;
    }

    public HashMap<String, Boolean> getAnswers() {
        return answers;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setStatueId(int statueId) {
        this.statueId = statueId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(HashMap<String, Boolean> answers) {
        this.answers = answers;
    }
}
