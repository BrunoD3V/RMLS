package d3v.bnb.rssimetro.Activities;


import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import d3v.bnb.rssimetro.Models.Measurement;
import d3v.bnb.rssimetro.R;
import d3v.bnb.rssimetro.Services.DBO;
import d3v.bnb.rssimetro.Services.REST_Client;
import d3v.bnb.rssimetro.Utilities.Utils;

public class MeasurementActivity extends Activity {

    private static final String TAG = "MeasurementActivity";
    private Button btnRestart;
    private TextView mainText;
    private WifiManager mainWifi;
    public WifiReceiver receiverWifi;
    public List<ScanResult> wifiList;

    private EditText editTextPosID;

    private static final String BEACON = "beacon";
    private static final String XIAOMI = "xiaomi";

    private IntentFilter filter = new IntentFilter();

    StringBuilder sb = new StringBuilder();

    List<Integer> powerBssid1 = new ArrayList<>();
    List<Integer> powerBssid2 = new ArrayList<>();
    List<Integer> powerBssid3 = new ArrayList<>();
    List<Integer> powerBssid4 = new ArrayList<>();

    final static String bssid1 = "34:ce:00:51:e6:d8";
    final static String bssid2 = "34:ce:00:51:72:20";
    final static String bssid3 = "34:ce:00:4b:32:84";
    final static String bssid4 = "34:ce:00:4b:1d:c0";

    private double mediaBssid1, mediaBssid2, mediaBssid3, mediaBssid4;
    private double mediaExpurgadaBssid1, mediaExpurgadaBssid2, mediaExpurgadaBssid3,  mediaExpurgadaBssid4;

    private List<Integer> expurgedPowersBssid1 = new ArrayList<>();
    private List<Integer> expurgedPowersBssid2 = new ArrayList<>();
    private List<Integer> expurgedPowersBssid3 = new ArrayList<>();
    private List<Integer> expurgedPowersBssid4 = new ArrayList<>();

