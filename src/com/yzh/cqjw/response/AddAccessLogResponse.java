// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AddAccessLogResponse.proto

package com.yzh.cqjw.response;

public final class AddAccessLogResponse {
  private AddAccessLogResponse() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface AddAccessLogResponseMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:AddAccessLogResponseMessage)
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
  }
  /**
   * Protobuf type {@code AddAccessLogResponseMessage}
   */
  public  static final class AddAccessLogResponseMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:AddAccessLogResponseMessage)
      AddAccessLogResponseMessageOrBuilder {
    // Use AddAccessLogResponseMessage.newBuilder() to construct.
    private AddAccessLogResponseMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private AddAccessLogResponseMessage() {
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private AddAccessLogResponseMessage(
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
      return com.yzh.cqjw.response.AddAccessLogResponse.internal_static_AddAccessLogResponseMessage_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.yzh.cqjw.response.AddAccessLogResponse.internal_static_AddAccessLogResponseMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage.class, com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage.Builder.class);
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
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (errorMsg_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getErrorMsg());
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
      if (!(obj instanceof com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage)) {
        return super.equals(obj);
      }
      com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage other = (com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage) obj;

      boolean result = true;
      result = result && (hasErrorMsg() == other.hasErrorMsg());
      if (hasErrorMsg()) {
        result = result && getErrorMsg()
            .equals(other.getErrorMsg());
      }
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
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage parseFrom(
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
    public static Builder newBuilder(com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage prototype) {
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
     * Protobuf type {@code AddAccessLogResponseMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:AddAccessLogResponseMessage)
        com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.yzh.cqjw.response.AddAccessLogResponse.internal_static_AddAccessLogResponseMessage_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.yzh.cqjw.response.AddAccessLogResponse.internal_static_AddAccessLogResponseMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage.class, com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage.Builder.class);
      }

      // Construct using com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage.newBuilder()
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
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.yzh.cqjw.response.AddAccessLogResponse.internal_static_AddAccessLogResponseMessage_descriptor;
      }

      public com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage getDefaultInstanceForType() {
        return com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage.getDefaultInstance();
      }

      public com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage build() {
        com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage buildPartial() {
        com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage result = new com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage(this);
        if (errorMsgBuilder_ == null) {
          result.errorMsg_ = errorMsg_;
        } else {
          result.errorMsg_ = errorMsgBuilder_.build();
        }
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
        if (other instanceof com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage) {
          return mergeFrom((com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage other) {
        if (other == com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage.getDefaultInstance()) return this;
        if (other.hasErrorMsg()) {
          mergeErrorMsg(other.getErrorMsg());
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
        com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage) e.getUnfinishedMessage();
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
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:AddAccessLogResponseMessage)
    }

    // @@protoc_insertion_point(class_scope:AddAccessLogResponseMessage)
    private static final com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage();
    }

    public static com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<AddAccessLogResponseMessage>
        PARSER = new com.google.protobuf.AbstractParser<AddAccessLogResponseMessage>() {
      public AddAccessLogResponseMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new AddAccessLogResponseMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<AddAccessLogResponseMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<AddAccessLogResponseMessage> getParserForType() {
      return PARSER;
    }

    public com.yzh.cqjw.response.AddAccessLogResponse.AddAccessLogResponseMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AddAccessLogResponseMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AddAccessLogResponseMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\032AddAccessLogResponse.proto\032\032ErrorMessa" +
      "geResponse.proto\">\n\033AddAccessLogResponse" +
      "Message\022\037\n\010errorMsg\030\001 \001(\0132\r.ErrorMessage" +
      "B-\n\025com.yzh.cqjw.responseB\024AddAccessLogR" +
      "esponseb\006proto3"
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
    internal_static_AddAccessLogResponseMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_AddAccessLogResponseMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AddAccessLogResponseMessage_descriptor,
        new java.lang.String[] { "ErrorMsg", });
    com.yzh.cqjw.response.ErrorMessageResponse.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
