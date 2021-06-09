// Targeted by JavaCPP version 1.5.6-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.pytorch;

import org.bytedeco.pytorch.Allocator;
import org.bytedeco.pytorch.Function;
import org.bytedeco.pytorch.Module;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;
import static org.bytedeco.openblas.global.openblas_nolapack.*;
import static org.bytedeco.openblas.global.openblas.*;

import static org.bytedeco.pytorch.global.torch.*;


// [doxygen private]
// These methods are not actually private but we don't want to document them, so
// they are marked `@private`, which hides them on the doxygen documentation for
// this page.

/** IValue (Interpreter Value) is a tagged union over the types
 *  supported by the TorchScript interpreter. IValues contain their
 *  values as an {@code IValue::Payload}, which holds primitive types
 *  ({@code int64_t}, {@code bool}, {@code double}, {@code Device}) and {@code Tensor} as values,
 *  and all other types as a {@code c10::intrusive_ptr}. In order to
 *  optimize performance of the destructor and related operations by
 *  making the {@code Tensor} and {@code c10::intrusive_ptr} paths generate the
 *  same code, we represent a null {@code c10::intrusive_ptr} as
 *  {@code UndefinedTensorImpl::singleton()}, *not* {@code nullptr}.
 * 
 *  IValues are used as inputs to and outputs from the TorchScript interpreter.
 *  To retrieve the value contained within an IValue, use the {@code .toX()} methods,
 *  where {@code X} is the type you are trying to get. Note that neither the {@code .toX()}
 *  methods nor the templated {@code .to<T>} functions do any kind of casting, they
 *  only unwrap the contained value. For example:
 * 
 *  \rst
 *  .. code-block:: cpp
 * 
 *    // Make the IValue
 *    torch::IValue my_ivalue(26);
 *    std::cout << my_ivalue << "\n";
 * 
 *    // Unwrap the IValue
 *    int64_t my_int = my_ivalue.toInt()
 *    std::cout << my_int << "\n";
 * 
 *    // This will throw an error!
 *    // {@code my_ivalue} is tagged as an int and cannot be used as another type
 *    torch::Tensor my_tensor = my_ivalue.toTensor()
 *  \endrst */
@Namespace("c10") @NoOffset @Properties(inherit = org.bytedeco.pytorch.presets.torch.class)
public class IValue extends Pointer {
    static { Loader.load(); }

  public IValue(@Const @ByRef IValue rhs) { super((Pointer)null); allocate(rhs); }
  private native void allocate(@Const @ByRef IValue rhs);

  /** \private [doxygen private] */

  public native @ByRef @Name("operator =") @NoException IValue put(@ByVal IValue rhs);

  public native void dump();

  /**
   * Equality comparison. The semantics are the same as Python's {@code ==}:
   * 1. Numerical types are compared by value.
   * 2. Tensors compute element-wise equality, returning a BoolTensor (see:
   * {@code torch.eq()})
   * 3. Strings are compared by value.
   * 4. Sequence types (list, tuple) are compared lexicographically by
   *    comparing their elements. Different sequence types never compare equal.
   * 5. Mappings (dict) must have equal (key, value) pairs.
   * 6. If not listed above, the default behavior for is to test identity
   * equality (e.g. pointer equality).
   *
   * Why does this return an IValue instead of a bool? Because in PyTorch,
   * {@code tensor1 == tensor2} returns a {@code BoolTensor}, not a bool.
   *
   * NOTE: we (like Python) assume that identity equality implies value equality
   * for efficiency.
   * TODO: need to support customizing equality
   */
  public native @ByVal IValue equals(@Const @ByRef IValue rhs);
  /**
   * This implements the same semantics as {@code bool(lhs == rhs)} in Python. which
   * is the same as {@code equals()} except for Tensor types.
   */
  
  

  /**
   * Identity comparison. Checks if {@code this} is the same object as {@code rhs}. The
   * semantics are the same as Python's {@code is} operator.
   *
   * NOTE: Like in Python, this operation is poorly defined for primitive types
   * like numbers and strings. Prefer to use {@code ==} unless you really want to
   * check identity equality.
   */
  public native @Cast("bool") boolean is(@Const @ByRef IValue rhs);

