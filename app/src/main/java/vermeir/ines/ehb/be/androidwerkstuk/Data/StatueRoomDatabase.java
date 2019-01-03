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
                                    //.fallbackToDestructiveMigration()
                                    .allowMainThreadQueries()
                                    .build();
                }
            }
        }
        return mINSTANCE;
    }


    public static Statue[] populateData(final Context context) {

//TODO: alles in string resource zetten
        //1 STANDBEELD
        //antwoorden voor vraag
        HashMap<String, Boolean> answers11 = new HashMap<>();
        answers11.put("58 cm",true);
        answers11.put("1m 20cm", false);
        answers11.put("1m", false);
        //Vraag
        Question question11 = new Question(
                1,
                "Hoe groot is manneke pis?",
                answers11
        );


        //antwoorden voor vraag
        HashMap<String, Boolean> answers12 = new HashMap<>();
        answers12.put("Geraardsbergen",true);
        answers12.put("Halle", false);
        answers12.put("Aalst", false);
        //Vraag
        Question question12 = new Question(
                1,
                "Waar staat er nog een manneke pis?",
                answers12
        );



        //vragen aan lijst toevoegen
        List<Question> questions11 = new ArrayList<Question>();
        questions11.add(question11);
        questions11.add(question12);




        //standbeeld aanmaken
        Statue mannekepis = new Statue(1, context.getString(R.string.nameMannekepis), context.getString(R.string.descriptionMannekepis), new LatLng(50.8450905,4.3490178),questions11);

        //_____________________________________________________________
        //2 STANDBEELD
        //antwoorden voor vraag
        HashMap<String, Boolean> answers21 = new HashMap<>();
        answers21.put("1987 cm",true);
        answers21.put("1948", false);
        answers21.put("2002", false);
        //Vraag
        Question question21 = new Question(
                2,
                "In welk jaar is Jeaneke pis geplaatst?",
                answers21
        );


        //antwoorden voor vraag
        HashMap<String, Boolean> answers22 = new HashMap<>();
        answers22.put("10 - 12",true);
        answers22.put("25-27", false);
        answers22.put("1 - 2", false);
        //Vraag
        Question question22 = new Question(
                2,
                "Tussen welke huisnummers staat ze?",
                answers22
        );



        //vragen aan lijst toevoegen
        List<Question> questions21 = new ArrayList<Question>();
        questions21.add(question21);
        questions21.add(question22);

        //standbeeld aanmaken
        Statue jeanekepis = new Statue(2, context.getString(R.string.jeanekepis), context.getString(R.string.descriptionMannekepis), new LatLng(50.8484807,4.3518519),questions21);
//TODO: change description to jeaneke pis
//_____________________________________________________________
        //3 STANDBEELD
        //antwoorden voor vraag
        HashMap<String, Boolean> answers31 = new HashMap<>();
        answers31.put("Oostende",true);
        answers31.put("Brugge", false);
        answers31.put("Antwerpen", false);
        //Vraag
        Question question31 = new Question(
                3,
                "In welke beglsiche stad staat er nog een ruitersstandbeeld van Leopold II?",
                answers31
        );




        //vragen aan lijst toevoegen
        List<Question> questions31 = new ArrayList<Question>();
        questions31.add(question31);

        //standbeeld aanmaken
        Statue leopold = new Statue(3, context.getString(R.string.leopold), context.getString(R.string.descriptionMannekepis), new LatLng(50.8404693,4.3622069),questions31);
//TODO: change description to leopold
        //_____________________________________________________________
        //4 STANDBEELD
        //antwoorden voor vraag
        HashMap<String, Boolean> answers41 = new HashMap<>();
        answers41.put("1848 cm",true);
        answers41.put("1100", false);
        answers41.put("1830", false);
        //Vraag
        Question question41 = new Question(
                4,
                "In welk jaar is het standbeeld van Godfried  van Bouillon gemaatk?",
                answers41
        );




        //vragen aan lijst toevoegen
        List<Question> questions41 = new ArrayList<Question>();
        questions41.add(question41);

        //standbeeld aanmaken
        Statue godfried = new Statue(4, context.getString(R.string.godfried), context.getString(R.string.descriptionMannekepis), new LatLng(50.8422926,4.3585692),questions41);
//TODO: change description to godfried

        //_____________________________________________________________
        questionDAO().insertQuestions(question11);
        questionDAO().insertQuestions(question12);
        questionDAO().insertQuestions(question21);
        questionDAO().insertQuestions(question22);
        questionDAO().insertQuestions(question31);
        questionDAO().insertQuestions(question41);

        //______________________________________________________________
        return new Statue[] {
                mannekepis, jeanekepis, leopold, godfried
        };
    }
}
