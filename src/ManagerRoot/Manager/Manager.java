package ManagerRoot.Manager;

import java.io.File;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;

public class Manager {

    public static Path currentPath(){
        return Paths.get(".");
    }

    public static boolean copy(Path source, Path destination){
        try {
            Files.copy(source, destination);
            return true;
        }catch (FileAlreadyExistsException e) {
            System.out.println("files exists");
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copyOverWrite(Path source, Path destination){
        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean exists(Path source){
        try {

            return Files.exists(source);

        }catch (Exception e){
            return false;
        }
    }

    public static Path createDir(Path dir){
        try {
            Path newPath = Files.createDirectory(dir);
            return newPath;
        }catch (FileAlreadyExistsException e){
            System.out.println("file exists");
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean delete(Path source){
        try {

            Files.delete(source);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

}
