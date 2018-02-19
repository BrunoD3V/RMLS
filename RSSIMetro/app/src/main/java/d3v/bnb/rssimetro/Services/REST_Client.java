package d3v.bnb.rssimetro.Services;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import d3v.bnb.rssimetro.Models.Measurement;

/**
 * Created by Bruno on 2/17/2018.
 */

public class REST_Client {

    public static void restInvoke(String requestType, String id, String outputStream ){
        switch (requestType){
            case "GET":
                try {
                    URL url = new URL("http://193.137.107.83:8080/RMLS_WS/webresources/_ws.measurements/" + id + "?");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod(requestType);

                    if(connection.getResponseCode() == 200){
                        //Success

                    }
                    else{
                        //Error

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

                    out.write(outputStream);

                    out.close();

                    connection.getInputStream();

                    if(connection.getResponseCode() == 204){
                        //Success
                    }
                    else{
                        //Error
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
