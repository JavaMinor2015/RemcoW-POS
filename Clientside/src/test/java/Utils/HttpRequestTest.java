package Utils;

import junit.framework.Assert;
import mockit.Mocked;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;


/** 
* HttpRequest Tester. 
* 
* @author <Authors name> 
* @since <pre>sep 30, 2015</pre> 
* @version 1.0 
*/ 
public class HttpRequestTest {


    HttpRequest httpRequest;

    String url = "http://localhost:8081/products/22222222";

    @Before
    public void before() throws Exception {
        httpRequest = new HttpRequest();

    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: makeGetReqeust(String url)
     */
    @Test
    public void testMakeGetReqeust() throws Exception {
        String stringExpected = httpRequest.makeGetReqeust(url);
        String actual = httpRequest.makeGetReqeust("http://localhost:8081/products/22222222");

        Assert.assertEquals(stringExpected,actual);
    }

}
