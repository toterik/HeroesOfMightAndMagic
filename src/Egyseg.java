import java.util.*;

/**
 * Az egysegeket megvalosito osztaly. A specifikus egysegek ebbol az osztalybol oroklodnek. Ezek az osztalyok megkapjak az Egyseg osztaly adattagjait, metodusait
 */
public class Egyseg {
    protected int ar;
    protected String nev;
    protected int sebzes;
    protected int eletero;
    protected int sebesseg;
    protected int kezdemenyezes;
    static int tipusdb;
    protected int X;
    protected int Y;
    protected int osszeletero;
    protected int dbSzam;
    protected boolean jatekos;
    protected int maxEletero = -1;
    protected boolean visszatamadhat;
    protected boolean alszik;

    /**
     * Az egyseg konstruktora. Ezeket meg az objektumok legeneralasa elott ismerjuk, a tobbit nem.
     * A tipusdarab azert fontos, hogy ne tudjon elindulni a jatek anelkul, hogy lenne legalabb egy egysege valamelyik felnek.
     * @param ar Egy egyseg ara
     * @param eletero Egy egyseg elete
     * @param sebesseg Az  egyseg sebessege (mennyi mezot lephet)
     * @param kezdemenyezes Az egyseg kezdemenyezese (A nagyobb kezdemenyezesu egysegek hamarabb tamadhatnak)
     */
    public Egyseg(int ar, int eletero, int sebesseg, int kezdemenyezes) {
        this.ar = ar;
        this.eletero = eletero;
        this.sebesseg = sebesseg;
        this.kezdemenyezes = kezdemenyezes;
        this.alszik = false;
        tipusdb = 0;
    }

    /**
     * @param egyseg Az az egység, amelyikről tudni szeretnénk, hogy életben van-e
     * @return Igaz, ha életben van az egység. Hamis, ha nincs.
     */

    public boolean elMeg(Egyseg egyseg) {
        if (egyseg.getOsszeletero() > 0) {
            return true;
        } else return false;
    }

