package kmu.likelion.homepage.post.entity;

public enum PostType {
    PROJECT("Project"),
    ACTIVITY("Activity");

    private String value;

    PostType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
