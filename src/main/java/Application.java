import io.github.codejanovic.java.filesearch.Search;

import javax.swing.*;
import java.io.File;

public class Application {

    public static void main(String[] args) {

        String path = InitializePath();

        new Search() //
                .notRecursively() //
                .directory(path) //
                .byFile() //
                .stream() //
                .filter(File::isFile) //
                .forEach(f -> new ConvertHEICIntoPNGViaAPI(path).apply(f)); //
    }

    private static String InitializePath() {
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
        new File(path + "\\.convertedPhotos").mkdir();
        return path;
    }
}
