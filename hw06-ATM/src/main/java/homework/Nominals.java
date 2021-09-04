package homework;

public enum Nominals {
    FIVE_HUNDRED(500), THOUSAND(1000), TWO_THOUSAND(2000), FIVE_THOUSAND(5000);
    private final int numberNominal;

    Nominals(int numberNominal) {
        this.numberNominal = numberNominal;
    }

    public int getNumberNominal() {
        return numberNominal;
    }
}
