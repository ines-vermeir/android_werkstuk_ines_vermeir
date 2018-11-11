package vermeir.ines.ehb.be.androidwerkstuk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startTheTour( View view ){
        //Toast.makeText(this, "Start" , Toast.LENGTH_SHORT).show();
        //Log.i(TAG, " User clicked the start button");
        //Intent intent = new Intent(this, MapsActivity.class);
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}
