package com.harleylizard.obj;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class ObjConverter {

    public void accept(Obj obj, ObjConsumer consumer) {
        var vertices = obj.getVertices();
        var texture = obj.getTextures();
        var normals = obj.getNormals();

        var faces = obj.getFaces();
        for (var face : faces) {
            for (var map : face.list()) {

                var i = (map.getInt(Face.Type.VERTEX) - 1) * 4;
                var j = (map.getInt(Face.Type.TEXTURE) - 1) * 2;
                var k = (map.getInt(Face.Type.NORMAL) - 1) * 3;

                var emptyTexture = texture.isEmpty();
                var u = emptyTexture ? 0.0F : texture.getFloat(j);
                var v = emptyTexture ? 0.0F : texture.getFloat(j + 1);

                consumer.accept(
                        vertices.getFloat(i),
                        vertices.getFloat(i + 1),
                        vertices.getFloat(i + 2),
                        vertices.getFloat(i + 3),
                        u,
                        v,
                        normals.getFloat(k),
                        normals.getFloat(k + 1),
                        normals.getFloat(k + 2)
                );
            }
        }
    }

    @FunctionalInterface
    public interface ObjConsumer {

        void accept(float x, float y, float z, float w, float u, float v, float nx, float ny, float nz);
    }
}
