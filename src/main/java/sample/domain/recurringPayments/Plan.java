package sample.domain.recurringPayments;

import com.google.gson.annotations.SerializedName;

public class Plan {
    @SerializedName("plan-name")
    String planName = "Platinum Gym Membership";
    String type = "SUBSCRIPTION";
    @SerializedName("customer-key")
    String customerKey = "CUSTOMER-KEY-HERE";
    @SerializedName("token-id")
    String tokenId = "TOKEN-ID-HERE";
    String frequency = "MONTHLY";
    @SerializedName("frequency-day")
    String frequencyDay = "15";
    @SerializedName("frequency-month")
    String frequencyMonth = "10";
    @SerializedName("start-date")
    String startDate = "2016-08-01";
    @SerializedName("end-date")
    String endDate = "2016-12-01";
    String status = "ACTIVE";
    @SerializedName("payment-amount")
    String paymentAmount = "50.00";
}
