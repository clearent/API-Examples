package sample.domain.recurringPayments;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apeck on 6/3/2016.
 */
public class TokenInfo {
    String description = "new token";
    @SerializedName("customer-key")
    String customerKey = "cust_f42eb217-8618-477a-ac26-a90a29f31ba3";
}
