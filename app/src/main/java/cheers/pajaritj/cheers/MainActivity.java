package cheers.pajaritj.cheers;

import android.app.FragmentManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String API_KEY = "0e94bfe6b6502157183e836c3ddfc61f";

    private final static String HAS_LABELS = "Y";

    private static final String TAG_RETAINED_FRAGMENT = "Retained Beer Data Fragment";

    private TextView beer_description,beer_name,welcome,instructions;

    private ImageView beer_label;

    private Button cheers_button;

    private ApiInterface apiService;

    private RetainedFragment mRetainedFragment;

    private ScrollView main_body;

    private Toolbar toolbar;

    private TextView toolbar_title;

    private String description = "No Description Available";

    private boolean loaded_screen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialisation
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Enchanted Land.otf");
        Typeface button_font = Typeface.createFromAsset(getAssets(), "fonts/CANDY.TTF");
        beer_description = (TextView) findViewById(R.id.Beer_Description);
        beer_name = (TextView) findViewById(R.id.Beer_Name);
        welcome = (TextView) findViewById(R.id.welcome_text);
        instructions = (TextView) findViewById(R.id.instructions);
        beer_label = (ImageView) findViewById(R.id.Beer_Label);
        cheers_button = (Button) findViewById(R.id.Cheers_Button);
        main_body = (ScrollView) findViewById(R.id.Main_Body);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        welcome.setTypeface(button_font);
        toolbar_title.setTypeface(button_font);
        beer_name.setTypeface(custom_font);
        cheers_button.setTypeface(button_font);
        final FragmentManager fm = getFragmentManager();
        mRetainedFragment = (RetainedFragment) fm.findFragmentByTag(TAG_RETAINED_FRAGMENT);
        apiService = ApiClient.getClient().create(ApiInterface.class);

        // Retain Fragments after configuration changes
        if ( mRetainedFragment != null) {
            loaded_screen=mRetainedFragment.getLoaded();
            setTexts(mRetainedFragment.getData());
        }else {
            mRetainedFragment = new RetainedFragment();
            fm.beginTransaction().add(mRetainedFragment, TAG_RETAINED_FRAGMENT).commit();
            //Set loaded_screen=false
            mRetainedFragment.setLoaded(false);
        }

        cheers_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                apiService.getBeers(HAS_LABELS, API_KEY).enqueue(new Callback<Beer_Response>() {
                    @Override
                    public void onResponse(Call<Beer_Response> call, Response<Beer_Response> response) {
                        Log.d("TAG", response.code() + "");
                        Beer_Response resource = response.body();
                        Beer beer =resource.data;
                        mRetainedFragment.setLoaded(true);
                        loaded_screen=true;
                        setTexts(beer);
                        mRetainedFragment.setData(resource.data);
                    }

                    @Override
                    public void onFailure(Call<Beer_Response> call, Throwable t) {
                        Log.e(TAG, t.toString());
                    }
                });

                main_body.scrollTo(0, 0);
            }

        });

    }

    @Override
    public void onPause() {
        super.onPause();
        if(isFinishing()) {
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().remove(mRetainedFragment).commit();
        }
    }
    // Change texts and handle visibility for textviews
    public void setTexts(Beer beer){
        if(loaded_screen==true) {
            welcome.setVisibility(View.INVISIBLE);
            instructions.setVisibility(View.INVISIBLE);
            main_body.setVisibility(View.VISIBLE);
            if(beer!=null) {
                if (beer.style.description != null) {
                    description = beer.style.description;
                }
                beer_description.setText(beer.description != null ? beer.description : description);
                beer_name.setText(beer.name);
                Picasso.with(MainActivity.this).load(beer.labels.large).into(beer_label);
                loaded_screen=true;
            }else{
                Toast.makeText(MainActivity.this, "Ran out of Beers", Toast.LENGTH_LONG).show();

            }
        }
        else{
            welcome.setVisibility(View.VISIBLE);
            instructions.setVisibility(View.VISIBLE);
            main_body.setVisibility(View.INVISIBLE);
        }

    }
}