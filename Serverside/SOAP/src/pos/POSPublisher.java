package pos;

import javax.xml.ws.Endpoint;

/**
 * Created by Remco on 25-9-2015.
 */
public class POSPublisher {
    public static void main(String[] args){
        final String url = "http://localhost:8080/pos";
        System.out.println("Publishing POS at endpoint " + url);
        Endpoint.publish(url + "/world", new HelloWorldImpl());
        Endpoint.publish(url + "/universe", new HelloUniverseImpl());
    }
}
