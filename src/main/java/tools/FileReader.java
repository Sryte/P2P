package tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class FileReader {

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
            content = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(filePath)));
            //System.out.println(content);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }

    public FileReader(String file) {
        String path = System.getProperty("user.dir")+"\\share\\" + file;
        //System.out.println(path);
        this.data = encodeBase64(path);
    }

}
