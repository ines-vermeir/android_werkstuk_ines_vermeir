package vermeir.ines.ehb.be.androidwerkstuk.Data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import vermeir.ines.ehb.be.androidwerkstuk.Model.Question;

/**
 * Created by Ines on 7/12/2018.
 */

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM Question")
    public abstract LiveData<List<Question>> findAll();

    @Query("SELECT * FROM Question WHERE statue_id IS :id ")
    public abstract List<Question> findAllByStatue(int id);

    @Delete
    public void deleteQuestions(Question... questions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertQuestions(Question... questions);
}
