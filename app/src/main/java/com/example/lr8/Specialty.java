package com.example.lr8;

import com.google.gson.annotations.SerializedName;

public class Specialty {
    int id;

    @SerializedName("specialty_name")
    String name;

    @SerializedName("math_point")
    int min_math;

    @SerializedName("rus_point")
    int min_rus;

    @SerializedName("itk_point")
    int min_inform;

    @SerializedName("phys_point")
    int min_physics;

    @SerializedName("chem_point")
    int min_chemistry;

    @SerializedName("soc_point")
    int min_social;

    @SerializedName("en_point")
    int min_eng;

    @SerializedName("geo_point")
    int min_geo;

    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        sb.append("Мин. баллы: ");

        if (min_rus > 0) sb.append("Рус:").append(min_rus).append(" ");
        if (min_math > 0) sb.append("Мат:").append(min_math).append(" ");
        if (min_inform > 0) sb.append("Инф:").append(min_inform).append(" ");
        if (min_physics > 0) sb.append("Физ:").append(min_physics).append(" ");
        if (min_chemistry > 0) sb.append("Хим:").append(min_chemistry).append(" ");
        if (min_social > 0) sb.append("Общ:").append(min_social).append(" ");
        if (min_eng > 0) sb.append("Англ:").append(min_eng).append(" ");
        if (min_geo > 0) sb.append("Гео:").append(min_geo).append(" ");

        return sb.toString();
    }
}