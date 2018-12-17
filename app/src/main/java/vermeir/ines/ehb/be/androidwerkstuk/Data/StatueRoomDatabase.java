package vermeir.ines.ehb.be.androidwerkstuk.Data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;

import vermeir.ines.ehb.be.androidwerkstuk.Model.Question;
import vermeir.ines.ehb.be.androidwerkstuk.Model.Statue;
import vermeir.ines.ehb.be.androidwerkstuk.R;

/**
 * Created by Ines on 7/12/2018.
 */

@Database(entities = {Statue.class, Question.class}, version = 3)
public abstract class StatueRoomDatabase extends RoomDatabase {

    public abstract StatueDAO statueDAO();
    public abstract QuestionDAO questionDAO();

    private static volatile StatueRoomDatabase mINSTANCE;

    public static StatueRoomDatabase getDatabase(final Context context)
    {
        if(mINSTANCE == null)
        {
            synchronized (StatueRoomDatabase.class) {
                if(mINSTANCE == null){
                    mINSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    StatueRoomDatabase.class,
                                    "statueApp_db")
                                    .addCallback(new Callback() {
                                        @Override
                                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                            super.onCreate(db);
                                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                                @Override
                                                public void run() {
                                                    getDatabase(context).statueDAO().insertStatues(populateData(context));
                                                }
                                            });
                                        }
                                    })
                                    .fallbackToDestructiveMigration()
                                    .build();
                }
            }
        }
        return mINSTANCE;
    }


    public static Statue[] populateData(final Context context) {


        //1 STANDBEELD
        //antwoorden voor vraag
        HashMap<String, Boolean> answers1 = new HashMap<>();
        answers1.put("58 cm",true);
        answers1.put("1m 20cm", false);
        answers1.put("1m", false);
        //Vraag
        Question question1 = new Question(
                1,
                "Hoe groot is manneke pis",
                answers1
        );


        //antwoorden voor vraag
        HashMap<String, Boolean> answers2 = new HashMap<>();
        answers1.put("Geraardsbergen",true);
        answers1.put("Halle", false);
        answers1.put("Aalst", false);
        //Vraag
        Question question2 = new Question(
                1,
                "Waar staat er nog een manneke pis",
                answers2
        );



        //vragen aan lijst toevoegen
        List<Question> questions1 = new ArrayList<Question>();
        questions1.add(question1);
        questions1.add(question2);

        //standbeeld aanmaken
        Statue mannekepis = new Statue(1, context.getString(R.string.nameMannekepis), context.getString(R.string.descriptionMannekepis), new LatLng(50.8450905,4.3490178),questions1);


        return new Statue[] {
                mannekepis
        };
    }
}
//@NonNull String id, String name, String description, LatLng latLng, List<Question> questions)
//@NonNull int id, int statueId, String question, HashMap<String, String> answers