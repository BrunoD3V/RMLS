package d3v.bnb.rssimetro.Services;

import android.icu.util.Measure;

import d3v.bnb.rssimetro.Models.Measurement;

/**
 * Created by Bruno on 2/17/2018.
 */

public class DBO {

    public static String XMLToRequest (Measurement entity){

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" +
                "    <measurements> \n" +
                "        <equipType>"+ entity.getEquipType() + "</equipType> \n" +
                "        <measurementID>"+ entity.getMeasurementID() +"</measurementID> \n" +
                "        <posID>"+ entity.getPosID() +"</posID> \n" +
                "        <rssi1>"+ entity.getRssi1() +"</rssi1> \n" +
                "        <rssi2>"+ entity.getRssi2() +"</rssi2> \n" +
                "        <rssi3>"+ entity.getRssi3() +"</rssi3> \n" +
                "        <rssi4>"+ entity.getRssi4() +"</rssi4> \n" +
                "    </measurements> ";
    }
}
