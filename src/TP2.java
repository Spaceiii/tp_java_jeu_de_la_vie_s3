import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TP2 {
    public static void main(String[] args) {
        int nbTourJeu = Integer.parseInt(args[0]);
        int tailleInit = Integer.parseInt(args[1]);
        int modeDeReproduction = Integer.parseInt(args[2]);
        boolean accident = false;
        int accidentCount = 0;

        Population population = new Population();
        final Random random = new Random();

        for (int i=0; i<tailleInit; i++) {
            if (i%2 == 0) {
                population.addHumain(
                        new Femme(20, 55, String.valueOf(i), random.nextInt(1, 100))
                );
            } else {
                population.addHumain(
                        new Homme(20, 70, String.valueOf(i), random.nextInt(70, 100))
                );
            }
        }

        for (int tourDejeu=0; tourDejeu<nbTourJeu; tourDejeu++) {
            int n;
            int nbb = -1;
            int nbRencontre = 0;

            if (population.taille() < 2){
                System.out.println("No more humains !\nEnd of the game !");
                return;

            }
            switch (modeDeReproduction) {
                case 0:
                    n = random.nextInt(population.taille()/2);
                    break;
                case 1:
                    n = population.taille() / 2;
                    break;
                default:
                    n = population.taille() / 2;
                    nbb = Integer.parseInt(args[3]);
                    break;
            }
            List<Humain> bebeAAjouter = new ArrayList<>();

            int i = 0;
            int naissance = 0;
            while (true) {
                if (population.taille() == 0) return;

                Humain h1 = population.getRandomHumain();
                Humain h2 = population.getRandomHumain();

                if (h1 == null || h2 == null) {
                    System.out.println("not enough humains\nEnd of the game !");
                    return;
                }

                if (h1.isKid() && h2.isKid() && new Random().nextInt(0, 100) < 5) {
                    accidentCount++;
                }

                try {
                    Humain bebe = h1.rencontre(h2);
                    nbRencontre++;

                    if (bebe != null) {
                        bebeAAjouter.add(bebe);
                        naissance++;
                    }
                }
                catch (MeetingException e) {
                    System.out.println(e.getMessage());
                }

                i++;
                if (i >= n && nbb == -1 || nbb != -1 && naissance >= nbb) break;
            }

            population.vieillir();

            population.sortHumain();
            System.out.println("Tour de jeu : " + tourDejeu);
            population.print();
            population.resetHumainsTiresAuSort();

            for (Humain h : bebeAAjouter) {
                population.addHumain(h);
            }

            if (accident) {
                for(int j=0; j<nbRencontre; j++) {
                    population.removeHumain(population.getRandomHumain());
                }
            }

            population.resetHumainsTiresAuSort();

            for (int j=0; j<accidentCount; j++) {
                population.removeHumain(population.getRandomHumain());
            }

            population.resetHumainsTiresAuSort();
        }
    }
}