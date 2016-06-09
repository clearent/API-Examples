package sample.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apeck on 6/9/2016.
 */
public class Batch {
    @SerializedName("total-count")
    String totalCount = "2";
    @SerializedName("net-amount")
    String netAmount = "5.05";
}
