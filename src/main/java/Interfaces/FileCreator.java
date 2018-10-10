package Interfaces;

import java.io.File;

public interface FileCreator {
    /**
     *
     * @param pathToDir - path to directory
     * @return if file has been written - true
     */
    boolean doFile(String pathToDir);
}
