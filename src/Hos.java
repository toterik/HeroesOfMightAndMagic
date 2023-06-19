import java.util.*;

/**
 * A Hos osztaly segitsegevel valositom meg a jatekost, illetve ellenseget is <br>
 * A jatekosnak, es az ellensegnek is ugyanolyan adattagjai vannak, amik a tulajdonsagaik, egy Varazslatokbol allo lista, egy Egysegekbol allo lista, es egy Hashmap, amivel <br>
 * a Main classban letrehozott palyarol tudom lekerdezni, hogy az adott indexen levo karakterhez melyik Egyseg tartozik, hiszen a kulcs egy karakter, az erteke pedig egy egyseg
 */
public class Hos {
    private double tamadas;
    private double vedekezes;
    private int varazsero;
    private int tudas;
    private int moral;
    private double szerencse;
    private ArrayList <Varazslat> varazslatok;
    private ArrayList <Egyseg> egysegek;
    private HashMap<Character, Egyseg> map;
    private boolean jatekos;
    private int mana;
    private boolean tamadhat;


    private int arany;

    public Hos(boolean jatekos) {
        this.tamadas = 1.1;
        this.vedekezes = 1.05;
        this.varazsero = 1;
        this.tudas = 10;
        this.moral = 1;
        this.szerencse = 1.05;
        this.varazslatok = new ArrayList<>();
        this.egysegek = new ArrayList<>();
        this.jatekos=jatekos;
        this.map = new HashMap<>();
        this.mana=10;
        this.tamadhat = true;
    }

    /**
     * Ebben a metodusban adunk a hosnek egysegeket. Ha meg nincs benne a kapott egyseg, es van penze akkor hozzaadja. Ha pedig mar benne volt, akkor visszaszamolja a penzt <br>
     * es ujraszamolja, hogy mennyi darabot akart venni a jatekos, vagy a hos
     *
     * @param egyseg A kivant egyseg amelyik szeretnenk hozzaadni a Hoshoz
     * @param darab Mennyi egyseget szeretnenk hozzaadni
     * @param jatekos a jatekos azt mondja meg, hogy a kapott hos az jatekos, ha igaz erteket kap, es ellenseg, ha hamisat
     */

    public void egysegetHozzaad(Egyseg egyseg, int darab, boolean jatekos)
    {
        if(this.arany < egyseg.getAr() && egyseg.getDbSzam() < darab)
        {
            System.out.println("Sajnos nincs elég pénzed erre az egységre");
        }
        else
        {
            int i;

            if(!this.egysegek.contains(egyseg))
            {
                egyseg.setJatekos(jatekos);
                map.put(egyseg.getNev().charAt(0),egyseg);

                for ( i = 0; i < darab ; i++)
                {
                    this.arany -= egyseg.getAr();
                    if(this.arany - egyseg.getAr() < 0)
                    {
                        break;
                    }
                }

                if(this.arany - egyseg.getAr() < 0)
                {
                    egyseg.setDbSzam(i+1);
                    egyseg.setMaxEletero();
                    if(this.getJatekos()) egyseg.setOsszeletero(egyseg.getEletero() * egyseg.getDbSzam());
                }
                else
                {
                    egyseg.setDbSzam(i);
                    egyseg.setMaxEletero();
                    if(this.getJatekos()) egyseg.setOsszeletero(egyseg.getEletero() * egyseg.getDbSzam());
                }

                egysegek.add(egyseg);

                if (this.getJatekos())
                {
                    if(i + 1 < darab) System.out.println("Csak ennyi egységet bírtál venni: " + (egyseg.getDbSzam()));
                    else  System.out.println(egyseg.getDbSzam()+" darab "+egyseg.getNev()+" egységet vettél");
                    Egyseg.tipusdb++;
                }
            }
            else if (egysegek.contains(egyseg) && egyseg.getDbSzam() != darab)
            {

                for (i = 0; i < egyseg.getDbSzam() ; i++)
                {
                    this.arany += egyseg.getAr();
                }

                for ( i = 0; i < darab ; i++)
                {
                    this.arany -= egyseg.getAr();
                    if(this.arany - egyseg.getAr() < 0)
                    {
                        break;
                    }
                }

                if(this.arany - egyseg.getAr() < 0)
                {
                    egyseg.setDbSzam(i+1);
                    egyseg.setMaxEletero();
                    if(this.getJatekos()) egyseg.setOsszeletero(egyseg.getEletero() * egyseg.getDbSzam());
                }
                else
                {
                    egyseg.setDbSzam(i);
                    egyseg.setMaxEletero();
                    if(this.getJatekos()) egyseg.setOsszeletero(egyseg.getEletero() * egyseg.getDbSzam());

                }

                egyseg.setJatekos(jatekos);

                egysegek.remove(egyseg);
                egysegek.add(egyseg);
                map.put(egyseg.getNev().charAt(0), egyseg);

                if (this.getJatekos())
                {
                    if(i + 1 < darab) System.out.println("Csak ennyi egységet bírtál venni: " + (egyseg.getDbSzam()));
                    else  System.out.println(egyseg.getDbSzam()+" darab "+egyseg.getNev()+" egységet vettél");
                    Egyseg.tipusdb++;
                }

            }

        }
    }
    /**
     * A parameterben kapott varazslatot adja hozza a hos varazslatok listajahoz. Ha mar benne van, akkor nem adja hozza megegyszer,
     * hanem tajekoztatja a jatekost arrol, hogy mar megvan veve a varazslat.
     *
     * @param varazslat A varazslat, amelyet szeretnenk hozzaadni a Hos varazslatok listajahoz
     */

