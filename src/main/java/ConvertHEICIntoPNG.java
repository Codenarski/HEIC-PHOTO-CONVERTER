import org.im4java.core.*;
import org.im4java.process.ProcessStarter;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class ConvertHEICIntoPNG implements Consumer<File> {

    @Override
    public void accept(File file) {
        //TODO: Make mypath and file formats variable
        String filepath = file.getPath();
        String changedType = file.getName().replace(".heic", ".png");
        String filePathForFolderForConvertedFile = filepath.replace(file.getName(), ".convertedPhotos");
        String filepathForConvertedFile = filePathForFolderForConvertedFile + "\\" + changedType;
        new File(filePathForFolderForConvertedFile).mkdir();

        try {
            String myPath = "C:\\Program Files\\ImageMagick-7.0.10-Q16-HDRI";
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
