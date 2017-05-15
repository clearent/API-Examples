package sample.domain.recurringPayments;

import com.google.gson.annotations.SerializedName;

public class Customer {
    String email = "testcustomer@clearent.com";
    String phone = "0123456789";
    String comments = "Super loyal customer";
    @SerializedName("first-name")
    String firstName = "William";
    @SerializedName("last-name")
    String lastName = "Biller";

    @SerializedName("billing-address")
    BillingAddress address1 = new BillingAddress();
    @SerializedName("shipping-address")
    ShippingAddress address2 = new ShippingAddress();

    private static class BillingAddress {
        @SerializedName("first-name")
        String firstNameBill = "William";
        @SerializedName("last-name")
        String lastNameBill = "Biller";
        @SerializedName("company")
        String companyBill = "We Bill LLC";
        @SerializedName("street")
        String streetBill = "123 Bill St";
        @SerializedName("street2")
        String streetBill2 = "Suite 888";
        @SerializedName("city")
        String cityBill = "Richmond";
        @SerializedName("state")
        String stateBill = "MO";
        @SerializedName("zip")
        String zipBill = "63103";
        @SerializedName("country")
        String countryBill = "United States";
        @SerializedName("phone")
        String phoneBill = "0123456789";
    }

    private static class ShippingAddress {
        @SerializedName("first-name")
        String firstNameShip = "Chip";
        @SerializedName("last-name")
        String lastNameShip = "Shipper";
        @SerializedName("company")
        String companyShip = "We Ship LLC";
        @SerializedName("street")
        String streetShip = "123 Ship St";
        @SerializedName("street2")
        String streetShip2 = "Suite 555";
        @SerializedName("city")
        String cityShip = "Seattle";
        @SerializedName("state")
        String stateShip = "WA";
        @SerializedName("zip")
        String zipShip = "23227";
        @SerializedName("country")
        String countryShip = "United States";
        @SerializedName("phone")
        String phoneShip = "0123456789";
    }
}