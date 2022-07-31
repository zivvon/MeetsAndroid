package com.example.meets;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MakeMoimFormActivity extends AppCompatActivity {

    //하단탭 설정
    Button moveHome;
    Button moveCal;
    Button moveMypg;

    Button moveBack;

    Button make_moim_btn;

    EditText moim_naim;

    //이미지뷰 설정
    ImageView imageView;
    String imgName = "osz.png";    // 이미지 이름

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makemoim_form);

        //하단탭 버튼 설정
        moveHome = findViewById(R.id.homeBtn);
        moveCal = findViewById(R.id.calBtn);
        moveMypg = findViewById(R.id.myBtn);

        //뒤로가기 버튼 설정
        moveBack = findViewById(R.id.pre_page_btn);

        //이미지뷰 설정
        imageView = findViewById(R.id.moim_pic);

        moim_naim = findViewById(R.id.moim_name);

        try {
            String imgpath = getCacheDir() + "/" + imgName;   // 내부 저장소에 저장되어 있는 이미지 경로
            Bitmap bm = BitmapFactory.decodeFile(imgpath);
            imageView.setImageBitmap(bm);   // 내부 저장소에 저장된 이미지를 이미지뷰에 셋
            Toast.makeText(getApplicationContext(), "파일 로드 성공", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "파일 로드 실패", Toast.LENGTH_SHORT).show();
        }

        //데이터 보내는 완료 버튼
        make_moim_btn = (Button)findViewById(R.id.make_moim_btn);

        make_moim_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

                float scale = (float)(1024/(float)bitmap.getWidth());
                int image_w = (int)(bitmap.getWidth() * scale);
                int image_h = (int)(bitmap.getHeight() * scale);

                Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);

                resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent intent = new Intent(); //메인에 전달할 값을 저장

                String input = moim_naim.getText().toString();

                intent.putExtra("string", input);
                intent.putExtra("image", byteArray);
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }

    public void moveHome(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void moveCal(View v){
        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(intent);
    }

    public void moveMypg(View v){
        Intent intent = new Intent(getApplicationContext(), MypgActivity.class);
        startActivity(intent);
    }

    public void moveBack(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    //이미지뷰 설정하기
    public void selectImg(View view) {    // 이미지 선택 누르면 실행됨 이미지 고를 갤러리 오픈
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 갤러리
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Uri fileUri = data.getData();
                ContentResolver resolver = getContentResolver();
                try {
                    InputStream instream = resolver.openInputStream(fileUri);
                    Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
                    imageView.setImageBitmap(imgBitmap);    // 선택한 이미지 이미지뷰에 셋
                    instream.close();   // 스트림 닫아주기
                    saveBitmapToJpeg(imgBitmap);    // 내부 저장소에 저장
                    Toast.makeText(getApplicationContext(), "파일 불러오기 성공", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "파일 불러오기 실패", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void saveBitmapToJpeg(Bitmap bitmap) {   // 선택한 이미지 내부 저장소에 저장
        File tempFile = new File(getCacheDir(), imgName);    // 파일 경로와 이름 넣기
        try {
            tempFile.createNewFile();   // 자동으로 빈 파일을 생성하기
            FileOutputStream out = new FileOutputStream(tempFile);  // 파일을 쓸 수 있는 스트림을 준비하기
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);   // compress 함수를 사용해 스트림에 비트맵을 저장하기
            out.close();    // 스트림 닫아주기
            Toast.makeText(getApplicationContext(), "파일 저장 성공", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "파일 저장 실패", Toast.LENGTH_SHORT).show();
        }
    }

//    public void deleteImg(View view) {    // 이미지 삭제
//        try {
//            File file = getCacheDir();  // 내부저장소 캐시 경로를 받아오기
//            File[] flist = file.listFiles();
//            for (int i = 0; i < flist.length; i++) {    // 배열의 크기만큼 반복
//                if (flist[i].getName().equals(imgName)) {   // 삭제하고자 하는 이름과 같은 파일명이 있으면 실행
//                    flist[i].delete();  // 파일 삭제
//                    Toast.makeText(getApplicationContext(), "파일 삭제 성공", Toast.LENGTH_SHORT).show();
//                }
//            }
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "파일 삭제 실패", Toast.LENGTH_SHORT).show();
//        }
//    }

}
