package github.com.stoneimagecompress.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class ImageUtil {

    static {
        System.loadLibrary("compressImage");
    }


    public static boolean compressImage(Bitmap bitmap,int quality,String dstFile,boolean  optimize){

        int ret = compressBitmap( bitmap, quality, dstFile,  optimize);

        return  ret==1;
    }

    public static native int compressBitmap(Bitmap bitmap, int quality, String dstFile,boolean  optimize);

    /**
     *
     * @param path
     * @return
     */
    public static Bitmap decodeFile(String path){

        int finalWidth = 960;
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, o);
        o.inSampleSize = calculateSize(o.outWidth,finalWidth);
        o.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, o);
    }

    private static int calculateSize(int originalWidth,int newWidth) {
        return originalWidth/newWidth;
    }
}
