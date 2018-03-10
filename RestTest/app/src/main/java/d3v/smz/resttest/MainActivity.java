package d3v.smz.resttest;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        restInvoke("PUT", "10", null);
    }

     private void restInvoke(String requestType, String id, Positions entity ){
        switch (requestType){
            case "GET":
                try {
                    URL url = new URL("http://193.137.107.83:8080/RMLS_WS/webresources/_ws.measurements/" + id + "?");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod(requestType);

                    if(connection.getResponseCode() == 200){
                        //Success
                        Toast.makeText(this, "Success GET", Toast.LENGTH_SHORT).show();
                    }
                    else{
                       //Error
                        Toast.makeText(this, "Error GET", Toast.LENGTH_SHORT).show();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "PUT":
                try {
                    URL url = new URL("http://193.137.107.83:8080/RMLS_WS/webresources/_ws.measurements/" + id + "?");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod(requestType);
                    connection.setDoInput(true);
                    connection.setRequestProperty("Content-Type", "application/xml");

                    OutputStreamWriter out = new OutputStreamWriter(
                            connection.getOutputStream());
                    out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" +
                            "    <measurements> \n" +
                            "        <equipType>123</equipType> \n" +
                            "        <measurementID>10</measurementID> \n" +
                            "        <posID>10</posID> \n" +
                            "        <rssi1>10</rssi1> \n" +
                            "        <rssi2>10</rssi2> \n" +
                            "        <rssi3>10</rssi3> \n" +
                            "        <rssi4>10</rssi4> \n" +
                            "    </measurements> ");
                    out.close();
                    connection.getInputStream();

                    if(connection.getResponseCode() == 204){
                        //Success
                        Toast.makeText(this, "Success PUT", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //Error
                        Toast.makeText(this, "Error PUT", Toast.LENGTH_SHORT).show();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "DELETE":

                break;
        }
    }
}