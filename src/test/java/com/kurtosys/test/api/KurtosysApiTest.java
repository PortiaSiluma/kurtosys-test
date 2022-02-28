
package com.kurtosys.test.api;

import static io.restassured.RestAssured.*;
import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author Portia.Siluma
 */
public class KurtosysApiTest {
     private final String api = "https://www.kurtosys.com";
    private URL url;
    
    @Before
    public void setUp() throws MalformedURLException {
        this.url = new URL(api);
    }
    
    @Test
    public void testResponseCode() {
        int responseCode = get(url).statusCode();
        int expectedResponseCode = 200;
        
        assertEquals(expectedResponseCode, responseCode);
    }
    
    @Test
    public void testResponseTimeLessThan2Seconds() {
        long responseTimeInMillies = get(url).getTime();
        long responseTimeInSeconds = responseTimeInMillies / 1000;
        
        assertTrue(responseTimeInSeconds < 2);
    }
    
    @Test
    public void testServerHeader() {
        String serverHeaderValue = get(url).getHeader("Server");
        
        assertEquals(serverHeaderValue, "cloudflare");
    }
}
