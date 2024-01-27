package Translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Translate {



    static String translateText(String text, String targetLanguage) throws IOException{

         String apiUrl = "https://libretranslate.de/translate";
         URL url = new URL(apiUrl);

         HttpURLConnection connection  = (HttpURLConnection) url.openConnection();
         connection.setRequestMethod("POST");
         connection.setRequestProperty("Content-type","application/json");
         connection.setDoOutput(true);

         String jsontInputString = "{\"q\":\"" + text + "\",\"source\":\"en\",\"target\":\"" + targetLanguage + "\"}";
         try(OutputStream os = connection.getOutputStream()){
             byte[] input = jsontInputString.getBytes(StandardCharsets.UTF_8);
             os.write(input,0,input.length);
         }

         try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),StandardCharsets.UTF_8))){
             StringBuilder response = new StringBuilder();
             String responseLine;
             while ((responseLine = br.readLine())!=null){
                 response.append(responseLine.trim());
             }
             return response.toString();
         }
         finally {
             connection.disconnect();
         }
    }


}
