package org.example;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SpravaPojistenych {

    Scanner sc = new Scanner(System.in, "Windows-1250");

    Set<Pojistenci> zaznamy = new HashSet<>();

    private static String jmeno = "";
    private static String prijmeni = "";
    private static int vek = 0;
    private static String telefoniCislo = "";


    /**
     * Ověří zdali uživatelem zadané jmeno neobsahuje číslice
     * @return true pokud splněna podmínka viz výše, defaultně false
     */
    public boolean overeniJmena() {
        if (jmeno.matches("[a-zA-Z]+$")) {
            return true;
        }
        return false;
    }

    /**
     * Ověří zdali uživatelem zadané příjmení neobsahuje číslice
     * @return true pokud splněna podmínka viz výše, defaultně false
     */
    public boolean overeniPrijemni() {
        if (prijmeni.matches("[a-zA-Z]+$")) {
            return true;
        }
        return false;
    }


    /**
     * Ověří zdali zadaný věk uživatelem je větší nežli 0 a zároveň menší, nebo rovno 99
     * @return true pokud splněna podmínky viz výše, defaultně false
     */

    public boolean overeniVeku() {
        if ((vek > 0) && (vek <= 99)) {
            return true;
        }
        return false;
    }

    /**
     * Ověří uživatelem zadané telefoní číslo, hlídá zdali první číslo není 0 a zdali zadáno právě 9 číslic
     * @return true pokud splěny podmínky viz výše, defaultně false
     */

    public boolean overeniTelefonihoCisla() {
        if (telefoniCislo.matches("^(?=(?:[1-9]){1})(?=[0-9]{9}$).*")) {
            return true;
        }
        return false;
    }

    /**
     * Na základě uživatelem zadaných příkazů, slouží k výběru příslušných metod a tedy ovládání celé app
     */
    public void programEvidence() {
        vytiskniMenu();
        String prikaz = "";
        while(!prikaz.equals("konec")) {
            System.out.println("Zadejte příkaz:");
            prikaz = sc.nextLine().trim().toLowerCase();
            switch(prikaz) {
                case "novy": {
                    novyZaznam();
                    break;
                }
                case "zaznamy": {
                    zobrazZaznamy();
                    break;
                }

                case "hledat": {
                    hledatZaznamy();
                    break;
                }

                case "konec": {
                    ukonciProgram();
                    break;
                }
            }
        }
    }

    /**
     * Přidá nový záznam pojištěnce
     * Záznam se uloží do kolekce (HashSet)
     */
    public void novyZaznam() {
        System.out.println("\nZadejte jméno pojištěného bez diakritiky");
        while(true) {
            jmeno = sc.nextLine().trim().toUpperCase();
            if (overeniJmena()) {
                break;
            }
            else {
                System.out.println("Špatně zadáno: nepoužívejte speciální znaky, ani diakritiku zadejte ve formátu například: Frantisek");
            }
        }

        System.out.println("Zadejte příjmení pojištěného bez diakritiky");
        while(true) {
            prijmeni = sc.nextLine().trim().toUpperCase();
            if (overeniPrijemni()) {
                break;
            }
            else {
                System.out.println("Špatně zadáno: nepoužívejte speciální znaky, ani diakritiku zadejte ve formátu například: Novak");
            }
        }

        System.out.println("Zadejte věk");
        while(true) {
            try {
                vek = Integer.parseInt(sc.nextLine().trim());
                if (overeniVeku()) {
                    break;
                }
                else {
                    System.out.println("Špatně zadáno: zadejte věk v rozmezí 1 - 99");
                }
            } catch (Exception e) {
                System.out.println("Špatně zadáno: zadejte věk ve formátu například: 30");
                }
        }

        System.out.println("Zadejte telefonní číslo");
        while(true) {
                telefoniCislo = sc.nextLine().trim();
                if (overeniTelefonihoCisla()) {
                    break;
                }
                else {
                    System.out.println("Špatně zadáno: zadejte číslo ve formátu například: 123456789");
                }
        }

        Pojistenci novyPojistenec = new Pojistenci(jmeno, prijmeni, vek, telefoniCislo);
        zaznamy.add(novyPojistenec);
        System.out.println("\nData byla uložena");
        System.out.println("--------------------------------\n");
    }

    /**
     * Zobrazí všechny pojištěné uložené v kolekci
     */
    public void zobrazZaznamy() {
        System.out.println("\nVýpis pojištěných:");
        for (Pojistenci pojistenec:zaznamy) {
               System.out.println(pojistenec);
           }
        System.out.println("--------------------------------\n");
    }

    /**
     * Vyhledá pojištěného podle zadaného jména a příjmení
     */
    public void hledatZaznamy() {
        System.out.println("\nZadejte jméno hledaného pojištěného");
        String hledaneJmeno = sc.nextLine().trim().toUpperCase();
        System.out.println("Zadejte příjmení hledaného pojištěného");
        String hledanePrijmeni = sc.nextLine().trim().toUpperCase();
        System.out.println("\nNalezený pojištěný:");
        zaznamy.stream()
                .filter(pojistenci -> pojistenci.getJmeno().equals(hledaneJmeno) && pojistenci.getPrijmeni().equals(hledanePrijmeni))
                .forEach(System.out::println);
        System.out.println("--------------------------------\n");
    }

    /**
     * Ukončí program
     */
    public void ukonciProgram() {
        System.out.println("\nUkončuji program");
        System.out.println("--------------------------------");

    }

    /**
     * Zobrazí uživateli ovládací menu aplikace
     */
    public void vytiskniMenu() {
        System.out.println("----------------   EVIDENCE POJIŠTĚNÝCH   ----------------");
        System.out.println("--------  VYBERTE AKCI: --------");
        System.out.println("- novy: Vytvoření nového pojištěného");
        System.out.println("- zaznamy: Zobrazí všechny pojištěné");
        System.out.println("- hledat: Vyhledá zvoleného pojištěného");
        System.out.println("- smazat: Odstranění vybraného pojištěného");
        System.out.println("- konec: Zavření evidence a ukončení programu");
        System.out.println("----------------   EVIDENCE POJIŠTĚNÝCH   ----------------");
    }
}

