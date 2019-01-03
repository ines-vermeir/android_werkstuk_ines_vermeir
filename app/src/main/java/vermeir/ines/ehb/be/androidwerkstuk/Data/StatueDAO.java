package vermeir.ines.ehb.be.androidwerkstuk.Data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import vermeir.ines.ehb.be.androidwerkstuk.Model.Statue;

/**
 * Created by Ines on 7/12/2018.
 */

@Dao
public interface StatueDAO {

    @Query("SELECT * FROM Statue")
    public abstract LiveData<List<Statue>> findAll();

    @Query("SELECT * FROM Statue WHERE id LIKE :id")
    public Statue findById(int id);

    @Delete
    public void deleteStatues(Statue... statues);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertStatues(Statue... statues);
}
