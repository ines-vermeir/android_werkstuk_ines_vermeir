package vermeir.ines.ehb.be.androidwerkstuk.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vermeir.ines.ehb.be.androidwerkstuk.R;


public class TextFragment extends Fragment {

    TextView textViewInfo;

    public TextFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_text, container, false);

        textViewInfo = view.findViewById(R.id.txtInfo);

        return view;
    }



    public void setText(String description) {
        textViewInfo.setText(description);
    }


}
