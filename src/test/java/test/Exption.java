package test;

import base.BasePage;
import base.BaseTest;
import org.apache.logging.log4j.Logger;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Exption extends BaseTest {
    public static void main(String[] args) {
        try {

            Path path = Paths.get("C:\\Users\\CCST\\Desktop\\Mobauto101\\src\\test");
            String content = Files.readString(path);

        } catch (java.nio.file.NoSuchFileException e) {

            throw new FrameworkException("Required file nahi mili! Path check karo: " + e.getFile(), e);

        } catch (java.io.IOException e) {

            throw new FrameworkException("File read karte waqt I/O error aa gaya: " + e.getMessage(), e);
        }
    }
}

class FrameworkException extends RuntimeException {
    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
