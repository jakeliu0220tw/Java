package org.example;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Objects;
import java.io.IOException;
import java.nio.file.Files;

public class App
{
    public static void main( String[] args )
    {
        // parse arguments
        Settings settings = new Settings();
        try {
            JCommander.newBuilder()
                    .addObject(settings)
                    .build()
                    .parse(args);
        }
        catch (ParameterException e){
            settings.usage();
            return;
        }

        String format = settings.format;
        String input = settings.input;
        String inputStr = "";

        if (Objects.equals(format, new String("path"))) {
            // read file content to string
            inputStr = readFileContent(input);
        } else if (Objects.equals(format, new String("url"))) {
            // use http client to get body
            inputStr = readWebContent(input);
        } else if (Objects.equals(format, new String("text"))) {
            // put text into string
            inputStr = input;
        } else {
            // show usage info
            settings.usage();
            return;
        }

        System.out.println("inputStr: \n" + inputStr);
    }

    // Read file content into string
    private static String readFileContent(String filePath)
    {
        String content = "";

        try
        {
            content = new String (Files.readAllBytes(Paths.get(filePath)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return content;
    }

    // Read web content into string
    private static String readWebContent(String url)
    {
        String content = "";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofMillis(5000))
                    .GET()
                    .build();

            HttpResponse<String> response = null;
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                content = response.body();
            }
            else
            {
                // print error status code
                System.out.println("StatusCode: " + response.statusCode());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }
}
