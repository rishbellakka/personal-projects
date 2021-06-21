package hid;
import java.io.File;
import java.io.IOException;

public class Repository {
    // current directory
    public static final File CWD = new File(System.getProperty("user.dir"));
    // repository to hold all hidden and encrypted files and contents
    // needed to support operation of the tool
    public static final File REPOSITORY = Utils.join(CWD, ".hid");
    // Place to hold maps for references towards which files have which contents
    public static final File REFS = Utils.join(REPOSITORY, "refs");
    // directory for containing files which contain the contents of the user
    // files
    public static final File BLOBS = Utils.join(REPOSITORY, "blobs");
    // map for which files go to which blobs
    public static final File FILE_MAP = Utils.join(REFS, "file-map");
    // initializes a hid repository in the user CWD
    public static void createRepository() {
        try {
            if (REPOSITORY.exists()) {
                throw new IOException("There already exists a hid repository");
            } else if (!REPOSITORY.mkdir()
                    || !REFS.mkdir() || !BLOBS.mkdir()
                    || !FILE_MAP.createNewFile()) {
                throw new IOException();
            }
        } catch (IOException e) {
           System.out.println(e.getMessage());
        }
        RefMap files = new RefMap();
        Utils.writeObject(FILE_MAP, files);
    }
    // adds to File map, Key is file name, Value is sha-1 hash of blob content
    public static void addToMap(String file, String blobHash) {
        RefMap retrieved = Utils.readObject(FILE_MAP, RefMap.class);
        retrieved.put(file, blobHash);
        Utils.writeObject(FILE_MAP, retrieved);
    }
    // gets the file's blob's sha-1 hash from FILE_MAP
    public static String getFromMap(String file) {
        RefMap retrieved = Utils.readObject(FILE_MAP, RefMap.class);
        return retrieved.get(file);
    }
    public static void makeBlob(String fileName) {
        Blob b = new Blob(fileName);
        Blob.makeBlob(fileName, b);
    }
    public static void hide(String fileName) {
        try {
            Repository.makeBlob(fileName);
            File f = Utils.join(CWD, fileName);
            if (!f.delete()) throw new IOException("Unable to delete file " + fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static Blob getBlob(String sha) {
        File blobFile = Utils.join(BLOBS, sha);
        return Utils.readObject(blobFile, Blob.class);
    }
    public static void removeFromMap(String fileName) {
        RefMap retrieved = Utils.readObject(FILE_MAP, RefMap.class);
        retrieved.remove(fileName);
        Utils.writeObject(FILE_MAP, retrieved);
    }
    public static void recover(String fileName) {
        try {
            String sha = getFromMap(fileName);
            Blob b = getBlob(sha);
            File recovered = Utils.join(CWD, fileName);
            if (!recovered.createNewFile()) throw new IOException("Unable to recreate "
                    + fileName);
            Utils.writeContents(recovered, b.contents);
            Blob.deleteBlob(fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }




}
