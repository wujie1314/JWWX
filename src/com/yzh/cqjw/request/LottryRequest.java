// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: LottryRequest.proto

package com.yzh.cqjw.request;

public final class LottryRequest {
  private LottryRequest() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface LottryRequestMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:LottryRequestMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string version = 1;</code>
     */
    java.lang.String getVersion();
    /**
     * <code>optional string version = 1;</code>
     */
    com.google.protobuf.ByteString
        getVersionBytes();

    /**
     * <code>optional string phone = 2;</code>
     */
    java.lang.String getPhone();
    /**
     * <code>optional string phone = 2;</code>
     */
    com.google.protobuf.ByteString
        getPhoneBytes();

    /**
     * <pre>
     *平台
     * </pre>
     *
     * <code>optional string platform = 3;</code>
     */
    java.lang.String getPlatform();
    /**
     * <pre>
     *平台
     * </pre>
     *
     * <code>optional string platform = 3;</code>
     */
    com.google.protobuf.ByteString
        getPlatformBytes();

    /**
     * <pre>
     *登錄成功返回的會話
     * </pre>
     *
     * <code>optional string session = 4;</code>
     */
    java.lang.String getSession();
    /**
     * <pre>
     *登錄成功返回的會話
     * </pre>
     *
     * <code>optional string session = 4;</code>
     */
    com.google.protobuf.ByteString
        getSessionBytes();
  }
  /**
   * Protobuf type {@code LottryRequestMessage}
   */
  public  static final class LottryRequestMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:LottryRequestMessage)
      LottryRequestMessageOrBuilder {
    // Use LottryRequestMessage.newBuilder() to construct.
    private LottryRequestMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private LottryRequestMessage() {
      version_ = "";
      phone_ = "";
      platform_ = "";
      session_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private LottryRequestMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              version_ = s;
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              phone_ = s;
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              platform_ = s;
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              session_ = s;
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
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.yzh.cqjw.request.LottryRequest.internal_static_LottryRequestMessage_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.yzh.cqjw.request.LottryRequest.internal_static_LottryRequestMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.yzh.cqjw.request.LottryRequest.LottryRequestMessage.class, com.yzh.cqjw.request.LottryRequest.LottryRequestMessage.Builder.class);
    }

    public static final int VERSION_FIELD_NUMBER = 1;
    private volatile java.lang.Object version_;
    /**
     * <code>optional string version = 1;</code>
     */
    public java.lang.String getVersion() {
      java.lang.Object ref = version_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        version_ = s;
        return s;
      }
    }
    /**
     * <code>optional string version = 1;</code>
     */
    public com.google.protobuf.ByteString
        getVersionBytes() {
      java.lang.Object ref = version_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        version_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PHONE_FIELD_NUMBER = 2;
    private volatile java.lang.Object phone_;
    /**
     * <code>optional string phone = 2;</code>
     */
    public java.lang.String getPhone() {
      java.lang.Object ref = phone_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        phone_ = s;
        return s;
      }
    }
    /**
     * <code>optional string phone = 2;</code>
     */
    public com.google.protobuf.ByteString
        getPhoneBytes() {
      java.lang.Object ref = phone_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        phone_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PLATFORM_FIELD_NUMBER = 3;
    private volatile java.lang.Object platform_;
    /**
     * <pre>
     *平台
     * </pre>
     *
     * <code>optional string platform = 3;</code>
     */
    public java.lang.String getPlatform() {
      java.lang.Object ref = platform_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        platform_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *平台
     * </pre>
     *
     * <code>optional string platform = 3;</code>
     */
    public com.google.protobuf.ByteString
        getPlatformBytes() {
      java.lang.Object ref = platform_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        platform_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int SESSION_FIELD_NUMBER = 4;
    private volatile java.lang.Object session_;
    /**
     * <pre>
     *登錄成功返回的會話
     * </pre>
     *
     * <code>optional string session = 4;</code>
     */
    public java.lang.String getSession() {
      java.lang.Object ref = session_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        session_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *登錄成功返回的會話
     * </pre>
     *
     * <code>optional string session = 4;</code>
     */
    public com.google.protobuf.ByteString
        getSessionBytes() {
      java.lang.Object ref = session_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        session_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getVersionBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, version_);
      }
      if (!getPhoneBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, phone_);
      }
      if (!getPlatformBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, platform_);
      }
      if (!getSessionBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, session_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getVersionBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, version_);
      }
      if (!getPhoneBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, phone_);
      }
      if (!getPlatformBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, platform_);
      }
      if (!getSessionBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, session_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.yzh.cqjw.request.LottryRequest.LottryRequestMessage)) {
        return super.equals(obj);
      }
      com.yzh.cqjw.request.LottryRequest.LottryRequestMessage other = (com.yzh.cqjw.request.LottryRequest.LottryRequestMessage) obj;

      boolean result = true;
      result = result && getVersion()
          .equals(other.getVersion());
      result = result && getPhone()
          .equals(other.getPhone());
      result = result && getPlatform()
          .equals(other.getPlatform());
      result = result && getSession()
          .equals(other.getSession());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + VERSION_FIELD_NUMBER;
      hash = (53 * hash) + getVersion().hashCode();
      hash = (37 * hash) + PHONE_FIELD_NUMBER;
      hash = (53 * hash) + getPhone().hashCode();
      hash = (37 * hash) + PLATFORM_FIELD_NUMBER;
      hash = (53 * hash) + getPlatform().hashCode();
      hash = (37 * hash) + SESSION_FIELD_NUMBER;
      hash = (53 * hash) + getSession().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.yzh.cqjw.request.LottryRequest.LottryRequestMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yzh.cqjw.request.LottryRequest.LottryRequestMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yzh.cqjw.request.LottryRequest.LottryRequestMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yzh.cqjw.request.LottryRequest.LottryRequestMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yzh.cqjw.request.LottryRequest.LottryRequestMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yzh.cqjw.request.LottryRequest.LottryRequestMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yzh.cqjw.request.LottryRequest.LottryRequestMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.yzh.cqjw.request.LottryRequest.LottryRequestMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yzh.cqjw.request.LottryRequest.LottryRequestMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yzh.cqjw.request.LottryRequest.LottryRequestMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.yzh.cqjw.request.LottryRequest.LottryRequestMessage prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
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
     * Protobuf type {@code LottryRequestMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:LottryRequestMessage)
        com.yzh.cqjw.request.LottryRequest.LottryRequestMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.yzh.cqjw.request.LottryRequest.internal_static_LottryRequestMessage_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.yzh.cqjw.request.LottryRequest.internal_static_LottryRequestMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.yzh.cqjw.request.LottryRequest.LottryRequestMessage.class, com.yzh.cqjw.request.LottryRequest.LottryRequestMessage.Builder.class);
      }

      // Construct using com.yzh.cqjw.request.LottryRequest.LottryRequestMessage.newBuilder()
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
        }
      }
      public Builder clear() {
        super.clear();
        version_ = "";

        phone_ = "";

        platform_ = "";

        session_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.yzh.cqjw.request.LottryRequest.internal_static_LottryRequestMessage_descriptor;
      }

      public com.yzh.cqjw.request.LottryRequest.LottryRequestMessage getDefaultInstanceForType() {
        return com.yzh.cqjw.request.LottryRequest.LottryRequestMessage.getDefaultInstance();
      }

      public com.yzh.cqjw.request.LottryRequest.LottryRequestMessage build() {
        com.yzh.cqjw.request.LottryRequest.LottryRequestMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.yzh.cqjw.request.LottryRequest.LottryRequestMessage buildPartial() {
        com.yzh.cqjw.request.LottryRequest.LottryRequestMessage result = new com.yzh.cqjw.request.LottryRequest.LottryRequestMessage(this);
        result.version_ = version_;
        result.phone_ = phone_;
        result.platform_ = platform_;
        result.session_ = session_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.yzh.cqjw.request.LottryRequest.LottryRequestMessage) {
          return mergeFrom((com.yzh.cqjw.request.LottryRequest.LottryRequestMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.yzh.cqjw.request.LottryRequest.LottryRequestMessage other) {
        if (other == com.yzh.cqjw.request.LottryRequest.LottryRequestMessage.getDefaultInstance()) return this;
        if (!other.getVersion().isEmpty()) {
          version_ = other.version_;
          onChanged();
        }
        if (!other.getPhone().isEmpty()) {
          phone_ = other.phone_;
          onChanged();
        }
        if (!other.getPlatform().isEmpty()) {
          platform_ = other.platform_;
          onChanged();
        }
        if (!other.getSession().isEmpty()) {
          session_ = other.session_;
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.yzh.cqjw.request.LottryRequest.LottryRequestMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.yzh.cqjw.request.LottryRequest.LottryRequestMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object version_ = "";
      /**
       * <code>optional string version = 1;</code>
       */
      public java.lang.String getVersion() {
        java.lang.Object ref = version_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          version_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string version = 1;</code>
       */
      public com.google.protobuf.ByteString
          getVersionBytes() {
        java.lang.Object ref = version_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          version_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string version = 1;</code>
       */
      public Builder setVersion(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        version_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string version = 1;</code>
       */
      public Builder clearVersion() {
        
        version_ = getDefaultInstance().getVersion();
        onChanged();
        return this;
      }
      /**
       * <code>optional string version = 1;</code>
       */
      public Builder setVersionBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        version_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object phone_ = "";
      /**
       * <code>optional string phone = 2;</code>
       */
      public java.lang.String getPhone() {
        java.lang.Object ref = phone_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          phone_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string phone = 2;</code>
       */
      public com.google.protobuf.ByteString
          getPhoneBytes() {
        java.lang.Object ref = phone_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          phone_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string phone = 2;</code>
       */
      public Builder setPhone(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        phone_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string phone = 2;</code>
       */
      public Builder clearPhone() {
        
        phone_ = getDefaultInstance().getPhone();
        onChanged();
        return this;
      }
      /**
       * <code>optional string phone = 2;</code>
       */
      public Builder setPhoneBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        phone_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object platform_ = "";
      /**
       * <pre>
       *平台
       * </pre>
       *
       * <code>optional string platform = 3;</code>
       */
      public java.lang.String getPlatform() {
        java.lang.Object ref = platform_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          platform_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *平台
       * </pre>
       *
       * <code>optional string platform = 3;</code>
       */
      public com.google.protobuf.ByteString
          getPlatformBytes() {
        java.lang.Object ref = platform_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          platform_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *平台
       * </pre>
       *
       * <code>optional string platform = 3;</code>
       */
      public Builder setPlatform(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        platform_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *平台
       * </pre>
       *
       * <code>optional string platform = 3;</code>
       */
      public Builder clearPlatform() {
        
        platform_ = getDefaultInstance().getPlatform();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *平台
       * </pre>
       *
       * <code>optional string platform = 3;</code>
       */
      public Builder setPlatformBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        platform_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object session_ = "";
      /**
       * <pre>
       *登錄成功返回的會話
       * </pre>
       *
       * <code>optional string session = 4;</code>
       */
      public java.lang.String getSession() {
        java.lang.Object ref = session_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          session_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *登錄成功返回的會話
       * </pre>
       *
       * <code>optional string session = 4;</code>
       */
      public com.google.protobuf.ByteString
          getSessionBytes() {
        java.lang.Object ref = session_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          session_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *登錄成功返回的會話
       * </pre>
       *
       * <code>optional string session = 4;</code>
       */
      public Builder setSession(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        session_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *登錄成功返回的會話
       * </pre>
       *
       * <code>optional string session = 4;</code>
       */
      public Builder clearSession() {
        
        session_ = getDefaultInstance().getSession();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *登錄成功返回的會話
       * </pre>
       *
       * <code>optional string session = 4;</code>
       */
      public Builder setSessionBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        session_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:LottryRequestMessage)
    }

    // @@protoc_insertion_point(class_scope:LottryRequestMessage)
    private static final com.yzh.cqjw.request.LottryRequest.LottryRequestMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.yzh.cqjw.request.LottryRequest.LottryRequestMessage();
    }

    public static com.yzh.cqjw.request.LottryRequest.LottryRequestMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<LottryRequestMessage>
        PARSER = new com.google.protobuf.AbstractParser<LottryRequestMessage>() {
      public LottryRequestMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new LottryRequestMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<LottryRequestMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<LottryRequestMessage> getParserForType() {
      return PARSER;
    }

    public com.yzh.cqjw.request.LottryRequest.LottryRequestMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LottryRequestMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LottryRequestMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023LottryRequest.proto\"Y\n\024LottryRequestMe" +
      "ssage\022\017\n\007version\030\001 \001(\t\022\r\n\005phone\030\002 \001(\t\022\020\n" +
      "\010platform\030\003 \001(\t\022\017\n\007session\030\004 \001(\tB%\n\024com." +
      "yzh.cqjw.requestB\rLottryRequestb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_LottryRequestMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_LottryRequestMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LottryRequestMessage_descriptor,
        new java.lang.String[] { "Version", "Phone", "Platform", "Session", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
