package vermeir.ines.ehb.be.androidwerkstuk.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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

        mLstStatues = findViewById(R.id.list_item);

        mStatuesViewModel = ViewModelProviders.of(this).get(StatueViewModel.class);

        mStatuesViewModel.getAllStatues().observe(this, new Observer<List<Statue>>() {
            @Override
            public void onChanged(@Nullable List<Statue> statues) {
                if(statues != null){
                    ArrayList<String> values = new ArrayList<>();
                    for (Statue statue : statues){
                        values.add(statue.getName());
                    }
                    adapter = new ArrayAdapter<>(showDatabase.this, android.R.layout.simple_list_item_1, values);
                    mLstStatues.setAdapter(adapter);
                }
            }
        });
    }
}
