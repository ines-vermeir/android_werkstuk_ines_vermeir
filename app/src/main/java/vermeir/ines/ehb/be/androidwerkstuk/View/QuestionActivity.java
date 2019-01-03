package vermeir.ines.ehb.be.androidwerkstuk.View;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.Map;

import vermeir.ines.ehb.be.androidwerkstuk.Model.Question;
import vermeir.ines.ehb.be.androidwerkstuk.Model.Statue;
import vermeir.ines.ehb.be.androidwerkstuk.R;

public class QuestionActivity extends AppCompatActivity {

    private TextView infoStatue;
    private Statue statue;

    private StatueViewModel mStatuesViewModel;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle data = getIntent().getExtras();
        int statueId = data.getInt("statueId");

        infoStatue = findViewById(R.id.info_statue);

        //TODO: get statues from database
        mStatuesViewModel = ViewModelProviders.of(this).get(StatueViewModel.class);

        statue = mStatuesViewModel.getStatueById(statueId);
        statue.setQuestions(mStatuesViewModel.getAllQuestionsStatue(statueId));

        Toast.makeText(this, Integer.toString(statueId), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Integer.toString(statue.getQuestions().size()), Toast.LENGTH_SHORT).show();

        linearLayout = findViewById(R.id.linearLayoutQ);

        for (Question question: statue.getQuestions()) {
            TextView q = new TextView(this);
            q.setText(question.getQuestion());
            linearLayout.addView(q);

            Toast.makeText(this, question.getQuestion(), Toast.LENGTH_SHORT).show();

            RadioGroup radioGroup = new RadioGroup(this);
            linearLayout.addView(radioGroup);

            Iterator it = question.getAnswers().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();

                RadioButton a = new RadioButton(this);
                a.setText(pair.getKey().toString());
                radioGroup.addView(a);
                linearLayout.addView(a);

                it.remove(); // avoids a ConcurrentModificationException
            }
        }

        Button button = new Button(this);
        button.setText("Volgende");
        //TODO: string value van maken
        linearLayout.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAnswers();
            }
        });
    }

    private void CheckAnswers(){
        Toast.makeText(this, "vragen worden gecontroleerd", Toast.LENGTH_SHORT).show();
    }
}
