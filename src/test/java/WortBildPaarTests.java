import at.ac.tgm.awansch.WortBildPaar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WortBildPaarTests {

    private WortBildPaar w = new WortBildPaar("Test", "https://images.gutefrage.net/media/fragen/bilder/suesser-hund-aber-welche-rasse/0_original.jpg");

    @Test
    public void KonstruktorTest() {
        Assertions.assertEquals(w.getWort(), "Test");
        Assertions.assertEquals(w.getUrl().toString(), "https://images.gutefrage.net/media/fragen/bilder/suesser-hund-aber-welche-rasse/0_original.jpg");
    }

    @Test
    public void setWortTest() {
        w.setWort("Hund");
        Assertions.assertEquals(w.getWort(), "Hund");

        Assertions.assertThrows(IllegalArgumentException.class, () -> w.setWort(""));
    }

    @Test
    public void setUrlTest() {
        w.setUrl("https://ais.badische-zeitung.de/piece/0b/5e/72/bc/190739132.jpg");
        Assertions.assertEquals(w.getUrl().toString(), "https://ais.badische-zeitung.de/piece/0b/5e/72/bc/190739132.jpg");

        Assertions.assertThrows(IllegalArgumentException.class, () -> w.setUrl(""));
    }
}
