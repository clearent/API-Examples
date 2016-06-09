package sample.domain;

import com.google.gson.annotations.SerializedName;

public class Batch {
    @SerializedName("total-count")
    String totalCount = "2";
    @SerializedName("net-amount")
    String netAmount = "5.05";
}
