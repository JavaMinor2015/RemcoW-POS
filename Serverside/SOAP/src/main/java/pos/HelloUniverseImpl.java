package pos;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Remco on 25-9-2015.
 */
@WebService
public class HelloUniverseImpl implements Hello {
    @WebMethod
    public String hello(){
        return "Hello Universe!";
    }
}
