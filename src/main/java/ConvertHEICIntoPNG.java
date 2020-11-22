import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class ConvertHEICIntoPNG implements Consumer<File> {

    String myPath;

    public ConvertHEICIntoPNG() {
        String operatingSystem = System.getProperty("os.name");

        if (operatingSystem.toLowerCase().contains("win")) {
            this.myPath = System.getProperty("user.dir") + "\\ImageMagickWindows";
        }
    }

    @Override
    public void accept(File file) {
        //TODO: Make mypath and file formats variable
        String filepath = file.getPath();
        String changedType = file.getName().replace(".HEIC", ".png");
        if (changedType.equals(file.getName())) {
            changedType = file.getName().replace(".heic", ".png");
        }
        String filePathForFolderForConvertedFile = filepath.replace(file.getName(), ".convertedPhotos");
        String filepathForConvertedFile = filePathForFolderForConvertedFile + "\\" + changedType;
        new File(filePathForFolderForConvertedFile).mkdir();

        try {
            ProcessStarter.setGlobalSearchPath(myPath);
            ConvertCmd cmd = new ConvertCmd();
            cmd.setSearchPath(myPath);
            IMOperation op = new IMOperation();
            op.addImage(filepath);
            op.addImage(filepathForConvertedFile);
            cmd.run(op);
        } catch (IOException | InterruptedException | IM4JavaException e) {
            e.printStackTrace();
        }
    }
}
