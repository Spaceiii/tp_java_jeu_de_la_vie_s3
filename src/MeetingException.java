public class MeetingException extends Exception {
    protected Humain[] source;

    public MeetingException(Humain h1, Humain h2, String message) {
        super(message);
        source = new Humain[2];
        source[0] = h1;
        source[1] = h2;
    }

    public Humain[] getHumain() {
        return source;
    }
}