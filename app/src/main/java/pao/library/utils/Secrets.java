package pao.library.utils;

public enum Secrets {
    DATABASE_URL("jdbc:postgresql://ec2-63-35-156-160.eu-west-1.compute.amazonaws.com:5432/derqc5g9ar8b4c?sslmode=require&user=lsqucghuclddue&password=d83e9fe141a25b604107d8ad239c7dd615de3699a4266e4dea5fcdf0307115c9");

    private final String str;

    Secrets(String value) {
        this.str = value;
    }

    public String getStr() {
        return this.str;
    }
}
