// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.arrow;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.arrow.global.arrow.*;


@Namespace("arrow") @Properties(inherit = org.bytedeco.arrow.presets.arrow.class)
public class Compression extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public Compression() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public Compression(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public Compression(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public Compression position(long position) {
        return (Compression)super.position(position);
    }

  /** \brief Compression algorithm */
  public enum type { UNCOMPRESSED(0), SNAPPY(1), GZIP(2), BROTLI(3), ZSTD(4), LZ4(5), LZO(6), BZ2(7);

      public final int value;
      private type(int v) { this.value = v; }
      private type(type e) { this.value = e.value; }
      public type intern() { for (type e : values()) if (e.value == value) return e; return this; }
      @Override public String toString() { return intern().name(); }
  }
}