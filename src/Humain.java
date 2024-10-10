import java.util.*;

public abstract class Humain implements Comparable<Humain> {

    protected static Random loto = new Random(Calendar.getInstance().getTimeInMillis());
    protected int age;
    protected int poids;
    protected String nom;
    protected int esperanceVie;

    Humain(String nom) {
        this.nom = nom;
        age = 0;
        poids = 3;

        setEsperanceVie();
    }

    Humain(int age, int poids, String nom) {
        this.age = age;
        this.poids = poids;
        this.nom = nom;

        setEsperanceVie();
    }

    void setNom(String nom) {
        this.nom = nom;
    }

    void setAge(int age) {
        this.age = age;
    }

    void setPoids(int poids) {
        this.poids = poids;
    }

    int getAge() {
        return age;
    }

    int getPoids() {
        return poids;
    }

    String getNom() {
        return nom;
    }

    protected void setEsperanceVie() {
        esperanceVie = 70;
    }

    public void vieillir() {
        age++;
    }

    public void grossir(int p) {
        if (poids + p < 0) return;
        poids += p;
    }

    public boolean isDead() {
        return age > esperanceVie;
    }

    public abstract boolean isHomme();

    public abstract boolean isFemme();

    public abstract Humain rencontre(Humain h) throws MeetingException;

    public void print() {
        String s = "Je m'appelle " + nom + ", " + age + " ans.";
        if (isHomme()) {
            s += " J'ai un salaire de " + ((Homme) this).salaire + " euros.";
        }
        System.out.println(s);
    }

    public abstract boolean isKid();
   
    public int compareTo(Humain h) {
        return age - h.age;
    }
}