package tools;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class StringWriter {

    public void writeFile(String content, String filePath) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content);

        writer.close();
    }
}
