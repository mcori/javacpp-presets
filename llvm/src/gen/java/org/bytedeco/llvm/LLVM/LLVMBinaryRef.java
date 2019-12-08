// Targeted by JavaCPP version 1.5.2: DO NOT EDIT THIS FILE

package org.bytedeco.llvm.LLVM;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.llvm.global.LLVM.*;


/**
 * @see llvm::object::Binary
 */
@Name("LLVMOpaqueBinary") @Opaque @Properties(inherit = org.bytedeco.llvm.presets.LLVM.class)
public class LLVMBinaryRef extends Pointer {
    /** Empty constructor. Calls {@code super((Pointer)null)}. */
    public LLVMBinaryRef() { super((Pointer)null); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public LLVMBinaryRef(Pointer p) { super(p); }
}