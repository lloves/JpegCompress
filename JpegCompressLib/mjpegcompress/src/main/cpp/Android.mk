LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE    := compressImage

LOCAL_SRC_FILES := ${LOCAL_PATH}/compressImage.cpp \
                    ${LOCAL_PATH}/github_com_stoneimagecompress_util_ImageUtil.h

LOCAL_STATIC_LIBRARIES := ${LOCAL_PATH}/libstjpeg.so

LOCAL_C_INCLUDES := ${LOCAL_PATH}/include

include $(BUILD_STATIC_LIBRARY)
