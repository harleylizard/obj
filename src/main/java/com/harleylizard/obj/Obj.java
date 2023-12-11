package com.harleylizard.obj;

import it.unimi.dsi.fastutil.floats.FloatList;

import java.util.List;

public sealed interface Obj permits ImmutableObj {

    FloatList getVertices();

    FloatList getTextures();

    FloatList getNormals();

    List<Face> getFaces();
}
