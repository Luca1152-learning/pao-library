package pao.library.api.model;

public enum Role {
    USER,
    LIBRARIAN;

    public static Role fromString(String str) {
        switch (str) {
            case "user":
                return USER;
            case "librarian":
                return LIBRARIAN;
        }
        throw new RuntimeException("Type " + str + " isn't being handled");
    }


    @Override
    public String toString() {
        return switch (this) {
            case USER -> "user";
            case LIBRARIAN -> "librarian";
        };
    }
}