    public void varazslatotHozzaad(Varazslat varazslat)
    {
        if (this.arany > varazslat.getAr())
        {
            if (this.arany - varazslat.getAr() > 2)
            {
                if(!varazslatok.contains(varazslat))
                {
                    varazslatok.add(varazslat);
                    if (this.getJatekos()) System.out.println("Megvetted a "+varazslat.getNev()+" varázslatot");
                    this.arany-=varazslat.getAr();
                }
                else if (this.jatekos)
                {
                    System.out.println("Ezt a varázslatot már megvetted!");
                }
            }
            else
            {
                System.out.println("Igy nem fog maradni pénzed legalább egy egységre!");
            }
        }
        else
        {
            System.out.println("Sajnos nincs elég pénzed, hogy megvedd ezt a varázslatot: " + varazslat.getNev());
        }
    }
    /**
     * A csata elokeszitesi szakaszaban levo kezdolepeseket valositja meg. A jatekos nem tud az elso ket oszlopon kivulre tenni egysegeket. Addig ker be szamokat, mig az az adott teruleten belul nem lesz <br>
     * Az ellenseg egysegeit pedig random helyezi el a jobb oldali ket utolso oszlopban.<br>
     * Az egysegek nem tudnak egymasra kerulni, illetve csak akkor van vege a ciklusnak, ha a jatekos illetve az ellenseg is az osszes egyseget letette
     */
    public void kezdolepesek()
    {
        if(this.getJatekos())
        {
            for(int i = 0; i < this.egysegek.size(); i++)
            {
                int sor = 0;
                int oszlop = 0;
                int index=0;
                boolean kilepes=true;

                if(this.getJatekos()) System.out.println("Add meg hová szeretnéd tenni a(z) "+this.egysegek.get(i).getNev()+" egységedet (sor oszlop)");
                if(index == this.egysegek.size()) break;
                do
                {
                    try
                    {
                        String [] tomb;
                        Scanner sc = new Scanner(System.in);
                        String idk = sc.nextLine();
                        tomb = idk.split(" ");
                        sor= Integer.parseInt(tomb[0]);
                        oszlop = Integer.parseInt(tomb[1]);


                        if((sor <= 0 || sor > 10 || oszlop <= 0 || oszlop > 2)) System.out.println("Csak az első két oszlopban helyezhetsz el egységeket!");
                        else if(Main.tabla[sor-1][oszlop-1] != '-') System.out.println("Ezen a pozíción már van egységed!");
                        else
                        {
                            this.egysegek.get(i).setX(sor);
                            this.egysegek.get(i).setY(oszlop);

                            Main.tabla[sor-1][oszlop-1] = this.egysegek.get(i).getNev().charAt(0);
                            index++;

                            Main.palyatKiir();
                            kilepes = false;
                        }
                    }
                    catch (Exception exception)
                    {
                        System.out.println("Hiba!");
                    }

                }while (kilepes);
            }
        }
        else
        {
            for(int i = 0; i < 3; i++)
            {
                int sor = 0;
                int oszlop = 0;
                Random rnd = new Random();
                do
                {
                    sor = rnd.nextInt(1,10);
                    oszlop = rnd.nextInt(11,13);
                    if(Main.tabla[sor-1][oszlop-1] == '-')
                    {
                        this.egysegek.get(i).setX(sor);
                        this.egysegek.get(i).setY(oszlop);
                        Main.tabla[sor-1][oszlop-1] = this.egysegek.get(i).getNev().charAt(0);
                        break;
                    }
                }while (true);
            }
            Main.palyatKiir();
        }

    }
    /**
     * Az egysegek lepeset valositja meg ez a fuggveny. Nem kepes a teruleten kivulre tenni egyseget, olyan helyre tenni, ahol mar allnak, illetve a sebessegenel tobb egyseget ugrani
     *
     * @param egyseg az az egyseg amivel lepni szeretnenk
     */
    public void lepes(Egyseg egyseg)
    {
        boolean kilepes = true;
        Main.palyatKiir();
        do
        {
            int sor = 0;
            do
            {
                System.out.println("Adj meg egy sort: ");
                Scanner scanner = new Scanner(System.in);
                String bemenet = scanner.nextLine();
                try
                {
                    sor = Integer.parseInt(bemenet);
                }
                catch (Exception e)
                {
                    System.out.println("Hiba");
                }
            } while (sor < 1  || sor > 10 );

            int oszlop = 0;
            do
            {
                System.out.println("Adj meg egy oszlopot: ");
                Scanner scanner = new Scanner(System.in);
                String bemenet = scanner.nextLine();
                try
                {
                    oszlop = Integer.parseInt(bemenet);
                }
                catch (Exception e)
                {
                    System.out.println("Hiba");
                }
            } while (oszlop < 1  || oszlop > 12);

            if(Math.abs(oszlop - egyseg.getY()) > egyseg.getSebesseg() || Math.abs(sor - egyseg.getX()) > egyseg.getSebesseg())
            {
                System.out.println("Nem léphetsz ilyen messzire");
            }
            else if(Main.tabla[sor-1][oszlop-1] != '-')
            {
                System.out.println("Ezen a mezőn már van egység!");
            }
            else
            {
                Main.tabla[egyseg.getX()-1][egyseg.getY()-1] = '-';
                Main.tabla[sor-1][oszlop-1] = egyseg.getNev().charAt(0);
                egyseg.setX(sor);
                egyseg.setY(oszlop);
                kilepes = false;
            }
        }while (kilepes);

    }

