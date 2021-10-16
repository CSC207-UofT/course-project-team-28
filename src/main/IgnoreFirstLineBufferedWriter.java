import java.io.*;

public class IgnoreFirstLineBufferedWriter extends BufferedWriter {
    private int lineCounter;

    public IgnoreFirstLineBufferedWriter(Writer out, int i) {
        super(out);
        this.lineCounter = lineCounter;
    }

    /**
    * A class extending BufferedWriter so that the first line of comment in movie review files are not written.
    */
    @Override
    public void write(String str) throws IOException {
        if (lineCounter > 0) {
            super.write(str);
        }
        lineCounter++;
    }
}

