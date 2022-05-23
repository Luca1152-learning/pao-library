package pao.library.utils;

public enum Secrets {
    DATABASE_URL("jdbc:postgresql://ec2-54-77-40-202.eu-west-1.compute.amazonaws" +
            ".com:5432/ddsjhmrenu1bp3?sslmode=require"),
    DATABASE_USER("gpjltautyhuyqg"),
    DATABASE_PASSWORD("13a7cfb2e27c5ecb6f9830d8676c0c1efd54c1a032ca35882388d48e13065d0b");

    private final String str;

    Secrets(String value) {
        this.str = value;
    }

    public String getStr() {
        return this.str;
    }
}
