package d3v.bnb.rssimetro.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import d3v.bnb.rssimetro.Models.Measurement;

/**
 * Created by Bruno on 2/17/2018.
 */

public class REST_Client {

    public static String restGET(String id, String table) {

        boolean success = false;
        String result = "";
        try {
            URL url = new URL("http://193.137.106.74:8080/RMLS_WS/webresources/_ws." + table +"/"+ id + "?");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {
                //Success
                success = true;
                InputStream responseBody = connection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader streamReader = new BufferedReader(responseBodyReader);
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                result = responseStrBuilder.toString();
                connection.disconnect();

            } else {
                //Error

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (success)? result : "";
    }

    public static String restPUT(String id, String outputStream, String table) {

        String result = "";
        try {
            URL url = new URL("http://193.137.106.74:8080/RMLS_WS/webresources/_ws." + table + "/"+ id + "?");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/xml");

            //TODO: Catch connection failure
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());

            out.write(outputStream);

            out.close();

            result = connection.getInputStream().toString();

            if (connection.getResponseCode() == 204) {
                //Success
            } else {
                //Error
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