    /**
     * Visszaszamolom, mennyi tamadas pontot kapott az ellenseg, es azzal a szammal szorzom meg a tizet, ebbol kapom meg a hos sebzeset es a kivalasztott egyseg eletet ennyivel csokkentem
     * @param egyseg A celpont egyseg, amelyet a hos megtamad
     */
    public void hosTamad(Egyseg egyseg)
    {
        //ellenséges egység
        int szorzo = 1;
        if (this.getTamadas() != 1.1)
        {
            for (double i = 1; i <= this.getTamadas() ; i += 0.1)
            {
                szorzo++;
            }
        }

        egyseg.setOsszeletero(egyseg.getOsszeletero()-((int)(szorzo * 10)));
        System.out.println("Ezt az egységet támadtad meg a hőssel: "+egyseg.getNev()+" élete: "+egyseg.getOsszeletero());
        if(egyseg.getOsszeletero() == 0)
        {
            egyseg.setDbSzam(0);
            Main.tabla[egyseg.getX()-1][egyseg.getY()-1] ='-';
        }
        else egyseg.setDbSzam(egyseg.getOsszeletero() / egyseg.getEletero() + 1);
    }

    /**
     *
     * @return visszaadja a tamadas erteket 1 tizedesjeyyre kerekitve
     */
    public double getTamadas() {
        return (Math.round(tamadas * 10 )) / 10.0;
    }

