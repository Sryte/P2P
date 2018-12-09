package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;

public class FileReading {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static String encodeBase64(String filePath)
    {
            String content = "";
            try
            {
                FileReader fr = new FileReader(filePath);
                BufferedReader br = new BufferedReader(fr);

                String reader;
                String fileContent = "";

            while ((reader = br.readLine()) != null) {
                fileContent+=reader;
            }

            content = Base64.getEncoder().encodeToString(fileContent.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }

    public FileReading(String file) {
        this.data = encodeBase64(file);
    }

}
