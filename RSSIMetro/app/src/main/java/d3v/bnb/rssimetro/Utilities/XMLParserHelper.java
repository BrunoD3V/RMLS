package d3v.bnb.rssimetro.Utilities;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.stream.Stream;

import d3v.bnb.rssimetro.Models.Measurement;

/**
 * Created by Bruno on 3/10/2018.
 */

public class XMLParserHelper {

    private XmlPullParserFactory xmlFactoryObject;
    private XmlPullParser myParser;

   public XMLParserHelper(){
       try {
           xmlFactoryObject = XmlPullParserFactory.newInstance();
           myParser = xmlFactoryObject.newPullParser();
       } catch (XmlPullParserException e) {
           e.printStackTrace();
       }
   }

   public Measurement parseMeasureXML(){
       Measurement entityResult = new Measurement();




       return entityResult;
   }

}
