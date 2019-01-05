package vermeir.ines.ehb.be.androidwerkstuk.Data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vermeir.ines.ehb.be.androidwerkstuk.Model.Question;
import vermeir.ines.ehb.be.androidwerkstuk.Model.Statue;
import vermeir.ines.ehb.be.androidwerkstuk.Model.StatueWithQuestions;

/**
 * Created by Ines on 7/12/2018.
 */

@Dao
public abstract class StatueDAO {

    @Transaction @Query("SELECT * FROM Statue")
    abstract List<StatueWithQuestions> loadStatueWithQuestions();

    @Transaction
    public void insertStatueWithQuestions(List<Statue> statues){

        try {
            for (Statue s : statues) {
                for (Question q : s.getQuestions()) {
                    q.setStatueId(s.getId());
                }
                insertStatue(s);
                insertQuestions(s.getQuestions());

            }
        }catch (Exception e){
            Log.e("ERROR DAO", e.getMessage());
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertQuestions(List<Question> questions);

    @Query("SELECT * FROM Statue")
    public abstract LiveData<List<Statue>> findAll();

    @Transaction @Query("SELECT * FROM Statue WHERE id LIKE :id")
    abstract StatueWithQuestions findById(int id);

    @Transaction
    public List<Statue> getStatuesWithQ() {
        List<StatueWithQuestions> statueWithQuestions = loadStatueWithQuestions();
        List<Statue> statues = new ArrayList<Statue>(statueWithQuestions.size());
        for(StatueWithQuestions i : statueWithQuestions) {
            i.statue.setQuestions(i.questions);
            statues.add(i.statue);
        }
        return statues;
    }

    @Transaction
    public Statue getStatuesByIdWithQ(int id) {
        StatueWithQuestions statueWithQuestions = findById(id);
        Statue statue = statueWithQuestions.statue;
        statue.setQuestions(statueWithQuestions.questions);
        return statue;
    }

    @Delete
    abstract void deleteStatues(Statue... statues);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertStatue(Statue statues);

    @Update
    abstract void updateStatue(Statue... statues);
}