    /**
     * Jatekos eseten addig ker be egy int szamot a program a Main classban, hogy az egy es tiz kozott legyen <br>
     * A tamadas, vedekezes, szerencse, moral, tudas, varazsero setterek mind igy vannak megoldva. Ha a jatekos beirja, hogy "exit", akkor tovabb megy a program
     * @param tamadas mennyi pontot tett a jatekos vagy az ellenseg az adott pontra
     */
    public void setTamadas(int tamadas)
    {
        double ar=5;
        int osszeg=0;
        if (this.tamadas > 1.1)
        {
            for (double i = 1.1 ; i < this.tamadas ; i = i + 0.1)
            {
                osszeg += ar;
                ar = Math.ceil(ar * 1.1);
            }

            this.arany += osszeg;
        }

        this.tamadas=1.1;
        if (tamadas <= 0)
        {
            System.out.println("A támadás nem lehet 1-nél kisebb");
            setTamadas(1);
        }
        else
        {
            ar = 5;
            int fejlesztesMennyiseg=0;
            if(this.arany > ar)
            {
                if(tamadas >= 11)
                {
                    for (int i = 1; i < 10 ; i++)
                    {
                        this.arany-= Math.ceil(ar);
                        ar = Math.ceil(ar * 1.1);
                        this.tamadas += 0.1;
                        fejlesztesMennyiseg++;
                        if(this.arany < ar)
                        {
                            System.out.println("Nincs több pénzed fejleszteni");
                            break;
                        }

                    }
                    System.out.println("A támadás maximum 10 pont lehet, és ennyire is lett állítva");
                }
                else
                {
                    for (int i = 1; i < tamadas ; i++)
                    {
                        this.arany-= Math.ceil(ar);
                        ar = Math.ceil(ar * 1.1);
                        this.tamadas += 0.1;
                        fejlesztesMennyiseg++;
                        if(this.arany < ar)
                        {
                            System.out.println("Nincs több pénzed fejleszteni");
                            break;
                        };
                    }
                    if(jatekos) System.out.println("A támadásod "+(1+fejlesztesMennyiseg)+" ponttra lett módosítva");
                }
            }
            else
            {
                System.out.println("Sajnos nincs elég pénzed egy fejlesztésre sem!");
            }
        }
    }

    public double getVedekezes()
    {
        return (Math.round(vedekezes * 10 )) / 10.0;
    }

    public void setVedekezes(double vedekezes)
    {
        double ar=5;
        int osszeg=0;
        if (this.vedekezes > 1.05)
        {
            for (double i = 1.05 ; i < this.vedekezes ; i = i + 0.05)
            {
                osszeg += ar;
                ar = Math.ceil(ar * 1.1);
            }

            this.arany += osszeg;
        }

        this.vedekezes=1.05;
        if (vedekezes < 0)
        {
            setVedekezes(0);
        }
        else
        {
            ar = 5;
            int fejlesztesMennyiseg=0;
            if(this.arany > ar)
            {
                if(vedekezes >= 11)
                {
                    for (int i = 1; i < 10 ; i++)
                    {
                        this.arany-= Math.ceil(ar);
                        ar = Math.ceil(ar * 1.1);
                        this.vedekezes += 0.05;
                        fejlesztesMennyiseg++;
                        if(this.arany < ar)
                        {
                            System.out.println("Nincs több pénzed fejleszteni");
                            break;
                        }

                    }
                    System.out.println("A védekezés maximum 10 pont lehet, és ennyire is lett állítva");
                }
                else
                {
                    for (int i = 1; i < vedekezes ; i++)
                    {
                        this.arany-= Math.ceil(ar);
                        ar = Math.ceil(ar * 1.1);
                        this.vedekezes += 0.05;
                        fejlesztesMennyiseg++;
                        if(this.arany < ar)
                        {
                            System.out.println("Nincs több pénzed fejleszteni");
                            break;
                        }
                    }
                    if(jatekos) System.out.println("A védekezésed "+(1+fejlesztesMennyiseg)+" ponttra lett módosítva");
                }
            }
            else
            {
                System.out.println("Sajnos nincs elég pénzed egy fejlesztésre sem!");
            }
        }
    }

