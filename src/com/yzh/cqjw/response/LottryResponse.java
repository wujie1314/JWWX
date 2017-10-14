// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: LottryResponse.proto

package com.yzh.cqjw.response;

public final class LottryResponse {
  private LottryResponse() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface LottryResponseMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:LottryResponseMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional .ErrorMessage errorMsg = 1;</code>
     */
    boolean hasErrorMsg();
    /**
     * <code>optional .ErrorMessage errorMsg = 1;</code>
     */
    com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage getErrorMsg();
    /**
     * <code>optional .ErrorMessage errorMsg = 1;</code>
     */
    com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessageOrBuilder getErrorMsgOrBuilder();

    /**
     * <pre>
     * 兑奖结果
     * </pre>
     *
     * <code>optional string flag = 2;</code>
     */
    java.lang.String getFlag();
    /**
     * <pre>
     * 兑奖结果
     * </pre>
     *
     * <code>optional string flag = 2;</code>
     */
    com.google.protobuf.ByteString
        getFlagBytes();

    /**
     * <pre>
     * 中奖信息
     * </pre>
     *
     * <code>optional string info = 3;</code>
     */
    java.lang.String getInfo();
    /**
     * <pre>
     * 中奖信息
     * </pre>
     *
     * <code>optional string info = 3;</code>
     */
    com.google.protobuf.ByteString
        getInfoBytes();

