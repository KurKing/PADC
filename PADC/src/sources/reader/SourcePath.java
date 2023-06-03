package sources.reader;

public class SourcePath {

    public static String getMGPath() { return getPath("MG.txt"); }
    public static String getMBPath() { return getPath("MB.txt"); }
    public static String getMTPath() { return getPath("MT.txt"); }
    public static String getMZPath() { return getPath("MZ.txt"); }
    public static String getMEPath() { return getPath("ME.txt"); }

    public static String getAPath() { return getPath("A.txt"); }
    public static String getCPath() { return getPath("C.txt"); }

    public static String getSmallAPath() { return getPath("smallA.txt"); }

    private static String getPath(String fileName) {

//        return "/Users/oleksii/Documents/PADC/PADC/src/sources/files/"+fileName;
        return "/Users/oleksii/Documents/PADC/PADC/src/sources/filesForTest/"+fileName;
    }
}
