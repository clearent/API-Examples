package sample.domain.recurringPayments;

import com.google.gson.annotations.SerializedName;

public class TokenInfo {
    String description = "new token";
    @SerializedName("customer-key")
    String customerKey = "cust_f42eb217-8618-477a-ac26-a90a29f31ba3";
}
