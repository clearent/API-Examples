package sample.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apeck on 6/9/2016.
 */
public class TokenAVS {
    String card = "4111111111111111";
    @SerializedName("exp-date")
    String expDate = "0818";
    String csc = "999";
    String description = "test";
    @SerializedName("card-type")
    String cardType ="VISA";
    @SerializedName("avs-address")
    String avsAddress = "123 test";
    @SerializedName("avs-zip")
    String avsZip = "85284";
    @SerializedName("require-avs")
    String requireAvs = "true";
}
