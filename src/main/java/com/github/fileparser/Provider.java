package main.java.com.github.fileparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Providers of source code
 * 
 */
public class Provider {

    public static List<String> provider(File file) {
        if (file == null) {
            return null;
        }
        try {
            return provider(new FileInputStream(file));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> provider(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> lines = new ArrayList<>();
        String line;

        try {
            while (true) {
                if (((line = reader.readLine()) != null))
                    lines.add(line);
                else {
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }


        return lines;
    }

}
