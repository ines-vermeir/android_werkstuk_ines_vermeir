package vermeir.ines.ehb.be.androidwerkstuk.Data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import vermeir.ines.ehb.be.androidwerkstuk.Model.Question;
import vermeir.ines.ehb.be.androidwerkstuk.Model.Statue;

/**
 * Created by Ines on 7/12/2018.
 */

public class StatuesRepository {

    private StatueDAO mStatueDAO;
    private QuestionDAO mQuestionDAO;
    private LiveData<List<Statue>> mAllStatues;
    private List<Question> mAllQuestionsStatue;

    public StatuesRepository (Application application) {
        StatueRoomDatabase db =
                StatueRoomDatabase.getDatabase(application.getApplicationContext());
        mStatueDAO = db.statueDAO();
        mQuestionDAO = db.questionDAO();
    }

    public void insertStatues(Statue... statues)
    {
        new insertStatuesAsync(mStatueDAO).execute(statues);
    }

    public void insertQuestion(Question... questions)
    {
        new insertQuestionsAsync(mQuestionDAO).execute(questions);
    }

    public LiveData<List<Statue>> getAllStatues() {
        if(mAllStatues == null){
            mAllStatues = mStatueDAO.findAll();
        }
        return mAllStatues;
    }


    public List<Question> getAllQuestionByStatue(int id) {
        if(mAllQuestionsStatue == null){
            mAllQuestionsStatue = mQuestionDAO.findAllByStatue(id);
        }
        return mAllQuestionsStatue;
    }

    public Statue getStatueById(int id){
        return mStatueDAO.findById(id);
    }

    private static class insertStatuesAsync extends AsyncTask<Statue, Void, Void> {
        private StatueDAO statueDAO;

        insertStatuesAsync(StatueDAO statueDAO) {
            this.statueDAO = statueDAO;
        }

        @Override
        protected Void doInBackground(Statue... statues) {
            //statueDAO.insertStatues(statues);
            //return DataSingleton.getInstance().downloadPlainText(strings[0]);
            return null;
        }
    }


    private static class insertQuestionsAsync extends AsyncTask<Question, Void, Void> {
        private QuestionDAO questionDAO;

        insertQuestionsAsync(QuestionDAO questionDAO) {
            this.questionDAO = questionDAO;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            questionDAO.insertQuestions(questions);
            return null;
        }
    }
}
