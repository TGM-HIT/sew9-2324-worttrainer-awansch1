package at.ac.tgm.awansch;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.net.MalformedURLException;
import java.net.URL;
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

    public void zufaelligesPaarAuswaehlen() {
        if (!wortBildPaare.isEmpty()) {
            Random random = new Random();
            aktuellesPaar = wortBildPaare.get(random.nextInt(wortBildPaare.size()));
        } else {
            aktuellesPaar = null;
        }
    }

    public boolean wortErraten(String vermutetesWort) {
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

    public void anzeigenBildUndEingabe() throws MalformedURLException {
        if (aktuellesPaar != null) {
            ImageIcon icon = new ImageIcon(new URL(aktuellesPaar.getUrl()));

            String geratenesWort = (String) JOptionPane.showInputDialog(
                    null,
                    "Was siehst du auf dem Bild?",
                    "Bild",
                    JOptionPane.INFORMATION_MESSAGE,
                    icon,
                    null,
                    "");

            if (geratenesWort != null && !geratenesWort.isEmpty()) {
                boolean istRichtig = wortErraten(geratenesWort);

                if (istRichtig) {
                    JOptionPane.showMessageDialog(null, "Richtig!");
                } else {
                    JOptionPane.showMessageDialog(null, "Falsch.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Kein Wort eingegeben.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Kein aktuelles Wort-Bild-Paar ausgewählt.");
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        // Beispiel Wort-Bild-Paare
        Worttrainer worttrainer = new Worttrainer();
        worttrainer.wortBildPaarHinzufuegen(new WortBildPaar("Hund", "https://images.gutefrage.net/media/fragen/bilder/suesser-hund-aber-welche-rasse/0_original.jpg"));
        worttrainer.wortBildPaarHinzufuegen(new WortBildPaar("Katze", "https://ais.badische-zeitung.de/piece/0b/5e/72/bc/190739132.jpg"));

        // Beispiel: Zufälliges Wort-Bild-Paar auswählen
        worttrainer.zufaelligesPaarAuswaehlen();

        // Anzeigen des Bilds und der Eingabeaufforderung
        worttrainer.anzeigenBildUndEingabe();
    }
}

