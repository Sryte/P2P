package tools;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.Base64;

public class StringWriter {

    private String data;
    private String fileName;

    public void writeFile() throws IOException {
        //String path = System.getProperty("user.dir")+"\\share\\" + fileName;
        String path = "share/" + fileName;

        BufferedWriter writer = new BufferedWriter(new FileWriter(path));

        byte[] decodedBytes = Base64.getDecoder().decode(data.getBytes());

        String fileContent = new String(decodedBytes, "UTF-8");
        writer.write(fileContent);

        writer.close();
    }

    public StringWriter(String data, String fileName) {
        this.data = data;
        this.fileName = fileName;
    }

}