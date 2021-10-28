# Gitlet Design Document

**Name**: Rish Bellakka

## Classes and Data Structures

### Commit

#### Fields

1. private String message;
2. private String dateTime;
3. private String parentHash;
4. private HashMap<String, String> fileList;


### Blob

#### Fields

1. public byte [] contents;


## Algorithms
N/A

## Persistence
1.  public static File objects = join(GITLET_DIR, "objects");
    public static File blobs = join(GITLET_DIR, "blobs");
    public static File ref = join(GITLET_DIR, "ref");
    public static File head = join(GITLET_DIR, "HEAD");
    public static File branches = join(ref,"branches");
    public static File master = join(branches,"master");
    public static File commitTree = join(ref,"commit_tree");
    public static File blobMap = join(ref,"blob_map");
    public static File masterMap = join(ref, "master_map");
    public static File stagingArea = join(GITLET_DIR, "staging_area");
    public static File addStage = join(stagingArea, "add_stage");
    public static File removeStage = join(stagingArea, "remove_stage");

