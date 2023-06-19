import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Varazslat osztaly. Itt adom meg, hogy az egyes varazslatoknak milyen hatasa van.
 */
public class Varazslat
{
    private String nev;
    private int ar;
    private int manna;

    public Varazslat(String nev, int ar, int manna)
    {
        this.nev = nev;
        this.ar = ar;
        this.manna = manna;
    }

    /**
     * A tuzlabda 3x3-as teruleten sebez mindenkit. Ehhez kulon kell ellenorizni a sarkokat, es a szeleket. Ha a jatekos egyseget sebzi, akkor azt irja ki, ha az ellenseget, akkor azt.
     * @param hos Az a hos, amelyik hasznalja a kepesseget
     * @param jatekos A jatekos egysegeihez tartozo HashMap
     * @param ellenseg Az ellenseg egysegeihez tartozo HashMap
     */
    public void tuzlabda(Hos hos, HashMap<Character, Egyseg> jatekos, HashMap<Character, Egyseg> ellenseg)
    {
        if(hos.getMana() > this.getManna())
        {
            int sor = 0;
            int oszlop = 0;
            boolean eltalalt = false;
            if (hos.getJatekos())
            {
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
            }
            else //ellenség
            {
                for (int i = 0; i < Main.SOR ; i++)
                {
                    for (int j = 0; j < Main.OSZLOP; j++)
                    {
                        if (jatekos.containsKey(Main.tabla[i][j]))
                        {
                            sor = jatekos.get(Main.tabla[i][j]).getX();
                            oszlop = jatekos.get(Main.tabla[i][j]).getY();
                        }
                    }
                }
            }

                //bal felső sarok
                if (sor == 1 && oszlop == 1) {
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            if (Main.tabla[i][j] != '-' && jatekos.containsKey(Main.tabla[i][j]) && jatekos.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                jatekos.get(Main.tabla[i][j]).setOsszeletero(jatekos.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("A játékos egysége sebződött a tűzlabda által: "+jatekos.get(Main.tabla[i][j]).getNev()+" élete: "+jatekos.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (jatekos.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    jatekos.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else jatekos.get((Main.tabla[i][j])).setDbSzam(jatekos.get((Main.tabla[i][j])).getOsszeletero() / jatekos.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                            else if (Main.tabla[i][j] != '-' && ellenseg.containsKey(Main.tabla[i][j]) && ellenseg.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                ellenseg.get(Main.tabla[i][j]).setOsszeletero(ellenseg.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("Az ellenség ezen egysége sebződött a tűzlabda által: "+ellenseg.get(Main.tabla[i][j]).getNev()+" élete: "+ellenseg.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (ellenseg.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    ellenseg.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else ellenseg.get((Main.tabla[i][j])).setDbSzam(ellenseg.get((Main.tabla[i][j])).getOsszeletero() / ellenseg.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                        }
                    }
                }
                //jobb felső sarok
                else if (sor == 1 && oszlop == 12) {
                    for (int i = 0; i < 2; i++) {
                        for (int j = 10; j < 12; j++) {
                            if (Main.tabla[i][j] != '-' && jatekos.containsKey(Main.tabla[i][j]) && jatekos.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                jatekos.get(Main.tabla[i][j]).setOsszeletero(jatekos.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("A játékos egysége sebződött a tűzlabda által: "+jatekos.get(Main.tabla[i][j]).getNev()+" élete: "+jatekos.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (jatekos.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    jatekos.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else jatekos.get((Main.tabla[i][j])).setDbSzam(jatekos.get((Main.tabla[i][j])).getOsszeletero() / jatekos.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                            else if (Main.tabla[i][j] != '-' && ellenseg.containsKey(Main.tabla[i][j]) && ellenseg.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                ellenseg.get(Main.tabla[i][j]).setOsszeletero(ellenseg.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("Az ellenség ezen egysége sebződött a tűzlabda által: "+ellenseg.get(Main.tabla[i][j]).getNev()+" élete: "+ellenseg.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (ellenseg.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    ellenseg.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else ellenseg.get((Main.tabla[i][j])).setDbSzam(ellenseg.get((Main.tabla[i][j])).getOsszeletero() / ellenseg.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                        }
                    }
                }
                //jobb alsó sarok
                else if (sor == 10 && oszlop == 12) {
                    for (int i = 8; i < 10; i++) {
                        for (int j = 10; j < 12; j++) {
                            if (Main.tabla[i][j] != '-' && jatekos.containsKey(Main.tabla[i][j]) && jatekos.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                jatekos.get(Main.tabla[i][j]).setOsszeletero(jatekos.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("A játékos egysége sebződött a tűzlabda által: "+jatekos.get(Main.tabla[i][j]).getNev()+" élete: "+jatekos.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (jatekos.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    jatekos.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else jatekos.get((Main.tabla[i][j])).setDbSzam(jatekos.get((Main.tabla[i][j])).getOsszeletero() / jatekos.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                            else if (Main.tabla[i][j] != '-' && ellenseg.containsKey(Main.tabla[i][j]) && ellenseg.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                ellenseg.get(Main.tabla[i][j]).setOsszeletero(ellenseg.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("Az ellenség ezen egysége sebződött a tűzlabda által: "+ellenseg.get(Main.tabla[i][j]).getNev()+" élete: "+ellenseg.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (ellenseg.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    ellenseg.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else ellenseg.get((Main.tabla[i][j])).setDbSzam(ellenseg.get((Main.tabla[i][j])).getOsszeletero() / ellenseg.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                        }
                    }
                }
                //bal alsó sarok
                else if (sor == 10 && oszlop == 1) {
                    for (int i = 8; i < 10; i++) {
                        for (int j = 0; j < 2; j++) {
                            if (Main.tabla[i][j] != '-' && jatekos.containsKey(Main.tabla[i][j]) && jatekos.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                jatekos.get(Main.tabla[i][j]).setOsszeletero(jatekos.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("A játékos egysége sebződött a tűzlabda által: "+jatekos.get(Main.tabla[i][j]).getNev()+" élete: "+jatekos.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (jatekos.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    jatekos.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else jatekos.get((Main.tabla[i][j])).setDbSzam(jatekos.get((Main.tabla[i][j])).getOsszeletero() / jatekos.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                            else if (Main.tabla[i][j] != '-' && ellenseg.containsKey(Main.tabla[i][j]) && ellenseg.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                ellenseg.get(Main.tabla[i][j]).setOsszeletero(ellenseg.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("Az ellenség ezen egysége sebződött a tűzlabda által: "+ellenseg.get(Main.tabla[i][j]).getNev()+" élete: "+ellenseg.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (ellenseg.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    ellenseg.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else ellenseg.get((Main.tabla[i][j])).setDbSzam(ellenseg.get((Main.tabla[i][j])).getOsszeletero() / ellenseg.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                        }
                    }
                }
                //felső sor
                else if (sor == 1) {
                    for (int i = 0; i < 2; i++) {
                        for (int j = oszlop - 2; j < oszlop + 1; j++) {
                            if (Main.tabla[i][j] != '-' && jatekos.containsKey(Main.tabla[i][j]) && jatekos.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                jatekos.get(Main.tabla[i][j]).setOsszeletero(jatekos.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("A játékos egysége sebződött a tűzlabda által: "+jatekos.get(Main.tabla[i][j]).getNev()+" élete: "+jatekos.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (jatekos.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    jatekos.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else jatekos.get((Main.tabla[i][j])).setDbSzam(jatekos.get((Main.tabla[i][j])).getOsszeletero() / jatekos.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                            else if (Main.tabla[i][j] != '-' && ellenseg.containsKey(Main.tabla[i][j]) && ellenseg.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                ellenseg.get(Main.tabla[i][j]).setOsszeletero(ellenseg.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("Az ellenség ezen egysége sebződött a tűzlabda által: "+ellenseg.get(Main.tabla[i][j]).getNev()+" élete: "+ellenseg.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (ellenseg.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    ellenseg.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else ellenseg.get((Main.tabla[i][j])).setDbSzam(ellenseg.get((Main.tabla[i][j])).getOsszeletero() / ellenseg.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                        }
                    }
                }
                //jobb oldali oszlop
                else if (oszlop == 12)
                {
                    for (int i = sor - 2; i < sor + 1; i++)
                    {
                        for (int j = 10; j < 12; j++)
                        {
                            if (Main.tabla[i][j] != '-' && jatekos.containsKey(Main.tabla[i][j]) && jatekos.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                jatekos.get(Main.tabla[i][j]).setOsszeletero(jatekos.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("A játékos egysége sebződött a tűzlabda által: "+jatekos.get(Main.tabla[i][j]).getNev()+" élete: "+jatekos.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (jatekos.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    jatekos.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else jatekos.get((Main.tabla[i][j])).setDbSzam(jatekos.get((Main.tabla[i][j])).getOsszeletero() / jatekos.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                            else if (Main.tabla[i][j] != '-' && ellenseg.containsKey(Main.tabla[i][j]) && ellenseg.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                ellenseg.get(Main.tabla[i][j]).setOsszeletero(ellenseg.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("Az ellenség ezen egysége sebződött a tűzlabda által: "+ellenseg.get(Main.tabla[i][j]).getNev()+" élete: "+ellenseg.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (ellenseg.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    ellenseg.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else ellenseg.get((Main.tabla[i][j])).setDbSzam(ellenseg.get((Main.tabla[i][j])).getOsszeletero() / ellenseg.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                        }
                    }
                }
                //alsó sor
                else if (sor == 10) {
                    for (int i = 8; i < 10; i++) {
                        for (int j = oszlop - 2; j < oszlop + 1; j++) {
                            if (Main.tabla[i][j] != '-' && jatekos.containsKey(Main.tabla[i][j]) && jatekos.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                jatekos.get(Main.tabla[i][j]).setOsszeletero(jatekos.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("A játékos egysége sebződött a tűzlabda által: "+jatekos.get(Main.tabla[i][j]).getNev()+" élete: "+jatekos.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (jatekos.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    jatekos.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else jatekos.get((Main.tabla[i][j])).setDbSzam(jatekos.get((Main.tabla[i][j])).getOsszeletero() / jatekos.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                            else if (Main.tabla[i][j] != '-' && ellenseg.containsKey(Main.tabla[i][j]) && ellenseg.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                ellenseg.get(Main.tabla[i][j]).setOsszeletero(ellenseg.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("Az ellenség ezen egysége sebződött a tűzlabda által: "+ellenseg.get(Main.tabla[i][j]).getNev()+" élete: "+ellenseg.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (ellenseg.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    ellenseg.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else ellenseg.get((Main.tabla[i][j])).setDbSzam(ellenseg.get((Main.tabla[i][j])).getOsszeletero() / ellenseg.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                        }
                    }
                }

                //bal oszlop
                else if (oszlop == 1) {
                    for (int i = sor - 2; i < sor + 1; i++) {
                        for (int j = 0; j < 2; j++) {
                            if (Main.tabla[i][j] != '-' && jatekos.containsKey(Main.tabla[i][j]) && jatekos.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                jatekos.get(Main.tabla[i][j]).setOsszeletero(jatekos.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("A játékos egysége sebződött a tűzlabda által: "+jatekos.get(Main.tabla[i][j]).getNev()+" élete: "+jatekos.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (jatekos.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    jatekos.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else jatekos.get((Main.tabla[i][j])).setDbSzam(jatekos.get((Main.tabla[i][j])).getOsszeletero() / jatekos.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                            else if (Main.tabla[i][j] != '-' && ellenseg.containsKey(Main.tabla[i][j]) && ellenseg.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                ellenseg.get(Main.tabla[i][j]).setOsszeletero(ellenseg.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("Az ellenség ezen egysége sebződött a tűzlabda által: "+ellenseg.get(Main.tabla[i][j]).getNev()+" élete: "+ellenseg.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (ellenseg.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    ellenseg.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else ellenseg.get((Main.tabla[i][j])).setDbSzam(ellenseg.get((Main.tabla[i][j])).getOsszeletero() / ellenseg.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                        }
                    }
                } else
                {
                    for (int i = sor - 2; i < sor + 1; i++) {
                        for (int j = oszlop - 2; j < oszlop + 1; j++)
                        {
                            if (Main.tabla[i][j] != '-' && jatekos.containsKey(Main.tabla[i][j]) && jatekos.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                jatekos.get(Main.tabla[i][j]).setOsszeletero(jatekos.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("A játékos egysége sebződött a tűzlabda által: "+jatekos.get(Main.tabla[i][j]).getNev()+" élete: "+jatekos.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (jatekos.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    jatekos.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else jatekos.get((Main.tabla[i][j])).setDbSzam(jatekos.get((Main.tabla[i][j])).getOsszeletero() / jatekos.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                            else if (Main.tabla[i][j] != '-' && ellenseg.containsKey(Main.tabla[i][j]) && ellenseg.get(Main.tabla[i][j]).getOsszeletero() != 0)
                            {
                                ellenseg.get(Main.tabla[i][j]).setOsszeletero(ellenseg.get(Main.tabla[i][j]).getOsszeletero() - hos.getVarazsero() * 20);
                                System.out.println("Az ellenség ezen egysége sebződött a tűzlabda által: "+ellenseg.get(Main.tabla[i][j]).getNev()+" élete: "+ellenseg.get(Main.tabla[i][j]).getOsszeletero());
                                eltalalt = true;
                                if (ellenseg.get(Main.tabla[i][j]).getOsszeletero() == 0)
                                {
                                    ellenseg.get((Main.tabla[i][j])).setDbSzam(0);
                                    Main.tabla[i][j] = '-';
                                } else ellenseg.get((Main.tabla[i][j])).setDbSzam(ellenseg.get((Main.tabla[i][j])).getOsszeletero() / ellenseg.get(Main.tabla[i][j]).getEletero() + 1);
                            }
                        }
                    }
                }
                hos.setMana(hos.getMana() - this.getManna());
        if (!eltalalt) System.out.println("A tűzlabda nem talált el senkit sem!");
        }
        else
        {
            System.out.println("Nincs elég mannád!");
        }
    }
    /**
     * A program beker egy ellenseges egyseget, es azt tamadja meg a villamcsapas kepesseggel.
     * @param hos Az a hos, amelyik a kepesseget hasznalja
     * @param list A jatekos es az ellenseg egysegeit tartalmazo lista
     */
    public void villamcsapas(Hos hos, List <Egyseg> list)
    {
        Egyseg celpont = list.get(0);
        if(hos.getMana() > this.getManna())
        {
            System.out.println("Válaszd ki melyik egységet szeretnéd megtámadni");
            String bemenet="";

            for (Egyseg item : list)
            {
                if (!item.isJatekos() && item.getOsszeletero() != 0) System.out.println(item.getNev());
            }

            boolean kilepes = true;
            do
            {
                try
                {
                    Scanner sc = new Scanner(System.in);
                    bemenet = sc.nextLine();

                    for (Egyseg item : list)
                    {
                        if (bemenet.equals(item.getNev()) && !item.isJatekos() && item.osszeletero != 0)
                        {
                            celpont = item;
                            kilepes = false;
                            break;
                        }
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Hiba");
                }
            }while (kilepes);

            celpont.setOsszeletero(celpont.getOsszeletero() - (hos.getVarazsero() * 20));
            if(celpont.getEletero() == 0)
            {
                celpont.setDbSzam(0);
                Main.tabla[celpont.getX()-1][celpont.getY()-1]='-';
            }
            else celpont.setDbSzam(celpont.getOsszeletero() / celpont.getEletero() + 1);
            hos.setMana(hos.getMana() - this.getManna());

            System.out.println("A megtámadott egység: ("+celpont.getNev()+"), élete : "+celpont.getOsszeletero());
            System.out.println("A mannád: "+hos.getMana());
        }
        else
        {
            System.out.println("Nincs elég mannád!");
        }
    }

    /**
     * Jatekos eseteben beker egy hozzatarozo egyeget, es azt gyogyitja fel, maximum annak kezdo maximum eleterejere <br>
     * Gep eseteben vegigmegy a gep egysegein, es ha valamelyik nem a kezdoerteken van az osszeletereje, akkor gyogyit
     * @param hos Az a hos, amelyik a kepesseget hasznalja
     * @param list A jatekos es a hos egysegeit tartalmazo lista
     */
    public void gyogyitas(Hos hos, List <Egyseg> list)
    {
        Egyseg kit = null;
        if (hos.getJatekos())
        {
            if(hos.getMana() > this.getManna())
            {
                System.out.println("Válaszd ki melyik egységet szeretnéd gyógyítani");
                String bemenet="";

                for (Egyseg item : list)
                {
                    if (item.isJatekos() && item.getOsszeletero() != 0) System.out.println(item.getNev());
                }

                boolean kilepes = true;
                do
                {
                    try
                    {
                        Scanner sc = new Scanner(System.in);
                        bemenet = sc.nextLine();

                        for (Egyseg item : list)
                        {
                            if (bemenet.equals(item.getNev()) && item.isJatekos() && item.getOsszeletero() != 0)
                            {
                                kit = item;
                                kilepes = false;
                                break;
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Hiba");
                    }
                }while (kilepes);


                kit.setOsszeletero(kit.getOsszeletero() + hos.getVarazsero() * 50);
                kit.setDbSzam(kit.getOsszeletero() / kit.getEletero() + 1);

                System.out.println("A(z) "+kit.getNev() + " egységedet felgyógyítottad "+kit.osszeletero+" életre" );
                hos.setMana(hos.getMana() - this.getManna());
            }
            else System.out.println("Nincs elég mannád!");
        }
        else // nem játékos
        {
            for (int i = 0; i < Main.SOR ; i++)
            {
                for (int j = 0; j < Main.OSZLOP; j++)
                {
                    if (hos.getMap().containsKey(Main.tabla[i][j]) && hos.getMap().get(Main.tabla[i][j]).getMaxEletero() != hos.getMap().get(Main.tabla[i][j]).getOsszeletero())
                    {
                        kit = hos.getMap().get(Main.tabla[i][j]);
                    }
                }
            }
            if (kit != null)
            {
                kit.setOsszeletero(kit.getOsszeletero() + (hos.getVarazsero() * 50));
                kit.setDbSzam(kit.getOsszeletero() / kit.getEletero() + 1);

                System.out.println("Az ellenség felgyógyította a(z) "+kit.getNev()+" egységét. Élete: "+kit.getOsszeletero());
            }
        }
    }

    /**
     * Jatekos eseten beker egy oszlopot, es azon az oszlopon mindenkit sebez <br>
     * Gep eseten, a gep vegigmegy a palyan, es ha talal egy ellenseget, akkor annak az oszlopaban sebez
     * @param hos A hos, amelyik a kepesseget hasznalja
     * @param jatekos A jatekos egysegeihez tartozo HashMap
     * @param ellenseg Az ellenseg egysegeihez tartozo hashmap
     */
    public void tuzSzonyeg( Hos hos, HashMap<Character, Egyseg> jatekos, HashMap<Character, Egyseg> ellenseg)
    {
        if (hos.getJatekos()) System.out.println("Ellenséges tűzszőnyeg");
        int oszlop = 0;
        if (hos.getJatekos())
        {
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
        }
        else
        {
            for (int i = 0; i < Main.SOR ; i++)
            {
                for (int j = 0; j < Main.OSZLOP; j++)
                {
                    if (jatekos.containsKey(Main.tabla[i][j]))
                    {
                        oszlop = j + 1;
                    }
                }
            }
        }


        if(hos.getMana() > this.getManna())
        {
            hos.setMana(hos.getMana() - this.getManna());
            for (int i = 0; i < Main.SOR ; i++)
            {
                if (Main.tabla[i][oszlop-1] != '-' && jatekos.containsKey(Main.tabla[i][oszlop-1]) && jatekos.get(Main.tabla[i][oszlop-1]).getOsszeletero() != 0)
                {
                    jatekos.get(Main.tabla[i][oszlop-1]).setOsszeletero(jatekos.get(Main.tabla[i][oszlop-1]).getOsszeletero() - hos.getVarazsero() * 20);
                    System.out.println("Ez az egység sebződött a tűzszőnyegben: játékos "+jatekos.get(Main.tabla[i][oszlop-1]).getNev()+" élete: "+jatekos.get(Main.tabla[i][oszlop-1]).getOsszeletero());
                    if (jatekos.get(Main.tabla[i][oszlop-1]).getOsszeletero() == 0)
                    {
                        jatekos.get((Main.tabla[i][oszlop-1])).setDbSzam(0);
                        Main.tabla[i][oszlop-1] = '-';
                    } else jatekos.get((Main.tabla[i][oszlop-1])).setDbSzam(jatekos.get((Main.tabla[i][oszlop-1])).getOsszeletero() / jatekos.get(Main.tabla[i][oszlop-1]).getEletero() + 1);
                }
                else if (Main.tabla[i][oszlop-1] != '-' && ellenseg.containsKey(Main.tabla[i][oszlop-1]) && ellenseg.get(Main.tabla[i][oszlop-1]).getOsszeletero() != 0)
                {
                    ellenseg.get(Main.tabla[i][oszlop-1]).setOsszeletero(ellenseg.get(Main.tabla[i][oszlop-1]).getOsszeletero() - hos.getVarazsero() * 20);
                    System.out.println("Ez az egység sebződött a tűzszőnyegben: ellenséges "+ellenseg.get(Main.tabla[i][oszlop-1]).getNev()+" élete: "+ellenseg.get(Main.tabla[i][oszlop-1]).getOsszeletero());
                    if (ellenseg.get(Main.tabla[i][oszlop-1]).getOsszeletero() == 0)
                    {
                        ellenseg.get((Main.tabla[i][oszlop-1])).setDbSzam(0);
                        Main.tabla[i][oszlop-1] = '-';
                    } else ellenseg.get((Main.tabla[i][oszlop-1])).setDbSzam(ellenseg.get((Main.tabla[i][oszlop-1])).getOsszeletero() / ellenseg.get(Main.tabla[i][oszlop-1]).getEletero() + 1);
                }
            }
        }
        else System.out.println("Nincs elég mannád!");

    }

    /**
     * A kepesseg elaltatja az ellenseg egy egyseget, aki egy kort igy kihagy
     * @param hos A hos, aki hasznalja a kepesseget
     * @param list A jatekos es az ellenseg egysegeit tartalmazo lista
     */
    public void altatas(Hos hos, List <Egyseg> list)
    {
        if (hos.getMana() > this.getManna())
        {
            Egyseg kit = list.get(0);
            System.out.println("Válaszd ki melyik egységet szeretnéd altatni");
            String bemenet = "";

            for (Egyseg item : list) {
                if (!item.isJatekos() && item.getOsszeletero() != 0) System.out.println(item.getNev());
            }

            boolean kilepes = true;
            do {
                try {
                    Scanner sc = new Scanner(System.in);
                    bemenet = sc.nextLine();

                    for (Egyseg item : list) {
                        if (bemenet.equals(item.getNev()) && !item.isJatekos() && item.osszeletero != 0) {
                            kit = item;
                            kilepes = false;
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Hiba");
                }
            } while (kilepes);
            kit.setAlszik(true);
        }
    }

    public String getNev() {
        return nev;
    }


    public int getAr() {
        return ar;
    }

    public int getManna() {
        return manna;
    }

    public void setManna(int manna) {
        this.manna = manna;
    }
}
