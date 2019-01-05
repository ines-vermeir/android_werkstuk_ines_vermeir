package vermeir.ines.ehb.be.androidwerkstuk.Data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

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

@Database(entities = {Statue.class, Question.class}, version = 4, exportSchema = false)
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
                                                    Log.e("ERROR DAO", "Voor insert");
                                                   getDatabase(context).statueDAO().insertStatueWithQuestions(populateData(context));
                                                    Log.e("ERROR DAO", "na insert");
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


    public static List<Statue> populateData(final Context context) {

        //1 STANDBEELD
        //antwoorden voor vraag
        HashMap<String, Boolean> answers11 = new HashMap<>();
        answers11.put(context.getString(R.string.hoegrootismannekepisAntw1),true);
        answers11.put(context.getString(R.string.hoegrootismannekepisAntw2), false);
        answers11.put(context.getString(R.string.hoegrootismannekepisAntw3), false);
        //Vraag
        Question question11 = new Question(
                1,
                context.getString(R.string.hoegrootismannenpisVraag),
                answers11
        );


        //antwoorden voor vraag
        HashMap<String, Boolean> answers12 = new HashMap<>();
        answers12.put(context.getString(R.string.geraardsbergen),true);
        answers12.put(context.getString(R.string.halle), false);
        answers12.put(context.getString(R.string.aalst), false);
        //Vraag
        Question question12 = new Question(
                1,
                context.getString(R.string.WaarstaaternogeenmannekenpisVraag),
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
        answers21.put(context.getString(R.string.data1987),true);
        answers21.put(context.getString(R.string.data1948), false);
        answers21.put(context.getString(R.string.data2002), false);
        //Vraag
        Question question21 = new Question(
                2,
                context.getString(R.string.InwelkjaarisjeanekepisgeplaatstVraag),
                answers21
        );


        //antwoorden voor vraag
        HashMap<String, Boolean> answers22 = new HashMap<>();
        answers22.put(context.getString(R.string.huisnummer1012),true);
        answers22.put(context.getString(R.string.huisnummer2527), false);
        answers22.put(context.getString(R.string.huisnummer12), false);
        //Vraag
        Question question22 = new Question(
                2,
                context.getString(R.string.tussenwelkehuisnummersstaatzeVraag),
                answers22
        );



        //vragen aan lijst toevoegen
        List<Question> questions21 = new ArrayList<Question>();
        questions21.add(question21);
        questions21.add(question22);

        //standbeeld aanmaken
        Statue jeanekepis = new Statue(2, context.getString(R.string.jeanekepis), context.getString(R.string.descriptionJeanekepis), new LatLng(50.8484807,4.3518519),questions21);

//_____________________________________________________________
        //3 STANDBEELD
        //antwoorden voor vraag
        HashMap<String, Boolean> answers31 = new HashMap<>();
        answers31.put(context.getString(R.string.oostende),true);
        answers31.put(context.getString(R.string.brugge), false);
        answers31.put(context.getString(R.string.antwerpen), false);
        //Vraag
        Question question31 = new Question(
                3,
                context.getString(R.string.InwelkestadstaatnogeenleopoldVraag),
                answers31
        );




        //vragen aan lijst toevoegen
        List<Question> questions31 = new ArrayList<Question>();
        questions31.add(question31);

        //standbeeld aanmaken
        Statue leopold = new Statue(3, context.getString(R.string.leopold), context.getString(R.string.descriptionLeopold), new LatLng(50.8404693,4.3622069),questions31);

        //_____________________________________________________________
        //4 STANDBEELD
        //antwoorden voor vraag
        HashMap<String, Boolean> answers41 = new HashMap<>();
        answers41.put(context.getString(R.string.data1848),true);
        answers41.put(context.getString(R.string.data1100), false);
        answers41.put(context.getString(R.string.data1830), false);
        //Vraag
        Question question41 = new Question(
                4,
                context.getString(R.string.InwelkjaarisgodfriedgemaaktVraag),
                answers41
        );




        //vragen aan lijst toevoegen
        List<Question> questions41 = new ArrayList<Question>();
        questions41.add(question41);

        //standbeeld aanmaken
        Statue godfried = new Statue(4, context.getString(R.string.godfried), context.getString(R.string.descriptionGodfried), new LatLng(50.8422926,4.3585692),questions41);

        //______________________________________________________________

        List<Statue> statues = new ArrayList<>();
        statues.add(mannekepis);
        statues.add(jeanekepis);
        statues.add(leopold);
        statues.add(godfried);
        return statues;

    }
}
