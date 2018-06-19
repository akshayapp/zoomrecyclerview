package introapp.com.zoomrecyclerview;

/**
 * Created by Anand prakas yadav on 12/27/2016.
 */


import android.util.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HTTPURLConnectionGet {
    public String sendGet(String urlMain, String headerNo,String access_token){
        String responsee="";
        try {
            URL obj = new URL(urlMain);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty ("authtoken",access_token);
            String basicAuth = "Basic " + new String(Base64.encode("timelineauth:123456789".getBytes(), Base64.NO_WRAP ));



            con.setRequestProperty ("Authorization", basicAuth);
//            if(headerNo.equalsIgnoreCase("two")) {
//                con.setRequestProperty("Accept", "application/json");
//            }else if(headerNo.equalsIgnoreCase("one")){
//            }

            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            responsee=response.toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        return responsee;
    }


}