   /**
   * Hashing for IValues. Returns an IValue-boxed int.
   *
   * Some notes:
   * - Like eager, Tensors are hashed by looking at the pointer. This is not
   *   strictly correct because two value-equal tensors with different tensor
   *   pointers will hash differently, but we choose to reproduce the eager
   *   semantics.
   * - Hashing is not defined on all built-in IValue types (e.g. list and
   *   dict), following Python. Calling {@code hash()} on these types will throw.
   */
  public native @ByVal IValue hash();
  // This is defined because `c10::hash` dispatches to a function of this
  // signature. See the member function `hash()`.
  public static native @Cast("size_t") long hash(@Const @ByRef IValue iv);

  /**
   * \private [doxygen private]
   * [container equality]
   * This is an equality implementation that assumes objects with the same
   * identity equal themselves, for efficiency reasons. We primarily have this
   * for consistency, because Python does the same thing. This actually
   * provokes user-visible changes in behavior due to quirks in torch:
   *      [tensor1] == [tensor1] -> True (because container equality will first
   * compare identity) [tensor1] == [tensor1_copy] -> RuntimeError: bool value
   * of Tensor is ambiguous
   */
  

  /** \private [doxygen private] */
  public native @Cast("bool") boolean isAliasOf(@Const @ByRef IValue rhs);

  /** \private [doxygen private] */
  public native @Cast("size_t") @NoException long use_count();

  /** \private [doxygen private] */
  public native @NoException void swap(@ByRef IValue rhs);

  // Accessors for subtypes are arranged together below
  // While some of these accessors could be generated through templates,
  // we prefer to write them manually for clarity

  public IValue(@ByVal Tensor t) { super((Pointer)null); allocate(t); }
  private native void allocate(@ByVal Tensor t);
  public native @Cast("bool") boolean isTensor();
  
  public native @ByRef Tensor toTensor();
  public native TensorImpl unsafeToTensorImpl();

  public IValue(@Cast({"", "c10::Storage&&"}) @StdMove Storage s) { super((Pointer)null); allocate(s); }
  private native void allocate(@Cast({"", "c10::Storage&&"}) @StdMove Storage s);
  public native @Cast("bool") boolean isStorage();
  
  public native @Cast({"", "c10::Storage&&"}) @StdMove Storage toStorage();
  public native @ByRef IValue toIValue();

  /** \private [doxygen private] */

  /** \private [doxygen private] */
  public native @Cast("bool") boolean isBlob();

  /** \private [doxygen private] */
  

  /** \private [doxygen private] */

  // Capsule. No new callsites of these APIs should
  // be introduced.
  public native @Cast("bool") boolean isCapsule();
  

  // Custom C++ classes
  public native @Cast("bool") boolean isCustomClass();
  

  // Tuple
  public native @Cast("bool") boolean isTuple();
  

  // Double
  public IValue(double d) { super((Pointer)null); allocate(d); }
  private native void allocate(double d);
  public native @Cast("bool") boolean isDouble();
  public native double toDouble();

  // ComplexDouble
  public native @Cast("bool") boolean isComplexDouble();

  // Future
  public native @Cast("bool") boolean isFuture();
  

  // RRef
  public native @Cast("bool") boolean isRRef();
  

  // Quantizer
  public native @Cast("bool") boolean isQuantizer();
  

  // Int
  public IValue(@Cast("int64_t") long i) { super((Pointer)null); allocate(i); }
  private native void allocate(@Cast("int64_t") long i);

  // allow you to pass literals (3, 4) without ambiguity
  public IValue(int i) { super((Pointer)null); allocate(i); }
  private native void allocate(int i);

  public native @Cast("bool") boolean isInt();

  public native @Cast("int64_t") long toInt();

  // Bool
  public IValue(@Cast("bool") boolean b) { super((Pointer)null); allocate(b); }
  private native void allocate(@Cast("bool") boolean b);
  public native @Cast("bool") boolean isBool();
  public native @Cast("bool") boolean toBool();

  // IntList
  public native @Cast("bool") boolean isIntList();
  
  public native @ByVal @Cast("std::vector<int64_t>*") LongVector toIntVector();

  // ConstantString
  public IValue(@StdString BytePointer v) { super((Pointer)null); allocate(v); }
  private native void allocate(@StdString BytePointer v);
  public IValue(@StdString String v) { super((Pointer)null); allocate(v); }
  private native void allocate(@StdString String v);
  public native @Cast("bool") boolean isString();
  
