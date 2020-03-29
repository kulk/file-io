package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ClientInterface {

    public ArrayList<String> getUserInput() {
        String companyName = askCompanyName();
        String jobName = askJobName();
        String language = chooseLanguage();
        ArrayList<String> languageCompany = new ArrayList<>();
        Collections.addAll(languageCompany, language, companyName, jobName);
        return languageCompany;
    }

    private String askCompanyName(){
        boolean keepAsking = true;
        Scanner input = new Scanner(System.in);
        String companyName = "";
        while (keepAsking){
            System.out.println("Voer bedrijfsnaam in:");
            companyName = input.nextLine();
            if (companyName.equals("")){
                System.out.println("Bedrijfsnaam mag niet leeg zijn!");
            } else {
                keepAsking = false;
            }
        }
        return companyName;
    }

    //Todo: refactor, askJobName & askCompanyName are the same code
    private String askJobName(){
        boolean keepAsking = true;
        Scanner input = new Scanner(System.in);
        String jobName = "";
        while (keepAsking){
            System.out.println("Voer functie in:");
            jobName = input.nextLine();
            if (jobName.equals("")){
                System.out.println("Functie mag niet leeg zijn!");
            } else {
                keepAsking = false;
            }
        }
        return jobName;
    }

    private String chooseLanguage() {
        boolean keepAsking = true;
        Scanner input = new Scanner(System.in);
        String answer = "";
        while (keepAsking) {
            System.out.println("Kies taal: 1 voor NL of 2 voor ENG");
            answer = input.nextLine();
            if (answer.equals("1") || answer.equals("2")) {
                keepAsking = false;
            } else {
                System.out.println("Ongeldige antwoord");
            }
        }
        return answer;
    }

}

