# JpegCompress

Android自带的jpeg的压缩：一种是降采样率压缩，另外一种是质量压缩。

第一种:

 BitmapFactory.Options o = new BitmapFactory.Options();
 o.inJustDecodeBounds = true;
 BitmapFactory.decodeFile(path, o);
 o.inSampleSize=自己计算
 o.inJustDecodeBounds = false;
 BitmapFactory.decodeFile(path, o);


第二种:

bitmap.compress(Bitmap.CompressFormat.JPEG, 20, new FileOutputStream("sdcard/result.jpg"));

Android上调用Jpeg-tirbo提升jpeg压缩比不高、压缩效率还低，所以实现基于libjpeg的方式，大致流程如下：
1. Android java层传递Bitmap到jni层，jni层把bitmap图像去除a通道，转成原始的rgb格式保存；
2. 调用libjpeg的功能，指定压缩比（quality），实现rgb压缩为jpeg格式图像保存；

借用了网友的的代码（ https://github.com/skcodestack/StoneImageCompress-Demo ），改了下，实现了aar，方便做更多修改提供接口给内部其他项目组使用。
