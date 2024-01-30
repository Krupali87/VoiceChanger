package com.app.voicechangereffect;

import java.io.File;

public class lpFilenameUtils {
    private static final char lpotherSeparator;

    static {
        if (isSystemWindows()) {
            lpotherSeparator = '/';
        } else {
            lpotherSeparator = '\\';
        }
    }

    public static String lpremoveExtension(String str) {
        if (str == null) {
            return null;
        }
        failIfNullByte(str);
        int lpextensionIndex = lpextensionIndex(str);
        return lpextensionIndex == -1 ? str : str.substring(0, lpextensionIndex);
    }

    public static int lpextensionIndex(String str) throws IllegalArgumentException {
        if (str == null) {
            return -1;
        }
        if (!isSystemWindows() || str.indexOf(58, getAdsCriticalOffset(str)) == -1) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastSeparatorIndex(str) > lastIndexOf) {
                return -1;
            }
            return lastIndexOf;
        }
        throw new IllegalArgumentException("NTFS ADS separator (':') in file name is forbidden.");
    }

    static boolean isSystemWindows() {
        return File.separatorChar == '\\';
    }

    public static int lastSeparatorIndex(String str) {
        if (str == null) {
            return -1;
        }
        return Math.max(str.lastIndexOf(47), str.lastIndexOf(92));
    }

    private static int getAdsCriticalOffset(String str) {
        int lastIndexOf = str.lastIndexOf(File.separatorChar);
        int lastIndexOf2 = str.lastIndexOf(lpotherSeparator);
        if (lastIndexOf != -1) {
            return lastIndexOf2 == -1 ? lastIndexOf + 1 : Math.max(lastIndexOf, lastIndexOf2) + 1;
        } else if (lastIndexOf2 == -1) {
            return 0;
        } else {
            return lastIndexOf2 + 1;
        }
    }

    private static void failIfNullByte(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == 0) {
                throw new IllegalArgumentException("Null byte present in file/path name. There are no known legitimate use cases for such data, but several injection attacks may use it");
            }
        }
    }

    public static String lpgetExtension(String str) throws IllegalArgumentException {
        if (str == null) {
            return null;
        }
        int lpextensionIndex = lpextensionIndex(str);
        return lpextensionIndex == -1 ? "" : str.substring(lpextensionIndex + 1);
    }

    public static String getBaseName(String str) {
        return lpremoveExtension(getName(str));
    }

    public static String getName(String str) {
        if (str == null) {
            return null;
        }
        failIfNullByte(str);
        return str.substring(lastSeparatorIndex(str) + 1);
    }
}
