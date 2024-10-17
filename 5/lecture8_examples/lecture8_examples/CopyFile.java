import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Example code for lecture 8.
 * @author Jeffrey Eppinger & Terry Lee
 */
public class CopyFile {

    /**
     * Simple test program to copy files using io package.
     * @param args arguments
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Program to copy a given file to a target file.");
            System.out.println("Usage: java CopyFile sourceFile targetFile");
            return;
        }

        File sourceFile = new File(args[0]);
        if (!sourceFile.exists()) {
            System.out.println("Source file " + args[0] + " does not exist");
            return;
        }

        File targetFile = new File(args[1]);
        if (targetFile.exists()) {
            System.out.println("Target file " + args[1] + " already exists");
            return;
        }

        BufferedInputStream input = new BufferedInputStream(
                new FileInputStream(sourceFile));

        BufferedOutputStream output = new BufferedOutputStream(
                new FileOutputStream(targetFile));

        int r = 0;
        int numberOfBytesCopied = 0;
        while ((r = input.read()) != -1) {
            output.write((byte) r);
            numberOfBytesCopied++;
        }

        output.close();
        input.close();
        System.out.println(numberOfBytesCopied + " bytes copied");
    }

}
