package com.company;

import java.io.Console;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console console = System.console();
        String timeLineCommand= "";
        if (console != null) {
            timeLineCommand = console.readLine("Введите строку формата \"-R -D D:\\TESTING POLYGON...  :::fileName.txt::: \" или  \"D:\\TESTING POLYGON..  \"");

        }

        String bolvanchik = ".txt";
   //     Scanner in = new Scanner(System.in);
   //     System.out.print("Введите строку формата \"-R -D D:\\TESTING POLYGON...  :::fileName.txt::: \" или  \"D:\\TESTING POLYGON..  \"");
    //     timeLineCommand = in.nextLine();



        for (int i = 0; i < timeLineCommand.length(); i++) {                                       //проверка файлнейм
            String timeI = Character.toString(timeLineCommand.charAt(i));
            if (timeI.equals(":")) {
                if (Character.toString(timeLineCommand.charAt(i + 1)).equals(":")) {
                    if (Character.toString(timeLineCommand.charAt(i + 2)).equals(":")) {
                        bolvanchik = timeLineCommand.split(":::")[1];
                    }
                }
            }
        }

        String[] timeOldDirectory = timeLineCommand.split(" ");         //проверка остальной вводимой строки
        String rrrtest = "-R";
        String dddtest = "+D";
        String testdirectory = "D:\\TESTING POLYGON";
        for (int i = 0; i < timeOldDirectory.length; i++) {
            String charAtone = Character.toString(timeOldDirectory[i].charAt(0));
            String charAttwo = Character.toString(timeOldDirectory[i].charAt(1));
            if (charAtone.equals("-")) {
                if (charAttwo.equals("R")) rrrtest = "-R";
                if (charAttwo.equals("D")) dddtest = "-D";
            }
            if (charAttwo.equals(":"))
                testdirectory = timeOldDirectory[i];
        }



        File directory = new File(testdirectory);

        if (dddtest.equals("-D")) {                         //присваивание корневой папки если -Д то начало поиска с корневой папки

            String timeTestDir = new String();
            char timeD = testdirectory.charAt(0);
            timeTestDir = timeD + ":\\";
            File timeDirectory = new File(timeTestDir);
            directory = timeDirectory;

        } else {                                            //инае начало поиска с норм папки

            if (!testdirectory.equals("")) {
                File timeDirectory = new File(testdirectory);
                directory = timeDirectory;
            }
        }

        LibFiles finallly;
        LibFiles fileList = new LibFiles();
        LibFiles allFilesInOneDir = new LibFiles();
        System.out.print(dddtest);

        finallly = allFilesInOneDir.AllFilesInOneDirDDDMODIFICATOR(directory, fileList, bolvanchik, rrrtest, dddtest, testdirectory);
        for (File file : finallly.libFiles) {
            System.out.println("FINALLY "+file.getAbsolutePath());
            if (console != null) {
                console.printf(file.getAbsolutePath());
            }
        }
    }
}

class LibFiles {
    ArrayList<File> libFiles;

    public LibFiles() {
        this.libFiles = new ArrayList<>();
    }

    public LibFiles AllFilesInOneDirDDDMODIFICATOR(File originalFile, LibFiles fileList, String bolvanchik, String modificatorRRR, String dddTest, String modificatorDDD) {
        LibFiles timeFileList = fileList;                                       //
        if ((originalFile.isDirectory())) {                                       // смотрим является ли директория папкой

            if (dddTest.equals("+D")) {
                System.out.println("searching: " + originalFile.getAbsolutePath());

                File[] directoryFiles = originalFile.listFiles();                                // получим все файлы, которые лежат внутри папки
                if (directoryFiles != null) {                                                       // если нам удалось получить файлы
                    for (File file : directoryFiles) {                                                   // для каждого файла


                        if ((file.isDirectory()) && (modificatorRRR.equals("-R"))) {                               // проверка на поиск в поддиректориях
                            AllFilesInOneDirDDDMODIFICATOR(file, timeFileList, bolvanchik, modificatorRRR, dddTest, modificatorDDD);                  //
                        } else {
                            if (file.getName().toLowerCase().endsWith(bolvanchik)) {                 //если файл не является папкой то проверка условий, по которым ищем
                                System.out.println("!!!!!!!!!!!!!!!!!!!!!: ");
                                timeFileList.libFiles.add(file);

                            }
                        }


                    }
                }

            } else {
                if (originalFile.getParent() != modificatorDDD) {
                    System.out.println("searching: " + originalFile.getAbsolutePath());
                    File[] directoryFiles = originalFile.listFiles();
                    if (directoryFiles != null) {
                        for (File file : directoryFiles) {
                            if ((file.isDirectory()) && (modificatorRRR.equals("-R"))) {
                                AllFilesInOneDirDDDMODIFICATOR(file, timeFileList, bolvanchik, modificatorRRR, dddTest, modificatorDDD);
                            } else {
                                if (file.getName().toLowerCase().endsWith(bolvanchik)) {
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
        return timeFileList;
    }
}
