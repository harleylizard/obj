package com.harleylizard.obj;

import it.unimi.dsi.fastutil.floats.FloatList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.List;

public sealed interface Obj permits ImmutableObj {

    FloatList parameterise(List<Attribute> format);

    IntList triangulate();
}
