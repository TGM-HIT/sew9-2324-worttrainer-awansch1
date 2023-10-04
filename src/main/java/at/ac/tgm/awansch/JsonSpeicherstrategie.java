package at.ac.tgm.awansch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonSpeicherstrategie implements Speicherstrategie {

    @Override
    public void speichern(String filePath, Worttrainer worttrainer) throws IOException {
        if(!new File(filePath).exists()) new File(filePath).createNewFile();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("wortBildPaare", worttrainer.getWortBildPaare());
        jsonObject.put("aktuellesPaar", worttrainer.getAktuellesPaar());
        jsonObject.put("korrekteVersuche", worttrainer.getStatistik().getKorrekteVersuche());
        jsonObject.put("falscheVersuche", worttrainer.getStatistik().getFalscheVersuche());
        jsonObject.put("versuche", worttrainer.getStatistik().getVersuche());

        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(jsonObject.toString());
        fileWriter.close();
    }

    @Override
    public Worttrainer laden(String filePath, Worttrainer worttrainer) {
        if(!new File(filePath).exists()) return null;
        File file = new File(filePath);

        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));

            JSONObject jsonObject = new JSONObject(content);
            worttrainer.setWortBildPaare(convertJSONArrayToArrayList(jsonObject.getJSONArray("wortBildPaare")));
            worttrainer.getStatistik().setKorrekteVersuche(jsonObject.getInt("korrekteVersuche"));
            worttrainer.getStatistik().setFalscheVersuche(jsonObject.getInt("falscheVersuche"));
            worttrainer.getStatistik().setVersuche(jsonObject.getInt("versuche"));

            return worttrainer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<WortBildPaar> convertJSONArrayToArrayList(JSONArray jsonArray) {
        ArrayList<WortBildPaar> wortBildPaare = new ArrayList<WortBildPaar>();
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            wortBildPaare.add(new WortBildPaar(jsonObject.getString("wort"), jsonObject.getString("url")));
        }
        return wortBildPaare;
    }
}