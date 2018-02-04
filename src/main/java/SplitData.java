import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class SplitData {


    private byte[] toBytes (File init){
        byte[] data;
        try {
            data = FileUtils.readFileToByteArray(init);
            return data;
        } catch (IOException e){}

        return null;
    }

    private void toFile (File newFile, byte[] data){
        try {
                FileUtils.writeByteArrayToFile(newFile, data);
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
    }

    public static void main(String[] args) {
        byte[] data;


        int lines, helpers = 3;

        /**
         * toBytes(), then split into packets, then reassemble packets, then toFile()
         */
        try {
            File initialFile = new File("drums.wav");
            File newFile = new File ("assets/drumsnew.wav");

            data = FileUtils.readFileToByteArray(initialFile);
            System.out.println(data.length);


        } catch (IOException e) {}
    }
}
