package ru.stqa.pft.mantis.appmanager;


import org.omg.CORBA.NameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class httpSession {
   private CloseablehttpClient httpclient;
   private ApplicationManager app;

   public httpSession(ApplicationManager applicationManager) {
      this.app =app;
     httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();

   }

   public boolean login(String username, String password) throws IOException{
      HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
      List<NameValuePair> params = new ArrayList<>();
      List<NameValuePair> params = new ArrayList<NameValuePair>();
      params.add(new BasicNameValuePair("username", password));
      params.add(new BasicNameValuePair("password", password));
      params.add(new BasicNameValuePair("secure_session", "on"));
      params.add(new BasicNameValuePair("return","index.php"));
      post.setEnity(new UrlEncodedFormEntity(params));
      CloseableHttpResponse response = httpclient.execute(post);
      String body = geTextFrom(response);
      return body.contains(String.format("<span class=\"italic\">%s</span>",username ));


   }

}