  public native @StdString BytePointer toStringRef();
  public native @ByVal @Cast("c10::optional<std::reference_wrapper<const std::string> >*") Pointer toOptionalStringRef();

  // DoubleList
  public native @Cast("bool") boolean isDoubleList();
  
  public native @ByVal @Cast("std::vector<double>*") DoubleVector toDoubleVector();

  // ComplexDoubleList
  public native @Cast("bool") boolean isComplexDoubleList();
  

  // BoolList
  public native @Cast("bool") boolean isBoolList();
  

  // TensorList
  public native @Cast("bool") boolean isTensorList();
  
  public native @StdMove TensorVector toTensorVector();

  // GenericList
  public native @Cast("bool") boolean isList();
  
  public native @ByVal IValueArrayRef toListRef();

  // Some template constructors of IValue calls another constructor recursively.
  // This SNIFAEs the called constructor exists.

  // GenericDict
  public IValue(@ByVal IValueIValueDict v) { super((Pointer)null); allocate(v); }
  private native void allocate(@ByVal IValueIValueDict v);
  public native @Cast("bool") boolean isGenericDict();
  
  public native @ByVal IValueIValueDict toGenericDict();
  public IValue(@ByVal @Cast("c10::nullopt_t*") Pointer arg0) { super((Pointer)null); allocate(arg0); }
  private native void allocate(@ByVal @Cast("c10::nullopt_t*") Pointer arg0);

  // ClassType
  public native @Cast("bool") boolean isObject();
  
  public native @Const @ByRef Object toObjectRef();

  
  public native @Cast("bool") boolean isModule();

  // PyObject
  public native @Cast("bool") boolean isPyObject();
  
  public native @Cast("PyObject*") Pointer toPyObject();

  // Enum
  public native @Cast("bool") boolean isEnum();
  

  // None
  public IValue() { super((Pointer)null); allocate(); }
  private native void allocate();
  public native @Cast("bool") boolean isNone();
  public native @StdString BytePointer toNone();

  public static native @ByVal IValue uninitialized();

  // Scalar, which gets encoded as either an Int, a Double or a ComplexDouble
  public IValue(@ByVal Scalar s) { super((Pointer)null); allocate(s); }
  private native void allocate(@ByVal Scalar s);

  public native @Cast("bool") boolean isScalar();

  public native @ByVal Scalar toScalar();

  // Device
  public IValue(@ByVal Device d) { super((Pointer)null); allocate(d); }
  private native void allocate(@ByVal Device d);
  public native @Cast("bool") boolean isDevice();
  public native @ByVal Device toDevice();

  //Stream
  public IValue(@ByVal Stream stream) { super((Pointer)null); allocate(stream); }
  private native void allocate(@ByVal Stream stream);
  
  public native @ByVal Stream toStream();
  public native @Cast("bool") boolean isStream();

  // ScalarType
  public IValue(ScalarType t) { super((Pointer)null); allocate(t); }
  private native void allocate(ScalarType t);
  public native ScalarType toScalarType();

  // Layout
  public IValue(Layout l) { super((Pointer)null); allocate(l); }
  private native void allocate(Layout l);
  public IValue(@Cast("c10::Layout") byte l) { super((Pointer)null); allocate(l); }
  private native void allocate(@Cast("c10::Layout") byte l);
  public native @ByVal Layout toLayout();

  // MemoryFormat
  public IValue(MemoryFormat m) { super((Pointer)null); allocate(m); }
  private native void allocate(MemoryFormat m);
  public native @ByVal MemoryFormat toMemoryFormat();

  // QScheme
  public IValue(@ByVal QScheme qscheme) { super((Pointer)null); allocate(qscheme); }
  private native void allocate(@ByVal QScheme qscheme);

  public native @ByVal QScheme toQScheme();

  // Dimname
  public IValue(@ByVal Dimname dimname) { super((Pointer)null); allocate(dimname); }
  private native void allocate(@ByVal Dimname dimname);

  public native @ByVal Dimname toDimname();

  // Generator
  public IValue(@ByVal Generator g) { super((Pointer)null); allocate(g); }
  private native void allocate(@ByVal Generator g);
  public native @Cast("bool") boolean isGenerator();
  
  public native @ByVal Generator toGenerator();

