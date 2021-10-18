package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class MainTest {


    @Test
    public void testing() {

        File directory = new File("D:\\TESTING POLYGON");
        LibFiles fileList = new LibFiles();
        String bolvanchik = ".txt";
        String rrrtest = "-R";
        String dddtest = "+D";
        String testdirectory = "";
        LibFiles finallly = new LibFiles();
        LibFiles allFilesInOneDir = new LibFiles();
        finallly = allFilesInOneDir.AllFilesInOneDirDDDMODIFICATOR(directory, fileList, bolvanchik, rrrtest, dddtest, testdirectory);
        File finalyTest = new File("D:\\TESTING POLYGON\\TESTINGPOLYGON.txt");
        File fin = new File("");
       for (File file : finallly.libFiles){
           fin = file;
       }

        Assert.assertEquals(finalyTest,fin );
    }


}