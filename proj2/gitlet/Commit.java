package gitlet;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.io.File;
import java.io.Serializable;
/** Represents a gitlet commit object.
 *
 *  does at a high level.
 *
 *  @author Rish Bellakka
 */
public class Commit implements Serializable {
    /**
     *
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /**
     * The message of this Commit.
     */
    private String message;
    private String dateTime;
    private String parentHash;
    private HashMap<String, String> fileList;
    private boolean merged = false;
    private String firstParent;
    private String secondParent;

    public Commit(String message, String parentHash, HashMap<String, String> fileList) {
        this.message = message;
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z");
        if (parentHash == null) {
            this.dateTime = format.format(new Date(0));
        } else {
            this.dateTime = format.format(new Date());
        }
        this.parentHash = parentHash;
        this.fileList = fileList;
    }

    public String save() {
        File saved = Utils.join(Repository.OBJECTS, "temp");
        try {
            if (!saved.createNewFile()) {
                throw new IOException("Not created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Utils.writeObject(saved, this);
        String hash = Utils.sha1(Utils.readContentsAsString(saved));
        File h = Utils.join(Repository.OBJECTS, hash);
        if (!saved.renameTo(h)) {
            throw new GitletException();
        }
        return hash;
    }

    public String date() {
        return dateTime;
    }

    public String message() {
        return message;
    }

    public String parentHash() {
        return parentHash;
    }

    public HashMap<String, String> getFileList() {
        return fileList;
    }

    public String mergeProcedure(File curBranch, File givenBranch) {
        merged = true;
        firstParent = Utils.readContentsAsString(curBranch);
        secondParent = Utils.readContentsAsString(givenBranch);
        return save();
    }

    public boolean merged() {
        return merged;
    }

    public String firstParent() {
        return firstParent;
    }

    public String secondParent() {
        return secondParent;
    }
}
