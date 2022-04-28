
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.*;
import java.net.URL;


public class Main {
    static final String URL = "https://api.nasa.gov/planetary/apod?api_key=wxBc4yUn0ewigrRn8SIqvf7IALlAEOTx76Sn74ul";
    static final String fileName = "data.json";


    public static void main(String[] args) throws IOException, ParseException {

        HttpGet request = new HttpGet(URL);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        File jsonFile = new File(fileName);
        CloseableHttpResponse httpResponse = httpClient.execute(request);

        FileUtils.writeStringToFile(jsonFile, EntityUtils.toString(httpResponse.getEntity()));
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(fileName));
        String URLNewString = (String) jsonObject.get("url");
        String imageName = URLNewString.substring(URLNewString.lastIndexOf("/") + 1);
        URL URLNew1 = new URL(URLNewString);
        FileUtils.copyURLToFile(URLNew1, new File(imageName));

    }
}