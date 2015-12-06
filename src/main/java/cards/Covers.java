package cards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by cyou on 12/6/15.
 */
public class Covers {

    public boolean saveCover(int alboumId, byte[] coverBytes){
        try {
            FileOutputStream fos = new FileOutputStream(new File("cardset-cover-" + alboumId + ".jpg"));
            fos.write(coverBytes);
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
