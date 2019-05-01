import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RandomWord {
    private HashMap<String, String> websiteInfo = new HashMap<>();
    private List<HTMLInformation> urlAndElement = new ArrayList<>();

    String getRandomWord(String category) throws IOException {
        String randomWord = "";
        switch (category) {
            case "U.S Cities":
                randomWord = findRandomWord(0);
                break;
            case "Superpowers":
                randomWord = findRandomWord(1);
                break;
            case "Countries":
                randomWord = findRandomWord(2);
                break;
            case "U.S. States":
                randomWord = findRandomWord(3);
                break;
            case "Celebrities":
                randomWord = findRandomWord(4);
                break;
            case "Colors":
                randomWord = findRandomWord(5);
                break;
            case "Animals":
                randomWord = findRandomWord(6);
                break;
        }
        return randomWord;
    }

    String findRandomWord(int orderOfUrlElement) throws IOException {
        setMap();
        setWebsiteInfo();
        try {
            String urlElement = urlAndElement.get(orderOfUrlElement).getElement();
            String url = urlAndElement.get(orderOfUrlElement).getURL();
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass(urlElement);
            return elements.get(0).getElementsByClass("font-18").text();
        } catch (UnknownHostException | NoRouteToHostException ignored) {

        }
        return null;
    }

    private void setMap() {
        websiteInfo.put("https://www.bestrandoms.com/random-country","col-sm-12");
        websiteInfo.put("https://www.bestrandoms.com/random-city","col-md-4");
        websiteInfo.put("https://www.bestrandoms.com/random-celebrity","col-md-6");
        websiteInfo.put("https://www.bestrandoms.com/random-animal-generator","col-sm-12");
        websiteInfo.put("https://www.bestrandoms.com/random-colors","col-sm-12");
        websiteInfo.put("https://www.bestrandoms.com/random-state","col-md-4");
        websiteInfo.put("https://www.bestrandoms.com/random-superpower-generator","col-sm-12");

    }

    private void setWebsiteInfo() {
        for ( Map.Entry<String, String> entry : websiteInfo.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            HTMLInformation information = new HTMLInformation(key, value);
            urlAndElement.add(information);
        }
    }
}
