package com.harleylizard.obj;

import it.unimi.dsi.fastutil.objects.Object2IntMap;

import java.util.Iterator;
import java.util.List;

public final class Face implements Iterable<Object2IntMap<Attribute>> {
    private final List<Object2IntMap<Attribute>> list;

    public Face(List<Object2IntMap<Attribute>> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    @Override
    public Iterator<Object2IntMap<Attribute>> iterator() {
        return list.iterator();
    }
}