    private int count;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);
        mainText = (TextView) findViewById(R.id.showText);
        mainWifi = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        editTextPosID = (EditText) findViewById(R.id.editTextPosID);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        count = 0;
        receiverWifi = new WifiReceiver();
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        //mainWifi.startScan();
        requestLocationPermissions();
        mainText.setText("\\nStarting Scan...\\n");

        btnRestart = (Button) findViewById(R.id.btnRestart);

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powerBssid1.clear();
                expurgedPowersBssid1.clear();
                mediaBssid1 = 0;
                mediaExpurgadaBssid1 = 0;

                powerBssid2.clear();
                expurgedPowersBssid2.clear();
                mediaBssid2 = 0;
                mediaExpurgadaBssid2 = 0;

                powerBssid3.clear();
                expurgedPowersBssid3.clear();
                mediaBssid3 = 0;
                mediaExpurgadaBssid3 = 0;

                powerBssid4.clear();
                expurgedPowersBssid4.clear();
                mediaBssid4 = 0;
                mediaExpurgadaBssid4 = 0;

                count = 0;

                sb.setLength(0);
            }
        });
    }
    @TargetApi(23)
    public void requestLocationPermissions(){
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "LOCATION PERMISSION GRANTED");
                    mainWifi.startScan();
                } else {
                    Log.i(TAG, "LOCATION PERMISSION NOT GRANTED");
                }
                return;
            }
        }
    }




    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Refresh");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        mainWifi.startScan();
        mainText.setText("Starting Scan");
        return super.onMenuItemSelected(featureId, item);
    }

    protected void onPause() {
        unregisterReceiver(receiverWifi);
        super.onPause();
    }

    protected void onResume() {
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    class WifiReceiver extends BroadcastReceiver {
        public void onReceive(Context c, final Intent intent) {
            count++;

            sb = new StringBuilder();
            wifiList = getWifiList(mainWifi,intent);
            ScanResult result;

            String ssid, bsssid;
            int rssi;
            for (int i = 1; i < wifiList.size(); i++) {
                result = wifiList.get(i);
                ssid = result.SSID;
                bsssid = result.BSSID;
                rssi = result.level;
                if(count <= 10){
                    System.out.println(bsssid);

                    if(bsssid.equalsIgnoreCase(bssid1))
                        powerBssid1.add(rssi);
                    if(bsssid.equalsIgnoreCase(bssid2))
                        powerBssid2.add(rssi);
                    if(bsssid.equalsIgnoreCase(bssid3))
                        powerBssid3.add(rssi);
                    if(bsssid.equalsIgnoreCase(bssid4))
                        powerBssid4.add(rssi);

                    if(count == 9){
                        mediaBssid1 = meanRssi(powerBssid1);
                        double desvioPadraoBssid1 = getStdDev(powerBssid1, mediaBssid1);
                        expurgedPowersBssid1 = expurgePowers(powerBssid1, mediaBssid1, desvioPadraoBssid1);
                        mediaExpurgadaBssid1 = meanRssi(expurgedPowersBssid1);

                        mediaBssid2 = meanRssi(powerBssid2);
                        double desvioPadraoBssid2 = getStdDev(powerBssid2, mediaBssid2);
                        expurgedPowersBssid2 = expurgePowers(powerBssid2,mediaBssid2, desvioPadraoBssid2);
                        mediaExpurgadaBssid2 = meanRssi(expurgedPowersBssid2);

                        mediaBssid3 = meanRssi(powerBssid3);
                        double desvioPadraoBssid3 = getStdDev(powerBssid3, mediaBssid3);
                        expurgedPowersBssid3 = expurgePowers(powerBssid3,mediaBssid3, desvioPadraoBssid3);
                        mediaExpurgadaBssid3 = meanRssi(expurgedPowersBssid3);

                        mediaBssid4 = meanRssi(powerBssid4);
                        double desvioPadraoBssid4 = getStdDev(powerBssid4, mediaBssid4);
                        expurgedPowersBssid4 = expurgePowers(powerBssid4, mediaBssid4, desvioPadraoBssid4);
                        mediaExpurgadaBssid4 = meanRssi(expurgedPowersBssid4);
                    }
                }
                System.out.println("SSID: " + ssid);
                System.out.println("BSSID: " + bsssid);
                System.out.println("SIGNAL: " + rssi);
            }

            //TODO: switch ordenado consoante posição a registar.
            sb.append("\n" + count);
            sb.append("\n BSSID 1:");
            sb.append("\n media = " + mediaBssid1);
            sb.append("\n mediaExpurgada = " + mediaExpurgadaBssid1);
            sb.append("\n BSSID 2:");
            sb.append("\n media = " + mediaBssid2);
            sb.append("\n mediaExpurgada = " + mediaExpurgadaBssid2);
            sb.append("\n BSSID 3:");
            sb.append("\n media = " + mediaBssid3);
            sb.append("\n mediaExpurgada = " + mediaExpurgadaBssid3);
            sb.append("\n BSSID 4:");
            sb.append("\n media = " + mediaBssid4);
            sb.append("\n mediaExpurgada = " + mediaExpurgadaBssid4);

            mainText.setText(sb);
        }

        public List<ScanResult> getWifiList(WifiManager mainWifi, final Intent intent){
            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(intent.getAction())){
                registerReceiver(receiverWifi, filter);
                mainWifi.startScan();
            }


            return mainWifi.getScanResults();
        }
    }

    public void onClickBtnSave(View v){
        if(Utils.isEmpty(editTextPosID)){
            Toast.makeText(getApplicationContext(), "Please fill the field Pos ID", Toast.LENGTH_LONG).show();
            return;
        }

        //TODO: valor do equipamento tem de ser dinâmico
        Measurement entity = new Measurement("pos" + editTextPosID.getText().toString(), XIAOMI, mediaBssid1, mediaBssid2 , mediaBssid3, mediaBssid4);

        String output = DBO.XMLToRequestPUT_Measurement(entity);
        String table = "measurements";
        REST_Client.restPUT( "pos" +editTextPosID.getText().toString(), output , table);

    }

    private double meanRssi(List<Integer> rssis){
        double aux = 0.0;
        for(Integer i: rssis){
            aux += i;
        }
        return (double) aux/rssis.size();
    }

    private double getStdDev(List<Integer> rssis, double mean){
        double aux = 0;
        for(Integer i: rssis){
            aux += (i - mean) * (i - mean);
        }
        return Math.sqrt(aux/rssis.size());
    }
    private List<Integer> expurgePowers(List<Integer> rssis, double mean, double stddev){
        List<Integer> expurgedPower = new ArrayList<>();
        for(Integer i: rssis){
            if(i >= mean - 2 * stddev){
                expurgedPower.add(i);
            }
        }
        return expurgedPower;
    }
    private double getDistance(double rsssip, double rsssi_cal){
        if(rsssip >= rsssi_cal)
            return Math.pow(10, rsssip / rsssi_cal);
        return 0.9 * Math.pow(7.71, rsssip / rsssi_cal) + 0.11;
    }
}
