// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pad_msg.proto

package if_proto_common;

public final class PadMsg {
  private PadMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code if_proto_common.DrivingAction}
   */
  public enum DrivingAction
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>STOP = 0;</code>
     */
    STOP(0),
    /**
     * <code>START = 1;</code>
     */
    START(1),
    /**
     * <code>RESET = 2;</code>
     */
    RESET(2),
    ;

    /**
     * <code>STOP = 0;</code>
     */
    public static final int STOP_VALUE = 0;
    /**
     * <code>START = 1;</code>
     */
    public static final int START_VALUE = 1;
    /**
     * <code>RESET = 2;</code>
     */
    public static final int RESET_VALUE = 2;


    public final int getNumber() {
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static DrivingAction valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static DrivingAction forNumber(int value) {
      switch (value) {
        case 0: return STOP;
        case 1: return START;
        case 2: return RESET;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<DrivingAction>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        DrivingAction> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<DrivingAction>() {
            public DrivingAction findValueByNumber(int number) {
              return DrivingAction.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return if_proto_common.PadMsg.getDescriptor().getEnumTypes().get(0);
    }

    private static final DrivingAction[] VALUES = values();

    public static DrivingAction valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private DrivingAction(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:if_proto_common.DrivingAction)
  }

  public interface PadMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:if_proto_common.PadMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * control mode, set mode according to low level defination
     * </pre>
     *
     * <code>optional .if_proto_common.Header header = 1;</code>
     * @return Whether the header field is set.
     */
    boolean hasHeader();
    /**
     * <pre>
     * control mode, set mode according to low level defination
     * </pre>
     *
     * <code>optional .if_proto_common.Header header = 1;</code>
     * @return The header.
     */
    if_proto_common.BxtCommonHeader.Header getHeader();
    /**
     * <pre>
     * control mode, set mode according to low level defination
     * </pre>
     *
     * <code>optional .if_proto_common.Header header = 1;</code>
     */
    if_proto_common.BxtCommonHeader.HeaderOrBuilder getHeaderOrBuilder();

    /**
     * <pre>
     * send driving mode to drive
     * </pre>
     *
     * <code>optional .if_proto_common.Chassis.DrivingMode driving_mode = 2;</code>
     * @return Whether the drivingMode field is set.
     */
    boolean hasDrivingMode();
    /**
     * <pre>
     * send driving mode to drive
     * </pre>
     *
     * <code>optional .if_proto_common.Chassis.DrivingMode driving_mode = 2;</code>
     * @return The drivingMode.
     */
    if_proto_common.ChassisOuterClass.Chassis.DrivingMode getDrivingMode();

    /**
     * <pre>
     * action in the driving_mode
     * </pre>
     *
     * <code>optional .if_proto_common.DrivingAction action = 3;</code>
     * @return Whether the action field is set.
     */
    boolean hasAction();
    /**
     * <pre>
     * action in the driving_mode
     * </pre>
     *
     * <code>optional .if_proto_common.DrivingAction action = 3;</code>
     * @return The action.
     */
    if_proto_common.PadMsg.DrivingAction getAction();
  }
  /**
   * Protobuf type {@code if_proto_common.PadMessage}
   */
  public static final class PadMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:if_proto_common.PadMessage)
      PadMessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use PadMessage.newBuilder() to construct.
    private PadMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private PadMessage() {
      drivingMode_ = 0;
      action_ = 0;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new PadMessage();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private PadMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              if_proto_common.BxtCommonHeader.Header.Builder subBuilder = null;
              if (((bitField0_ & 0x00000001) != 0)) {
                subBuilder = header_.toBuilder();
              }
              header_ = input.readMessage(if_proto_common.BxtCommonHeader.Header.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(header_);
                header_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000001;
              break;
            }
            case 16: {
              int rawValue = input.readEnum();
                @SuppressWarnings("deprecation")
              if_proto_common.ChassisOuterClass.Chassis.DrivingMode value = if_proto_common.ChassisOuterClass.Chassis.DrivingMode.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(2, rawValue);
              } else {
                bitField0_ |= 0x00000002;
                drivingMode_ = rawValue;
              }
              break;
            }
            case 24: {
              int rawValue = input.readEnum();
                @SuppressWarnings("deprecation")
              if_proto_common.PadMsg.DrivingAction value = if_proto_common.PadMsg.DrivingAction.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(3, rawValue);
              } else {
                bitField0_ |= 0x00000004;
                action_ = rawValue;
              }
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return if_proto_common.PadMsg.internal_static_if_proto_common_PadMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return if_proto_common.PadMsg.internal_static_if_proto_common_PadMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              if_proto_common.PadMsg.PadMessage.class, if_proto_common.PadMsg.PadMessage.Builder.class);
    }

    private int bitField0_;
    public static final int HEADER_FIELD_NUMBER = 1;
    private if_proto_common.BxtCommonHeader.Header header_;
    /**
     * <pre>
     * control mode, set mode according to low level defination
     * </pre>
     *
     * <code>optional .if_proto_common.Header header = 1;</code>
     * @return Whether the header field is set.
     */
    @java.lang.Override
    public boolean hasHeader() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <pre>
     * control mode, set mode according to low level defination
     * </pre>
     *
     * <code>optional .if_proto_common.Header header = 1;</code>
     * @return The header.
     */
    @java.lang.Override
    public if_proto_common.BxtCommonHeader.Header getHeader() {
      return header_ == null ? if_proto_common.BxtCommonHeader.Header.getDefaultInstance() : header_;
    }
    /**
     * <pre>
     * control mode, set mode according to low level defination
     * </pre>
     *
     * <code>optional .if_proto_common.Header header = 1;</code>
     */
    @java.lang.Override
    public if_proto_common.BxtCommonHeader.HeaderOrBuilder getHeaderOrBuilder() {
      return header_ == null ? if_proto_common.BxtCommonHeader.Header.getDefaultInstance() : header_;
    }

    public static final int DRIVING_MODE_FIELD_NUMBER = 2;
    private int drivingMode_;
    /**
     * <pre>
     * send driving mode to drive
     * </pre>
     *
     * <code>optional .if_proto_common.Chassis.DrivingMode driving_mode = 2;</code>
     * @return Whether the drivingMode field is set.
     */
    @java.lang.Override public boolean hasDrivingMode() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <pre>
     * send driving mode to drive
     * </pre>
     *
     * <code>optional .if_proto_common.Chassis.DrivingMode driving_mode = 2;</code>
     * @return The drivingMode.
     */
    @java.lang.Override public if_proto_common.ChassisOuterClass.Chassis.DrivingMode getDrivingMode() {
      @SuppressWarnings("deprecation")
      if_proto_common.ChassisOuterClass.Chassis.DrivingMode result = if_proto_common.ChassisOuterClass.Chassis.DrivingMode.valueOf(drivingMode_);
      return result == null ? if_proto_common.ChassisOuterClass.Chassis.DrivingMode.COMPLETE_MANUAL : result;
    }

    public static final int ACTION_FIELD_NUMBER = 3;
    private int action_;
    /**
     * <pre>
     * action in the driving_mode
     * </pre>
     *
     * <code>optional .if_proto_common.DrivingAction action = 3;</code>
     * @return Whether the action field is set.
     */
    @java.lang.Override public boolean hasAction() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <pre>
     * action in the driving_mode
     * </pre>
     *
     * <code>optional .if_proto_common.DrivingAction action = 3;</code>
     * @return The action.
     */
    @java.lang.Override public if_proto_common.PadMsg.DrivingAction getAction() {
      @SuppressWarnings("deprecation")
      if_proto_common.PadMsg.DrivingAction result = if_proto_common.PadMsg.DrivingAction.valueOf(action_);
      return result == null ? if_proto_common.PadMsg.DrivingAction.STOP : result;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) != 0)) {
        output.writeMessage(1, getHeader());
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        output.writeEnum(2, drivingMode_);
      }
      if (((bitField0_ & 0x00000004) != 0)) {
        output.writeEnum(3, action_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getHeader());
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, drivingMode_);
      }
      if (((bitField0_ & 0x00000004) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(3, action_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof if_proto_common.PadMsg.PadMessage)) {
        return super.equals(obj);
      }
      if_proto_common.PadMsg.PadMessage other = (if_proto_common.PadMsg.PadMessage) obj;

      if (hasHeader() != other.hasHeader()) return false;
      if (hasHeader()) {
        if (!getHeader()
            .equals(other.getHeader())) return false;
      }
      if (hasDrivingMode() != other.hasDrivingMode()) return false;
      if (hasDrivingMode()) {
        if (drivingMode_ != other.drivingMode_) return false;
      }
      if (hasAction() != other.hasAction()) return false;
      if (hasAction()) {
        if (action_ != other.action_) return false;
      }
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasHeader()) {
        hash = (37 * hash) + HEADER_FIELD_NUMBER;
        hash = (53 * hash) + getHeader().hashCode();
      }
      if (hasDrivingMode()) {
        hash = (37 * hash) + DRIVING_MODE_FIELD_NUMBER;
        hash = (53 * hash) + drivingMode_;
      }
      if (hasAction()) {
        hash = (37 * hash) + ACTION_FIELD_NUMBER;
        hash = (53 * hash) + action_;
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static if_proto_common.PadMsg.PadMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static if_proto_common.PadMsg.PadMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static if_proto_common.PadMsg.PadMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static if_proto_common.PadMsg.PadMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static if_proto_common.PadMsg.PadMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static if_proto_common.PadMsg.PadMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static if_proto_common.PadMsg.PadMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static if_proto_common.PadMsg.PadMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static if_proto_common.PadMsg.PadMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static if_proto_common.PadMsg.PadMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static if_proto_common.PadMsg.PadMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static if_proto_common.PadMsg.PadMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(if_proto_common.PadMsg.PadMessage prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code if_proto_common.PadMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:if_proto_common.PadMessage)
        if_proto_common.PadMsg.PadMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return if_proto_common.PadMsg.internal_static_if_proto_common_PadMessage_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return if_proto_common.PadMsg.internal_static_if_proto_common_PadMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                if_proto_common.PadMsg.PadMessage.class, if_proto_common.PadMsg.PadMessage.Builder.class);
      }

      // Construct using if_proto_common.PadMsg.PadMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
          getHeaderFieldBuilder();
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        if (headerBuilder_ == null) {
          header_ = null;
        } else {
          headerBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        drivingMode_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        action_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return if_proto_common.PadMsg.internal_static_if_proto_common_PadMessage_descriptor;
      }

      @java.lang.Override
      public if_proto_common.PadMsg.PadMessage getDefaultInstanceForType() {
        return if_proto_common.PadMsg.PadMessage.getDefaultInstance();
      }

      @java.lang.Override
      public if_proto_common.PadMsg.PadMessage build() {
        if_proto_common.PadMsg.PadMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public if_proto_common.PadMsg.PadMessage buildPartial() {
        if_proto_common.PadMsg.PadMessage result = new if_proto_common.PadMsg.PadMessage(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          if (headerBuilder_ == null) {
            result.header_ = header_;
          } else {
            result.header_ = headerBuilder_.build();
          }
          to_bitField0_ |= 0x00000001;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          to_bitField0_ |= 0x00000002;
        }
        result.drivingMode_ = drivingMode_;
        if (((from_bitField0_ & 0x00000004) != 0)) {
          to_bitField0_ |= 0x00000004;
        }
        result.action_ = action_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof if_proto_common.PadMsg.PadMessage) {
          return mergeFrom((if_proto_common.PadMsg.PadMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(if_proto_common.PadMsg.PadMessage other) {
        if (other == if_proto_common.PadMsg.PadMessage.getDefaultInstance()) return this;
        if (other.hasHeader()) {
          mergeHeader(other.getHeader());
        }
        if (other.hasDrivingMode()) {
          setDrivingMode(other.getDrivingMode());
        }
        if (other.hasAction()) {
          setAction(other.getAction());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if_proto_common.PadMsg.PadMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (if_proto_common.PadMsg.PadMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private if_proto_common.BxtCommonHeader.Header header_;
      private com.google.protobuf.SingleFieldBuilderV3<
          if_proto_common.BxtCommonHeader.Header, if_proto_common.BxtCommonHeader.Header.Builder, if_proto_common.BxtCommonHeader.HeaderOrBuilder> headerBuilder_;
      /**
       * <pre>
       * control mode, set mode according to low level defination
       * </pre>
       *
       * <code>optional .if_proto_common.Header header = 1;</code>
       * @return Whether the header field is set.
       */
      public boolean hasHeader() {
        return ((bitField0_ & 0x00000001) != 0);
      }
      /**
       * <pre>
       * control mode, set mode according to low level defination
       * </pre>
       *
       * <code>optional .if_proto_common.Header header = 1;</code>
       * @return The header.
       */
      public if_proto_common.BxtCommonHeader.Header getHeader() {
        if (headerBuilder_ == null) {
          return header_ == null ? if_proto_common.BxtCommonHeader.Header.getDefaultInstance() : header_;
        } else {
          return headerBuilder_.getMessage();
        }
      }
      /**
       * <pre>
       * control mode, set mode according to low level defination
       * </pre>
       *
       * <code>optional .if_proto_common.Header header = 1;</code>
       */
      public Builder setHeader(if_proto_common.BxtCommonHeader.Header value) {
        if (headerBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          header_ = value;
          onChanged();
        } else {
          headerBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <pre>
       * control mode, set mode according to low level defination
       * </pre>
       *
       * <code>optional .if_proto_common.Header header = 1;</code>
       */
      public Builder setHeader(
          if_proto_common.BxtCommonHeader.Header.Builder builderForValue) {
        if (headerBuilder_ == null) {
          header_ = builderForValue.build();
          onChanged();
        } else {
          headerBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <pre>
       * control mode, set mode according to low level defination
       * </pre>
       *
       * <code>optional .if_proto_common.Header header = 1;</code>
       */
      public Builder mergeHeader(if_proto_common.BxtCommonHeader.Header value) {
        if (headerBuilder_ == null) {
          if (((bitField0_ & 0x00000001) != 0) &&
              header_ != null &&
              header_ != if_proto_common.BxtCommonHeader.Header.getDefaultInstance()) {
            header_ =
              if_proto_common.BxtCommonHeader.Header.newBuilder(header_).mergeFrom(value).buildPartial();
          } else {
            header_ = value;
          }
          onChanged();
        } else {
          headerBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <pre>
       * control mode, set mode according to low level defination
       * </pre>
       *
       * <code>optional .if_proto_common.Header header = 1;</code>
       */
      public Builder clearHeader() {
        if (headerBuilder_ == null) {
          header_ = null;
          onChanged();
        } else {
          headerBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      /**
       * <pre>
       * control mode, set mode according to low level defination
       * </pre>
       *
       * <code>optional .if_proto_common.Header header = 1;</code>
       */
      public if_proto_common.BxtCommonHeader.Header.Builder getHeaderBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getHeaderFieldBuilder().getBuilder();
      }
      /**
       * <pre>
       * control mode, set mode according to low level defination
       * </pre>
       *
       * <code>optional .if_proto_common.Header header = 1;</code>
       */
      public if_proto_common.BxtCommonHeader.HeaderOrBuilder getHeaderOrBuilder() {
        if (headerBuilder_ != null) {
          return headerBuilder_.getMessageOrBuilder();
        } else {
          return header_ == null ?
              if_proto_common.BxtCommonHeader.Header.getDefaultInstance() : header_;
        }
      }
      /**
       * <pre>
       * control mode, set mode according to low level defination
       * </pre>
       *
       * <code>optional .if_proto_common.Header header = 1;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          if_proto_common.BxtCommonHeader.Header, if_proto_common.BxtCommonHeader.Header.Builder, if_proto_common.BxtCommonHeader.HeaderOrBuilder> 
          getHeaderFieldBuilder() {
        if (headerBuilder_ == null) {
          headerBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              if_proto_common.BxtCommonHeader.Header, if_proto_common.BxtCommonHeader.Header.Builder, if_proto_common.BxtCommonHeader.HeaderOrBuilder>(
                  getHeader(),
                  getParentForChildren(),
                  isClean());
          header_ = null;
        }
        return headerBuilder_;
      }

      private int drivingMode_ = 0;
      /**
       * <pre>
       * send driving mode to drive
       * </pre>
       *
       * <code>optional .if_proto_common.Chassis.DrivingMode driving_mode = 2;</code>
       * @return Whether the drivingMode field is set.
       */
      @java.lang.Override public boolean hasDrivingMode() {
        return ((bitField0_ & 0x00000002) != 0);
      }
      /**
       * <pre>
       * send driving mode to drive
       * </pre>
       *
       * <code>optional .if_proto_common.Chassis.DrivingMode driving_mode = 2;</code>
       * @return The drivingMode.
       */
      @java.lang.Override
      public if_proto_common.ChassisOuterClass.Chassis.DrivingMode getDrivingMode() {
        @SuppressWarnings("deprecation")
        if_proto_common.ChassisOuterClass.Chassis.DrivingMode result = if_proto_common.ChassisOuterClass.Chassis.DrivingMode.valueOf(drivingMode_);
        return result == null ? if_proto_common.ChassisOuterClass.Chassis.DrivingMode.COMPLETE_MANUAL : result;
      }
      /**
       * <pre>
       * send driving mode to drive
       * </pre>
       *
       * <code>optional .if_proto_common.Chassis.DrivingMode driving_mode = 2;</code>
       * @param value The drivingMode to set.
       * @return This builder for chaining.
       */
      public Builder setDrivingMode(if_proto_common.ChassisOuterClass.Chassis.DrivingMode value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000002;
        drivingMode_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * send driving mode to drive
       * </pre>
       *
       * <code>optional .if_proto_common.Chassis.DrivingMode driving_mode = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearDrivingMode() {
        bitField0_ = (bitField0_ & ~0x00000002);
        drivingMode_ = 0;
        onChanged();
        return this;
      }

      private int action_ = 0;
      /**
       * <pre>
       * action in the driving_mode
       * </pre>
       *
       * <code>optional .if_proto_common.DrivingAction action = 3;</code>
       * @return Whether the action field is set.
       */
      @java.lang.Override public boolean hasAction() {
        return ((bitField0_ & 0x00000004) != 0);
      }
      /**
       * <pre>
       * action in the driving_mode
       * </pre>
       *
       * <code>optional .if_proto_common.DrivingAction action = 3;</code>
       * @return The action.
       */
      @java.lang.Override
      public if_proto_common.PadMsg.DrivingAction getAction() {
        @SuppressWarnings("deprecation")
        if_proto_common.PadMsg.DrivingAction result = if_proto_common.PadMsg.DrivingAction.valueOf(action_);
        return result == null ? if_proto_common.PadMsg.DrivingAction.STOP : result;
      }
      /**
       * <pre>
       * action in the driving_mode
       * </pre>
       *
       * <code>optional .if_proto_common.DrivingAction action = 3;</code>
       * @param value The action to set.
       * @return This builder for chaining.
       */
      public Builder setAction(if_proto_common.PadMsg.DrivingAction value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000004;
        action_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * action in the driving_mode
       * </pre>
       *
       * <code>optional .if_proto_common.DrivingAction action = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearAction() {
        bitField0_ = (bitField0_ & ~0x00000004);
        action_ = 0;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:if_proto_common.PadMessage)
    }

    // @@protoc_insertion_point(class_scope:if_proto_common.PadMessage)
    private static final if_proto_common.PadMsg.PadMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new if_proto_common.PadMsg.PadMessage();
    }

    public static if_proto_common.PadMsg.PadMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<PadMessage>
        PARSER = new com.google.protobuf.AbstractParser<PadMessage>() {
      @java.lang.Override
      public PadMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PadMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<PadMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<PadMessage> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public if_proto_common.PadMsg.PadMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_if_proto_common_PadMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_if_proto_common_PadMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rpad_msg.proto\022\017if_proto_common\032\027bxt.co" +
      "mmon.Header.proto\032\rchassis.proto\"\241\001\n\nPad" +
      "Message\022\'\n\006header\030\001 \001(\0132\027.if_proto_commo" +
      "n.Header\022:\n\014driving_mode\030\002 \001(\0162$.if_prot" +
      "o_common.Chassis.DrivingMode\022.\n\006action\030\003" +
      " \001(\0162\036.if_proto_common.DrivingAction*/\n\r" +
      "DrivingAction\022\010\n\004STOP\020\000\022\t\n\005START\020\001\022\t\n\005RE" +
      "SET\020\002"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          if_proto_common.BxtCommonHeader.getDescriptor(),
          if_proto_common.ChassisOuterClass.getDescriptor(),
        });
    internal_static_if_proto_common_PadMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_if_proto_common_PadMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_if_proto_common_PadMessage_descriptor,
        new java.lang.String[] { "Header", "DrivingMode", "Action", });
    if_proto_common.BxtCommonHeader.getDescriptor();
    if_proto_common.ChassisOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
