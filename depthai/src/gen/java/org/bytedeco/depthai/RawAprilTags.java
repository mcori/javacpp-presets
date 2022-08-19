// Targeted by JavaCPP version 1.5.8-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.depthai;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;
import static org.bytedeco.openblas.global.openblas_nolapack.*;
import static org.bytedeco.openblas.global.openblas.*;
import org.bytedeco.opencv.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_core.*;
import org.bytedeco.opencv.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

import static org.bytedeco.depthai.global.depthai.*;


/** RawAprilTags structure */
@Namespace("dai") @NoOffset @Properties(inherit = org.bytedeco.depthai.presets.depthai.class)
public class RawAprilTags extends RawBuffer {
    static { Loader.load(); }
    /** Default native constructor. */
    public RawAprilTags() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public RawAprilTags(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public RawAprilTags(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public RawAprilTags position(long position) {
        return (RawAprilTags)super.position(position);
    }
    @Override public RawAprilTags getPointer(long i) {
        return new RawAprilTags((Pointer)this).offsetAddress(i);
    }

    public native @StdVector AprilTag aprilTags(); public native RawAprilTags aprilTags(AprilTag setter);

    // Related to input ImgFrame
    public native @Cast("int64_t") long sequenceNum(); public native RawAprilTags sequenceNum(long setter);  // increments for each frame
    public native @ByRef Timestamp ts(); public native RawAprilTags ts(Timestamp setter);        // generation timestamp, synced to host time
    public native @ByRef Timestamp tsDevice(); public native RawAprilTags tsDevice(Timestamp setter);  // generation timestamp, direct device monotonic clock

    public native @Override void serialize(@Cast("std::uint8_t*") @StdVector BytePointer metadata, @ByRef @Cast("dai::DatatypeEnum*") IntPointer datatype);
    public native @Override void serialize(@Cast("std::uint8_t*") @StdVector ByteBuffer metadata, @ByRef @Cast("dai::DatatypeEnum*") IntBuffer datatype);
    public native @Override void serialize(@Cast("std::uint8_t*") @StdVector byte[] metadata, @ByRef @Cast("dai::DatatypeEnum*") int[] datatype);
}