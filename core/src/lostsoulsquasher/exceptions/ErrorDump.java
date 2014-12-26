package lostsoulsquasher.exceptions;

import java.io.*;

public class ErrorDump {
    public static void dumpExceptionToTempFile(Throwable e) {
        e.printStackTrace();
        try {
            dumpExceptionToTempFileWrapped(e);
        } catch (Throwable e2) {
            e2.printStackTrace();
        }
    }

    private static void dumpExceptionToTempFileWrapped(Throwable e) throws IOException {
        File file = File.createTempFile("lostsoulsquashergdx_", ".log");
        FileWriter writer = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(writer);
        try {
            writeExceptionStack(e, bw);
        } finally {
            bw.close();
        }
    }

    private static void writeExceptionStack(Throwable e, BufferedWriter bw) throws IOException {
        while (e != null) {
            bw.write(e.getMessage() + "\n");
            e.printStackTrace(new PrintWriter(bw));
            bw.write("\n");
            e = e.getCause();
        }
    }
}
