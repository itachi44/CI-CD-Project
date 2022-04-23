package sn.ept.git.seminaire.cicd.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MyFileReader {

    public List<String> read(String path)  {
        List<String> r= Arrays.asList();
        try {
             r= Files.readAllLines(Paths.get(path));
        }catch (IOException e){
            //what to do ?
        }
        return r;
    }

}
