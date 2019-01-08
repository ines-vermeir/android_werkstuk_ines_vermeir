package vermeir.ines.ehb.be.androidwerkstuk.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vermeir.ines.ehb.be.androidwerkstuk.Model.Statue;
import vermeir.ines.ehb.be.androidwerkstuk.R;

public class InformationActivity extends AppCompatActivity implements ButtonFragment.OnFragmentInteractionListener{

    private TextView infoStatue;
    private Statue statue;

    private StatueViewModel mStatuesViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle data = getIntent().getExtras();
        int statueId = data.getInt("statueId");



        mStatuesViewModel = ViewModelProviders.of(this).get(StatueViewModel.class);
        statue = mStatuesViewModel.getStatueById(statueId);

        statue.setComplete(Boolean.TRUE);

        mStatuesViewModel.update(statue);
    }


    @Override
    public void change(int id) {
        TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.textFragment);
        switch (id){
            case 1 : textFragment.setTextFragment(statue.getDescription()); break;
            case 2 :

                textFragment.setImgFragment(statue.getId());
                break;
        }


    }



    public void backToMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
