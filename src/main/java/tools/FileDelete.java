package tools;


import java.io.File;

public class FileDelete {

    private String fileName;

    public boolean deleteFile() {

        String path = System.getProperty("user.dir")+"\\share\\" + fileName;
        File file = new File(path);

        if(file.exists())
            return file.delete();
        else
            return false;
    }

    public FileDelete(String fileName) {
        this.fileName = fileName;
    }
}
