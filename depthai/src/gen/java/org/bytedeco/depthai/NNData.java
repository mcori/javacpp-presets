// Targeted by JavaCPP version 1.5.6-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.depthai;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;

import static org.bytedeco.depthai.global.depthai.*;


/**
 * NNData message. Carries tensors and their metadata
 */
@Namespace("dai") @NoOffset @Properties(inherit = org.bytedeco.depthai.presets.depthai.class)
public class NNData extends Buffer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public NNData(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public NNData(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public NNData position(long position) {
        return (NNData)super.position(position);
    }
    @Override public NNData getPointer(long i) {
        return new NNData((Pointer)this).offsetAddress(i);
    }

    /**
     * Construct NNData message.
     */
    public NNData() { super((Pointer)null); allocate(); }
    private native void allocate();
    public NNData(@SharedPtr RawNNData ptr) { super((Pointer)null); allocate(ptr); }
    private native void allocate(@SharedPtr RawNNData ptr);

    // Expose
    // uint8_t
    /**
     * Set a layer with datatype U8.
     * @param name Name of the layer
     * @param data Data to store
     */
    public native void setLayer(@StdString BytePointer name, @Cast("std::uint8_t*") @StdVector BytePointer data);
    public native void setLayer(@StdString String name, @Cast("std::uint8_t*") @StdVector ByteBuffer data);
    public native void setLayer(@StdString BytePointer name, @Cast("std::uint8_t*") @StdVector byte[] data);
    public native void setLayer(@StdString String name, @Cast("std::uint8_t*") @StdVector BytePointer data);
    public native void setLayer(@StdString BytePointer name, @Cast("std::uint8_t*") @StdVector ByteBuffer data);
    public native void setLayer(@StdString String name, @Cast("std::uint8_t*") @StdVector byte[] data);

    /**
     * Set a layer with datatype U8. Integers are casted to bytes.
     * @param name Name of the layer
     * @param data Data to store
     */
    public native void setLayer(@StdString BytePointer name, @Const @StdVector @ByRef IntPointer data);
    public native void setLayer(@StdString String name, @Const @StdVector @ByRef IntBuffer data);
    public native void setLayer(@StdString BytePointer name, @Const @StdVector @ByRef int[] data);
    public native void setLayer(@StdString String name, @Const @StdVector @ByRef IntPointer data);
    public native void setLayer(@StdString BytePointer name, @Const @StdVector @ByRef IntBuffer data);
    public native void setLayer(@StdString String name, @Const @StdVector @ByRef int[] data);

    // fp16
    /**
     * Set a layer with datatype FP16. Float values are converted to FP16.
     * @param name Name of the layer
     * @param data Data to store
     */
    public native void setLayer(@StdString BytePointer name, @StdVector FloatPointer data);
    public native void setLayer(@StdString String name, @StdVector FloatBuffer data);
    public native void setLayer(@StdString BytePointer name, @StdVector float[] data);
    public native void setLayer(@StdString String name, @StdVector FloatPointer data);
    public native void setLayer(@StdString BytePointer name, @StdVector FloatBuffer data);
    public native void setLayer(@StdString String name, @StdVector float[] data);

    /**
     * Set a layer with datatype FP16. Double values are converted to FP16.
     * @param name Name of the layer
     * @param data Data to store
     */
    public native void setLayer(@StdString BytePointer name, @StdVector DoublePointer data);
    public native void setLayer(@StdString String name, @StdVector DoubleBuffer data);
    public native void setLayer(@StdString BytePointer name, @StdVector double[] data);
    public native void setLayer(@StdString String name, @StdVector DoublePointer data);
    public native void setLayer(@StdString BytePointer name, @StdVector DoubleBuffer data);
    public native void setLayer(@StdString String name, @StdVector double[] data);

    // getters
    /**
     * @return Names of all layers added
     */
    public native @ByVal StringVector getAllLayerNames();

    /**
     * @return All layers and their information
     */
    public native @StdVector TensorInfo getAllLayers();

    /**
     * Retrieve layers tensor information
     * @param name Name of the layer
     * @param tensor [out] Outputs tensor infromation of that layer
     * @return True if layer exists, false otherwise
     */
    public native @Cast("bool") boolean getLayer(@StdString BytePointer name, @ByRef TensorInfo tensor);
    public native @Cast("bool") boolean getLayer(@StdString String name, @ByRef TensorInfo tensor);

    /**
     * Checks if given layer exists
     * @param name Name of the layer
     * @return True if layer exists, false otherwise
     */
    public native @Cast("bool") boolean hasLayer(@StdString BytePointer name);
    public native @Cast("bool") boolean hasLayer(@StdString String name);

    /**
     * Retrieve datatype of a layers tensor
     * @param name Name of the layer
     * @param datatype [out] Datatype of layers tensor
     * @return True if layer exists, false otherwise
     */
    public native @Cast("bool") boolean getLayerDatatype(@StdString BytePointer name, @ByRef @Cast("dai::TensorInfo::DataType*") IntPointer datatype);
    public native @Cast("bool") boolean getLayerDatatype(@StdString String name, @ByRef @Cast("dai::TensorInfo::DataType*") IntBuffer datatype);
    public native @Cast("bool") boolean getLayerDatatype(@StdString BytePointer name, @ByRef @Cast("dai::TensorInfo::DataType*") int[] datatype);
    public native @Cast("bool") boolean getLayerDatatype(@StdString String name, @ByRef @Cast("dai::TensorInfo::DataType*") IntPointer datatype);
    public native @Cast("bool") boolean getLayerDatatype(@StdString BytePointer name, @ByRef @Cast("dai::TensorInfo::DataType*") IntBuffer datatype);
    public native @Cast("bool") boolean getLayerDatatype(@StdString String name, @ByRef @Cast("dai::TensorInfo::DataType*") int[] datatype);

    // uint8
    /**
     * Convinience function to retrieve U8 data from layer
     * @param name Name of the layer
     * @return U8 binary data
     */
    public native @Cast("std::uint8_t*") @StdVector BytePointer getLayerUInt8(@StdString BytePointer name);
    public native @Cast("std::uint8_t*") @StdVector ByteBuffer getLayerUInt8(@StdString String name);

    // fp16
    /**
     * Convinience function to retrieve float values from layers FP16 tensor
     * @param name Name of the layer
     * @return Float data
     */
    public native @StdVector FloatPointer getLayerFp16(@StdString BytePointer name);
    public native @StdVector FloatBuffer getLayerFp16(@StdString String name);

    // int32
    /**
     * Convinience function to retrieve INT32 values from layers tensor
     * @param name Name of the layer
     * @return INT32 data
     */
    public native @Cast("std::int32_t*") @StdVector IntPointer getLayerInt32(@StdString BytePointer name);
    public native @Cast("std::int32_t*") @StdVector IntBuffer getLayerInt32(@StdString String name);

    // first layer
    /**
     * Convinience function to retrieve U8 data from first layer
     * @return U8 binary data
     */
    public native @Cast("std::uint8_t*") @StdVector BytePointer getFirstLayerUInt8();

    /**
     * Convinience function to retrieve float values from first layers FP16 tensor
     * @return Float data
     */
    public native @StdVector FloatPointer getFirstLayerFp16();

    /**
     * Convinience function to retrieve INT32 values from first layers tensor
     * @return INT32 data
     */
    public native @Cast("std::int32_t*") @StdVector IntPointer getFirstLayerInt32();
}