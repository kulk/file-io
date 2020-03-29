package controller;

import client.ClientInterface;
import model.FinalVariables;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Operator {
    FinalVariables finalVariables = new FinalVariables();
    private String engCv = finalVariables.getEngCv();
    private String nlCv = finalVariables.getNlCv();
    private String engCvName = finalVariables.getEngCvName();
    private String nlCvName = finalVariables.getNlCvName();
    private String nlLetter = finalVariables.getNlLetter();
    private String engLetter = finalVariables.getEngLetter();
    private String path = finalVariables.getPath();
    private String destinationFolder;

    public void run() {
        ClientInterface client = new ClientInterface();
        ArrayList<String> languageCompany = client.getUserInput();
        copyLanguageFiles(languageCompany);
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

    private void copyLanguageFiles(ArrayList<String> languageCompanyJob) {
        this.destinationFolder = createFolder(languageCompanyJob);
        String letterName = languageCompanyJob.get(1) + "-" + languageCompanyJob.get(2) + "-brief.docx";
        if (languageCompanyJob.get(0).equalsIgnoreCase("2")) {
            copyFile(engCv, destinationFolder + "\\" + engCvName);
            copyFile(engLetter, destinationFolder + "\\" + letterName);
        } else if (languageCompanyJob.get(0).equalsIgnoreCase("1")) {
            copyFile(nlCv, destinationFolder + "\\" + nlCvName);
            copyFile(nlLetter, destinationFolder + "\\" + letterName);
        }
    }

    private String createFolder(ArrayList<String> languageCompanyJob) {
        String destinationFolder = path + "\\" + languageCompanyJob.get(1) + "-"
                + languageCompanyJob.get(2) + "-"+ getCurrentDate();
        File newDir = new File(destinationFolder);
        if (newDir.mkdir()) {
            System.out.println("Directory created");
        } else {
            System.out.println("Error: directory not created");
        }
        return destinationFolder;
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