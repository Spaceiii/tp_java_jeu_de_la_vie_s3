public class NoBreadingException extends MeetingException {
    protected Humain[] source;

    public NoBreadingException(Humain h1, Humain h2) {
        super(h1, h2, "naissance impossible : " + h1.getNom() + " et " + h2.getNom() + " ne veulent pas se reproduire");
        source = new Humain[2];
        source[0] = h1;
        source[1] = h2;
    }

    public Humain[] getHumain() {
        return source;
    }
}
