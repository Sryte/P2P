package arlo;

public class FileData {

    private final String message;
    private final String data;

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }

    public FileData(String message, String data) {
        this.message = message;
        this.data = data;
    }

}
