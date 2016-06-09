package sample.domain.recurringPayments;

import com.google.gson.annotations.SerializedName;

public class Token {
    String card = "4111111111111111";
    @SerializedName("card-type")
    String cardType = "VISA";
    @SerializedName("exp-date")
    String expDate = "0819";
    String csc = "999";
    @SerializedName("avs-address")
    String avsAddress = "123 test";
    @SerializedName("avs-zip")
    String avsZip = "85482";
    String description = "test";
    @SerializedName("customer-key")
    String customerKey = "CUSTOMER-KEY-HERE";
}
