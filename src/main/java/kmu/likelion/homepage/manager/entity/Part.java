package kmu.likelion.homepage.manager.entity;

public enum Part {
    FRONTEND("프론트엔드"),
    BACKEND("백엔드"),
    DESIGN("디자인");

    private String value;

    Part(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Part fromString(String value){
        for(Part part: Part.values()){
            if (part.name().equalsIgnoreCase(value) || part.value.equals(value)) {
                return part;
            }
        }
        throw new IllegalArgumentException("잘못된 Part 값: " + value);
    }

    @Override
    public String toString(){
        return value;
    }
}
