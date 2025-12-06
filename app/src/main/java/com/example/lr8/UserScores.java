package com.example.lr8;

import com.google.gson.annotations.SerializedName;

public class UserScores {
    @SerializedName("math")
    int math;
    @SerializedName("rus")
    int rus;
    @SerializedName("inform")
    int inform;
    @SerializedName("social")
    int social;
    @SerializedName("physics")
    int physics;
    @SerializedName("chemistry")
    int chemistry;
    @SerializedName("eng")
    int eng;
    @SerializedName("geo")
    int geo;

    public UserScores(int math, int rus, int inform, int social, int physics, int chemistry, int eng, int geo) {
        this.math = math;
        this.rus = rus;
        this.inform = inform;
        this.social = social;
        this.physics = physics;
        this.chemistry = chemistry;
        this.eng = eng;
        this.geo = geo;
    }
}