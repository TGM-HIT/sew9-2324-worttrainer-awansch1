package at.ac.tgm.awansch;

public class WortBildPaar {
    private String wort;
    private String url;

    public WortBildPaar(String wort, String url) {
        this.wort = wort;
        this.url = url;
    }

    public String getWort() {
        return wort;
    }

    public void setWort(String wort) {
        this.wort = wort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
