package cheers.pajaritj.cheers;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import cheers.pajaritj.cheers.BeerResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Map;
import java.util.Random;



public class MainActivity extends AppCompatActivity {

        private static final String TAG = MainActivity.class.getSimpleName();

        private final static String API_KEY = "0e94bfe6b6502157183e836c3ddfc61f";

        private final int CHEERS_LIMIT = 15;

        private TextView beer_description,beer_name;

        private ImageView beer_label;

        private Button cheers_button;

        private ApiInterface apiService;

        Random rand;

        List <BeerResponse.Beer> beers;

        private int current_beer=1;
        private int current_page =1;
        private int max_page=1;
        private int cheers_counter = 0;
        private int data_counter =1;
        private int glass_value=5;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Draft Beer.ttf");
            beer_description = (TextView) findViewById(R.id.Beer_Description);
            beer_name = (TextView) findViewById(R.id.Beer_Name);
            beer_name.setTypeface(custom_font);
            beer_label = (ImageView) findViewById(R.id.Beer_Label);
            cheers_button= (Button) findViewById(R.id.Cheers_Button);
            rand = new Random();
            glass_value = rand.nextInt(14)+1;
            Map <String,String> query_data =new HashMap<>();
            query_data.put("glasswareId",String.valueOf(glass_value));
            query_data.put("key",API_KEY);
            query_data.put("p",String.valueOf(current_page));

            if (API_KEY.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from BreweryDB", Toast.LENGTH_LONG).show();
                return;
            }

             apiService =  ApiClient.getClient().create(ApiInterface.class);

             apiService.getBeers(query_data).enqueue(new Callback<BeerResponse>() {
                 @Override
                 public void onResponse(Call<BeerResponse> call, Response<BeerResponse> response) {
                     Log.d("TAG",response.code()+"");
                     BeerResponse resource = response.body();
                     beers=resource.data;
                     max_page=resource.numberOfPages ==null? current_page : resource.numberOfPages;
                     beer_description.setText(beers.get(current_beer).description);
                     beer_name.setText(beers.get(current_beer).nameDisplay);
                     if(beers.get(current_beer).labels==null) {
                        beer_label.setImageResource(R.mipmap.noimg);
                     }
                     else{
                         Picasso.with(MainActivity.this).load(beers.get(current_beer).labels.large).into(beer_label);
                     }
                 }

                 @Override
                 public void onFailure(Call<BeerResponse> call, Throwable t) {
                     Log.e(TAG, t.toString());
                 }
             });

            cheers_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    cheers_counter += 1;
                    current_beer = rand.nextInt(beers.size());
                    beer_description.setText(beers.get(current_beer).description);
                    beer_name.setText(beers.get(current_beer).nameDisplay);
                    if (beers.get(current_beer).labels == null) {
                        beer_label.setImageResource(R.mipmap.noimg);
                    } else {
                        Picasso.with(MainActivity.this).load(beers.get(current_beer).labels.large).into(beer_label);
                    }
                    if(cheers_counter==CHEERS_LIMIT*data_counter) {
                        Map <String,String> query_data =new HashMap<>();
                        query_data.put("glasswareId",String.valueOf(glass_value));
                        query_data.put("key",API_KEY);
                        current_page = current_page<max_page ? (current_page+1) : max_page;
                        query_data.put("p",String.valueOf(current_page));
                        apiService.getBeers(query_data).enqueue(new Callback<BeerResponse>() {
                            @Override
                            public void onResponse(Call<BeerResponse> call, Response<BeerResponse> response) {
                                Log.d("TAG",response.code()+"");
                                BeerResponse resource = response.body();
                                beers=resource.data;
                                beer_description.setText(beers.get(current_beer).description);
                                beer_name.setText(beers.get(current_beer).nameDisplay);
                                if(beers.get(current_beer).labels==null) {
                                    beer_label.setImageResource(R.mipmap.noimg);
                                }
                                else{
                                    Picasso.with(MainActivity.this).load(beers.get(current_beer).labels.large).into(beer_label);
                                }
                            }

                            @Override
                            public void onFailure(Call<BeerResponse> call, Throwable t) {
                                Log.e(TAG, t.toString());
                            }
                        });
                    }
                }
            });

        }

    }

