package d3v.bnb.rssimetro;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by E749 on 17/02/2018.
 */

public class Scan {

    private WifiManager mainWifi;
    private MeasurementActivity.WifiReceiver receiverWifi;
    private List<ScanResult> wifiList;
}
