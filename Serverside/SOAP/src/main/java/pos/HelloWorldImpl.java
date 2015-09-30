package pos;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Remco on 25-9-2015.
 */
@WebService(endpointInterface = "pos.Hello")
public class HelloWorldImpl implements Hello {
    @WebMethod
    public String hello(){
        return "Hello World!";
    }
}
