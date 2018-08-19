package model;

public class KeyStore {

    private String ACCESS_KEY;
    private String SECRET_KEY;
    private String BUCKET;

    public String getAccessKey() {
        return ACCESS_KEY;
    }

    public void setAccessKey(String ACCESS_KEY) {
        this.ACCESS_KEY = ACCESS_KEY;
    }

    public String getSecretKey() {
        return SECRET_KEY;
    }

    public void setSecretKey(String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    public String getBucket() {
        return BUCKET;
    }

    public void setBucket(String BUCKET) {
        this.BUCKET = BUCKET;
    }
}
