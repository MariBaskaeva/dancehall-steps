package ru.baskaeva.dancehallsteps.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyFileReader {
    public static String readFile(String path) {
        StringBuilder read = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                read.append(line);
                read.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return read.toString();
    }
}