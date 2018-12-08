package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;
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
                java.io.FileReader fr = new java.io.FileReader(String.valueOf(Paths.get(filePath))); //change to relative path
                BufferedReader br = new BufferedReader(fr);

                String reader;
                String fileContent = "";

            while ((reader = br.readLine()) != null) {
                fileContent+=reader;
            }

            content = Base64.getEncoder().encodeToString(fileContent.getBytes());
            System.out.println(content);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }

    public FileReading(String file) {
        String path = System.getProperty("user.dir")+"\\share\\" + file;
        //System.out.println(path);
        this.data = encodeBase64(path);
    }

}