    public int getVarazsero() {
        return varazsero;
    }

    public void setVarazsero(int varazsero) {
        double ar=5;
        int osszeg=0;
        if (this.varazsero > 1)
        {
            for (double i = 1 ; i < this.varazsero ; i++)
            {
                osszeg += ar;
                ar = Math.ceil(ar * 1.1);
            }

            this.arany += osszeg;
        }

        this.varazsero = 1;
        if (tamadas < 0)
        {
            setVarazsero(0);
        }
        else
        {
            ar = 5;
            int fejlesztesMennyiseg=0;
            if(this.arany > ar)
            {
                if(varazsero >= 11)
                {
                    for (int i = 1; i < 10 ; i++)
                    {
                        this.arany -= Math.ceil(ar);
                        ar = Math.ceil(ar * 1.1);
                        this.varazsero += 1;
                        fejlesztesMennyiseg++;
                        if(this.arany < ar)
                        {
                            System.out.println("Nincs több pénzed fejleszteni");
                            break;
                        }

                    }
                    System.out.println("A varazserod maximum 10 pont lehet, és ennyire is lett állítva");

                }
                else
                {
                    for (int i = 1; i < varazsero ; i++)
                    {
                        this.arany-= Math.ceil(ar);
                        ar = Math.ceil(ar * 1.1);
                        this.varazsero += 1;
                        fejlesztesMennyiseg++;
                        if(this.arany < ar)
                        {
                            System.out.println("Nincs több pénzed fejleszteni");
                            break;
                        }
                    }
                    if(jatekos) System.out.println("A varázserőd "+(1+fejlesztesMennyiseg)+" ponttra lett módosítva");
                }
            }
            else
            {
                System.out.println("Sajnos nincs elég pénzed egy fejlesztésre sem!");
            }
        }
    }

    public int getTudas() {
        return tudas;
    }

    public void setTudas(int tudas)
    {
        double ar=5;
        int osszeg=0;
        if (this.tudas > 10)
        {
            for (double i = 10 ; i < this.tudas ; i = i + 10)
            {
                osszeg += ar;
                ar = Math.ceil(ar * 1.1);
            }

            this.arany += osszeg;
        }

        this.tudas = 10;
        if (tamadas < 0)
        {
            setTudas(0);
        }
        else
        {
            ar = 5;
            int fejlesztesMennyiseg=0;
            if(this.arany > ar)
            {
                if(tudas >= 11)
                {
                    for (int i = 1; i < 10 ; i++)
                    {
                        this.arany-= Math.ceil(ar);
                        ar = Math.ceil(ar * 1.1);
                        this.tudas += 10;
                        fejlesztesMennyiseg++;
                        if(this.arany < ar)
                        {
                            System.out.println("Nincs több pénzed fejleszteni");
                            break;
                        }

                    }
                    System.out.println("A tudásod maximum 10 pont lehet, és ennyire is lett állítva");

                }
                else
                {
                    for (int i = 1; i < tudas ; i++)
                    {
                        this.arany-= Math.ceil(ar);
                        ar = Math.ceil(ar * 1.1);
                        this.tudas += 10;
                        fejlesztesMennyiseg++;
                        if(this.arany < ar)
                        {
                            System.out.println("Nincs több pénzed fejleszteni");
                            break;
                        }
                    }
                    if(jatekos) System.out.println("A tudásod "+(1+fejlesztesMennyiseg)+" ponttra lett módosítva");
                }
            }
            else
            {
                System.out.println("Sajnos nincs elég pénzed egy fejlesztésre sem!");
            }
        }

        this.mana = this.tudas;
    }

    public int getMoral() {
        return moral;
    }

