package vermeir.ines.ehb.be.androidwerkstuk.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import vermeir.ines.ehb.be.androidwerkstuk.Model.Statue;
import vermeir.ines.ehb.be.androidwerkstuk.R;

public class QuestionActivity extends AppCompatActivity implements ButtonFragment.OnFragmentInteractionListener{

    private TextView infoStatue;
    private Statue statue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Bundle data = getIntent().getExtras();
        int statueId = data.getParcelable("statueId");

        infoStatue = findViewById(R.id.info_statue);

        //TODO: get statues from database

    }


    @Override
    public void change(int id) {
        TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.txtInfo);
        switch (id){
            case 1 : textFragment.setText(statue.getDescription());
            case 2 :
                //TODO:google places api voor adres weer te geven
                textFragment.setText("adres");
        }


    }
}
