package helpers;

import java.io.*;
import java.util.List;

import static java.nio.charset.StandardCharsets.*;

public class TextWriter extends ParentHelper
{
    private FileOutputStream fileOutputStream;

    public void stringListToTXT(List<String> stringList, String path) throws IOException
    {
        log.info("write titles to file...");

        fileOutputStream
            = new FileOutputStream(path);

        for (String str : stringList)
        {
            fileOutputStream.write((str + "\n").getBytes(UTF_8));
        }

        fileOutputStream.close();

        log.info("wrote titles to file: " + stringList.toString());
    }
}
