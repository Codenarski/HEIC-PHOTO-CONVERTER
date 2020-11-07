import java.io.File;
import java.util.function.Predicate;

public class isHEIVCFile implements Predicate<File> {

    @Override
    public boolean test(File file) {
        //TODO: Make file format variable
        String filename = file.getName();
        String[] strings = filename.split("\\.");
        return strings[1].toLowerCase().equals("heic");
    }
}
