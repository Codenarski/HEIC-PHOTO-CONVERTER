import io.github.codejanovic.java.filesearch.Search;

import javax.swing.*;
import java.io.File;

public class Application {

    public static void main(String[] args) {

        String path = InitializePath();

        new Search() //
                .recursively() //
                .directory(path) //
                .byFile() //
                .stream() //
                .filter(File::isFile) //
                .filter(f -> new isHEIVCFile().test(f)) //
                .peek(f -> System.out.println("Conversion started for " + f.getPath()))
                .forEach(f -> new ConvertHEICIntoPNG().accept(f)); //
        System.out.print("Conversion finished successfully");
    }

    private static String InitializePath() {
        //TODO: make format variable on ui
        String path = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = chooser.showOpenDialog(null);
        File file = null;

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        }
        if (file != null) {
            path = file.getPath();
        }
        return path;
    }
}
