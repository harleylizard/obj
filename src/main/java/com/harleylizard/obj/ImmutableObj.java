package com.harleylizard.obj;

import it.unimi.dsi.fastutil.floats.FloatList;
import lombok.Data;

import java.util.List;

@Data
public final class ImmutableObj implements Obj {
    private final FloatList vertices;
    private final FloatList textures;
    private final FloatList normals;
    private final List<Face> faces;
}
