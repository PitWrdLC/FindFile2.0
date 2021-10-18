package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File directory = new File("D:\\");
        String dddModificator = new String();
        Scanner in = new Scanner(System.in);
        System.out.print("input file and filetype or filetype(.txt)");
        String bolvanchik = in.nextLine();
        System.out.print("-+R ");
        String rrrtest = in.nextLine();
        System.out.print("-+D ");
        String dddtest = in.nextLine();
        System.out.print("директория D:\\TESTING POLYGON...");
        String testdirectory = in.nextLine();


        if (dddtest.equals("-D")) {
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!");
            String timeTestDir = new String();
            char timeD = testdirectory.charAt(0);                               // поиск на определенном диске, указанном в дддтест но без папки, указаной там же
            timeTestDir = timeD + ":\\";
            File timeDirectory = new File(timeTestDir);
            directory = timeDirectory;
            System.out.print(timeTestDir + "!!!!!!!!!!!!!!!!!!!!!!!!!");
        } else {

            if (!testdirectory.equals("")) {
                File timeDirectory = new File(testdirectory);
                directory = timeDirectory;
            }
        }

        LibFiles finallly = new LibFiles();
        LibFiles fileList = new LibFiles();
        LibFiles allFilesInOneDir = new LibFiles();
        System.out.print(dddtest);

        finallly = allFilesInOneDir.AllFilesInOneDirDDDMODIFICATOR(directory, fileList, bolvanchik, rrrtest, dddtest, testdirectory);
        for (File file : finallly.libFiles) {
            System.out.println(file.getAbsolutePath());
        }
    }
}

class LibFiles {
    ArrayList<File> libFiles;

    public LibFiles() {
        this.libFiles = new ArrayList<>()  ;
    }

    public LibFiles AllFilesInOneDirDDDMODIFICATOR(File originalFile, LibFiles fileList, String bolvanchik, String modificatorRRR, String dddTest, String modificatorDDD) {
        LibFiles timeFileList = fileList;
        if ((originalFile.isDirectory())) {                                                                                           // смотрим является ли директория папкой
            if (dddTest.equals("+D")) {
                System.out.println("searching: " + originalFile.getAbsolutePath());
                File[] directoryFiles = originalFile.listFiles();                                                                        // получим все файлы, которые лежат внутри папки
                if (directoryFiles != null) {                                                                                        // если нам удалось получить файлы
                    for (File file : directoryFiles) {                                                                              // для каждого файла
                        if ((file.isDirectory()) && (modificatorRRR.equals("+R"))) {                                                    // проверка на поиск в поддиректориях
                            AllFilesInOneDirDDDMODIFICATOR(file, timeFileList, bolvanchik, modificatorRRR, dddTest, modificatorDDD);                            //
                        } else {
                            if (file.getName().toLowerCase().endsWith(bolvanchik)) {                                                    //если файл не является папкой то проверка условий, по которым ищем
                                timeFileList.libFiles.add(file);

                            }
                        }
                    }
                }
            } else {
                System.out.print("\n" + modificatorDDD);
                System.out.print("\n" + dddTest);
                if (originalFile.getParent() != modificatorDDD) {
                    System.out.println("searching: " + originalFile.getAbsolutePath());
                    File[] directoryFiles = originalFile.listFiles();
                    if (directoryFiles != null) {
                        for (File file : directoryFiles) {
                            if ((file.isDirectory()) && (modificatorRRR.equals("+R"))) {
                                AllFilesInOneDirDDDMODIFICATOR(file, timeFileList, bolvanchik, modificatorRRR, dddTest, modificatorDDD);
                            } else {
                                if (file.getName().toLowerCase().endsWith(bolvanchik)) {
                                    System.out.print("%%%%%%%%%%%" + "\n" + file.getParent() + "\n" + "%%%%%%%%%%%");
                                    if (file.getParent().equals(modificatorDDD)) {
                                    } else {
                                        timeFileList.libFiles.add(file);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return fileList;
    }
}
