package ru.baskaeva.dancehallsteps.tools;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyFileReader {
    public static String readFile(String name) {
        try {
            Path path = Paths.get(MyFileReader.class.getClassLoader().getResource(name).toURI());
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}