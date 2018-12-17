package vermeir.ines.ehb.be.androidwerkstuk.View;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import vermeir.ines.ehb.be.androidwerkstuk.Data.StatuesRepository;
import vermeir.ines.ehb.be.androidwerkstuk.Model.Statue;

/**
 * Created by Ines on 7/12/2018.
 */

public class StatueViewModel extends AndroidViewModel {
    private StatuesRepository statuesRepository;
    private LiveData<List<Statue>> mAllStatues;  // artist cache

    public StatueViewModel(@NonNull Application application) {
        super(application);
        statuesRepository = new StatuesRepository(application);
        mAllStatues = statuesRepository.getAllStatues();
    }

    public LiveData<List<Statue>> getAllStatues(){
        return mAllStatues;
    }



    public void insert(Statue statues) {
        statuesRepository.insertStatues(statues);
    }
}
