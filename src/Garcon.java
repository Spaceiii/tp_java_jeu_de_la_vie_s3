public class Garcon extends Homme {
    Garcon(String nom) {
        super(nom);
        salaire = 0;
    }

    Garcon(int age, int poids, String nom, int batifolage) {
        super(age, poids, nom, batifolage);
    }

    public void vieillir() {
        super.vieillir();
    }

    @Override
    public Humain rencontre(Humain h) throws MeetingException {
        if (h.isFemme()) {
            return rencontre((Femme) h);
        }

        throw new BreedingForbiddenException(this, h);
    }

    @Override
    public Humain rencontre(Femme f) throws MeetingException {
        int b = loto.nextInt(0, 100);
        int c = loto.nextInt(0, 100);

        if (b < batifolage) {
            throw new NoBreadingException(this, f, NoBreadingException.NoBreadingReason.BATIFOLAGE);
        } else if (f.isKid()) {
            throw new NoBreadingException(this, f, NoBreadingException.NoBreadingReason.KID);
        } else if (c < f.getFertilite()) {
            throw new NoBreadingException(this, f, NoBreadingException.NoBreadingReason.FERTILITE);
        }

        Humain e;
        int p = loto.nextInt(0, 100);
        if (p < 50) {
            e = new Garcon(nom + f.getNom());
        } else {
            e = new Fille(nom + f.getNom());
        }

        return e;
    }

    @Override
    public boolean isKid() {
        return true;
    }
}
