package cheers.pajaritj.cheers;

/**
 * Created by jessa on 23/05/2017.
 */

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;


public class BeerResponse {

    @SerializedName("currentPage")
    public Integer currentPage;
    @SerializedName("numberOfPages")
    public Integer numberOfPages;
    @SerializedName("totalResults")
    public Integer totalResults;
    @SerializedName("data")
    public List<Beer>data;
    @SerializedName("status")
    public String status;


    public class Beer {

        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("nameDisplay")
        public String nameDisplay;
        @SerializedName("description")
        public String description;
        @SerializedName("abv")
        public String abv;
        @SerializedName("ibu")
        public String ibu;
        @SerializedName("glasswareId")
        public Integer glasswareId;
        @SerializedName("srmId")
        public Integer srmId;
        @SerializedName("availableId")
        public Integer availableId;
        @SerializedName("styleId")
        public Integer styleId;
        @SerializedName("isOrganic")
        public String isOrganic;
        @SerializedName("labels")
        public Labels labels;
        @SerializedName("status")
        public String status;
        @SerializedName("statusDisplay")
        public String statusDisplay;
        @SerializedName("foodPairings")
        public String foodPairings;
        @SerializedName("createDate")
        public String createDate;
        @SerializedName("updateDate")
        public String updateDate;
        @SerializedName("glass")
        public Glass glass;
        @SerializedName("srm")
        public Srm srm;
        @SerializedName("available")
        public Available available;
        @SerializedName("style")
        public Style style;

        public class Labels {

            @SerializedName("icon")

            public String icon;
            @SerializedName("medium")

            public String medium;
            @SerializedName("large")

            public String large;
        }

        public class Glass {

            @SerializedName("id")

            public Integer id;
            @SerializedName("name")

            public String name;
            @SerializedName("createDate")

            public String createDate;

        }

        public class Srm {

            @SerializedName("id")

            public Integer id;
            @SerializedName("name")

            public String name;
            @SerializedName("hex")

            public String hex;

        }

        public class Available {

            @SerializedName("id")

            public Integer id;
            @SerializedName("name")

            public String name;
            @SerializedName("description")

            public String description;

        }

        public class Style {

            @SerializedName("id")

            public Integer id;
            @SerializedName("categoryId")

            public Integer categoryId;
            @SerializedName("category")

            public Category category;
            @SerializedName("name")

            public String name;
            @SerializedName("shortName")

            public String shortName;
            @SerializedName("description")

            public String description;
            @SerializedName("ibuMin")

            public String ibuMin;
            @SerializedName("ibuMax")

            public String ibuMax;
            @SerializedName("abvMin")

            public String abvMin;
            @SerializedName("abvMax")

            public String abvMax;
            @SerializedName("srmMin")

            public String srmMin;
            @SerializedName("srmMax")

            public String srmMax;
            @SerializedName("ogMin")

            public String ogMin;
            @SerializedName("fgMin")

            public String fgMin;
            @SerializedName("fgMax")
            public String fgMax;
            @SerializedName("createDate")
            public String createDate;
            @SerializedName("updateDate")
            public String updateDate;

            public class Category {

                @SerializedName("id")

                public Integer id;
                @SerializedName("name")

                public String name;
                @SerializedName("createDate")

                public String createDate;

            }
        }

    }

}