    public void setMoral(int moral) {
        double ar=5;
        int osszeg=0;
        if (this.tudas > 1)
        {
            for (double i = 1 ; i < this.moral ; i++)
            {
                osszeg += ar;
                ar = Math.ceil(ar * 1.1);
            }

            this.arany += osszeg;
        }
        this.moral = 1;
        if (moral < 0)
        {
            setMoral(0);
        }
        else
        {
            ar = 5;
            int fejlesztesMennyiseg=0;
            if(this.arany > ar)
            {
                if(moral >= 11)
                {
                    for (int i = 1; i < 10 ; i++)
                    {
                        this.arany-= Math.ceil(ar);
                        ar = Math.ceil(ar * 1.1);
                        this.moral += 1;
                        fejlesztesMennyiseg++;
                        if(this.arany < ar)
                        {
                            System.out.println("Nincs több pénzed fejleszteni");
                            break;
                        }

                    }
                    System.out.println("A moralod maximum 10 pont lehet, és ennyire is lett állítva");

                }
                else
                {
                    for (int i = 1; i < moral ; i++)
                    {
                        this.arany-= Math.ceil(ar);
                        ar = Math.ceil(ar * 1.1);
                        this.moral += 1;
                        fejlesztesMennyiseg++;
                        if(this.arany < ar)
                        {
                            System.out.println("Nincs több pénzed fejleszteni");
                            break;
                        }
                    }
                    if(jatekos) System.out.println("A morálod "+(1+fejlesztesMennyiseg)+" ponttra lett módosítva");
                }
            }
            else
            {
                System.out.println("Sajnos nincs elég pénzed egy fejlesztésre sem!");
            }
        }
    }

    public double getSzerencse() {
        return (Math.round(szerencse * 10 )) / 10.0;
    }

    public void setSzerencse(int szerencse)
    {
        double ar=5;
        int osszeg=0;
        if (this.szerencse > 1.05)
        {
            for (double i = 1.05 ; i < this.szerencse ; i = i + 0.05)
            {
                osszeg += ar;
                ar = Math.ceil(ar * 1.1);
            }
            this.arany += osszeg;
        }

        this.szerencse=1.05;
        if (szerencse < 0)
        {
            setSzerencse(0);
        }
        else
        {
            ar = 5;
            int fejlesztesMennyiseg=0;
            if(this.arany > ar)
            {
                if(szerencse >= 11)
                {
                    for (int i = 1; i < 10 ; i++)
                    {
                        this.arany-= Math.ceil(ar);
                        ar = Math.ceil(ar * 1.1);
                        this.szerencse += 0.05;
                        fejlesztesMennyiseg++;
                        if(this.arany < ar)
                        {
                            System.out.println("Nincs több pénzed fejleszteni");
                            break;
                        }

                    }
                    System.out.println("A szerencse maximum 10 pont lehet, és ennyire is lett állítva");

                }
                else
                {
                    for (int i = 1; i < szerencse ; i++)
                    {
                        this.arany-= Math.ceil(ar);
                        ar = Math.ceil(ar * 1.1);
                        this.szerencse += 0.05;
                        fejlesztesMennyiseg++;
                        if(this.arany < ar)
                        {
                            System.out.println("Nincs több pénzed fejleszteni");
                            break;
                        }
                    }
                    if(jatekos) System.out.println("A szerencséd "+(1+fejlesztesMennyiseg)+" ponttra lett módosítva");
                }
            }
            else
            {
                System.out.println("Sajnos nincs elég pénzed egy fejlesztésre sem!");
            }
        }
    }

    public void setArany(int arany) {
        this.arany = arany;
    }

    public int getArany() {
        return arany;
    }

    public void setMana(int mana)
    {
        this.mana = mana;
    }

    public int getMana() {
        return mana;
    }

    public boolean getTamadhat() {
        return tamadhat;
    }
    public void setTamadhat(boolean tamadhat)
    {
        this.tamadhat = tamadhat;
    }

    public HashMap<Character, Egyseg> getMap() {
        return map;
    }

    public ArrayList<Egyseg> getEgysegek() {
        return egysegek;
    }

    public List<Varazslat> getVarazslatok() {
        return varazslatok;
    }

    public boolean getJatekos(){return jatekos;}
}
