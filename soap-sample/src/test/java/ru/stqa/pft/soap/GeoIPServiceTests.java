package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIPServiceTests {
   @Test
   public void testMyIp(){
      String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("5.35.1.229");
      assertEquals(ipLocation,"<GeoIP><Country>RU</Country><State>47</State></GeoIP>");
   }
   @Test(enabled = false)
   public void testInvalidIp(){
      String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("5.xx.1.229");
      assertEquals(ipLocation,"<GeoIP><Country>RU</Country><State>47</State></GeoIP>");
   }
}
