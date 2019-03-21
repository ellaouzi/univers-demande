package com.fosagri.rest;

import java.io.File;
import java.io.FilenameFilter;

public class Test {


    public static void main(String [] args){

        File dir = new File("C:\\CITIZEN\\");
         File[] files = dir.listFiles((dir1, name) -> name.startsWith("tes") );
         System.out.println(files.length);
    }
}
