package at.ac.tgm.awansch;

import java.net.MalformedURLException;
import java.net.URL;

public class WortBildPaar {
    private String wort;
    private String url;

    public WortBildPaar(String wort, String url) {
        setWort(wort);
        setUrl(url);
    }

    public String getWort() {
        return wort;
    }

    public void setWort(String wort) {
        if(wort != null && wort.length() > 1) {
            this.wort = wort;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        try {
            new URL(url);
            this.url = url;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
