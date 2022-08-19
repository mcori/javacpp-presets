// Targeted by JavaCPP version 1.5.8-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.bullet.BulletCollision;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;
import org.bytedeco.bullet.LinearMath.*;
import static org.bytedeco.bullet.global.LinearMath.*;

import static org.bytedeco.bullet.global.BulletCollision.*;

@Name("btAlignedObjectArray<btCell32>") @NoOffset @Properties(inherit = org.bytedeco.bullet.presets.BulletCollision.class)
public class btCell32Array extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public btCell32Array(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public btCell32Array(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public btCell32Array position(long position) {
        return (btCell32Array)super.position(position);
    }
    @Override public btCell32Array getPointer(long i) {
        return new btCell32Array((Pointer)this).offsetAddress(i);
    }

	public native @ByRef @Name("operator =") btCell32Array put(@Const @ByRef btCell32Array other);
	public btCell32Array() { super((Pointer)null); allocate(); }
	private native void allocate();

	/**Generally it is best to avoid using the copy constructor of an btAlignedObjectArray, and use a (const) reference to the array instead. */
	public btCell32Array(@Const @ByRef btCell32Array otherArray) { super((Pointer)null); allocate(otherArray); }
	private native void allocate(@Const @ByRef btCell32Array otherArray);

	/** return the number of elements in the array */
	public native int size();

	public native @ByRef btCell32 at(int n);

	public native @ByRef @Name("operator []") btCell32 get(int n);

	/**clear the array, deallocated memory. Generally it is better to use array.resize(0), to reduce performance overhead of run-time memory (de)allocations. */
	public native void clear();

	public native void pop_back();

	/**resize changes the number of elements in the array. If the new size is larger, the new elements will be constructed using the optional second argument.
	 * when the new number of elements is smaller, the destructor will be called, but memory will not be freed, to reduce performance overhead of run-time memory (de)allocations. */
	public native void resizeNoInitialize(int newsize);

	public native void resize(int newsize, @Const @ByRef(nullValue = "btCell32()") btCell32 fillData);
	public native void resize(int newsize);
	public native @ByRef btCell32 expandNonInitializing();

	public native @ByRef btCell32 expand(@Const @ByRef(nullValue = "btCell32()") btCell32 fillValue);
	public native @ByRef btCell32 expand();

	public native void push_back(@Const @ByRef btCell32 _Val);

	/** return the pre-allocated (reserved) elements, this is at least as large as the total number of elements,see size() and reserve() */
	public native @Name("capacity") int _capacity();

	public native void reserve(int _Count);

	/**heap sort from http://www.csse.monash.edu.au/~lloyd/tildeAlgDS/Sort/Heap/ */ /*downHeap*/

	public native void swap(int index0, int index1);

	/**non-recursive binary search, assumes sorted array */
	

	

	// If the key is not in the array, return -1 instead of 0,
	// since 0 also means the first element in the array.
	

	public native void removeAtIndex(int index);
	

	//PCK: whole function
	public native void initializeFromBuffer(Pointer buffer, int size, int _capacity);

	public native void copyFromArray(@Const @ByRef btCell32Array otherArray);
}