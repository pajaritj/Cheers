package cheers.pajaritj.cheers;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by jessa on 25/05/2017.
 */

public class RetainedFragment extends Fragment {

    // data object we want to retain
    private Beer data;


    private Boolean loaded;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(Beer data) {
        this.data = data;
    }

    public Beer getData() {
        return data;
    }

    public Boolean getLoaded() {
        return loaded;
    }

    public void setLoaded(Boolean loaded) {
        this.loaded = loaded;
    }

}
