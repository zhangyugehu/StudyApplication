package com.thssh.pdfviewer;

import android.graphics.Canvas;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

import java.io.File;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PDFView pdfView = (PDFView) findViewById(R.id.pdf_view);
        File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String fileName = "Android.pdf";
        File pdfFile = new File(downloadDir, fileName);
        if(pdfFile.exists()) {
            pdfView.fromFile(pdfFile)   //设置pdf文件地址
                    .defaultPage(1)         //设置默认显示第1页
                    .onPageChange(new OnPageChangeListener() {
                        @Override
                        public void onPageChanged(int page, int pageCount) {
                            Log.d("onPageChanged", page + "/" + pageCount);
                        }
                    })     //设置翻页监听
                    .onLoad(new OnLoadCompleteListener() {
                        @Override
                        public void loadComplete(int nbPages) {
                            Log.d("loadComplete", "nbPages > " + nbPages);
                        }
                    })           //设置加载监听
                    .onDraw(new OnDrawListener() {
                        @Override
                        public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                            Log.d("onLayerDrawn", "displayedPage > " + displayedPage);
                        }
                    })            //绘图监听
                    .enableSwipe(true)   //是否允许翻页，默认是允许翻
                     .pages( 2 , 3 , 4 , 5  )  //只显示2 , 3 , 4 , 5页
                    .load();
        }else{
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }
}
