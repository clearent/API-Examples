package sample.domain.settings;

import com.google.gson.annotations.SerializedName;

public class Hpp {
    @SerializedName("hpp-domain")
    String hppDomain = "https://test.com";
    @SerializedName("hpp-enabled")
    String hppEnabled = "true";
}