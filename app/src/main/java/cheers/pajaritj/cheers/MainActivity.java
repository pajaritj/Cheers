package cheers.pajaritj.cheers;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        private TextView beer_description,beer_name;

        private ImageView beer_label;

        private Button cheers_button;

        private ApiInterface apiService;

        Random rand;

        List <BeerResponse.Beer> beers;

        int current_beer=1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            beer_description = (TextView) findViewById(R.id.Beer_Description);
            beer_name = (TextView) findViewById(R.id.Beer_Name);
            beer_label = (ImageView) findViewById(R.id.Beer_Label);
            cheers_button= (Button) findViewById(R.id.Cheers_Button);
            rand = new Random();
            Map <String,String> query_data =new HashMap<>();
            query_data.put("glasswareId",String.valueOf(5));
            query_data.put("key",API_KEY);
            query_data.put("p",String.valueOf(1));

            if (API_KEY.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from BreweryDB", Toast.LENGTH_LONG).show();
                return;
            }

             apiService =  ApiClient.getClient().create(ApiInterface.class);

             apiService.getBeers(query_data).enqueue(new Callback<BeerResponse>() {
                 @Override
                 public void onResponse(Call<BeerResponse> call, Response<BeerResponse> response) {
                     Log.d("TAG",response.code()+"");
                     String displayResponse = "";
                     BeerResponse resource = response.body();
                     beers=resource.data;
                     beer_description.setText(beers.get(current_beer).description);
                     beer_name.setText(beers.get(current_beer).nameDisplay);
                     Picasso.with(MainActivity.this).load(beers.get(current_beer).labels.large).into(beer_label);
                     beers.remove(current_beer);
                 }

                 @Override
                 public void onFailure(Call<BeerResponse> call, Throwable t) {
                     Log.e(TAG, t.toString());
                 }
             });

            cheers_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    current_beer= rand.nextInt(beers.size()) + 1;
                    beer_description.setText(beers.get(current_beer).description);
                    beer_name.setText(beers.get(current_beer).nameDisplay);
                    Picasso.with(MainActivity.this).load(beers.get(current_beer).labels.large).into(beer_label);
                    beers.remove(current_beer);
                }
            });

        }

    }

