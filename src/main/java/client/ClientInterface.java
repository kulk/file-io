package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ClientInterface {

    public ArrayList<String> getUserInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Voer bedrijfsnaam in:");
        String companyName = input.nextLine();
        System.out.println("NL of ENG?");
        String language = input.nextLine();
        ArrayList<String> languageCompany = new ArrayList<>();
        Collections.addAll(languageCompany, language, companyName);
        return languageCompany;
    }

}
