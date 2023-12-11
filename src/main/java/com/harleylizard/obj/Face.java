package com.harleylizard.obj;

import it.unimi.dsi.fastutil.objects.Object2IntMap;

import java.util.List;

public record Face(List<Object2IntMap<Type>> list) {

    public enum Type {
        VERTEX,
        TEXTURE,
        NORMAL
    }
}
