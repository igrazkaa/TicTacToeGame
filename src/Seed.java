public enum Seed {
    CROSS("X"),
    NOUGHT("O"),
    NO_SEED(" ");
    private final String sign;

    private Seed(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

}
