package model;

public class Token {

    private String ACCESS_KEY;
    private String SECRET_KEY;
    private String BUCKET;

    public String getACCESS_KEY() {
        return ACCESS_KEY;
    }

    public void setACCESS_KEY(String ACCESS_KEY) {
        this.ACCESS_KEY = ACCESS_KEY;
    }

    public String getSECRET_KEY() {
        return SECRET_KEY;
    }

    public void setSECRET_KEY(String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    public String getBUCKET() {
        return BUCKET;
    }

    public void setBUCKET(String BUCKET) {
        this.BUCKET = BUCKET;
    }
}
