package vermeir.ines.ehb.be.androidwerkstuk.View;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import vermeir.ines.ehb.be.androidwerkstuk.Model.Statue;
import vermeir.ines.ehb.be.androidwerkstuk.R;
public class showDatabase extends AppCompatActivity {

    StatueViewModel mStatuesViewModel;
    ArrayAdapter adapter;
    ListView mLstStatues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_btn);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mLstStatues = findViewById(R.id.list_item);

        mStatuesViewModel = ViewModelProviders.of(this).get(StatueViewModel.class);

        mStatuesViewModel.getAllStatues().observe(this, statues -> {
            if(statues != null){
                ArrayList<String> values = new ArrayList<>();
                for (Statue statue : statues){
                    values.add(statue.getName());
                }
                adapter = new ArrayAdapter<>(showDatabase.this, android.R.layout.simple_list_item_1, values);
                mLstStatues.setAdapter(adapter);
            }
        });


    }
}
