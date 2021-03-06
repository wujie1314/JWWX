// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RecommendLineResponse.proto

package com.yzh.cqjw.response;

public final class RecommendLineResponse {
  private RecommendLineResponse() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface RecommendLineOrBuilder extends
      // @@protoc_insertion_point(interface_extends:RecommendLine)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *推荐线路名称
     * </pre>
     *
     * <code>optional string lineName = 1;</code>
     */
    java.lang.String getLineName();
    /**
     * <pre>
     *推荐线路名称
     * </pre>
     *
     * <code>optional string lineName = 1;</code>
     */
    com.google.protobuf.ByteString
        getLineNameBytes();

    /**
     * <pre>
     *耗时
     * </pre>
     *
     * <code>optional int32 totalTime = 2;</code>
     */
    int getTotalTime();

    /**
     * <pre>
     *步行距离
     * </pre>
     *
     * <code>optional int32 walkDistance = 3;</code>
     */
    int getWalkDistance();

    /**
     * <pre>
     *花费
     * </pre>
     *
     * <code>optional string cost = 4;</code>
     */
    java.lang.String getCost();
    /**
     * <pre>
     *花费
     * </pre>
     *
     * <code>optional string cost = 4;</code>
     */
    com.google.protobuf.ByteString
        getCostBytes();

    /**
     * <pre>
     *起点站
     * </pre>
     *
     * <code>optional string stationName = 5;</code>
     */
    java.lang.String getStationName();
    /**
     * <pre>
     *起点站
     * </pre>
     *
     * <code>optional string stationName = 5;</code>
     */
    com.google.protobuf.ByteString
        getStationNameBytes();

    /**
     * <pre>
     *地铁入口
     * </pre>
     *
     * <code>optional string entranceName = 6;</code>
     */
    java.lang.String getEntranceName();
    /**
     * <pre>
     *地铁入口
     * </pre>
     *
     * <code>optional string entranceName = 6;</code>
     */
    com.google.protobuf.ByteString
        getEntranceNameBytes();

