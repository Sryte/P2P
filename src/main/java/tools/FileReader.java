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
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }

    public FileReader(String file) {
        String path = "C:\\Users\\laure\\Desktop\\P2P\\" + file; // a modifier
        this.data = encodeBase64(path);
    }

}
