package at.ac.tgm.awansch;

public class Statistik {
    private int versuche;
    private int korrekteVersuche;
    private int falscheVersuche;

    public Statistik() {
        this.versuche = 0;
        this.korrekteVersuche = 0;
        this.falscheVersuche = 0;
    }

    public void versucheErhoehen() {
        this.versuche++;
    }

    public void setVersuche(int versuche) {
        this.versuche = versuche;
    }

    public int getVersuche() {
        return this.versuche;
    }


    public void korrekteVersucheErhoehen() {
        this.korrekteVersuche++;
    }

    public void setKorrekteVersuche(int korrekteVersuche) {
        this.korrekteVersuche = korrekteVersuche;
    }

    public int getKorrekteVersuche() {
        return this.korrekteVersuche;
    }

    public void falscheVersucheErhoehen() {
        this.falscheVersuche++;
    }

    public int getFalscheVersuche() {
        return this.falscheVersuche;
    }

    public void setFalscheVersuche(int falscheVersuche) {
        this.falscheVersuche = falscheVersuche;
    }

    @Override
    public String toString() {
        return "Versuche: " + this.versuche + "\nRichtig: " + this.korrekteVersuche + "\nFalsch: " + this.falscheVersuche;
    }
}
