// Targeted by JavaCPP version 1.5.9: DO NOT EDIT THIS FILE

package org.bytedeco.tensorrt.nvinfer;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;
import org.bytedeco.cuda.cudart.*;
import static org.bytedeco.cuda.global.cudart.*;
import org.bytedeco.cuda.cublas.*;
import static org.bytedeco.cuda.global.cublas.*;
import org.bytedeco.cuda.cudnn.*;
import static org.bytedeco.cuda.global.cudnn.*;
import org.bytedeco.cuda.nvrtc.*;
import static org.bytedeco.cuda.global.nvrtc.*;

import static org.bytedeco.tensorrt.global.nvinfer.*;
 // class IScatterLayer

/**
 *  \class IOneHotLayer
 * 
 *  \brief A OneHot layer in a network definition.
 * 
 *  The OneHot layer has three input tensors: Indices, Values, and Depth, one output tensor:
 *  Output, and an axis attribute.
 *  * Indices is an Int32 tensor that determines which locations in Output to set as on_value.
 *  * Values is a two-element (rank=1) tensor that consists of [off_value, on_value]
 *  * Depth is an Int32 shape tensor of rank 0, which contains the depth (number of classes) of the one-hot encoding.
 *    The depth tensor must be a build-time constant, and its value should be positive.
 *  * Output is a tensor with rank = rank(indices)+1, where the added dimension contains the one-hot encoding.
 *    The data types of Output is equal to the Values data type.
 *  * Axis is a scaler specifying to which dimension of the output one-hot encoding is added.
 *    Axis defaults to -1, that is the new dimension in the output is its final dimension.
 *    Valid range for axis is -rank(indices)-1 <= axis <= rank(indices).
 * 
 *  The output is computed by copying off_values to all output elements, then setting on_value on the indices
 *  specified by the indices tensor.
 *  when axis = 0:
 *  output[indices[i, j, k], i, j, k] = on_value for all i, j, k and off_value otherwise.
 * 
 *  when axis = -1:
 *  output[i, j, k, indices[i, j, k]] = on_value for all i, j, k and off_value otherwise.
 * 
 *  \warning Do not inherit from this class, as doing so will break forward-compatibility of the API and ABI.
 *  */
@Namespace("nvinfer1") @NoOffset @Properties(inherit = org.bytedeco.tensorrt.presets.nvinfer.class)
public class IOneHotLayer extends ILayer {
    static { Loader.load(); }
    /** Default native constructor. */
    public IOneHotLayer() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public IOneHotLayer(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public IOneHotLayer(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public IOneHotLayer position(long position) {
        return (IOneHotLayer)super.position(position);
    }
    @Override public IOneHotLayer getPointer(long i) {
        return new IOneHotLayer((Pointer)this).offsetAddress(i);
    }

    /**
     *  \brief Set the axis parameter.
     * 
     *  @see IOneHotLayer
     *  */
    
    
    //!
    //!
    public native @NoException(true) void setAxis(int axis);

    /**
     *  \brief Get the value of the axis parameter.
     *  */
    public native @NoException(true) int getAxis();
}