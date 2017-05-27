package cheers.pajaritj.cheers;


import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        private TextView beer_description,beer_name;

        private ImageView beer_label;

        private Button cheers_button;

        private ApiInterface apiService;

        private RetainedFragment mRetainedFragment;

        private ScrollView main_body;

        private Toolbar toolbar;

        private TextView toolbar_title;

        private Beer current_beer;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Enchanted Land.otf");
            Typeface button_font = Typeface.createFromAsset(getAssets(),  "fonts/CANDY.TTF");
            beer_description = (TextView) findViewById(R.id.Beer_Description);
            beer_name = (TextView) findViewById(R.id.Beer_Name);
            beer_label = (ImageView) findViewById(R.id.Beer_Label);
            cheers_button= (Button) findViewById(R.id.Cheers_Button);
            main_body = (ScrollView) findViewById(R.id.Main_Body);
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar_title = (TextView) findViewById(R.id.toolbar_title);
            toolbar_title.setTypeface(button_font);
            beer_name.setTypeface(custom_font);
            cheers_button.setTypeface(button_font);
            final FragmentManager fm = getFragmentManager();
            mRetainedFragment = (RetainedFragment) fm.findFragmentByTag(TAG_RETAINED_FRAGMENT);
            apiService = ApiClient.getClient().create(ApiInterface.class);

            if(mRetainedFragment!=null) {
                current_beer = mRetainedFragment.getData();
                beer_description.setText(current_beer.description !=null ? current_beer.description : current_beer.style.description);
                beer_name.setText(current_beer.name);
                Picasso.with(MainActivity.this).load(current_beer.labels.large).into(beer_label);
            }
            else {
                mRetainedFragment = new RetainedFragment();
                fm.beginTransaction().add(mRetainedFragment, TAG_RETAINED_FRAGMENT).commit();
                apiService.getBeers(HAS_LABELS, API_KEY).enqueue(new Callback<Beer_Response>() {
                    @Override
                    public void onResponse(Call<Beer_Response> call, Response<Beer_Response> response) {
                        Log.d("TAG", response.code() + "");
                        Beer_Response resource = response.body();
                        current_beer = resource.data;
                        mRetainedFragment.setData(current_beer);
                        beer_description.setText(current_beer.description != null ? current_beer.description : current_beer.style.description);
                        beer_name.setText(current_beer.name);
                        Picasso.with(MainActivity.this).load(current_beer.labels.large).into(beer_label);
                            // load data from a data source or perform any calculation

                    }

                    @Override
                    public void onFailure(Call<Beer_Response> call, Throwable t) {
                        Log.e(TAG, t.toString());
                    }
                });
            }
            cheers_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    apiService.getBeers(HAS_LABELS,API_KEY).enqueue(new Callback<Beer_Response>() {
                        @Override
                        public void onResponse(Call<Beer_Response> call, Response<Beer_Response> response) {
                            Log.d("TAG",response.code()+"");
                            Beer_Response resource=response.body();
                            current_beer = resource.data;
                            mRetainedFragment.setData(current_beer);
                            beer_description.setText(current_beer.description !=null ? current_beer.description : current_beer.style.description);
                            beer_name.setText(current_beer.name);
                            Picasso.with(MainActivity.this).load(current_beer.labels.large).into(beer_label);

                        }

                        @Override
                        public void onFailure(Call<Beer_Response> call, Throwable t) {
                            Log.e(TAG, t.toString());
                        }
                    });
                    main_body.scrollTo(0,0);
                    }

        });
        }
}


