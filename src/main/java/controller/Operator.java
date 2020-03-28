package controller;

import client.ClientInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Operator {
    final static String engCv = "C:\\Users\\Roeland\\Dropbox\\Job\\Job 2020\\CV ENG\\CV R.Kulk ENG 2020.pdf";
    final static String nlCv = "C:\\Users\\Roeland\\Dropbox\\Job\\Job 2020\\CV NL\\CV R. Kulk NL 2020.pdf";
    final static String engCvName = "CV R.Kulk ENG 2020.pdf";
    final static String nlCvName = "CV R.Kulk NL 2020.pdf";
    final static String path = "C:\\Users\\Roeland\\Dropbox\\Job\\Job 2020\\Sollicitatie activiteiten";
    final static String testPath = "C:\\Users\\Roeland\\Dropbox\\Job\\Job 2020\\Sollicitatie activiteiten\\test";
    private String destinationFolder;


    public void run() {
        ClientInterface client = new ClientInterface();
        ArrayList<String> languageCompany = client.getUserInput();
        copyLanguageFile(languageCompany);
        try {
            createnewFile(languageCompany.get(1));
        } catch (java.lang.Exception error) {
            System.out.println("Error");
        }
    }

    private void createnewFile(String companyName) throws Exception {
        //Blank Document
        XWPFDocument document = new XWPFDocument();
        //Write the Document in file system
        System.out.println(destinationFolder + "\\" + companyName + "-vacature-" + getCurrentDate() + ".docx");
        FileOutputStream out = new FileOutputStream(
                new File(destinationFolder + "\\" + companyName + "-vacature-" + getCurrentDate()) + ".docx");
        document.write(out);
        out.close();
        System.out.println("createdocument.docx written successully");
    }

    private void copyLanguageFile(ArrayList<String> languageCompany) {
        this.destinationFolder = createFolder(languageCompany.get(1));
        if (languageCompany.get(0).equalsIgnoreCase("eng")) {
            copyFile(engCv, destinationFolder + "\\" + engCvName);
        } else if (languageCompany.get(0).equalsIgnoreCase("nl")) {
            copyFile(nlCv, destinationFolder + "\\" + nlCvName);
        }

    }

    private String createFolder(String companyName) {
        String destionationFolder = path + "\\" + companyName + " " + getCurrentDate();
        File newDir = new File(destionationFolder);
        System.out.println(path + "\\" + companyName + " " + getCurrentDate());
        if (newDir.mkdir()) {
            System.out.println("Directory created");
        } else {
            System.out.println("Directory not created");
        }
        return destionationFolder;
    }

    private String getCurrentDate() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDateTime now = LocalDateTime.now();
        return dateFormat.format(now);
    }

    private void copyFile(String srcFilePath, String destFilePath) {
        try {
            if (srcFilePath != null && srcFilePath.trim().length() > 0 && destFilePath != null && destFilePath.trim().length() > 0) {
                /* Create the source Path instance. */
                Path srcPathObj = Paths.get(srcFilePath);
                /* Create the target Path instance. */
                Path destPathObj = Paths.get(destFilePath);
                /* Rename source to target, replace it if target exist. */
                Path targetPathObj = Files.copy(srcPathObj, destPathObj, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Use java new io to move success from " + srcFilePath + " to " + destFilePath);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}