import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class QuickStart {

    public static void main(String[] args) {
        try {
            HttpClient httpClient = HttpClients.createDefault ();
            HttpGet httpget = new HttpGet ("http://localhost:8008/files/school.jpg");
            HttpResponse response = httpClient.execute (httpget);
            HttpEntity entity = response.getEntity ();
            if (entity != null) {
                InputStream IS = entity.getContent ();
                FileOutputStream FOS = new FileOutputStream(new File ("local_files/school.jpg"));
                int inByte;
                while((inByte = IS.read()) != -1)
                    FOS.write(inByte);
                IS.close();
                FOS.close();
            }
        } catch (IOException ioerr) {
            ioerr.printStackTrace (System.out);
        }
    }

}