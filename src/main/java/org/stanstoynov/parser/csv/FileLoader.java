package org.stanstoynov.parser.csv;

import java.io.File;
import java.net.URL;

public class FileLoader {

    public File loadFileFromResources(String fileName) {
        URL url = this.getClass()
                .getClassLoader()
                .getResource(fileName);

        if (url == null) {
            throw new IllegalArgumentException(String.format("File with file name %s not found", fileName));
        }

        return new File(url.getFile());
    }
}
