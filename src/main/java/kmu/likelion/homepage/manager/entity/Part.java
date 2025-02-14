package kmu.likelion.homepage.manager.entity;

public enum Part {
    FRONT("Frontend"),
    BACK("Backend"),
    PnD("P/D");

    private String value;

    Part(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
