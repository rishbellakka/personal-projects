package hid;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Blob implements Serializable {
    String contents;
    public Blob(String fileName) {
        File f = Utils.join(Repository.CWD, fileName);
        contents = Utils.readContentsAsString(f);
    }
    public static String makeBlob(String fileName, Blob b) {
        String sha = Utils.sha1(b.contents);
        File blobFile = Utils.join(Repository.BLOBS, sha);
        Utils.writeObject(blobFile, b);
        Repository.addToMap(fileName, sha);
        return sha;
    }
    public static void deleteBlob(String fileName) {
        try {
            String sha = Repository.getFromMap(fileName);
            File blobFile = Utils.join(Repository.BLOBS, sha);
            Repository.removeFromMap(fileName);
            if (!blobFile.delete()) throw new IOException("Unable to delete " + sha);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


}
