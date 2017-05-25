package cheers.pajaritj.cheers;

/**
 * Created by jessa on 23/05/2017.
 */

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;




public class Beer_Response {

    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public Beer data;
    @SerializedName("status")
    public String status;

}

