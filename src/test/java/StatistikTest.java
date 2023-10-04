import at.ac.tgm.awansch.Statistik;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatistikTest {

    private final Statistik statistik = new Statistik();

    @Test
    public void testInitialValues() {
        Assertions.assertEquals(0, statistik.getVersuche());
        Assertions.assertEquals(0, statistik.getKorrekteVersuche());
        Assertions.assertEquals(0, statistik.getFalscheVersuche());
    }

    @Test
    public void testVersucheErhoehen() {
        statistik.versucheErhoehen();
        Assertions.assertEquals(1, statistik.getVersuche());
    }

    @Test
    public void testKorrekteVersucheErhoehen() {
        statistik.korrekteVersucheErhoehen();
        Assertions.assertEquals(1, statistik.getKorrekteVersuche());
    }

    @Test
    public void testFalscheVersucheErhoehen() {
        statistik.falscheVersucheErhoehen();
        Assertions.assertEquals(1, statistik.getFalscheVersuche());
    }

    @Test
    public void testToString() {
        statistik.versucheErhoehen();
        statistik.korrekteVersucheErhoehen();
        statistik.falscheVersucheErhoehen();

        String expectedString = "Versuche: 1\nRichtig: 1\nFalsch: 1";
        Assertions.assertEquals(expectedString, statistik.toString());
    }

    @Test
    public void testSetVersuche() {
        statistik.setVersuche(10);
        Assertions.assertEquals(10, statistik.getVersuche());
    }

    @Test
    public void testSetKorrekteVersuche() {
        statistik.setKorrekteVersuche(5);
        Assertions.assertEquals(5, statistik.getKorrekteVersuche());
    }

    @Test
    public void testSetFalscheVersuche() {
        statistik.setFalscheVersuche(3);
        Assertions.assertEquals(3, statistik.getFalscheVersuche());
    }
}