    /**
     * <pre>
     *线路站数
     * </pre>
     *
     * <code>optional int32 viaNum = 7;</code>
     */
    int getViaNum();
  }
  /**
   * Protobuf type {@code RecommendLine}
   */
  public  static final class RecommendLine extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:RecommendLine)
      RecommendLineOrBuilder {
    // Use RecommendLine.newBuilder() to construct.
    private RecommendLine(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private RecommendLine() {
      lineName_ = "";
      totalTime_ = 0;
      walkDistance_ = 0;
      cost_ = "";
      stationName_ = "";
      entranceName_ = "";
      viaNum_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private RecommendLine(
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

              lineName_ = s;
              break;
            }
            case 16: {

              totalTime_ = input.readInt32();
              break;
            }
            case 24: {

              walkDistance_ = input.readInt32();
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              cost_ = s;
              break;
            }
            case 42: {
              java.lang.String s = input.readStringRequireUtf8();

              stationName_ = s;
              break;
            }
            case 50: {
              java.lang.String s = input.readStringRequireUtf8();

              entranceName_ = s;
              break;
            }
            case 56: {

              viaNum_ = input.readInt32();
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
      return com.yzh.cqjw.response.RecommendLineResponse.internal_static_RecommendLine_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.yzh.cqjw.response.RecommendLineResponse.internal_static_RecommendLine_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.yzh.cqjw.response.RecommendLineResponse.RecommendLine.class, com.yzh.cqjw.response.RecommendLineResponse.RecommendLine.Builder.class);
    }

    public static final int LINENAME_FIELD_NUMBER = 1;
    private volatile java.lang.Object lineName_;
    /**
     * <pre>
     *推荐线路名称
     * </pre>
     *
     * <code>optional string lineName = 1;</code>
     */
    public java.lang.String getLineName() {
      java.lang.Object ref = lineName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        lineName_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *推荐线路名称
     * </pre>
     *
     * <code>optional string lineName = 1;</code>
     */
    public com.google.protobuf.ByteString
        getLineNameBytes() {
      java.lang.Object ref = lineName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        lineName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TOTALTIME_FIELD_NUMBER = 2;
    private int totalTime_;
    /**
     * <pre>
     *耗时
     * </pre>
     *
     * <code>optional int32 totalTime = 2;</code>
     */
    public int getTotalTime() {
      return totalTime_;
    }

    public static final int WALKDISTANCE_FIELD_NUMBER = 3;
    private int walkDistance_;
    /**
     * <pre>
     *步行距离
     * </pre>
     *
     * <code>optional int32 walkDistance = 3;</code>
     */
    public int getWalkDistance() {
      return walkDistance_;
    }

    public static final int COST_FIELD_NUMBER = 4;
    private volatile java.lang.Object cost_;
    /**
     * <pre>
     *花费
     * </pre>
     *
     * <code>optional string cost = 4;</code>
     */
    public java.lang.String getCost() {
      java.lang.Object ref = cost_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        cost_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *花费
     * </pre>
     *
     * <code>optional string cost = 4;</code>
     */
    public com.google.protobuf.ByteString
        getCostBytes() {
      java.lang.Object ref = cost_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        cost_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int STATIONNAME_FIELD_NUMBER = 5;
    private volatile java.lang.Object stationName_;
    /**
     * <pre>
     *起点站
     * </pre>
     *
     * <code>optional string stationName = 5;</code>
     */
    public java.lang.String getStationName() {
      java.lang.Object ref = stationName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        stationName_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *起点站
     * </pre>
     *
     * <code>optional string stationName = 5;</code>
     */
    public com.google.protobuf.ByteString
        getStationNameBytes() {
      java.lang.Object ref = stationName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        stationName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int ENTRANCENAME_FIELD_NUMBER = 6;
    private volatile java.lang.Object entranceName_;
    /**
     * <pre>
     *地铁入口
     * </pre>
     *
     * <code>optional string entranceName = 6;</code>
     */
    public java.lang.String getEntranceName() {
      java.lang.Object ref = entranceName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        entranceName_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *地铁入口
     * </pre>
     *
     * <code>optional string entranceName = 6;</code>
     */
    public com.google.protobuf.ByteString
        getEntranceNameBytes() {
      java.lang.Object ref = entranceName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        entranceName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int VIANUM_FIELD_NUMBER = 7;
    private int viaNum_;
    /**
     * <pre>
     *线路站数
     * </pre>
     *
     * <code>optional int32 viaNum = 7;</code>
     */
    public int getViaNum() {
      return viaNum_;
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
      if (!getLineNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, lineName_);
      }
      if (totalTime_ != 0) {
        output.writeInt32(2, totalTime_);
      }
      if (walkDistance_ != 0) {
        output.writeInt32(3, walkDistance_);
      }
      if (!getCostBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, cost_);
      }
      if (!getStationNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 5, stationName_);
      }
      if (!getEntranceNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 6, entranceName_);
      }
      if (viaNum_ != 0) {
        output.writeInt32(7, viaNum_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getLineNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, lineName_);
      }
      if (totalTime_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, totalTime_);
      }
      if (walkDistance_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, walkDistance_);
      }
      if (!getCostBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, cost_);
      }
      if (!getStationNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, stationName_);
      }
      if (!getEntranceNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, entranceName_);
      }
      if (viaNum_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(7, viaNum_);
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
      if (!(obj instanceof com.yzh.cqjw.response.RecommendLineResponse.RecommendLine)) {
        return super.equals(obj);
      }
      com.yzh.cqjw.response.RecommendLineResponse.RecommendLine other = (com.yzh.cqjw.response.RecommendLineResponse.RecommendLine) obj;

      boolean result = true;
      result = result && getLineName()
          .equals(other.getLineName());
      result = result && (getTotalTime()
          == other.getTotalTime());
      result = result && (getWalkDistance()
          == other.getWalkDistance());
      result = result && getCost()
          .equals(other.getCost());
      result = result && getStationName()
          .equals(other.getStationName());
      result = result && getEntranceName()
          .equals(other.getEntranceName());
      result = result && (getViaNum()
          == other.getViaNum());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + LINENAME_FIELD_NUMBER;
      hash = (53 * hash) + getLineName().hashCode();
      hash = (37 * hash) + TOTALTIME_FIELD_NUMBER;
      hash = (53 * hash) + getTotalTime();
      hash = (37 * hash) + WALKDISTANCE_FIELD_NUMBER;
      hash = (53 * hash) + getWalkDistance();
      hash = (37 * hash) + COST_FIELD_NUMBER;
      hash = (53 * hash) + getCost().hashCode();
      hash = (37 * hash) + STATIONNAME_FIELD_NUMBER;
      hash = (53 * hash) + getStationName().hashCode();
      hash = (37 * hash) + ENTRANCENAME_FIELD_NUMBER;
      hash = (53 * hash) + getEntranceName().hashCode();
      hash = (37 * hash) + VIANUM_FIELD_NUMBER;
      hash = (53 * hash) + getViaNum();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.yzh.cqjw.response.RecommendLineResponse.RecommendLine parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yzh.cqjw.response.RecommendLineResponse.RecommendLine parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yzh.cqjw.response.RecommendLineResponse.RecommendLine parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yzh.cqjw.response.RecommendLineResponse.RecommendLine parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yzh.cqjw.response.RecommendLineResponse.RecommendLine parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yzh.cqjw.response.RecommendLineResponse.RecommendLine parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yzh.cqjw.response.RecommendLineResponse.RecommendLine parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.yzh.cqjw.response.RecommendLineResponse.RecommendLine parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yzh.cqjw.response.RecommendLineResponse.RecommendLine parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yzh.cqjw.response.RecommendLineResponse.RecommendLine parseFrom(
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
    public static Builder newBuilder(com.yzh.cqjw.response.RecommendLineResponse.RecommendLine prototype) {
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
     * Protobuf type {@code RecommendLine}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:RecommendLine)
        com.yzh.cqjw.response.RecommendLineResponse.RecommendLineOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.yzh.cqjw.response.RecommendLineResponse.internal_static_RecommendLine_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.yzh.cqjw.response.RecommendLineResponse.internal_static_RecommendLine_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.yzh.cqjw.response.RecommendLineResponse.RecommendLine.class, com.yzh.cqjw.response.RecommendLineResponse.RecommendLine.Builder.class);
      }

      // Construct using com.yzh.cqjw.response.RecommendLineResponse.RecommendLine.newBuilder()
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
        lineName_ = "";

        totalTime_ = 0;

        walkDistance_ = 0;

        cost_ = "";

        stationName_ = "";

        entranceName_ = "";

        viaNum_ = 0;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.yzh.cqjw.response.RecommendLineResponse.internal_static_RecommendLine_descriptor;
      }

      public com.yzh.cqjw.response.RecommendLineResponse.RecommendLine getDefaultInstanceForType() {
        return com.yzh.cqjw.response.RecommendLineResponse.RecommendLine.getDefaultInstance();
      }

      public com.yzh.cqjw.response.RecommendLineResponse.RecommendLine build() {
        com.yzh.cqjw.response.RecommendLineResponse.RecommendLine result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.yzh.cqjw.response.RecommendLineResponse.RecommendLine buildPartial() {
        com.yzh.cqjw.response.RecommendLineResponse.RecommendLine result = new com.yzh.cqjw.response.RecommendLineResponse.RecommendLine(this);
        result.lineName_ = lineName_;
        result.totalTime_ = totalTime_;
        result.walkDistance_ = walkDistance_;
        result.cost_ = cost_;
        result.stationName_ = stationName_;
        result.entranceName_ = entranceName_;
        result.viaNum_ = viaNum_;
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
        if (other instanceof com.yzh.cqjw.response.RecommendLineResponse.RecommendLine) {
          return mergeFrom((com.yzh.cqjw.response.RecommendLineResponse.RecommendLine)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.yzh.cqjw.response.RecommendLineResponse.RecommendLine other) {
        if (other == com.yzh.cqjw.response.RecommendLineResponse.RecommendLine.getDefaultInstance()) return this;
        if (!other.getLineName().isEmpty()) {
          lineName_ = other.lineName_;
          onChanged();
        }
        if (other.getTotalTime() != 0) {
          setTotalTime(other.getTotalTime());
        }
        if (other.getWalkDistance() != 0) {
          setWalkDistance(other.getWalkDistance());
        }
        if (!other.getCost().isEmpty()) {
          cost_ = other.cost_;
          onChanged();
        }
        if (!other.getStationName().isEmpty()) {
          stationName_ = other.stationName_;
          onChanged();
        }
        if (!other.getEntranceName().isEmpty()) {
          entranceName_ = other.entranceName_;
          onChanged();
        }
        if (other.getViaNum() != 0) {
          setViaNum(other.getViaNum());
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
        com.yzh.cqjw.response.RecommendLineResponse.RecommendLine parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.yzh.cqjw.response.RecommendLineResponse.RecommendLine) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object lineName_ = "";
      /**
       * <pre>
       *推荐线路名称
       * </pre>
       *
       * <code>optional string lineName = 1;</code>
       */
      public java.lang.String getLineName() {
        java.lang.Object ref = lineName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          lineName_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *推荐线路名称
       * </pre>
       *
       * <code>optional string lineName = 1;</code>
       */
      public com.google.protobuf.ByteString
          getLineNameBytes() {
        java.lang.Object ref = lineName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          lineName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *推荐线路名称
       * </pre>
       *
       * <code>optional string lineName = 1;</code>
       */
      public Builder setLineName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        lineName_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *推荐线路名称
       * </pre>
       *
       * <code>optional string lineName = 1;</code>
       */
      public Builder clearLineName() {
        
        lineName_ = getDefaultInstance().getLineName();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *推荐线路名称
       * </pre>
       *
       * <code>optional string lineName = 1;</code>
       */
      public Builder setLineNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        lineName_ = value;
        onChanged();
        return this;
      }

      private int totalTime_ ;
      /**
       * <pre>
       *耗时
       * </pre>
       *
       * <code>optional int32 totalTime = 2;</code>
       */
      public int getTotalTime() {
        return totalTime_;
      }
      /**
       * <pre>
       *耗时
       * </pre>
       *
       * <code>optional int32 totalTime = 2;</code>
       */
      public Builder setTotalTime(int value) {
        
        totalTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *耗时
       * </pre>
       *
       * <code>optional int32 totalTime = 2;</code>
       */
      public Builder clearTotalTime() {
        
        totalTime_ = 0;
        onChanged();
        return this;
      }

      private int walkDistance_ ;
      /**
       * <pre>
       *步行距离
       * </pre>
       *
       * <code>optional int32 walkDistance = 3;</code>
       */
      public int getWalkDistance() {
        return walkDistance_;
      }
      /**
       * <pre>
       *步行距离
       * </pre>
       *
       * <code>optional int32 walkDistance = 3;</code>
       */
      public Builder setWalkDistance(int value) {
        
        walkDistance_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *步行距离
       * </pre>
       *
       * <code>optional int32 walkDistance = 3;</code>
       */
      public Builder clearWalkDistance() {
        
        walkDistance_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object cost_ = "";
      /**
       * <pre>
       *花费
       * </pre>
       *
       * <code>optional string cost = 4;</code>
       */
      public java.lang.String getCost() {
        java.lang.Object ref = cost_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          cost_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *花费
       * </pre>
       *
       * <code>optional string cost = 4;</code>
       */
      public com.google.protobuf.ByteString
          getCostBytes() {
        java.lang.Object ref = cost_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          cost_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *花费
       * </pre>
       *
       * <code>optional string cost = 4;</code>
       */
      public Builder setCost(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        cost_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *花费
       * </pre>
       *
       * <code>optional string cost = 4;</code>
       */
      public Builder clearCost() {
        
        cost_ = getDefaultInstance().getCost();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *花费
       * </pre>
       *
       * <code>optional string cost = 4;</code>
       */
      public Builder setCostBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        cost_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object stationName_ = "";
      /**
       * <pre>
       *起点站
       * </pre>
       *
       * <code>optional string stationName = 5;</code>
       */
      public java.lang.String getStationName() {
        java.lang.Object ref = stationName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          stationName_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *起点站
       * </pre>
       *
       * <code>optional string stationName = 5;</code>
       */
      public com.google.protobuf.ByteString
          getStationNameBytes() {
        java.lang.Object ref = stationName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          stationName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *起点站
       * </pre>
       *
       * <code>optional string stationName = 5;</code>
       */
      public Builder setStationName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        stationName_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *起点站
       * </pre>
       *
       * <code>optional string stationName = 5;</code>
       */
      public Builder clearStationName() {
        
        stationName_ = getDefaultInstance().getStationName();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *起点站
       * </pre>
       *
       * <code>optional string stationName = 5;</code>
       */
      public Builder setStationNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        stationName_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object entranceName_ = "";
      /**
       * <pre>
       *地铁入口
       * </pre>
       *
       * <code>optional string entranceName = 6;</code>
       */
      public java.lang.String getEntranceName() {
        java.lang.Object ref = entranceName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          entranceName_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *地铁入口
       * </pre>
       *
       * <code>optional string entranceName = 6;</code>
       */
      public com.google.protobuf.ByteString
          getEntranceNameBytes() {
        java.lang.Object ref = entranceName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          entranceName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *地铁入口
       * </pre>
       *
       * <code>optional string entranceName = 6;</code>
       */
      public Builder setEntranceName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        entranceName_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *地铁入口
       * </pre>
       *
       * <code>optional string entranceName = 6;</code>
       */
      public Builder clearEntranceName() {
        
        entranceName_ = getDefaultInstance().getEntranceName();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *地铁入口
       * </pre>
       *
       * <code>optional string entranceName = 6;</code>
       */
      public Builder setEntranceNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        entranceName_ = value;
        onChanged();
        return this;
      }

      private int viaNum_ ;
      /**
       * <pre>
       *线路站数
       * </pre>
       *
       * <code>optional int32 viaNum = 7;</code>
       */
      public int getViaNum() {
        return viaNum_;
      }
      /**
       * <pre>
       *线路站数
       * </pre>
       *
       * <code>optional int32 viaNum = 7;</code>
       */
      public Builder setViaNum(int value) {
        
        viaNum_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *线路站数
       * </pre>
       *
       * <code>optional int32 viaNum = 7;</code>
       */
      public Builder clearViaNum() {
        
        viaNum_ = 0;
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


      // @@protoc_insertion_point(builder_scope:RecommendLine)
    }

    // @@protoc_insertion_point(class_scope:RecommendLine)
    private static final com.yzh.cqjw.response.RecommendLineResponse.RecommendLine DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.yzh.cqjw.response.RecommendLineResponse.RecommendLine();
    }

    public static com.yzh.cqjw.response.RecommendLineResponse.RecommendLine getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<RecommendLine>
        PARSER = new com.google.protobuf.AbstractParser<RecommendLine>() {
      public RecommendLine parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new RecommendLine(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<RecommendLine> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<RecommendLine> getParserForType() {
      return PARSER;
    }

    public com.yzh.cqjw.response.RecommendLineResponse.RecommendLine getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RecommendLine_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_RecommendLine_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033RecommendLineResponse.proto\"\223\001\n\rRecomm" +
      "endLine\022\020\n\010lineName\030\001 \001(\t\022\021\n\ttotalTime\030\002" +
      " \001(\005\022\024\n\014walkDistance\030\003 \001(\005\022\014\n\004cost\030\004 \001(\t" +
      "\022\023\n\013stationName\030\005 \001(\t\022\024\n\014entranceName\030\006 " +
      "\001(\t\022\016\n\006viaNum\030\007 \001(\005B.\n\025com.yzh.cqjw.resp" +
      "onseB\025RecommendLineResponseb\006proto3"
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
    internal_static_RecommendLine_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_RecommendLine_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_RecommendLine_descriptor,
        new java.lang.String[] { "LineName", "TotalTime", "WalkDistance", "Cost", "StationName", "EntranceName", "ViaNum", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
