package com.harleylizard.obj;

public enum Attribute {
    POSITION(4),
    TEXTURE(2),
    NORMAL(3);

    private final int size;

    Attribute(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