  // for debugging
  public native @StdString BytePointer tagKind();

  // generic v.to<at::Tensor>() implementations
  // that can be used in special functions like pop/push
  // that use template meta-programming.
  // prefer the directly named methods when you can,
  // since they are simpler to understand

  // Note: if you get linker errors saying one of these is missing,
  // change it to ... && = delete; and you will see better error messages for
  // why However, we cannot commit this because some compiler versions barf on
  // it.
  

  // ToOptional: convert a IValue to the Optional obj that accepts both T and
  // None

  /** \private [doxygen private]
   *  this is a shallow comparison of two IValues to test the object identity */
  public native @Cast("bool") boolean isSameIdentity(@Const @ByRef IValue rhs);

  // Computes the "official" string representation of an IValue. This produces a
  // TorchScript expression that can be used to recreate an IValue with the same
  // value (e.g. when we are printing constants in the serializer).
  //
  // Callers can use `customFormatter` to override how `repr()` prints out an
  // IValue. This is useful if you have some other environment where you can
  // look up values, and you want to print a reference to that environment (like
  // the serializer's constant table).
  //
  // repr() is not necessarily defined on all objects!
  public native @Cast("std::ostream*") @ByRef Pointer repr(
        @Cast("std::ostream*") @ByRef Pointer stream,
        @ByVal CustomFormatter customFormatter);

  // Computes an "informal" string representation of an IValue. This should be
  // used for debugging, or servicing `print()`-like functions.
  // This is different from `repr()` in that there is no expectation that we can
  // exactly reconstruct an IValue from the output; feel free to use a
  // concise/pretty form
  

  public native @Cast("bool") boolean isPtrType();

  /** \private [doxygen private] */
  public native @Const Pointer internalToPointer();

  public native @SharedPtr Type type();

  // Detect aliased tensors.
  public static class HashAliasedIValue extends Pointer {
      static { Loader.load(); }
      /** Default native constructor. */
      public HashAliasedIValue() { super((Pointer)null); allocate(); }
      /** Native array allocator. Access with {@link Pointer#position(long)}. */
      public HashAliasedIValue(long size) { super((Pointer)null); allocateArray(size); }
      /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
      public HashAliasedIValue(Pointer p) { super(p); }
      private native void allocate();
      private native void allocateArray(long size);
      @Override public HashAliasedIValue position(long position) {
          return (HashAliasedIValue)super.position(position);
      }
      @Override public HashAliasedIValue getPointer(long i) {
          return new HashAliasedIValue((Pointer)this).offsetAddress(i);
      }
  
    public native @Cast("size_t") @Name("operator ()") long apply(@Const @ByRef IValue val);
  }

  public static class CompAliasedIValues extends Pointer {
      static { Loader.load(); }
      /** Default native constructor. */
      public CompAliasedIValues() { super((Pointer)null); allocate(); }
      /** Native array allocator. Access with {@link Pointer#position(long)}. */
      public CompAliasedIValues(long size) { super((Pointer)null); allocateArray(size); }
      /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
      public CompAliasedIValues(Pointer p) { super(p); }
      private native void allocate();
      private native void allocateArray(long size);
      @Override public CompAliasedIValues position(long position) {
          return (CompAliasedIValues)super.position(position);
      }
      @Override public CompAliasedIValues getPointer(long i) {
          return new CompAliasedIValues((Pointer)this).offsetAddress(i);
      }
  
    public native @Cast("bool") @Name("operator ()") boolean apply(@Const @ByRef IValue lhs, @Const @ByRef IValue rhs);
  }

  // Chechs if this and rhs has a subvalues in common.
  // [t1,t2] and [t2, t3] returns true.
  public native @Cast("bool") boolean overlaps(@Const @ByRef IValue rhs);

  // Inserts all subvalues of this in subValues.
  public native void getSubValues(@Cast("c10::IValue::HashAliasedIValues*") @ByRef IValueSet subValues);

  // Apply visitor to every subvalue.
  // TODO: There are several places that recurse over IValue. This is fragile.
  // This visitor should be used to recurse over ivalues.
  public native void visit(@Const @ByRef IValueVisitor visitor);
  public native @ByVal IValue deepcopy();
  public native @ByVal IValue deepcopy(@Cast("c10::IValue::HashAliasedIValueMap*") @ByRef IValueIValueMap memo);
}