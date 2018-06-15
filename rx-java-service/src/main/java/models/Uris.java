package models;

public enum Uris {

    CATEGORY_URI("https://api.stlouisfed.org/fred/category/"),
    API_KEY("566941141776fba22d310e7ea5cb4c0f");

    private String uri;

    Uris(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
