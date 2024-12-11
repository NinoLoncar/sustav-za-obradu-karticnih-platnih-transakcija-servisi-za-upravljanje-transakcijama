package foi.air.szokpt.transactionmng.enums;

public enum CardBrand {
    Visa("visa"),
    MasterCard("mastercard"),
    Diners("diners"),
    Discover("discover"),
    Maestro("maestro"),
    Invalid("invalid");

    private final String name;

    CardBrand(String name) {
        this.name = name;
    }

    public static CardBrand getByName(String name) {
        for (CardBrand cardBrand : values()) {
            if (cardBrand.getName().equals(name)) {
                return cardBrand;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

}

