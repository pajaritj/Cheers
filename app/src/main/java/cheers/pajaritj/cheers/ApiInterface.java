package cheers.pajaritj.cheers;

/**
 * Created by jessa on 23/05/2017.
 */
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


interface ApiInterface {
    @GET("/v2/beer/random")
    Call<Beer_Response> getBeers(@Query("hasLabels") String hasLabels, @Query("key") String key);
}