    /**
     * Kulon meg kell vizsgalni a palya szeleit, sarkait, hiszen ha csak az egyseg korul levo 3x3-as negyzetet szeretnenk vizsgalni, konnyen tulindexelhetjuk a tombot
     *
     * @param egyseg Melyik egysegrol szeretnenk tudni, hogy tamadhat-e, azaz vannak-e a kozeleben
     * @param map Az ellenseges fel Hashmapja. Ezzel tudom megallapitani, hogy az egyseg kozeleben levo egyseg, az "barat" egyseg, vagy az ellenfel egysege
     * @return ijasz eseten igaz, ha nincsenek a kozeleben. Hamis, ha vannak. Kozelharci egysegek eseten igaz, ha vannak a kozeleben. Hamis, ha nincsenek.
     */
    public boolean tamadhat(Egyseg egyseg, HashMap<Character, Egyseg> map) {
        List<Egyseg> egysegek = new ArrayList<>();
        //ellenség hashmap
        //bal felső sarokban:
        if (this.getX() == 1 && this.getY() == 1) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        egysegek.add(map.get(Main.tabla[i][j]));
                    }
                }
            }
        }
        //jobb felső sarok
        else if (this.getX() == 1 && this.getY() == 12) {
            for (int i = 0; i < 2; i++) {
                for (int j = 10; j < 12; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        egysegek.add(map.get(Main.tabla[i][j]));
                    }
                }
            }
        }
        //jobb alsó sarok
        else if (this.getX() == 10 && this.getY() == 12) {
            for (int i = 8; i < 10; i++) {
                for (int j = 10; j < 12; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        egysegek.add(map.get(Main.tabla[i][j]));
                    }
                }
            }
        }
        //bal alsó sarok
        else if (this.getX() == 10 && this.getY() == 1) {
            for (int i = 8; i < 10; i++) {
                for (int j = 0; j < 2; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        egysegek.add(map.get(Main.tabla[i][j]));
                    }
                }
            }
        }
        //felső sor
        else if (this.getX() == 1) {
            for (int i = 0; i < 2; i++) {
                for (int j = this.getY() - 2; j < this.getY() + 1; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        egysegek.add(map.get(Main.tabla[i][j]));
                    }
                }
            }
        }
        //jobb oldali oszlop
        else if (this.getY() == 12) {
            for (int i = this.getX() - 2; i < this.getX() + 1; i++) {
                for (int j = 10; j < 12; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        egysegek.add(map.get(Main.tabla[i][j]));
                    }
                }
            }
        }
        //alsó sor
        else if (this.getX() == 10) {
            for (int i = 8; i < 10; i++) {
                for (int j = this.getY() - 2; j < this.getY() + 1; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        egysegek.add(map.get(Main.tabla[i][j]));
                    }
                }
            }
        }
        //bal oszlop
        else if (this.getY() == 1) {
            for (int i = this.getX() - 2; i < this.getX() + 1; i++) {
                for (int j = 0; j < 2; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        egysegek.add(map.get(Main.tabla[i][j]));
                    }
                }
            }
        } else {
            for (int i = this.getX() - 2; i < this.getX() + 1; i++) {
                for (int j = this.getY() - 2; j < this.getY() + 1; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        egysegek.add(map.get(Main.tabla[i][j]));
                    }
                }
            }
        }

        if (egyseg instanceof Ijasz) {
            return egysegek.isEmpty();
        }

        if (!egysegek.isEmpty()) {
            if (egyseg.isJatekos()) {
                System.out.println("Ezek az ellenséges egyégek támadhatóak: ");
                for (Egyseg item : egysegek) {
                    System.out.println(item.getNev());
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Jatekos eseteben bekeri, hogy melyik ellenseges egyseget szeretne megtamadni. Majd egy adott keplet alapjan kiszamolja a sebzes merteket. Majd a vegen, ha a megtamadott egyseg eletben van <br>
     * es a tamado egyseg nem ijasz volt, akkor visszatamad a celpont, fele annyi sebzessel. <br>
     * Pajzsos egyseg eseteben 30% eselye van a Pajzsos egysegnek, hogy haritson, azaz ne kapjon sebzest
     * Orgyilkos tamado egyseg eseten az egyseg 25%-al tobbet sebez, ha a celpont egyseg mogott van (jobbra font, jobbra, jobbra lent) <br>
     * * A kritikus sebzes ketszer annyit sebez. Az eselye a szerencsevel no
     *
     *
     * @param tamadoHos Melyik hos egysege tamad
     * @param tamadoEgyseg A tamado hos melyik egysege tamad
     * @param celpontHos Melyik egyseget tamadjak
     * @param list Lista, amiben az osszes egyseg megtalalhato, jatekos es ellenseg egyben.
     */

    public void tamadas(Hos tamadoHos, Egyseg tamadoEgyseg, Hos celpontHos, List<Egyseg> list) {
        Egyseg celpontEgyseg = null;

        boolean kilepes = true;
        String bemenet = "";
        do {
            try {
                Scanner sc = new Scanner(System.in);
                bemenet = sc.nextLine();

                for (Egyseg item : list) {
                    if (bemenet.equals(item.getNev()) && !item.isJatekos() && item.osszeletero != 0) {
                        celpontEgyseg = item;
                        kilepes = false;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Hiba");
            }
        } while (kilepes);

        double vegsoTamadas = this.getSebzes();
        vegsoTamadas *= tamadoHos.getTamadas();

        if (tamadoEgyseg instanceof Orgyilkos && ((tamadoEgyseg.getX() == celpontEgyseg.getX() + 1 && tamadoEgyseg.getY() == celpontEgyseg.getY() + 1) ||
                tamadoEgyseg.getX() == celpontEgyseg.getX() && tamadoEgyseg.getY() == celpontEgyseg.getY() + 1) ||
                tamadoEgyseg.getX() == celpontEgyseg.getX() - 1 && tamadoEgyseg.getY() == celpontEgyseg.getY() + 1) {
            System.out.println("Bónusz sebzés");
            vegsoTamadas *= 1.25;
        }


        vegsoTamadas = Math.round(vegsoTamadas - (vegsoTamadas * (celpontHos.getVedekezes() - 1)));


        Random rnd = new Random();
        int krit = (int) Math.round((tamadoHos.getSzerencse() * 100) % 100);
        if (krit >= rnd.nextInt(1, 101)) {
            System.out.println("Kritikus támadás!");
            vegsoTamadas *= 2;
        }


        if (celpontEgyseg instanceof Pajzsos) {
            if (!(((Pajzsos) celpontEgyseg).haritott())) {
                celpontEgyseg.setOsszeletero(celpontEgyseg.getOsszeletero() - (int) vegsoTamadas);
                if (celpontEgyseg.getOsszeletero() == 0) {
                    celpontEgyseg.setDbSzam(0);
                    Main.tabla[celpontEgyseg.getX() - 1][celpontEgyseg.getY() - 1] = '-';
                } else celpontEgyseg.setDbSzam(celpontEgyseg.getOsszeletero() / celpontEgyseg.getEletero() + 1);

                System.out.println("Az ellenség " + celpontEgyseg.getNev() + " egysége lett megtámadva." + " Élete: " + celpontEgyseg.getOsszeletero());
            }
        } else {
            celpontEgyseg.setOsszeletero(celpontEgyseg.getOsszeletero() - (int) vegsoTamadas);
            if (celpontEgyseg.getOsszeletero() == 0) {
                celpontEgyseg.setDbSzam(0);
                Main.tabla[celpontEgyseg.getX() - 1][celpontEgyseg.getY() - 1] = '-';
            } else celpontEgyseg.setDbSzam(celpontEgyseg.getOsszeletero() / celpontEgyseg.getEletero() + 1);

            System.out.println("Az ellenség " + celpontEgyseg.getNev() + " egysége lett megtámadva." + " Élete: " + celpontEgyseg.getOsszeletero());
        }


        if (elMeg(celpontEgyseg) && celpontEgyseg.visszatamadhat && !(tamadoEgyseg instanceof Ijasz) && !(celpontEgyseg instanceof Ijasz))
        {
            visszatamad(celpontHos, tamadoHos, tamadoEgyseg);
            if (!(tamadoEgyseg instanceof Griff)) celpontEgyseg.setVisszatamadhat(false);
            System.out.println("Az ellenség " + celpontEgyseg.getNev() + " egysége visszatámadott");
            System.out.println("A játékos " + tamadoEgyseg.getNev() + " egységének élete: " + tamadoEgyseg.getOsszeletero());
        }
    }

    /**
     * Ugyanaz a keplet alapjan szamoljuk a visszatamadast, azonban a sebzest a vegen kettovel osztjuk
     *
     * @param tamadoHos Az a hos, akinek az egysege visszatamad
     * @param celpontHos Az a hos, amelynek egyseget tamadjak
     * @param celpontEgyseg Az az egyseg, amelyiket visszatamadjak
     */

    public void visszatamad(Hos tamadoHos, Hos celpontHos, Egyseg celpontEgyseg) {

        double vegsoTamadas = this.getSebzes();
        vegsoTamadas *= tamadoHos.getTamadas();

        vegsoTamadas = Math.round(vegsoTamadas - (vegsoTamadas * (celpontHos.getVedekezes() - 1)));


        vegsoTamadas /= 2;
        celpontEgyseg.setOsszeletero(celpontEgyseg.getOsszeletero() - (int) vegsoTamadas);

        if (celpontEgyseg.getOsszeletero() == 0) {
            celpontEgyseg.setDbSzam(0);
            Main.tabla[celpontEgyseg.getX() - 1][celpontEgyseg.getY() - 1] = '-';
        } else celpontEgyseg.setDbSzam(celpontEgyseg.getOsszeletero() / celpontEgyseg.getEletero() + 1);
    }

    /**
     * Az ellenseges ijasz mindig a legkevesebb eleterovel rendelkezo egyseget tamadja meg a hosnek. Tamadasat ugyanugy szamoljuk, ahogy a jatekosnak, az ellenseg is ugynaugy tud kritikus sebzest merni.
     *
     * @param tamadoHos Az ellenseg ijaszhoz tartozo hos
     * @param celpontHos A jatekos hose
     * @param lista Az osszes egyseget tartalmazo hos listaja
     */
    public void ellensegesIjaszTamad(Hos tamadoHos, Hos celpontHos, ArrayList<Egyseg> lista) {
        int i = 0;
        Egyseg celpontEgyseg = null;

        for (Egyseg item : lista) {
            if (item.isJatekos() && item.getOsszeletero() != 0) {
                i++;
            }
        }
        Egyseg[] tomb = new Egyseg[i];
        i = 0;
        for (Egyseg item : lista) {
            if (item.isJatekos() && item.getOsszeletero() != 0) {
                tomb[i] = item;
                i++;
            }
        }

        for (i = tomb.length - 1; i > 0; i--) {
            for (int j = 0; j < tomb.length - 1; j++) {
                if (tomb[j].getOsszeletero() < tomb[j + 1].getOsszeletero()) {
                    Egyseg tmp = tomb[j];
                    tomb[j] = tomb[j + 1];
                    tomb[j + 1] = tmp;
                }
            }
        }
        celpontEgyseg = tomb[tomb.length - 1];

        double vegsoTamadas = this.getSebzes();
        vegsoTamadas *= tamadoHos.getTamadas();
        vegsoTamadas = Math.round(vegsoTamadas - (vegsoTamadas * (celpontHos.getVedekezes() - 1)));


        Random rnd = new Random();
        int krit = (int) Math.round((tamadoHos.getSzerencse() * 100) % 100);
        if (krit >= rnd.nextInt(1, 101)) {
            System.out.println("Kritikus támadás!");
            vegsoTamadas *= 2;
        }


        if (celpontEgyseg instanceof Pajzsos) {
            if (!(((Pajzsos) celpontEgyseg).haritott())) {
                celpontEgyseg.setOsszeletero(celpontEgyseg.getOsszeletero() - (int) vegsoTamadas);
                if (celpontEgyseg.getOsszeletero() == 0) {
                    celpontEgyseg.setDbSzam(0);
                    Main.tabla[celpontEgyseg.getX() - 1][celpontEgyseg.getY() - 1] = '-';
                } else celpontEgyseg.setDbSzam(celpontEgyseg.getOsszeletero() / celpontEgyseg.getEletero() + 1);

                System.out.println("Az játékos " + celpontEgyseg.getNev() + " egysége lett megtámadva." + " Élete: " + celpontEgyseg.getOsszeletero());
            } else {
                System.out.println("A játékos pajzsos egysége hárított");
            }
        } else {
            celpontEgyseg.setOsszeletero(celpontEgyseg.getOsszeletero() - (int) vegsoTamadas);
            if (celpontEgyseg.getOsszeletero() == 0) {
                celpontEgyseg.setDbSzam(0);
                Main.tabla[celpontEgyseg.getX() - 1][celpontEgyseg.getY() - 1] = '-';
            } else celpontEgyseg.setDbSzam(celpontEgyseg.getOsszeletero() / celpontEgyseg.getEletero() + 1);

            System.out.println("Az játékos " + celpontEgyseg.getNev() + " egysége lett megtámadva." + " Élete: " + celpontEgyseg.getOsszeletero());
        }
    }

    /**
     * Ha az ijasz nem tud tamadni, akkor elmozog a negy irany egyikebe, amilyen messzire tud. Eloszor fuggolegesen probal elmozdulni, ha nem tud, akkor jobbra vagy balra
     *
     * @param egyseg Az ellenseges ijasz
     */
    public void ellensegesIjaszLepes(Egyseg egyseg) {
        if (this.getX() + sebesseg <= 10 && Main.tabla[this.getX() - 1 + sebesseg][this.getY() - 1] == '-') //le
        {
            Main.tabla[this.getX() - 1][this.getY() - 1] = '-';
            Main.tabla[this.getX() - 1 + sebesseg][this.getY() - 1] = egyseg.getNev().charAt(0);
            egyseg.setX(this.getX() + sebesseg);
        } else if (this.getX() - sebesseg >= 0 && Main.tabla[this.getX() - 1 - sebesseg][this.getY() - 1] == '-') //fel
        {
            Main.tabla[this.getX() - 1][this.getY() - 1] = '-';
            Main.tabla[this.getX() - 1 - sebesseg][this.getY() - 1] = egyseg.getNev().charAt(0);
            egyseg.setX(this.getX() - sebesseg);
        } else if (this.getY() - egyseg.sebesseg >= 0 && Main.tabla[this.getX() - 1][this.getY() - 1 - egyseg.sebesseg] == '-') //balra
        {
            Main.tabla[this.getX() - 1][this.getY() - 1] = '-';
            Main.tabla[this.getX() - 1][this.getY() - 1 - egyseg.sebesseg] = egyseg.getNev().charAt(0);
            egyseg.setX(this.getX());
        } else if (this.getY() + sebesseg <= 12 && Main.tabla[this.getX() - 1][this.getY() - 1 + sebesseg] == '-') //jobbra
        {
            Main.tabla[this.getX() - 1][this.getY() - 1] = '-';
            Main.tabla[this.getX() - 1][this.getY() - 1 + sebesseg] = egyseg.getNev().charAt(0);
            egyseg.setY(this.getY() + sebesseg);
        }
    }

    /**
     * Mielott hasznaljuk ezt a metodust, mar tudjuk, hogy van-e jatekos az ellenseg egysege korul, hiszen hasznaltuk a tamadhat() metodust <br>
     * Megkeresi a kozelben levo jatekos egyseget, es azt ugyanolyan modon tamadja meg, mint ahogy a jatekos egysege tamadja meg az ellenseg egyseget, ugyanazzal a keplettel szamolva
     *
     * @param celpontHos A megtamadott egyseghez tartozo hos
     * @param tamadoHos A tamado hose
     * @param tamadoEgyseg Az egyseg, aki tamad
     * @param map Az ellenseghez tartozo HashMap
     */
    public void ellensegTamadas(Hos celpontHos, Hos tamadoHos, Egyseg tamadoEgyseg, HashMap<Character, Egyseg> map) {
        //ellenséges hashmap
        Egyseg celpontEgyseg = null;

        if (this.getX() == 1 && this.getY() == 1) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        celpontEgyseg = map.get(Main.tabla[i][j]);
                        break;
                    }
                }
            }
        }
        //jobb felső sarok
        else if (this.getX() == 1 && this.getY() == 12) {
            for (int i = 0; i < 2; i++) {
                for (int j = 10; j < 12; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        celpontEgyseg = map.get(Main.tabla[i][j]);
                        break;
                    }
                }
            }
        }
        //jobb alsó sarok
        else if (this.getX() == 10 && this.getY() == 12) {
            for (int i = 8; i < 10; i++) {
                for (int j = 10; j < 12; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        celpontEgyseg = map.get(Main.tabla[i][j]);
                        break;
                    }
                }
            }
        }
        //bal alsó sarok
        else if (this.getX() == 10 && this.getY() == 1) {
            for (int i = 8; i < 10; i++) {
                for (int j = 0; j < 2; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        celpontEgyseg = map.get(Main.tabla[i][j]);
                        break;
                    }
                }
            }
        }
        //felső sor
        else if (this.getX() == 1) {
            for (int i = 0; i < 2; i++) {
                for (int j = this.getY() - 2; j < this.getY() + 1; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        celpontEgyseg = map.get(Main.tabla[i][j]);
                        break;
                    }
                }
            }
        }
        //jobb oldali oszlop
        else if (this.getY() == 12) {
            for (int i = this.getX() - 2; i < this.getX() + 1; i++) {
                for (int j = 10; j < 12; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        celpontEgyseg = map.get(Main.tabla[i][j]);
                        break;
                    }
                }
            }
        }
        //alsó sor
        else if (this.getX() == 10) {
            for (int i = 8; i < 10; i++) {
                for (int j = this.getY() - 2; j < this.getY() + 1; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        celpontEgyseg = map.get(Main.tabla[i][j]);
                        break;
                    }
                }
            }
        }
        //bal oszlop
        else if (this.getY() == 1) {
            for (int i = this.getX() - 2; i < this.getX() + 1; i++) {
                for (int j = 0; j < 2; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        celpontEgyseg = map.get(Main.tabla[i][j]);
                        break;
                    }
                }
            }
        } else {
            for (int i = this.getX() - 2; i < this.getX() + 1; i++) {
                for (int j = this.getY() - 2; j < this.getY() + 1; j++) {
                    if (Main.tabla[i][j] != '-' && map.containsKey(Main.tabla[i][j])) {
                        celpontEgyseg = map.get(Main.tabla[i][j]);
                        break;
                    }
                }
            }
        }

        double vegsoTamadas = this.getSebzes();
        vegsoTamadas *= tamadoHos.getTamadas();
        vegsoTamadas = Math.round(vegsoTamadas - (vegsoTamadas * (celpontHos.getVedekezes() - 1)));

        Random rnd = new Random();
        int krit = (int) Math.round((tamadoHos.getSzerencse() * 100) % 100);
        if (krit >= rnd.nextInt(1, 101)) {
            System.out.println("Kritikus támadás!");
            vegsoTamadas *= 2;
        }


        if (celpontEgyseg instanceof Pajzsos) {
            if (!(((Pajzsos) celpontEgyseg).haritott())) {
                celpontEgyseg.setOsszeletero(celpontEgyseg.getOsszeletero() - (int) vegsoTamadas);
                if (celpontEgyseg.getOsszeletero() == 0) {
                    celpontEgyseg.setDbSzam(0);
                    Main.tabla[celpontEgyseg.getX() - 1][celpontEgyseg.getY() - 1] = '-';
                } else celpontEgyseg.setDbSzam(celpontEgyseg.getOsszeletero() / celpontEgyseg.getEletero() + 1);

                System.out.println("A játékos " + celpontEgyseg.getNev() + " egysége lett megtámadva." + " Élete: " + celpontEgyseg.getOsszeletero());
            }
        } else {
            celpontEgyseg.setOsszeletero(celpontEgyseg.getOsszeletero() - (int) vegsoTamadas);
            if (celpontEgyseg.getOsszeletero() == 0) {
                celpontEgyseg.setDbSzam(0);
                Main.tabla[celpontEgyseg.getX() - 1][celpontEgyseg.getY() - 1] = '-';
            } else celpontEgyseg.setDbSzam(celpontEgyseg.getOsszeletero() / celpontEgyseg.getEletero() + 1);

            System.out.println("A játékos " + celpontEgyseg.getNev() + " egysége lett megtámadva." + " Élete: " + celpontEgyseg.getOsszeletero());
        }


        if (elMeg(celpontEgyseg) && celpontEgyseg.visszatamadhat && !(tamadoEgyseg instanceof Ijasz) && !(celpontEgyseg instanceof Ijasz)) {
            visszatamad(celpontHos, tamadoHos, tamadoEgyseg);
            if (!(tamadoEgyseg instanceof Griff)) celpontEgyseg.setVisszatamadhat(false);
            System.out.println("A játékos " + celpontEgyseg.getNev() + " egysége visszatámadott");
            System.out.println("Az ellenség " + tamadoEgyseg.getNev() + " egységének élete: " + tamadoEgyseg.getOsszeletero());
        }
    }

    /**
     *
     * @param jatekos a jatekos hose
     */
    public void ellensegesEgysegLepes(Hos jatekos, Hos ellenseg)
    {

        for (int i = 0; i < Main.SOR ; i++)
        {
            for (int j = 0; j < Main.OSZLOP; j++)
            {
                if (Main.tabla[i][j] != '-' && jatekos.getMap().containsKey(Main.tabla[i][j]))
                {
                    if (jatekos.getMap().get(Main.tabla[i][j]).getX() == this.getX() && jatekos.getMap().get(Main.tabla[i][j]).getY() < this.getY() && this.getY() - jatekos.getMap().get(Main.tabla[i][j]).getY() < sebesseg && Main.tabla[this.getX()-1][jatekos.getMap().get(Main.tabla[i][j]).getY()] == '-') //balra, lépésen belül van
                    {
                        Main.tabla[this.getX()-1][this.getY()-1] = '-';
                        Main.tabla[this.getX()-1][jatekos.getMap().get(Main.tabla[i][j]).getY()]=this.getNev().charAt(0);
                        this.setY(jatekos.getMap().get(Main.tabla[i][j]).getY() + 1);
                        return;
                    }
                    else if (jatekos.getMap().get(Main.tabla[i][j]).getX() == this.getX() && jatekos.getMap().get(Main.tabla[i][j]).getY() < this.getY() && this.getY() - jatekos.getMap().get(Main.tabla[i][j]).getY()  > sebesseg && Main.tabla[this.getX()-1][this.getY()-1 -sebesseg] =='-') // balra, lépésen kívül van
                    {
                        Main.tabla[this.getX()-1][this.getY()-1] = '-';
                        Main.tabla[this.getX()-1][this.getY()-1 -sebesseg] = this.getNev().charAt(0);
                        this.setY(this.getY() - sebesseg);
                        return;
                    }

                    else if (jatekos.getMap().get(Main.tabla[i][j]).getX() == this.getX() && jatekos.getMap().get(Main.tabla[i][j]).getY() > this.getY() && jatekos.getMap().get(Main.tabla[i][j]).getY() - this.getY() < sebesseg && Main.tabla[this.getX()-1][jatekos.getMap().get(Main.tabla[i][j]).getY() - 2] == '-') //jobbra, lépésen belül van
                    {
                        Main.tabla[this.getX()-1][this.getY()-1] = '-';
                        Main.tabla[this.getX()-1][jatekos.getMap().get(Main.tabla[i][j]).getY() - 2]=this.getNev().charAt(0);
                        this.setY(jatekos.getMap().get(Main.tabla[i][j]).getY() - 1);
                        return;
                    }

                    else if (jatekos.getMap().get(Main.tabla[i][j]).getX() == this.getX() && jatekos.getMap().get(Main.tabla[i][j]).getY() > this.getY() &&  jatekos.getMap().get(Main.tabla[i][j]).getY() - this.getY()  > sebesseg && Main.tabla[this.getX()-1][this.getY()-1 + sebesseg] == '-') // jobbra, lépésen kívül van
                    {
                        Main.tabla[this.getX()-1][this.getY()-1] = '-';
                        Main.tabla[this.getX()-1][this.getY()-1 + sebesseg] = this.getNev().charAt(0);
                        this.setY(this.getY() + sebesseg);
                        return;
                    }

                     if (jatekos.getMap().get(Main.tabla[i][j]).getX() > this.getX() && Main.tabla[this.getX()][this.getY() - 1] == '-') // lentebb van a játékos, akkor lentebb megy
                    {
                        Main.tabla[this.getX() - 1][this.getY() - 1] = '-';
                        Main.tabla[this.getX()][this.getY() - 1] = this.getNev().charAt(0);
                        this.setX(this.getX() + 1);
                        return;
                    }

                     if (jatekos.getMap().get(Main.tabla[i][j]).getX() < this.getX() && Main.tabla[this.getX() - 2][this.getY() - 1] == '-') // fentebb van játékos egysége, akkor fentebb megy
                    {
                        Main.tabla[this.getX() - 1][this.getY() - 1] = '-';
                        Main.tabla[this.getX() - 2][this.getY() - 1] = this.getNev().charAt(0);
                        this.setX(this.getX() - 1 );
                        return;
                    }

                }
            }
        }
    }

    public void setJatekos(boolean jatekos) {
        this.jatekos = jatekos;
    }

    public boolean isJatekos() {
        return jatekos;
    }

    public int getOsszeletero() {
        return osszeletero;
    }


    public void setOsszeletero(int osszeletero)
    {
        if(osszeletero < 0)
        {
            this.osszeletero = 0;
        }
        else if(osszeletero > maxEletero)
        {
            this.osszeletero = maxEletero;
        }
        else this.osszeletero = osszeletero;
    }

    public void setMaxEletero()
    {
        maxEletero = dbSzam*eletero;
    }

    public int getDbSzam() {
        return dbSzam;
    }

    public void setDbSzam(int dbSzam)
    {
        this.dbSzam = dbSzam;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        this.X = x;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public int getY() {
        return Y;
    }

    public String getNev() {return  nev;}

    public int getAr() {
        return ar;
    }

    public int getSebzes() {
        return sebzes * dbSzam;
    }

    public int getEletero() {
        return eletero;
    }

    public int getSebesseg() {
        return sebesseg;
    }

    public int getKezdemenyezes() {
        return kezdemenyezes;
    }

    public void setKezdemenyezes(int kezdemenyezes, Hos hos) {
        this.kezdemenyezes = kezdemenyezes + hos.getMoral();
    }


    public int getMaxEletero() {
        return maxEletero;
    }

    public boolean getVisszatamadhat(){return visszatamadhat;}

    public void setVisszatamadhat(boolean visszatamadhat) {this.visszatamadhat = visszatamadhat;}

    public void setAlszik(boolean alszik) {
        this.alszik = alszik;
    }

    public boolean isAlszik() {
        return alszik;
    }
}
