package com.harleylizard.obj;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.floats.FloatList;
import it.unimi.dsi.fastutil.floats.FloatLists;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import lombok.Data;

import java.util.List;

@Data
public final class ImmutableObj implements Obj {
    private final FloatList positions;
    private final FloatList textures;
    private final FloatList normals;

    private final List<Face> faces;

    @Override
    public FloatList parameterise(List<Attribute> format) {
        var vertices = new FloatArrayList();

        for (var face : faces) {
            for (var map : face) {

                for (var type : format) {
                    var size = type.getSize();
                    var i = (map.getInt(type) - 1) * size;

                    var list = getListFor(type);
                    if (list.isEmpty()) {
                        for (var j = 0; j < size; j++) {
                            vertices.add(0.0F);
                        }
                        continue;
                    }
                    for (var j = 0; j < size; j++) {
                        vertices.add(list.getFloat(i + j));
                    }
                }
            }
        }
        return FloatLists.unmodifiable(vertices);
    }

    @Override
    public IntList triangulate() {
        var elements = new IntArrayList();

        var last = 0;
        for (var face : faces) {
            switch (face.size()) {
                case 4 -> { // Quads.
                    var offset = last;
                    elements.add(0 + offset);
                    elements.add(1 + offset);
                    elements.add(2 + offset);
                    elements.add(2 + offset);
                    elements.add(3 + offset);
                    elements.add(0 + offset);
                    last += 4;
                }
                case 3 -> { // Triangles.
                    var offset = last;
                    elements.add(0 + offset);
                    elements.add(1 + offset);
                    elements.add(2 + offset);
                    last += 3;
                }
            }
        }
        return IntLists.unmodifiable(elements);
    }

    private FloatList getListFor(Attribute attribute) {
        return switch (attribute) {
            case POSITION -> positions;
            case TEXTURE -> textures;
            case NORMAL -> normals;
        };
    }
}
