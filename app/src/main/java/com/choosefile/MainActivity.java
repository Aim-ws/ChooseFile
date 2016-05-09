package com.choosefile;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Currency;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 0x2;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
    }

    public void onClick(View view){
        showFiles();
    }

    private void showFiles() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE:
                    Uri uri = data.getData();
                    Log.i("", "--------------------------" + uri);
                    String path = FileUtil.getPath(MainActivity.this,uri);
                    Bitmap bp = BitmapFactory.decodeFile(path);
                    imageView.setImageBitmap(bp);
                    Toast.makeText(MainActivity.this,path,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


}
