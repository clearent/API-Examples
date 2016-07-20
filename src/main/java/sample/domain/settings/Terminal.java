package sample.domain.settings;

import com.google.gson.annotations.SerializedName;

public class Terminal {
    @SerializedName("terminal-name")
    String terminalName = "Test Terminal";
//    allowed values are 'US/Alaska','US/Arizona','US/Central','US/East-Indiana','US/Eastern','US/Hawaii','US/Indiana-Starke','US/Michigan','US/Mountain','US/Pacific', 'UTC'.
    @SerializedName("time-zone")
    String timeZone = "US/Alaska";
    @SerializedName("batch-time-utc")
    String batchTime = "15:00";
    @SerializedName("enable-auto-close-batch")
    String enableAutoClose = "true";
}