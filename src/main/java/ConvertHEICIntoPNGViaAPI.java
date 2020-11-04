import com.cloudmersive.client.ConvertImageApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.*;

import java.io.*;
import java.util.function.Function;

public class ConvertHEICIntoPNGViaAPI implements Function<File, File> {

    String path;

    ConvertHEICIntoPNGViaAPI(String path) {
        this.path = path;
    }

    @Override
    public File apply(File HEICFile) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apikeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        apikeyAuth.setApiKey("bccacfb4-3343-4385-9a2b-86fda5e885f5");
        ConvertImageApi apiInstance = new ConvertImageApi();
        byte[] result = null;
        File outputFile = new File(createNewPath(path, HEICFile));

            try {
                result = apiInstance.convertImageImageFormatConvert("HEIC", "PNG", HEICFile);
            } catch (ApiException apiException) {
                apiException.printStackTrace();
            }

        try {
            OutputStream os = new FileOutputStream(outputFile);
            os.write(result);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;
    }
    String createNewPath(String path, File file) {
        return path + "\\.convertedPhotos\\" + file.getName().split("\\.")[0] + ".png";
    }
}
