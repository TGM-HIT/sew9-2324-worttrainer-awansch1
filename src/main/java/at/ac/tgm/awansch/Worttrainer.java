package at.ac.tgm.awansch;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worttrainer {
    private List<WortBildPaar> wortBildPaare;
    private WortBildPaar aktuellesPaar;
    private Statistik statistik;

    public Worttrainer() {
        this.wortBildPaare = new ArrayList<>();
        this.aktuellesPaar = null;
        this.statistik = new Statistik();
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

    public void statistikAktualisieren(boolean istKorrekteVermutung) {
        /*statistik.gesamtVersucheErhoehen();
        if (istKorrekteVermutung) {
            statistik.korrekteVersucheErhoehen();
        } else {
            statistik.falscheVersucheErhoehen();
        }*/
    }

    public void start() {
        Random random = new Random();
        aktuellesPaar = wortBildPaare.get(random.nextInt(wortBildPaare.size()));

        while(true) {
            if (aktuellesPaar != null) {
                ImageIcon icon = new ImageIcon(aktuellesPaar.getUrl());

                Image scaledImage = icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

                String geratenesWort = (String) JOptionPane.showInputDialog(
                        null,
                        /*buildStatsString() + */"\n\nWas siehst du auf dem Bild?",
                        null,
                        JOptionPane.PLAIN_MESSAGE,
                        scaledImageIcon, // Use the scaled ImageIcon here
                        null,
                        ""
                );

                if (geratenesWort != null && !geratenesWort.isEmpty()) {
                    boolean istRichtig = raten(geratenesWort);

                    if (istRichtig) {
                        JOptionPane.showMessageDialog(null, "Richtig!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Falsch.");
                    }
                } else {
                    aktuellesPaar = this.wortBildPaare.get(random.nextInt(wortBildPaare.size()));
                    break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Kein aktuelles Wort-Bild-Paar ausgewählt.");
            }
        }
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

