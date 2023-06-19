import java.util.*;
/**
 * A Main osztalyban valositom meg a bekeresek egy reszet, illetve szinte mindent, amit a felhasznalonak latnia kell a jatekhoz. Itt tortenik a csata kiiratasanak folyamata.
 */

public class Main
{
    public static char [][] tabla= new char[10][12];
    public static final int SOR= 10;
    public static final int OSZLOP= 12;
    private static Hos jatekos = new Hos(true);
    private static Hos ellenseg = new Hos(false);

    /**
     * A palyat kiiro fuggveny
     */
    public static void palyatKiir()
    {
        System.out.println("  1 2 3 4 5 6 7 8 9 0 1 2");
        for (int i = 0; i < SOR ; i++)
        {
            for (int j = 0; j < OSZLOP; j++)
            {
                if (i==9 && j == 0) System.out.print("0|");
                else if (j == 0) System.out.print((i + 1) + "|");

                System.out.print(tabla[i][j] + "|");
            }
            System.out.println();
        }
    }

    /**
     * Osszeszamolja, hogy mennyi olyan egyseg van, aminek 0 az osszeletere, es ha megegyezik a jatekoshoz vagy ellenseghez tartozo egyseg listanak a meretevel, akkor vege a jateknak
     * @param list A jatekos es az ellenseg egysegeit tartalmazo lista
     * @return Igaz, ha vege a jateknak, nyert valamelyik fel. Hamis, ha meg van eletben legalabb egy egysege mindketto felnek
     */
    public static boolean jatekVege(ArrayList <Egyseg> list)
    {
        boolean jatekosNyert = false;
        boolean ellensegNyert = false;
        int jatekosDb=0;
        int ellenfelDb=0;
        for (Egyseg egyseg : list)
        {
            if (egyseg.isJatekos() && egyseg.getOsszeletero() == 0)
            {
                jatekosDb++;
                if (jatekosDb == jatekos.getEgysegek().size())
                {
                    ellensegNyert = true;
                    break;
                }
            }
            else if (!egyseg.isJatekos() && egyseg.getOsszeletero() == 0)
            {
                ellenfelDb++;
                if (ellenfelDb == ellenseg.getEgysegek().size())
                {
                    jatekosNyert = true;
                    break;
                }
            }
        }
        if (jatekosNyert)
        {
            System.out.println("Gratulálok, te nyertél!");
            return true;
        }
        else if(ellensegNyert)
        {
            System.out.println("Az ellenség nyert");
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {

        Random rnd = new Random();

        int nehezseg;
        System.out.println("Válaszd ki a nehézséget: ");
        System.out.println("1: könnyű (1300 arany)" );
        System.out.println("2: közepes (1000 arany)");
        System.out.println("3: nehéz (700 arany)");
        do {
            nehezseg = 0;
            Scanner sc = new Scanner(System.in);
            String idk = sc.nextLine();
            try
            {
                nehezseg = Integer.parseInt(String.valueOf(idk));
            }
            catch (Exception exception)
            {
                System.out.println("Hiba, számot adj meg!");
            }
        } while (nehezseg < 1  || nehezseg > 3);

        System.out.println("A kiválasztott nehézség: " + nehezseg);

        switch (nehezseg)
        {
            case 1 -> jatekos.setArany(1300);
            case 2 -> jatekos.setArany(1000);
            case 3 -> jatekos.setArany(700);
        }
        System.out.println(jatekos.getArany());


        // Hős tulajdonságpontjainak beállítása


        System.out.println("Add meg, hogy melyik tulajdonságát szeretnéd módosítani a hősnek és mennyivel");
        System.out.println("Például: '1 5' a támadást fogja 5 ponttal módosítani");
        System.out.println("Ha nem szeretnéd tovább fejleszteni a hős tulajdonságait írd be, hogy 'exit' ");
        System.out.println("1: támadás");
        System.out.println("2: védekezés");
        System.out.println("3: varázserő");
        System.out.println("4: tudás");
        System.out.println("5: morál");
        System.out.println("6: szerencse");

        String idk;
        String [] tomb = new String[2];
        do {
            int tulajdonsagfejlesztes;
            int mennyivel;

            Scanner sc = new Scanner(System.in);
            idk = sc.nextLine();

            if(idk.equals("exit")) break;
            try
            {
                tomb = idk.split(" ",2);
                tulajdonsagfejlesztes= Integer.parseInt(tomb[0]);
                mennyivel = Integer.parseInt(tomb[1]);

                switch (tulajdonsagfejlesztes) {
                    case 1 -> jatekos.setTamadas(mennyivel);
                    case 2 -> jatekos.setVedekezes(mennyivel);
                    case 3 -> jatekos.setVarazsero(mennyivel);
                    case 4 -> jatekos.setTudas(mennyivel);
                    case 5 -> jatekos.setMoral(mennyivel);
                    case 6 -> jatekos.setSzerencse(mennyivel);
                }
                System.out.println("Aranyad: "+ jatekos.getArany());
            }
            catch (Exception exception)
            {
                System.out.println("Hiba, rossz formátum!");
            }

        }while (true);



        //Varázslatok hozzáadása
        Varazslat varazslat1= new Varazslat("Villámcsapás",60,5);
        Varazslat varazslat2= new Varazslat("Tűzlabda",120,9);
        Varazslat varazslat3= new Varazslat("Gyógyítás",120,6);
        Varazslat varazslat4= new Varazslat("Tűzszőnyeg",80,7);
        Varazslat varazslat5= new Varazslat("Altatás",40,4);


        System.out.println("Válaszd ki melyik várazslatot szeretnéd megvenni: ");
        System.out.println("Aranyad: "+jatekos.getArany());
        System.out.println("1: Villámcsapás   ára: "+varazslat1.getAr()+" Manna: "+varazslat1.getManna());
        System.out.println("2: Tűzlabda   ára: "+varazslat2.getAr()+" Manna: "+varazslat2.getManna());
        System.out.println("3: Gyógyítás:   ára: "+varazslat3.getAr()+" Manna: "+varazslat3.getManna());
        System.out.println("4: Tűzszőnyeg   ára: "+varazslat4.getAr()+" Manna: "+varazslat4.getManna());
        System.out.println("5: Altatás   ára: "+varazslat5.getAr()+" Manna: "+varazslat5.getManna());



        int a = 0;
        do {
            Scanner sc = new Scanner(System.in);
            idk = sc.nextLine();
            if(idk.equals("exit")) break;

            try {
                a = Integer.parseInt(idk);
                switch (a) {
                    case 1 -> jatekos.varazslatotHozzaad(varazslat1);
                    case 2 -> jatekos.varazslatotHozzaad(varazslat2);
                    case 3 -> jatekos.varazslatotHozzaad(varazslat3);
                    case 4 -> jatekos.varazslatotHozzaad(varazslat4);
                    case 5 -> jatekos.varazslatotHozzaad(varazslat5);
                }
                System.out.println("Aranyad: "+ jatekos.getArany());
            }
            catch (Exception exception)
            {
                System.out.println("Hiba!");
            }

        }while (true);



        //Egységek hozzáadása

        Foldmuves foldmuves = new Foldmuves(true);
        Ijasz ijasz = new Ijasz(true);
        Griff griff = new Griff(true);
        Pajzsos pajzsos = new Pajzsos(true);
        Orgyilkos orgyilkos = new Orgyilkos(true);

        System.out.println("Válasszon melyik egységet szeretné megvenni és mennyit, szóközzel elválasztva!");
        System.out.println("Például: '1 5' a Földművesből fog venni 5 darabot");
        System.out.println("Ha nem szeretne tovább fejleszteni a tulajdonságit írja be, hogy 'exit' ");

        System.out.println("1: Földműves   ára: "+foldmuves.getAr()+" Életereje: "+foldmuves.getEletero() + " Sebessége: "+foldmuves.getSebesseg() + " Kezdemenyezese: "+foldmuves.getKezdemenyezes());
        System.out.println("2: Ijasz   ára: "+ijasz.getAr()+" Életereje: "+ijasz.getEletero() + " Sebessége: "+ijasz.getSebesseg() + " Kezdemenyezese: "+ijasz.getKezdemenyezes());
        System.out.println("3: Griff   ára: "+griff.getAr()+" Életereje: "+griff.getEletero() + " Sebessége: "+griff.getSebesseg() + " Kezdemenyezese: "+griff.getKezdemenyezes());
        System.out.println("4: Pajzsos   ára: "+pajzsos.getAr()+" Életereje: "+pajzsos.getEletero() + " Sebessége: "+pajzsos.getSebesseg() + " Kezdemenyezese: "+pajzsos.getKezdemenyezes() +" Hárítási esélye: "+pajzsos.getHaritasiKepesseg()+"%");
        System.out.println("5: Orgyilkos   ára: "+orgyilkos.getAr()+" Életereje: "+orgyilkos.getEletero() + " Sebessége: "+orgyilkos.getSebesseg() + " Kezdemenyezese: "+orgyilkos.getKezdemenyezes()+" Bónusz sebzés ellenség mögül: "+orgyilkos.getBonuszSebzes()+"%") ;


        do {
            int egyseg;
            int darab;
            Scanner sc = new Scanner(System.in);
            idk = sc.nextLine();

            if(idk.equals("exit") && Egyseg.tipusdb > 0) break;
            try
            {
                tomb = idk.split(" ",2);
                egyseg= Integer.parseInt(tomb[0]);
                darab = Integer.parseInt(tomb[1]);
                if(darab > 0)
                {
                    switch (egyseg)
                    {
                        case 1 -> jatekos.egysegetHozzaad(foldmuves, darab, true);
                        case 2 -> jatekos.egysegetHozzaad(ijasz, darab,true);
                        case 3 -> jatekos.egysegetHozzaad(griff, darab,true);
                        case 4 -> jatekos.egysegetHozzaad(pajzsos, darab,true);
                        case 5 -> jatekos.egysegetHozzaad(orgyilkos, darab,true);
                    }
                    System.out.println("Aranyad: "+ jatekos.getArany());
                }
            }
            catch (Exception exception)
            {
                System.out.println("Hiba, nem vettél egy egységet sem, vagy rossz volt a formátum!");
            }
        }while (true);

        //ellenséges hős létrehozása:


        ellenseg.setArany(1000);

        ellenseg.setTamadas(rnd.nextInt(1,11));
        ellenseg.setVedekezes(rnd.nextInt(1,11));
        ellenseg.setMoral(rnd.nextInt(1,11));
        ellenseg.setSzerencse(rnd.nextInt(1,11));
        ellenseg.setTudas(rnd.nextInt(1,11));
        ellenseg.setVarazsero(rnd.nextInt(1,11));

        int sorszam;
        do
        {
            sorszam=rnd.nextInt(1,4);
            switch (sorszam)
            {
                case 1 -> ellenseg.varazslatotHozzaad(varazslat2);
                case 2 -> ellenseg.varazslatotHozzaad(varazslat3);
                case 3 -> ellenseg.varazslatotHozzaad(varazslat4);
            }
        }while (ellenseg.getVarazslatok().size() < 2);

        Egyseg ellensegesFoldmuves = new Foldmuves(false);
        Egyseg ellensegesIjasz = new Ijasz(false);
        Griff ellensegesGriff = new Griff(false);

        ellenseg.egysegetHozzaad(ellensegesGriff, 10,false);
        ellenseg.egysegetHozzaad(ellensegesIjasz, 10,false);
        ellenseg.egysegetHozzaad(ellensegesFoldmuves, 10,false);

        int megvehetodb;
        if(ellenseg.getArany() > 15)
        {
            megvehetodb = ellenseg.getArany() / 15;
            ellenseg.egysegetHozzaad(ellensegesGriff, megvehetodb + 10,false);
        }
        if (ellenseg.getArany() > 6)
        {
            megvehetodb = ellenseg.getArany() / 6;
            ellenseg.egysegetHozzaad(ellensegesIjasz, megvehetodb + 10,false);
        }
        if (ellenseg.getArany() > 2)
        {
            megvehetodb = ellenseg.getArany() / 2;
            ellenseg.egysegetHozzaad(ellensegesFoldmuves, megvehetodb + 10,false);
        }
        for (Egyseg item : ellenseg.getEgysegek())
        {
            item.setOsszeletero(item.getDbSzam () * item.getEletero());
        }




        Egyseg [] osszevont = new Egyseg[3 + jatekos.getEgysegek().size()];
        int index = 0;
        for (int i = 0 ; i < 3 ; i++)
        {
            osszevont[i] = ellenseg.getEgysegek().get(i);
            osszevont[i].setKezdemenyezes(osszevont[i].getKezdemenyezes(), ellenseg);
        }
        for (int i = 3 ; i < jatekos.getEgysegek().size() + 3 ; i++)
        {
            osszevont[i] = jatekos.getEgysegek().get(index);
            osszevont[i].setKezdemenyezes(osszevont[i].getKezdemenyezes(), jatekos);
            index++;
        }

        for (int i = osszevont.length - 1; i > 0 ; i--)
        {
            for (int j = 0; j < osszevont.length - 1 ; j++)
            {
                if (osszevont[j].getKezdemenyezes() < osszevont[j + 1].getKezdemenyezes())
                {
                    Egyseg tmp = osszevont [j];
                    osszevont[j] = osszevont [j + 1];
                    osszevont[j + 1] = tmp;
                }
            }
        }

        ArrayList <Egyseg> list = new ArrayList <Egyseg>(List.of(osszevont));

        for (int i = 0; i < SOR ; i++)
        {
            for (int j = 0; j < OSZLOP; j++)
            {
                tabla[i][j]='-';
            }
        }

        System.out.println();
        Thread.sleep(1000);

        ellenseg.kezdolepesek();
        jatekos.kezdolepesek();


        System.out.println();
        System.out.println("Az ellenség tulajdonságai: ");
        Thread.sleep(500);
        System.out.println("Támadás: "+ellenseg.getTamadas());
        Thread.sleep(500);
        System.out.println("Védekezés: "+ellenseg.getVedekezes());
        Thread.sleep(500);
        System.out.println("Morál: "+ellenseg.getMoral());
        Thread.sleep(500);
        System.out.println("Tudás: "+ellenseg.getTudas());
        Thread.sleep(500);
        System.out.println("Szerencse: "+ellenseg.getSzerencse());
        Thread.sleep(500);
        System.out.println("Varázserő: "+ellenseg.getVarazsero());
        Thread.sleep(500);
        System.out.println();


        System.out.println("Az ellenség egységei: ");
        System.out.println();
        for (Egyseg aa : ellenseg.getEgysegek())
        {
            System.out.println("Egység: "+aa.getNev()+" Mennyiség: "+aa.getDbSzam()+" darab" +" Összéleterő: "+aa.getOsszeletero());
            Thread.sleep(500);
        }

        System.out.println();
        System.out.println("Az ellenség varázslatai: ");
        System.out.println();
        for (Varazslat aa : ellenseg.getVarazslatok())
        {
            System.out.println("Varázslat: "+aa.getNev());
            Thread.sleep(500);
        }

        System.out.println();
        System.out.println("Az egységek a következő sorrendben következnek: ");
        System.out.println();
        index = 1;
        for (Egyseg kezdes : list)
        {
            System.out.println(index+": "+ (kezdes.jatekos ? "jatekos" : "ellenség") +" "+kezdes.getNev());
            index++;
            Thread.sleep(500);
        }




        boolean ellensegNyert = false;
        boolean jatekosNyert = false;
        int korokSzama = 0;
        int bemenet = 0;
        int jatekosDb=0;
        int ellenfelDb=0;
        boolean vege = false;

        //CSATA KEZDETE
        while(!vege)
        {
            System.out.println();
            System.out.println((korokSzama + 1)+". kör");
            System.out.println();
            Thread.sleep(1000);
            jatekos.setTamadhat(true);
            ellenseg.setTamadhat(true);

            System.out.println("A megmaradt egységek: ");
            for (Egyseg egyseg : list)
            {
                egyseg.setVisszatamadhat(true);


                if (egyseg.getOsszeletero() != 0)
                {
                    System.out.println((egyseg.isJatekos() ? "Játékos egysége: " : "ellenség egysége: ")+egyseg.getNev() + ". Élete: "+egyseg.getOsszeletero()+" Mennyiség: "+ egyseg.getDbSzam());
                    Thread.sleep(500);
                }
            }
            System.out.println();

            for (Egyseg item : list)
            {
                Thread.sleep(2000);
                if (item.isJatekos() && item.getOsszeletero() > 0)
                {
                    if (jatekos.getTamadhat())
                    {
                        System.out.println();
                        System.out.println("Egységed: "+item.getNev()+"  Mennyiség: "+item.getDbSzam());
                        System.out.println();
                        System.out.println("Adja meg mit szeretnél tenni: ");
                        System.out.println("1: Egységgel támad");
                        System.out.println("2: lép");
                        System.out.println("3: várakozás");
                        System.out.println("4: Hőssel támad");
                        System.out.println("5: Varázsol");
                        do {
                            try
                            {
                                Scanner sc = new Scanner(System.in);
                                idk = sc.nextLine();
                                bemenet = Integer.parseInt(idk);
                            }
                            catch (Exception e)
                            {
                                System.out.println("Hiba");
                            }
                        }while (bemenet < 1 || bemenet > 5);
                    }
                    else if (!item.isAlszik())
                    {
                        System.out.println();
                        System.out.println("Egységed: "+item.getNev()+" Mennyiség: "+item.getDbSzam());
                        System.out.println("Add meg mit szeretnél tenni: ");
                        System.out.println("1: Egységgel támad");
                        System.out.println("2: lép");
                        System.out.println("3: várakozás");
                        do {
                            try
                            {
                                Scanner sc = new Scanner(System.in);
                                idk = sc.nextLine();
                                bemenet = Integer.parseInt(idk);
                            }
                            catch (Exception e)
                            {
                                System.out.println("Hiba");
                            }
                        }while (bemenet < 1 || bemenet > 3);
                    }
                    else
                    {
                        System.out.println("Ez az egység alszik!");
                        item.setAlszik(false);
                    }

                    if(bemenet == 4)
                    {
                        if(jatekos.getTamadhat())
                        {
                            System.out.println("Válaszd ki melyik ellenséges egységet szeretnéd megtámadni: ");
                            index = 1;
                            for (int i = 0; i < list.size() ; i++)
                            {
                                if (!list.get(i).jatekos && list.get(i).getOsszeletero() != 0)
                                {
                                    System.out.println((index)+": "+list.get(i).getNev()+" Élete: "+list.get(i).getOsszeletero());
                                    index++;
                                }
                            }

                            do
                            {
                                try
                                {
                                    Scanner sc = new Scanner(System.in);
                                    idk = sc.nextLine();
                                    a = Integer.parseInt(idk);
                                }
                                catch (Exception e)
                                {
                                    System.out.println("Hiba");
                                }
                            }while (a < 1 || a > ellenseg.getEgysegek().size());

                            switch (a)
                            {
                                case 1:
                                {
                                    jatekos.hosTamad(ellensegesGriff);
                                    break;
                                }
                                case 2:
                                {
                                    jatekos.hosTamad(ellensegesIjasz);
                                    break;
                                }
                                case 3:
                                {
                                    jatekos.hosTamad(ellensegesFoldmuves);
                                    break;
                                }
                            }
                            if (jatekVege(list))
                            {
                                vege = true;
                                break;
                            }
                            jatekos.setTamadhat(false);
                        }
                    }

                    if(bemenet == 5)
                    {
                        if (jatekos.getVarazslatok().isEmpty())
                        {
                            System.out.println("Nem vásároltál egyetlen varázslatot sem!");
                        }
                        else
                        {
                            System.out.println("Add meg melyik varázslatodat szeretnéd használni");
                            System.out.println("Elérhető varázslatok: ");
                            for (Varazslat varazslat : jatekos.getVarazslatok())
                            {
                                System.out.println(varazslat.getNev());
                            }
                            String s = "";
                            do
                            {
                                try
                                {
                                    Scanner sc = new Scanner(System.in);
                                    idk = sc.nextLine();
                                    s = idk.substring(0, 1).toUpperCase() + idk.substring(1);
                                    if (s.equals(varazslat1.getNev()) && jatekos.getVarazslatok().contains(varazslat1))
                                    {
                                        varazslat1.villamcsapas(jatekos, list);
                                        ellenseg.setTamadhat(false);
                                        break;
                                    }
                                    else if (s.equals(varazslat2.getNev()) && jatekos.getVarazslatok().contains(varazslat2))
                                    {
                                        varazslat2.tuzlabda(jatekos, jatekos.getMap(), ellenseg.getMap());
                                        ellenseg.setTamadhat(false);
                                        break;
                                    }
                                    else if (s.equals(varazslat3.getNev()) && jatekos.getVarazslatok().contains(varazslat3))
                                    {
                                        varazslat3.gyogyitas(jatekos,list);
                                        ellenseg.setTamadhat(false);
                                        break;
                                    }
                                    else if (s.equals(varazslat4.getNev()) && jatekos.getVarazslatok().contains(varazslat4))
                                    {
                                        varazslat4.tuzSzonyeg(jatekos,jatekos.getMap(),ellenseg.getMap());
                                        ellenseg.setTamadhat(false);
                                        break;
                                    }
                                    else if (s.equals(varazslat5.getNev()) && jatekos.getVarazslatok().contains(varazslat5))
                                    {
                                        varazslat5.altatas(jatekos,list);
                                        ellenseg.setTamadhat(false);
                                        break;
                                    }

                                }
                                catch (Exception e)
                                {
                                    System.out.println("Hiba");
                                }
                            }while (true);

                            if (jatekVege(list))
                            {
                                vege = true;
                                break;
                            }
                        }
                    }

                    if (bemenet == 4 || bemenet == 5)
                    {
                        System.out.println();
                        System.out.println("Egységed: "+item.getNev()+" Mennyiség: "+item.getDbSzam());
                        System.out.println("Add meg mit szeretnél tenni: ");
                        System.out.println("1: Egységgel támad");
                        System.out.println("2: lép");
                        System.out.println("3: várakozás");
                        do {
                            try
                            {
                                Scanner sc = new Scanner(System.in);
                                idk = sc.nextLine();
                                bemenet = Integer.parseInt(idk);
                            }
                            catch (Exception e)
                            {
                                System.out.println("Hiba");
                            }
                        }while (bemenet < 1 || bemenet > 3);
                    }

                    switch (bemenet)
                    {
                        case 1:
                        {
                            if (item.tamadhat(item, ellenseg.getMap()))
                            {
                                if (item instanceof Ijasz)
                                {
                                    System.out.println("Ezek az ellenséges egységek támadhatóak: ");
                                    for (Egyseg nev : ellenseg.getEgysegek())
                                    {
                                        if (nev.getOsszeletero() != 0) System.out.println(nev.getNev()+" ("+nev.getOsszeletero()+" elet)");
                                    }
                                }
                                item.tamadas(jatekos, item, ellenseg, list);
                                if (jatekVege(list))
                                {
                                    vege = true;
                                    break;
                                }
                            }
                            else
                            {
                                System.out.println("Nincsen ellenséges egység, aki támadható lenne");
                            }
                            break;
                        }
                        case 2:
                        {
                            System.out.println("Egység sebessége: "+item.getSebesseg());
                            jatekos.lepes(item);
                            palyatKiir();
                            break;
                        }
                        case 3:
                        {
                            System.out.println("Ez az egység várakozik");
                            break;
                        }
                    }
                }
                else if (item.getOsszeletero() > 0)
                {
                    System.out.println("Ellenség egysége következik: "+item.getNev());
                    System.out.println();
                    if (ellenseg.getTamadhat() && ellenseg.getMana() > 9)
                    {
                        while(true)
                        {
                            int varazslatSorszam = rnd.nextInt(1,4);
                            if (varazslatSorszam == 1 && ellenseg.getVarazslatok().contains(varazslat2))
                            {
                                varazslat2.tuzlabda(ellenseg, jatekos.getMap(), ellenseg.getMap());
                                ellenseg.setTamadhat(false);
                                break;
                            }
                            if (varazslatSorszam == 2 && ellenseg.getVarazslatok().contains(varazslat3))
                            {
                                varazslat3.gyogyitas(ellenseg,list);
                                ellenseg.setTamadhat(false);
                                break;
                            }
                            if (varazslatSorszam == 3 && ellenseg.getVarazslatok().contains(varazslat4))
                            {
                                varazslat4.tuzSzonyeg(ellenseg,jatekos.getMap(), ellenseg.getMap());
                                ellenseg.setTamadhat(false);
                                break;
                            }
                        }
                        if (jatekVege(list))
                        {
                            vege = true;
                            break;
                        }
                    }
                    if (!item.isAlszik())
                    {
                        if (item instanceof Ijasz)
                        {
                            if (item.tamadhat(ellensegesIjasz,jatekos.getMap()))
                            {
                                item.ellensegesIjaszTamad(ellenseg,jatekos,list);

                                if (jatekVege(list))
                                {
                                    vege = true;
                                    break;
                                }
                            }
                            else
                            {
                                item.ellensegesIjaszLepes(item);
                                System.out.println("Az ellenség íjásza lépett.");
                                palyatKiir();
                            }
                        }
                        else if(item.tamadhat(item,jatekos.getMap()))
                        {
                            item.ellensegTamadas(jatekos,ellenseg,item,jatekos.getMap());
                        }
                        else
                        {
                            System.out.println("Az "+item.getNev()+" egység lépett");
                            item.ellensegesEgysegLepes(jatekos, ellenseg);
                            palyatKiir();
                        }

                        if (jatekVege(list))
                        {
                            vege = true;
                            break;
                        }
                    }
                    else
                    {
                        item.setAlszik(false);
                        System.out.println("Ez az egység alszik: "+ item.getNev());
                    }
                }
            }
            korokSzama++;
        }
    }
}
