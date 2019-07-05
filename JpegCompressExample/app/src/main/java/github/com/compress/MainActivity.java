package github.com.compress;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import github.com.stoneimagecompress.R;
import github.com.stoneimagecompress.util.ImageUtil;
import github.com.stoneimagecompress.util.ThreadPoolManager;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 要被压缩的图片
        imageList.add("/sdcard/image/jpeg/004.jpg");
        imageList.add("/sdcard/image/jpeg/003.jpg");
        imageList.add("/sdcard/image/jpeg/002.jpg");
        imageList.add("/sdcard/image/jpeg/001.jpg");

    }

    final CopyOnWriteArrayList<String> compressImageList=new CopyOnWriteArrayList<>();

    public void compress(View view){


        File compressDir =new File(Environment.getExternalStorageDirectory(),"compress_tem");
        if(!compressDir.exists()){
            compressDir.mkdirs();
        }

        // 图片被压缩后以原来的名字存储到这个路径
        String output_dir = "/sdcard/compress_tem";

        ThreadPoolManager.ThreadPool threadPool = ThreadPoolManager.getInstance().getShortTreadPool();

        for (final String imagePath : imageList) {

            //Log.d("MainActivity", "image path: " + imagePath);

            final String temFilePath = output_dir + File.separator + new File(imagePath).getName();

            Log.d("MainActivity", "temFilePath : " + temFilePath);


            threadPool.excute(new Runnable() {
                @Override
                public void run() {

                    long startTime = System.currentTimeMillis();

                    Bitmap bitmap = ImageUtil.decodeFile(imagePath);

                    if(ImageUtil.compressImage(bitmap,75,temFilePath,false)){
                        compressImageList.add(temFilePath);
                    }
                    if(bitmap != null) {
                        bitmap.recycle();
                    }

                    // 计算压缩需要的时间
                    long consumingTime = System.currentTimeMillis() - startTime;
                    Log.d("MainActivity", "take time " + consumingTime);
                }
            });


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){

        }

    }
}
