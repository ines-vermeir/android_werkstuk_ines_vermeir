package vermeir.ines.ehb.be.androidwerkstuk.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;

import vermeir.ines.ehb.be.androidwerkstuk.Model.Question;
import vermeir.ines.ehb.be.androidwerkstuk.Model.Statue;
import vermeir.ines.ehb.be.androidwerkstuk.R;

public class QuestionActivity extends AppCompatActivity {


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


        mStatuesViewModel = ViewModelProviders.of(this).get(StatueViewModel.class);
        statue = mStatuesViewModel.getStatueById(statueId);


        addLayout();


    }

    private void addLayout(){
        linearLayout = findViewById(R.id.linearLayoutQ);

        for (Question question: statue.getQuestions()) {
            TextView q = new TextView(this);
            q.setText(question.getQuestion());
            linearLayout.addView(q);

            RadioGroup radioGroup = new RadioGroup(this);


            Iterator it = question.getAnswers().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();

                RadioButton a = new RadioButton(this);
                a.setText(pair.getKey().toString());
                radioGroup.addView(a);

            }

            //TODO antwoorden random zetten
            radioGroup.setId(question.getId() + 1000);
            linearLayout.addView(radioGroup);
        }

        Button button = new Button(this);
        button.setText("Volgende");
        //TODO: string value van maken
        linearLayout.addView(button);
        button.setOnClickListener(v -> CheckAnswers());
    }

    private void CheckAnswers(){
        boolean correct = true;

        for(Question question : statue.getQuestions()){
            RadioGroup radioGroup = (RadioGroup) findViewById(question.getId() + 1000);
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            String selected = (String)radioButton.getText().toString();

            if(question.getAnswers().get(selected) != Boolean.TRUE) {
                radioButton.setTextColor(Color.RED);
                correct = false;
            }
        }

        if(correct){
            Intent intent = new Intent(this, InformationActivity.class);
            intent.putExtra("statueId", statue.getId());
            startActivity(intent);
        }else{
            return;
        }
    }
}
