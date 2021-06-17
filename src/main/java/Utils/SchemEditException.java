package Utils;
/** General SchemEdit exception used to control expected errors in the program
 * result of .getMessage() is the error message to be printed.
 */
public class SchemEditException extends RuntimeException{

    /** A SchemEdit exception with no message*/
    SchemEditException() {super();}

    /** A SchemEdit MSG as its message. */
    SchemEditException(String msg) {
        super(msg);
    }

}
