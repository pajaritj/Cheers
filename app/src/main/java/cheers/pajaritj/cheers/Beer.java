package cheers.pajaritj.cheers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jessa on 25/05/2017.
 */

public class Beer {

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("nameDisplay")
    public String nameDisplay;
    @SerializedName("description")
    public String description;
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
    @SerializedName("createDate")
    public String createDate;
    @SerializedName("updateDate")
    public String updateDate;
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