import java.util.*;

class Population {

    List<Humain> pop;
    List<Humain> humainsTiresAuSort;

    Population() {
        pop = new ArrayList<>();
        humainsTiresAuSort = new ArrayList<>();
    }

    public void vider() {
        pop = new ArrayList<>();
    }

    public void addHumain(Humain h) {
        pop.add(h);
    }

    public Humain getHumain(int index) {
        return pop.get(index);
    }

    public Humain removeHumain(Humain h) {
        pop.remove(h);
        return h;
    }

    public Humain removeHumain(int index) {
        return pop.remove(index);
    }

    public int taille() {
        return pop.size();
    }

    public void vieillir() {
        List<Humain> humainsATuer = new ArrayList<>();
        List<Humain> humainsAAjouter = new ArrayList<>();

        for (Humain h : pop) {
            h.vieillir();
            if (h.isDead()) {
                humainsATuer.add(h);
            }
        }

        for (Humain h : pop) {
            if (h.isKid() && h.getAge() >= 18) {
                humainsATuer.add(h);
                if (h.isHomme()) {
                    humainsAAjouter.add(new Garcon(h.getAge(), h.getPoids(), h.getNom(), 50));
                } else {
                    humainsAAjouter.add(new Femme(h.getAge(), h.getPoids(), h.getNom(), 50));
                }
            }
        }

        for (Humain h : humainsATuer) {
            pop.remove(h);
        }

        for (Humain h : humainsAAjouter) {
            pop.add(h);
        }
    }

    public Humain getRandomHumain() {
        Random random = new Random();
        List<Humain> humainsPossible = new ArrayList<>();

        for (Humain h : pop) {
            if (!humainsTiresAuSort.contains(h)) {
                humainsPossible.add(h);
            }
        }

        if (humainsPossible.isEmpty()) {
            return null;
        }

        int index = random.nextInt(humainsPossible.size());
        Humain humain = humainsPossible.get(index);

        humainsTiresAuSort.add(humain);

        return humain;
    }

    public Humain rencontre(int index1, int index2) {
        Humain h1 = getHumain(index1);
        Humain h2 = getHumain(index2);
        try {
            return h1.rencontre(h2);
        } catch (MeetingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void resetHumainsTiresAuSort() {
        humainsTiresAuSort = new ArrayList<>();
    }

    public void print() {
        System.out.println("Liste des humains : ");
        System.out.println("Nombre d'humain " + pop.size());
        for (Humain h : pop) {
            h.print();
        }
    }

    public void sortHumain() {
        Collections.sort(pop);
    }
}