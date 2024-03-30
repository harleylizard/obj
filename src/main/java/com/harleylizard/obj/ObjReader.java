package com.harleylizard.obj;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.floats.FloatLists;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

@UtilityClass
public final class ObjReader {

    public Obj read(InputStream inputStream) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return read(reader);
        }
    }

    public Obj read(Path path) throws IOException {
        try (var reader = Files.newBufferedReader(path)) {
            return read(reader);
        }
    }

    private Obj read(BufferedReader reader) throws IOException {
        var vertices = new FloatArrayList();
        var textures = new FloatArrayList();
        var normals = new FloatArrayList();

        var faces = new ArrayList<Face>();

        String line;
        while ((line = reader.readLine()) != null) {
            var tokens = line.split("\\s+");
            switch (tokens[0]) {
                case "v" -> {
                    vertices.add(Float.parseFloat(tokens[1]));
                    vertices.add(Float.parseFloat(tokens[2]));
                    vertices.add(Float.parseFloat(tokens[3]));

                    var w = tokens.length == 5 ? Float.parseFloat(tokens[4]) : 1.0F;
                    vertices.add(w);
                }
                case "vt" -> {
                    textures.add(Float.parseFloat(tokens[1]));
                    textures.add(Float.parseFloat(tokens[2]));
                }
                case "vn" -> {
                    normals.add(Float.parseFloat(tokens[1]));
                    normals.add(Float.parseFloat(tokens[2]));
                    normals.add(Float.parseFloat(tokens[3]));
                }
                case "f" -> {
                    var length = tokens.length - 1;

                    var list = new ArrayList<Object2IntMap<Attribute>>(length);
                    for (var i = 0; i < length; i++) {
                        list.add(createMap(tokens[i + 1]));
                    }
                    faces.add(new Face(Collections.unmodifiableList(list)));
                }
            }
        }

        return new ImmutableObj(
                FloatLists.unmodifiable(vertices),
                FloatLists.unmodifiable(textures),
                FloatLists.unmodifiable(normals),
                faces
        );
    }

    private Object2IntMap<Attribute> createMap(String token) {
        var map = new Object2IntArrayMap<Attribute>();

        String[] split;
        if (token.contains("//")) {
            split = token.split("//", 2);
            map.put(Attribute.POSITION, Integer.parseInt(split[0]));
            map.put(Attribute.NORMAL, Integer.parseInt(split[1]));
        } else {
            split = token.split("/", 3);
            map.put(Attribute.POSITION, Integer.parseInt(split[0]));
            map.put(Attribute.TEXTURE, Integer.parseInt(split[1]));
            map.put(Attribute.NORMAL, Integer.parseInt(split[2]));
        }

        return Object2IntMaps.unmodifiable(map);
    }
}
