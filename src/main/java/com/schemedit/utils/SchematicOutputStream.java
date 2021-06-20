package com.schemedit.utils;

import me.nullicorn.nedit.NBTOutputStream;
import me.nullicorn.nedit.type.NBTCompound;
import me.nullicorn.nedit.type.TagType;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/** Output stream that is specifc for schematics, overrides writeFully to fix a issue with naming */
public class SchematicOutputStream extends NBTOutputStream {
    public SchematicOutputStream(OutputStream out, boolean compress) throws IOException {
        super(out, compress);
    }

    @Override
    public void writeFully(NBTCompound compound) throws IOException {
        if (compound == null) {
            writeTagType(TagType.END);
        } else {
            writeTagType(TagType.COMPOUND);
            writeString("Schematic");
            writeCompound(compound);

            if (out instanceof GZIPOutputStream) {
                ((GZIPOutputStream) out).finish();
            }
        }
    }
}
