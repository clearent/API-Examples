package sample.domain.transaction;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apeck on 6/9/2016.
 */
public class ForcedSale {
    String type = "FORCED SALE";
    String card = "4111111111111111";
    @SerializedName("exp-date")
    String exp_date = "1219";
    String amount = "25.00";
    @SerializedName("order-id")
    String order_id = "12345";
    @SerializedName("authorization-code")
    String authorization_code = "APPC10";
}
