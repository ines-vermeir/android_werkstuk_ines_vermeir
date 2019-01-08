package vermeir.ines.ehb.be.androidwerkstuk.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vermeir.ines.ehb.be.androidwerkstuk.R;


public class TextFragment extends Fragment {

    TextView textViewInfo;
    ImageView imageView;

    public TextFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_text, container, false);

        textViewInfo = view.findViewById(R.id.txtInfo);
        imageView = view.findViewById(R.id.fotoStatue);


        return view;
    }



    public void setTextFragment(String description) {

        textViewInfo.setText(description);
    }

    public void setImgFragment(int id) {

        if(id == 1) {
            imageView.setImageResource(R.drawable.manneken_pis);
        }
        if(id == 2) {
            imageView.setImageResource(R.drawable.jeanneke_pis);
        }
        if(id == 3) {
            imageView.setImageResource(R.drawable.leopold_ii);

        }
        if(id == 4) {
            imageView.setImageResource(R.drawable.godfried);

        }
    }



}
