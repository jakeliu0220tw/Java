package org.example;

import com.beust.jcommander.Parameter;

public class Settings {
    @Parameter(names = "--help", help = true)
    public boolean help;

    @Parameter(names = "--format", description = "input format (possible values: path/url/text)", required = true)
    public String format;

    @Parameter(names = "--input", description = "input source", required = true)
    public String input;

    public void usage() {
        // print usage info
        System.out.println("Usage: <main class> [options]");
        System.out.println("Options:");
        System.out.println("--format    input format (possible values: path/url/text), required");
        System.out.println("--input     input source (filepath/url/text), required");
        System.out.println("Example:");
        System.out.println("--format path --input /tmp/input.json");
        System.out.println("--format url  --input 127.0.0.1:8080/input.html");
        System.out.println("--format text --input {\"aaa\":123}");
    }
}
