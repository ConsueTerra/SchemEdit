package Interface;

import Utils.SchemEditException;
import static Utils.Utils.error;
import static Utils.Core.*;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                throw error("Please enter a command.");
            }
            String command = args[0];
            switch (command) {
                case "help" -> {
                    argsCheck(1, 1, args);
                    help();
                }
                case "repo" -> {
                    argsCheck(2, 2, args);
                    repo(args[1]);
                }
                case "duplicate" -> {
                    argsCheck(3, 3, args);
                    duplicate(args[1],args[2]);
                }
                case "replace" -> {
                    argsCheck(4,4,args);
                    replace(args[1],args[2],args[3]);
                }
                case "variation" -> {
                    argsCheck(5,5,args);
                    variation(args[1],args[2],args[3],args[4]);
                }
                case "metadata" -> {
                    argsCheck(2, 2, args);
                    metadata(args[1]);
                }
                default -> throw error("No command with that name exists");
            }
        } catch (SchemEditException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    private static void argsCheck(int min, int max, String[] args) {
        if (args.length < min || args.length > max) {
            throw error("Incorrect operands.");
        }
    }
}