    /**
     * <pre>
     * 兑换码
     * </pre>
     *
     * <code>optional string code = 4;</code>
     */
    java.lang.String getCode();
    /**
     * <pre>
     * 兑换码
     * </pre>
     *
     * <code>optional string code = 4;</code>
     */
    com.google.protobuf.ByteString
        getCodeBytes();
  }
  /**
   * Protobuf type {@code LottryResponseMessage}
   */
  public  static final class LottryResponseMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:LottryResponseMessage)
      LottryResponseMessageOrBuilder {
    // Use LottryResponseMessage.newBuilder() to construct.
    private LottryResponseMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private LottryResponseMessage() {
      flag_ = "";
      info_ = "";
      code_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private LottryResponseMessage(
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
              com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage.Builder subBuilder = null;
              if (errorMsg_ != null) {
                subBuilder = errorMsg_.toBuilder();
              }
              errorMsg_ = input.readMessage(com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(errorMsg_);
                errorMsg_ = subBuilder.buildPartial();
              }

              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              flag_ = s;
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              info_ = s;
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              code_ = s;
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
      return com.yzh.cqjw.response.LottryResponse.internal_static_LottryResponseMessage_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.yzh.cqjw.response.LottryResponse.internal_static_LottryResponseMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.yzh.cqjw.response.LottryResponse.LottryResponseMessage.class, com.yzh.cqjw.response.LottryResponse.LottryResponseMessage.Builder.class);
    }

    public static final int ERRORMSG_FIELD_NUMBER = 1;
    private com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage errorMsg_;
    /**
     * <code>optional .ErrorMessage errorMsg = 1;</code>
     */
    public boolean hasErrorMsg() {
      return errorMsg_ != null;
    }
    /**
     * <code>optional .ErrorMessage errorMsg = 1;</code>
     */
    public com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage getErrorMsg() {
      return errorMsg_ == null ? com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage.getDefaultInstance() : errorMsg_;
    }
    /**
     * <code>optional .ErrorMessage errorMsg = 1;</code>
     */
    public com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessageOrBuilder getErrorMsgOrBuilder() {
      return getErrorMsg();
    }

    public static final int FLAG_FIELD_NUMBER = 2;
    private volatile java.lang.Object flag_;
    /**
     * <pre>
     * 兑奖结果
     * </pre>
     *
     * <code>optional string flag = 2;</code>
     */
    public java.lang.String getFlag() {
      java.lang.Object ref = flag_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        flag_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * 兑奖结果
     * </pre>
     *
     * <code>optional string flag = 2;</code>
     */
    public com.google.protobuf.ByteString
        getFlagBytes() {
      java.lang.Object ref = flag_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        flag_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int INFO_FIELD_NUMBER = 3;
    private volatile java.lang.Object info_;
    /**
     * <pre>
     * 中奖信息
     * </pre>
     *
     * <code>optional string info = 3;</code>
     */
    public java.lang.String getInfo() {
      java.lang.Object ref = info_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        info_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * 中奖信息
     * </pre>
     *
     * <code>optional string info = 3;</code>
     */
    public com.google.protobuf.ByteString
        getInfoBytes() {
      java.lang.Object ref = info_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        info_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int CODE_FIELD_NUMBER = 4;
    private volatile java.lang.Object code_;
    /**
     * <pre>
     * 兑换码
     * </pre>
     *
     * <code>optional string code = 4;</code>
     */
    public java.lang.String getCode() {
      java.lang.Object ref = code_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        code_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * 兑换码
     * </pre>
     *
     * <code>optional string code = 4;</code>
     */
    public com.google.protobuf.ByteString
        getCodeBytes() {
      java.lang.Object ref = code_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        code_ = b;
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
      if (errorMsg_ != null) {
        output.writeMessage(1, getErrorMsg());
      }
      if (!getFlagBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, flag_);
      }
      if (!getInfoBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, info_);
      }
      if (!getCodeBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, code_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (errorMsg_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getErrorMsg());
      }
      if (!getFlagBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, flag_);
      }
      if (!getInfoBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, info_);
      }
      if (!getCodeBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, code_);
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
      if (!(obj instanceof com.yzh.cqjw.response.LottryResponse.LottryResponseMessage)) {
        return super.equals(obj);
      }
      com.yzh.cqjw.response.LottryResponse.LottryResponseMessage other = (com.yzh.cqjw.response.LottryResponse.LottryResponseMessage) obj;

      boolean result = true;
      result = result && (hasErrorMsg() == other.hasErrorMsg());
      if (hasErrorMsg()) {
        result = result && getErrorMsg()
            .equals(other.getErrorMsg());
      }
      result = result && getFlag()
          .equals(other.getFlag());
      result = result && getInfo()
          .equals(other.getInfo());
      result = result && getCode()
          .equals(other.getCode());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      if (hasErrorMsg()) {
        hash = (37 * hash) + ERRORMSG_FIELD_NUMBER;
        hash = (53 * hash) + getErrorMsg().hashCode();
      }
      hash = (37 * hash) + FLAG_FIELD_NUMBER;
      hash = (53 * hash) + getFlag().hashCode();
      hash = (37 * hash) + INFO_FIELD_NUMBER;
      hash = (53 * hash) + getInfo().hashCode();
      hash = (37 * hash) + CODE_FIELD_NUMBER;
      hash = (53 * hash) + getCode().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.yzh.cqjw.response.LottryResponse.LottryResponseMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yzh.cqjw.response.LottryResponse.LottryResponseMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yzh.cqjw.response.LottryResponse.LottryResponseMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yzh.cqjw.response.LottryResponse.LottryResponseMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yzh.cqjw.response.LottryResponse.LottryResponseMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yzh.cqjw.response.LottryResponse.LottryResponseMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yzh.cqjw.response.LottryResponse.LottryResponseMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.yzh.cqjw.response.LottryResponse.LottryResponseMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yzh.cqjw.response.LottryResponse.LottryResponseMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yzh.cqjw.response.LottryResponse.LottryResponseMessage parseFrom(
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
    public static Builder newBuilder(com.yzh.cqjw.response.LottryResponse.LottryResponseMessage prototype) {
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
     * Protobuf type {@code LottryResponseMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:LottryResponseMessage)
        com.yzh.cqjw.response.LottryResponse.LottryResponseMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.yzh.cqjw.response.LottryResponse.internal_static_LottryResponseMessage_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.yzh.cqjw.response.LottryResponse.internal_static_LottryResponseMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.yzh.cqjw.response.LottryResponse.LottryResponseMessage.class, com.yzh.cqjw.response.LottryResponse.LottryResponseMessage.Builder.class);
      }

      // Construct using com.yzh.cqjw.response.LottryResponse.LottryResponseMessage.newBuilder()
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
        if (errorMsgBuilder_ == null) {
          errorMsg_ = null;
        } else {
          errorMsg_ = null;
          errorMsgBuilder_ = null;
        }
        flag_ = "";

        info_ = "";

        code_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.yzh.cqjw.response.LottryResponse.internal_static_LottryResponseMessage_descriptor;
      }

      public com.yzh.cqjw.response.LottryResponse.LottryResponseMessage getDefaultInstanceForType() {
        return com.yzh.cqjw.response.LottryResponse.LottryResponseMessage.getDefaultInstance();
      }

      public com.yzh.cqjw.response.LottryResponse.LottryResponseMessage build() {
        com.yzh.cqjw.response.LottryResponse.LottryResponseMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.yzh.cqjw.response.LottryResponse.LottryResponseMessage buildPartial() {
        com.yzh.cqjw.response.LottryResponse.LottryResponseMessage result = new com.yzh.cqjw.response.LottryResponse.LottryResponseMessage(this);
        if (errorMsgBuilder_ == null) {
          result.errorMsg_ = errorMsg_;
        } else {
          result.errorMsg_ = errorMsgBuilder_.build();
        }
        result.flag_ = flag_;
        result.info_ = info_;
        result.code_ = code_;
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
        if (other instanceof com.yzh.cqjw.response.LottryResponse.LottryResponseMessage) {
          return mergeFrom((com.yzh.cqjw.response.LottryResponse.LottryResponseMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.yzh.cqjw.response.LottryResponse.LottryResponseMessage other) {
        if (other == com.yzh.cqjw.response.LottryResponse.LottryResponseMessage.getDefaultInstance()) return this;
        if (other.hasErrorMsg()) {
          mergeErrorMsg(other.getErrorMsg());
        }
        if (!other.getFlag().isEmpty()) {
          flag_ = other.flag_;
          onChanged();
        }
        if (!other.getInfo().isEmpty()) {
          info_ = other.info_;
          onChanged();
        }
        if (!other.getCode().isEmpty()) {
          code_ = other.code_;
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
        com.yzh.cqjw.response.LottryResponse.LottryResponseMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.yzh.cqjw.response.LottryResponse.LottryResponseMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage errorMsg_ = null;
      private com.google.protobuf.SingleFieldBuilderV3<
          com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage, com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage.Builder, com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessageOrBuilder> errorMsgBuilder_;
      /**
       * <code>optional .ErrorMessage errorMsg = 1;</code>
       */
      public boolean hasErrorMsg() {
        return errorMsgBuilder_ != null || errorMsg_ != null;
      }
      /**
       * <code>optional .ErrorMessage errorMsg = 1;</code>
       */
      public com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage getErrorMsg() {
        if (errorMsgBuilder_ == null) {
          return errorMsg_ == null ? com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage.getDefaultInstance() : errorMsg_;
        } else {
          return errorMsgBuilder_.getMessage();
        }
      }
      /**
       * <code>optional .ErrorMessage errorMsg = 1;</code>
       */
      public Builder setErrorMsg(com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage value) {
        if (errorMsgBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          errorMsg_ = value;
          onChanged();
        } else {
          errorMsgBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <code>optional .ErrorMessage errorMsg = 1;</code>
       */
      public Builder setErrorMsg(
          com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage.Builder builderForValue) {
        if (errorMsgBuilder_ == null) {
          errorMsg_ = builderForValue.build();
          onChanged();
        } else {
          errorMsgBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <code>optional .ErrorMessage errorMsg = 1;</code>
       */
      public Builder mergeErrorMsg(com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage value) {
        if (errorMsgBuilder_ == null) {
          if (errorMsg_ != null) {
            errorMsg_ =
              com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage.newBuilder(errorMsg_).mergeFrom(value).buildPartial();
          } else {
            errorMsg_ = value;
          }
          onChanged();
        } else {
          errorMsgBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <code>optional .ErrorMessage errorMsg = 1;</code>
       */
      public Builder clearErrorMsg() {
        if (errorMsgBuilder_ == null) {
          errorMsg_ = null;
          onChanged();
        } else {
          errorMsg_ = null;
          errorMsgBuilder_ = null;
        }

        return this;
      }
      /**
       * <code>optional .ErrorMessage errorMsg = 1;</code>
       */
      public com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage.Builder getErrorMsgBuilder() {
        
        onChanged();
        return getErrorMsgFieldBuilder().getBuilder();
      }
      /**
       * <code>optional .ErrorMessage errorMsg = 1;</code>
       */
      public com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessageOrBuilder getErrorMsgOrBuilder() {
        if (errorMsgBuilder_ != null) {
          return errorMsgBuilder_.getMessageOrBuilder();
        } else {
          return errorMsg_ == null ?
              com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage.getDefaultInstance() : errorMsg_;
        }
      }
      /**
       * <code>optional .ErrorMessage errorMsg = 1;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage, com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage.Builder, com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessageOrBuilder> 
          getErrorMsgFieldBuilder() {
        if (errorMsgBuilder_ == null) {
          errorMsgBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage, com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessage.Builder, com.yzh.cqjw.response.ErrorMessageResponse.ErrorMessageOrBuilder>(
                  getErrorMsg(),
                  getParentForChildren(),
                  isClean());
          errorMsg_ = null;
        }
        return errorMsgBuilder_;
      }

      private java.lang.Object flag_ = "";
      /**
       * <pre>
       * 兑奖结果
       * </pre>
       *
       * <code>optional string flag = 2;</code>
       */
      public java.lang.String getFlag() {
        java.lang.Object ref = flag_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          flag_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * 兑奖结果
       * </pre>
       *
       * <code>optional string flag = 2;</code>
       */
      public com.google.protobuf.ByteString
          getFlagBytes() {
        java.lang.Object ref = flag_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          flag_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * 兑奖结果
       * </pre>
       *
       * <code>optional string flag = 2;</code>
       */
      public Builder setFlag(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        flag_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 兑奖结果
       * </pre>
       *
       * <code>optional string flag = 2;</code>
       */
      public Builder clearFlag() {
        
        flag_ = getDefaultInstance().getFlag();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 兑奖结果
       * </pre>
       *
       * <code>optional string flag = 2;</code>
       */
      public Builder setFlagBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        flag_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object info_ = "";
      /**
       * <pre>
       * 中奖信息
       * </pre>
       *
       * <code>optional string info = 3;</code>
       */
      public java.lang.String getInfo() {
        java.lang.Object ref = info_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          info_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * 中奖信息
       * </pre>
       *
       * <code>optional string info = 3;</code>
       */
      public com.google.protobuf.ByteString
          getInfoBytes() {
        java.lang.Object ref = info_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          info_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * 中奖信息
       * </pre>
       *
       * <code>optional string info = 3;</code>
       */
      public Builder setInfo(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        info_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 中奖信息
       * </pre>
       *
       * <code>optional string info = 3;</code>
       */
      public Builder clearInfo() {
        
        info_ = getDefaultInstance().getInfo();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 中奖信息
       * </pre>
       *
       * <code>optional string info = 3;</code>
       */
      public Builder setInfoBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        info_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object code_ = "";
      /**
       * <pre>
       * 兑换码
       * </pre>
       *
       * <code>optional string code = 4;</code>
       */
      public java.lang.String getCode() {
        java.lang.Object ref = code_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          code_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * 兑换码
       * </pre>
       *
       * <code>optional string code = 4;</code>
       */
      public com.google.protobuf.ByteString
          getCodeBytes() {
        java.lang.Object ref = code_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          code_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * 兑换码
       * </pre>
       *
       * <code>optional string code = 4;</code>
       */
      public Builder setCode(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        code_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 兑换码
       * </pre>
       *
       * <code>optional string code = 4;</code>
       */
      public Builder clearCode() {
        
        code_ = getDefaultInstance().getCode();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 兑换码
       * </pre>
       *
       * <code>optional string code = 4;</code>
       */
      public Builder setCodeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        code_ = value;
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


      // @@protoc_insertion_point(builder_scope:LottryResponseMessage)
    }

    // @@protoc_insertion_point(class_scope:LottryResponseMessage)
    private static final com.yzh.cqjw.response.LottryResponse.LottryResponseMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.yzh.cqjw.response.LottryResponse.LottryResponseMessage();
    }

    public static com.yzh.cqjw.response.LottryResponse.LottryResponseMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<LottryResponseMessage>
        PARSER = new com.google.protobuf.AbstractParser<LottryResponseMessage>() {
      public LottryResponseMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new LottryResponseMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<LottryResponseMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<LottryResponseMessage> getParserForType() {
      return PARSER;
    }

    public com.yzh.cqjw.response.LottryResponse.LottryResponseMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LottryResponseMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LottryResponseMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024LottryResponse.proto\032\032ErrorMessageResp" +
      "onse.proto\"b\n\025LottryResponseMessage\022\037\n\010e" +
      "rrorMsg\030\001 \001(\0132\r.ErrorMessage\022\014\n\004flag\030\002 \001" +
      "(\t\022\014\n\004info\030\003 \001(\t\022\014\n\004code\030\004 \001(\tB\'\n\025com.yz" +
      "h.cqjw.responseB\016LottryResponseb\006proto3"
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
          com.yzh.cqjw.response.ErrorMessageResponse.getDescriptor(),
        }, assigner);
    internal_static_LottryResponseMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_LottryResponseMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LottryResponseMessage_descriptor,
        new java.lang.String[] { "ErrorMsg", "Flag", "Info", "Code", });
    com.yzh.cqjw.response.ErrorMessageResponse.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
