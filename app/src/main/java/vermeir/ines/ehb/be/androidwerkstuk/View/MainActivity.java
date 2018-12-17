package vermeir.ines.ehb.be.androidwerkstuk.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vermeir.ines.ehb.be.androidwerkstuk.Data.StatuesRepository;
import vermeir.ines.ehb.be.androidwerkstuk.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        StatuesRepository mRepository = new StatuesRepository(this.getApplication());
        //mRepository.insertStatues(statues);
    }

    public void startTheTour( View view ){
        //Toast.makeText(this, "Start" , Toast.LENGTH_SHORT).show();
        //Log.i(TAG, " User clicked the start button");
        //Intent intent = new Intent(this, MapsActivity.class);
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void goToDatabase(View view) {
        Intent intent = new Intent(this,showDatabase.class);
        startActivity(intent);
    }
}
