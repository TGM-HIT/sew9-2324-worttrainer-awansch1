package at.ac.tgm.awansch;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Worttrainer {
    private ArrayList<WortBildPaar> wortBildPaare;
    private WortBildPaar aktuellesPaar;
    private Statistik statistik;
    private Speicherstrategie speicherstrategie;
    private final String path = "src/main/resources/words.json";

    public Worttrainer() {
        this.wortBildPaare = new ArrayList<>();
        this.aktuellesPaar = null;
        this.statistik = new Statistik();
        this.speicherstrategie = new JsonSpeicherstrategie();
    }

   public void speichern() {
        try {
            this.speicherstrategie.speichern(path, this);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
   }

    public void laden() {
        Worttrainer geladen = this.speicherstrategie.laden(path, this);
        if(geladen != null) {
            this.wortBildPaare = geladen.getWortBildPaare();
            this.aktuellesPaar = geladen.getAktuellesPaar();
            this.statistik.setKorrekteVersuche(geladen.getStatistik().getKorrekteVersuche());
            this.statistik.setFalscheVersuche(geladen.getStatistik().getFalscheVersuche());
            this.statistik.setVersuche(geladen.getStatistik().getVersuche());
        }
    }

    public void wortBildPaarHinzufuegen(WortBildPaar paar) {
        wortBildPaare.add(paar);
    }

    public boolean raten(String vermutetesWort) {
        if (aktuellesPaar != null) {
            return aktuellesPaar.getWort().equalsIgnoreCase(vermutetesWort);
        }
        return false;
    }

    public void start() {
        laden();
        Random random = new Random();
        aktuellesPaar = wortBildPaare.get(random.nextInt(wortBildPaare.size()));

        while(true) {
            if (aktuellesPaar != null) {
                ImageIcon icon = new ImageIcon(aktuellesPaar.getUrl());

                Image scaledImage = icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

                String geratenesWort = (String) JOptionPane.showInputDialog(
                        null,
                        statistik.toString() + "\n\nWas siehst du auf dem Bild?",
                        null,
                        JOptionPane.PLAIN_MESSAGE,
                        scaledImageIcon, // Use the scaled ImageIcon here
                        null,
                        ""
                );

                if (geratenesWort != null && !geratenesWort.isEmpty()) {
                    boolean istRichtig = raten(geratenesWort);
                    statistik.versucheErhoehen();

                    if (istRichtig) {
                        JOptionPane.showMessageDialog(null, "Richtig!");
                        statistik.korrekteVersucheErhoehen();
                        aktuellesPaar = this.wortBildPaare.get(random.nextInt(wortBildPaare.size()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Falsch.");
                        statistik.falscheVersucheErhoehen();
                    }
                } else {
                    break;
                }
                this.speichern();
            }
        }
    }

    public ArrayList<WortBildPaar> getWortBildPaare() {
        return this.wortBildPaare;
    }

    public void setWortBildPaare(ArrayList<WortBildPaar> wortBildPaare) {
        this.wortBildPaare = wortBildPaare;
    }

    public WortBildPaar getAktuellesPaar() {
        return this.aktuellesPaar;
    }

    public void setWortBildPaar(WortBildPaar wortBildPaar) {
        this.aktuellesPaar = wortBildPaar;
    }

    public Statistik getStatistik() {
        return this.statistik;
    }

    public static void main(String[] args) {
        // Beispiel Wort-Bild-Paare
        Worttrainer worttrainer = new Worttrainer();
        worttrainer.wortBildPaarHinzufuegen(new WortBildPaar("Hund", "https://images.gutefrage.net/media/fragen/bilder/suesser-hund-aber-welche-rasse/0_original.jpg"));
        worttrainer.wortBildPaarHinzufuegen(new WortBildPaar("Katze", "https://ais.badische-zeitung.de/piece/0b/5e/72/bc/190739132.jpg"));

        // Anzeigen des Bilds und der Eingabeaufforderung
        worttrainer.start();
    }
}

