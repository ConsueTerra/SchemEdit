package Utils;

public class Utils {

    /** Return a SchemEditException whose message is composed from MSG and ARGS as
     *  for the String.format method. */
    public static SchemEditException error(String msg, Object... args) {
        return new SchemEditException(String.format(msg, args));
    }
}
