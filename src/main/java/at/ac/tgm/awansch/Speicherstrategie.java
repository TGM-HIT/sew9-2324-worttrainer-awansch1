package at.ac.tgm.awansch;

import java.io.IOException;

interface Speicherstrategie {
    void speichern(String dateipfad, Worttrainer worttrainer) throws IOException;
    Worttrainer laden(String dateipfad, Worttrainer worttrainer);
}
