package cheers.pajaritj.cheers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;

/**
 * Created by jessa on 27/05/2017.
 */

public class Beer_Fragment extends Fragment{
    private TextView beer_description,beer_name,welcome,instructions;
    private ImageView beer_label;

    private ScrollView fragment_body;

    private String name = "name",description="description",label=" ";
    private boolean button_click = false;

    private Context context;
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle!=null) {
            name = bundle.getString("name");
            description = bundle.getString("description");
            label = bundle.getString("label");
            button_click = bundle.getBoolean("clicked");
        }
        context = getActivity().getApplicationContext();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.beer_fragment, container, false);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Enchanted Land.otf");
        Typeface button_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/CANDY.TTF");
        beer_description = (TextView) rootView.findViewById(R.id.Beer_Description);
        beer_name = (TextView) rootView.findViewById(R.id.Beer_Name);
        welcome = (TextView) rootView.findViewById(R.id.welcome_text);
        instructions = (TextView) rootView.findViewById(R.id.instructions);
        beer_label = (ImageView) rootView.findViewById(R.id.Beer_Label);
        fragment_body = (ScrollView) rootView.findViewById(R.id.scroll_fragment);

        welcome.setTypeface(button_font);

        if(button_click == true){
            welcome.setVisibility(View.INVISIBLE);
            instructions.setVisibility(View.INVISIBLE);
            fragment_body.setVisibility(View.VISIBLE);
            beer_name.setTypeface(custom_font);
            beer_description.setText(description);
            beer_name.setText(name);
            if(label != " ") {
                Picasso.with(rootView.getContext()).load(label).into(beer_label);
            }
            fragment_body.scrollTo(0,0);
        }
        else{
            fragment_body.setVisibility(View.INVISIBLE);
            welcome.setVisibility(View.VISIBLE);
            instructions.setVisibility(View.VISIBLE);
        }


        return rootView;
    }


}
