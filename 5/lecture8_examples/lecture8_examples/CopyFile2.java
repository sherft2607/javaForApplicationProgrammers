import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Example code for lecture 8.
 * @author Terry Lee
 */
public class CopyFile2 {

    /**
     * Simple test program to copy files using nio package.
     * @param args arguments
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Program to copy a given file to a target file.");
            System.out.println("Usage: java CopyFile sourceFile targetFile");
            return;
        }

        Path sourceFile = Paths.get(args[0]);
        if(!Files.exists(sourceFile)) {
            System.out.println("Source file " + args[0] + " does not exist");
            return;
        }

        Path targetFile = Paths.get(args[1]);
        if (Files.exists(targetFile)) {
            System.out.println("Target file " + args[1] + " already exists");
            return;
        }

        Path result = Files.copy(sourceFile, targetFile);
        System.out.println(Files.size(result) + " bytes copied");
    }

}
