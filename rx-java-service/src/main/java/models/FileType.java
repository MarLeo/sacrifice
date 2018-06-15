package models;

public enum FileType {

    JSON("json"), XML("xml"), CSV("csv");

    private String type;

    FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
