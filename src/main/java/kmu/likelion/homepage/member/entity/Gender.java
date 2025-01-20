package kmu.likelion.homepage.member.entity;

public enum Gender {
    MALE("M"),
    FEMALE("F");

    private String code;

    Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
