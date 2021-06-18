package com.schemedit.utils;


import me.nullicorn.nedit.NBTReader;
import me.nullicorn.nedit.NBTWriter;
import me.nullicorn.nedit.type.NBTCompound;

import java.io.File;
import java.io.IOException;


import static com.schemedit.utils.Utils.*;

public class Core {

    /**
     * The current working directory.
     */
    public static final File CWD = new File(System.getProperty("user.dir"));

    /** The settings for Schem edit, stored in the CWD as a text file */
    public static final File SETTINGS = join(CWD,"SchemEditSettings.txt");

    /**
     * The repo file
     */
    public static final File REPODIR = getRepoDir();

    public static final String SCHEMEXT = ".schem";

    public static void repo(String dirPath) {
        setRepoDir(dirPath);
        System.out.println("New Repository at :" + dirPath);
    }

    public static void list() {
        list("*");
    }

    public static void list(String filter) {
        File[] files = filterdFileList(REPODIR,filter + SCHEMEXT);
        for (File file : files) {
            System.out.println(file.getPath());
        }
    }

    public static void duplicate(String targetFileName, String newFileName) {
        throw error("Not Implemented!");
    }

    public static void replace(String targetFileName, String mask, String pattern) {
        throw error("Not Implemented!");
    }

    public static void variation(String targetFileName, String newFileName, String mask,
                                 String pattern) {
        throw error("Not Implemented!");
    }

    public static void metadata(String targetFileName) {
        File[] files = filterdFileList(REPODIR,targetFileName + SCHEMEXT);
        for (File file : files) {
            try {
                NBTCompound schematicNBT = NBTReader.readFile(file);
                System.out.println("-------------");
                System.out.println(file.getName());
                int width = schematicNBT.getInt("Width",-1);
                int length = schematicNBT.getInt("Length",-1);
                int height = schematicNBT.getInt("Height",-1);
                System.out.printf("Width: %s, Length: %s, Height: %s",width,length,height);
                System.out.println();
                int Xoff = schematicNBT.getInt("Metadata.WEOffsetX",666);
                int Yoff = schematicNBT.getInt("Metadata.WEOffsetY",666);
                int Zoff = schematicNBT.getInt("Metadata.WEOffsetZ",666);
                System.out.printf("Offset: (X: %s, Y: %s, Z: %s)",Xoff,Yoff,Zoff);
                System.out.println();
            } catch (IOException excp) {
                throw new IllegalArgumentException(excp.getMessage());
            }
        }
    }

    public static void help() {
        String message = """
    SchemEdit is a command line program to modify spigot Schematics (1.13+) without the use of WorldEdit
    
    SchemEdit accepts file wildcards for almost all operations and performs batch processing
    As well as file nesting, the ? wild card is a variable character card while the * card is a
    variable suffix prefix, ie so trees/* will select all schematics inside the trees directory
    
    SchemEdit only displays and manipulates schem files additionally the file extension is omitted ie
    Use tree1 INSTEAD of tree1.schem
         
    help -> brings this up
    repo [path] -> sets the repository directory for where SchemEdit will look for schematics,
        use absolute file path ie C:/Users/User/Desktop
    list -f <filter> lists all the schematics in a given repository, or that match the filename filter
    duplicate -> not implemented
    replace -> not implemented
    variation -> not implemented
    metadata [filter] -> displays the metadata for all schems in a given repository that match the filter
                """;
        System.out.println(message);
    }

    /* Aux funcions */

    private static File getRepoDir() {
        try {
            if (!SETTINGS.exists()) {
                SETTINGS.createNewFile();
                NBTCompound newSet = new NBTCompound();
                NBTWriter.writeToFile(newSet,SETTINGS);
            }
            NBTCompound settings = NBTReader.readFile(SETTINGS);
            String dirPath = settings.getString("Repo", CWD.getAbsolutePath());
            File repoDir = new File(dirPath);
            System.out.println("Current Repository at :" + dirPath);
            return repoDir;
        } catch (IOException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    private static void setRepoDir(String newRepoPath) {
        try {
            NBTCompound settings = NBTReader.readFile(SETTINGS);
            settings.put("Repo",newRepoPath);
            NBTWriter.writeToFile(settings,SETTINGS);
        } catch (IOException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }
}
