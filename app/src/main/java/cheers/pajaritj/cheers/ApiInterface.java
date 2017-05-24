package cheers.pajaritj.cheers;

/**
 * Created by jessa on 23/05/2017.
 */
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


interface ApiInterface {
    @GET("/v2/beers")
    Call<BeerResponse> getBeers(@QueryMap Map<String,String> options);
}
