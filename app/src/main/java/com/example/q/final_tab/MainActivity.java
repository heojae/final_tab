package com.example.q.final_tab;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView m_oListView = null;
    private ArrayList<ArrayList<String>> mGroupList = null;
    private ArrayList<String> mChildList = null;

    String[] strNumber = {"010-1362-2376", "010-2312-2568", "010-3671-2344", "010-8909-4687", "010-2359-2437",
            "010-4368-6346", "010-4309-4378", "010-4315-3289", "010-4369-4989","010-1010-3460"};

    ArrayList<String> sub_strNumber= new ArrayList<>(1000);

    String[] strName={"민지","성진","daniel","amy","혜민","아영","유진","szuky","민정","하늘"};

    //static ArrayList<String> sub_strName;
    ArrayList<String> sub_strName= new ArrayList<>(1000);

    String[] strCode={"민지","성진","daniel","amy","혜민","아영","유진","szuky","민정","하늘"};
    String[] strNation={"korea","america","canada","japan","korea","korea","swiss","japan","uk","korea"};
    ArrayList<String> sub_strNation= new ArrayList<>(1000);

    String TAG = "PhoneActivityTAG";
    Activity activity = MainActivity.this;
    String wantPermission = Manifest.permission.READ_PHONE_STATE;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private ArrayList<Map<String, String>> dataList;
    private ListView mListview;
    private Button mBtnAddress,mBtnContact;
    TextView textView1,textView2;
    String sName="text";
    String sNumber="text";
    static int k=1;
    static int nDatCnt=0;
    static ArrayList<ItemData> oData = new ArrayList<>();



    TextView user;
    TextView computer;
    TextView result;
    TextView textA;
    ProgressBar progress;
    int resultInt=50;


    DisplayMetrics mMetrics;



    private int[] mThumbIds = new int[] {
            R.drawable.gallery_image_01,
            R.drawable.gallery_image_02,
            R.drawable.gallery_image_03,
            R.drawable.gallery_image_04,
            R.drawable.gallery_image_05,
            R.drawable.gallery_image_06,
            R.drawable.gallery_image_07,
            R.drawable.gallery_image_08,
            R.drawable.gallery_image_01,
            R.drawable.gallery_image_10,
            R.drawable.a1,
            R.drawable.a2,
            R.drawable.a3,
            R.drawable.a4,
            R.drawable.a5,
            R.drawable.a6,
            R.drawable.a7,
            R.drawable.a8,
            R.drawable.a1,
            R.drawable.a10,
    };
    private int[] posterID = new int[] {
            R.drawable.gallery_image_01,
            R.drawable.gallery_image_02,
            R.drawable.gallery_image_03,
            R.drawable.gallery_image_04,
            R.drawable.gallery_image_05,
            R.drawable.gallery_image_06,
            R.drawable.gallery_image_07,
            R.drawable.gallery_image_08,
            R.drawable.gallery_image_01,
            R.drawable.gallery_image_10,
            R.drawable.a1,
            R.drawable.a2,
            R.drawable.a3,
            R.drawable.a4,
            R.drawable.a5,
            R.drawable.a6,
            R.drawable.a7,
            R.drawable.a8,
            R.drawable.a1,
            R.drawable.a10,
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        mGroupList = new ArrayList<ArrayList<String>>();
        mChildList = new ArrayList<String>();
        mBtnAddress = (Button) findViewById(R.id.btnAddress);
        mBtnAddress.setOnClickListener(this);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        if (!checkPermission(wantPermission)) {
            requestPermission(wantPermission);
        }


        user = (TextView)findViewById(R.id.textUser);
        computer = (TextView)findViewById(R.id.textComputer);
        result = (TextView)findViewById(R.id.textResult);
        Button buttonA = (Button)findViewById(R.id.buttonA);
        Button buttonB = (Button)findViewById(R.id.buttonB);
        Button buttonC = (Button)findViewById(R.id.buttonC);
        Button buttonK=(Button)findViewById(R.id.buttonK);
        Button buttonZ=(Button)findViewById(R.id.buttonZ);
        progress = (ProgressBar) findViewById(R.id.progress) ;

        textA = (TextView)findViewById(R.id.textA);

        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonK.setOnClickListener(this);
        buttonZ.setOnClickListener(this);

        Button button3=(Button) findViewById(R.id.button3);



//        GridView gridview = (GridView) findViewById(R.id.gridview);
//        gridview.setAdapter(new ImageAdapter(this));
//        gridview.setOnItemClickListener(gridviewOnItemClickListener);
//        mMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(mMetrics);




//        ImageView imageView = (ImageView)findViewById(R.id.imageView);
//        MyAdapter adapter = new MyAdapter (
//                getApplicationContext(),
//                R.layout.row,       // GridView 항목의 레이아웃 row.xml
//                imageIDs);    // 데이터
//        GridView gv = (GridView)findViewById(R.id.gridView1);
//        gv.setAdapter(adapter);
        final GridView gv=(GridView)findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);








        for (int i=0; i<10; ++i)
        {
            ItemData oItem = new ItemData();
            oItem.name = strName[nDatCnt];
            sub_strName.add(strName[nDatCnt]);
            sub_strNation.add(strNation[nDatCnt]);
            oItem.nation=strNation[nDatCnt];


            oItem.code=strCode[nDatCnt];

            sub_strNumber.add(strNumber[nDatCnt]);
            oItem.number = strNumber[nDatCnt++];
            oItem.onClickListener = this;
            if (k%10==1) {
                oItem.imgld = R.drawable.a1;
            }
            else if(k%10==2){
                oItem.imgld = R.drawable.a2;
            }
            else if(k%10==3){
                oItem.imgld = R.drawable.a3;
            }
            else if(k%10==4){
                oItem.imgld = R.drawable.a4;
            }
            else if(k%10==5){
                oItem.imgld = R.drawable.a5;
            }
            else if(k%10==6){
                oItem.imgld = R.drawable.a6;
            }
            else if(k%10==7){
                oItem.imgld = R.drawable.a7;
            }
            else if(k%10==8){
                oItem.imgld = R.drawable.a8;
            }
            else if(k%10==9){
                oItem.imgld = R.drawable.a9;
            }
            else if(k%10==0){
                oItem.imgld = R.drawable.a10;
            }
            else{
                oItem.imgld=R.drawable.china;
            }
            k++;

            oData.add(oItem);
            if (nDatCnt >= strNumber.length) nDatCnt = 0;
        }
        m_oListView = (ListView)findViewById(R.id.listView);
        ListAdapter oAdapter = new ListAdapter(oData);
        m_oListView.setAdapter(oAdapter);













        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("TAB 1") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("TAB 2") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("TAB 3") ;
        tabHost1.addTab(ts3) ;



    }
    @Override
    public void onClick(View v)
    {
        int offsetX=0;
        int offsetY=0;
        Toast toast;
        Intent intent;
        String comX;           // 컴퓨터가 낸 걸 보관하는 장소
        String userX;          // 사람이 낸 걸 보관하는 장소
        String resultX;        // 승패 결과를 보관하는 장소
        // 컴퓨터 가위 바위 보 선택
        Random r;
        float f;


        View oParentView;
        TextView name;
        TextView number;
        String position;
        int p;
        String nation;
        String number2;
        String strMsg;
        Intent mIntent;
        ListAdapter oAdapter;

        switch(v.getId()){

            case R.id.button3:
                oParentView = (View)v.getParent();
                name = (TextView) oParentView.findViewById(R.id.Name);
                number = (TextView) oParentView.findViewById(R.id.Number);
                position = (String) oParentView.getTag();
                p=Integer.valueOf(position);
                nation= sub_strNation.get(p);
                number2=sub_strNumber.get(p);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+number2));
                startActivity(intent);
                break;

            case R.id.btnSelector:
                oParentView = (View)v.getParent();
                name = (TextView) oParentView.findViewById(R.id.Name);
                number = (TextView) oParentView.findViewById(R.id.Number);
                position = (String) oParentView.getTag();
                p=Integer.valueOf(position);
                nation= sub_strNation.get(p);
                number2=sub_strNumber.get(p);

                //oData.remove(p);
                //m_oListView = (ListView)findViewById(R.id.listView);
                //oAdapter = new ListAdapter(oData);
                //m_oListView.setAdapter(oAdapter);
                //break;

                AlertDialog.Builder oDialog = new AlertDialog.Builder(this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog);
                strMsg="이름: "+name.getText()+"\n"+"전화번호: "+number.getText()+"\n"+"국적: "+ nation;
                oDialog.setMessage(strMsg)
                        .setPositiveButton("확인", null)
                        .setCancelable(true) // 백버튼으로 팝업창이 닫히지 않도록 한다.
                        .show();
                break;
            case R.id.btnAddress:
                mIntent= new Intent(Intent.ACTION_PICK);
                mIntent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(mIntent,0);
                break;

            case R.id.buttonK:
                intent= new Intent(getApplicationContext(),
                        Next.class
                );
                if (resultInt>=50){
                    intent.putExtra("승자","플레이어");
                    intent.putExtra("점수",resultInt);
                    intent.putExtra("day","y-m-d");
                    //Toast.makeText(getApplicationContext(),"하이 짧게", Toast.LENGTH_SHORT).show();
                    toast=Toast.makeText(getApplicationContext(),"you win"+"\n"+"저장되었습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, offsetX,offsetY);
                    toast.show();
                }
                else{
                    intent.putExtra("승자","컴퓨터");
                    intent.putExtra("점수",resultInt);
                    //Toast.makeText(getApplicationContext(),"하이 짧게", Toast.LENGTH_SHORT).show();
                    toast=Toast.makeText(getApplicationContext(),"you lose"+"\n"+"저장되었습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, offsetX,offsetY);
                    toast.show();
                }
                startActivity(intent);
                break;
            case R.id.buttonZ:
                finish();
                break;
            case R.id.buttonA:
                if (resultInt >= 100) {
                    toast=Toast.makeText(getApplicationContext(),"you win"+"\n"+"저장되었습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, offsetX,offsetY);
                    toast.show();

                    intent= new Intent(getApplicationContext(),
                            Next.class
                    );
                    if (resultInt>=50){
                        intent.putExtra("승자","플레이어");
                        intent.putExtra("점수",resultInt);
                        intent.putExtra("day","y-m-d");

                    }
                    else{
                        intent.putExtra("승자","컴퓨터");
                        intent.putExtra("점수",resultInt);

                    }
                    startActivity(intent);


                }
                else if(resultInt<=0){
                    toast=Toast.makeText(getApplicationContext(),"you lose"+"\n"+"저장되었습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, offsetX,offsetY);
                    toast.show();

                    intent= new Intent(getApplicationContext(),
                            Next.class
                    );
                    if (resultInt>=50){
                        intent.putExtra("승자","플레이어");
                        intent.putExtra("점수",resultInt);
                        intent.putExtra("day","y-m-d");
                    }
                    else{
                        intent.putExtra("승자","컴퓨터");
                        intent.putExtra("점수",resultInt);
                    }
                    startActivity(intent);

                }



                comX = "";           // 컴퓨터가 낸 걸 보관하는 장소
                userX = "";          // 사람이 낸 걸 보관하는 장소
                resultX = "";        // 승패 결과를 보관하는 장소
                // 컴퓨터 가위 바위 보 선택
                r = new Random();
                f = r.nextFloat();
                if(f<0.333){
                    comX = "가위";
                } else if (f<0.666){
                    comX = "바위";
                } else {
                    comX = "보";
                }
                computer.setText(comX);     // 컴퓨터가 낸 걸 화면에 표시

                // 사람 가위 바위 보 선택 (누른 버튼에 따라)
                switch(v.getId()) {
                    case R.id.buttonA:
                        userX = "가위";
                        break;
                    case R.id.buttonB:
                        userX = "바위";
                        break;
                    case R.id.buttonC:
                        userX = "보";
                        break;
                }
                user.setText(userX);    // 사람이 낸 걸 화면에 표시

                // 컴퓨터랑 사람의 승패 결정
                if(userX.equals("가위")){     // 사람이 가위를 낸 경우
                    if(comX.equals("가위"))
                        resultX = "비겼어요!!";
                    else if (comX.equals("바위"))
                        resultX = "컴퓨터 승리!!";
                    else        // 컴퓨터가 보를 낸 경우
                        resultX = "플레이어 승리!!";
                } else if(userX.equals("바위")){  // 사람이 바위를 낸 경우
                    if(comX.equals("가위"))
                        resultX = "플레이어 승리!!";
                    else if (comX.equals("바위"))
                        resultX = "비겼어요!!";
                    else        // 컴퓨터가 보를 낸 경우
                        resultX = "컴퓨터 승리!!";
                } else if(userX.equals("보")){       // 사람이 보를 낸 경우
                    if(comX.equals("가위"))
                        resultX = "컴퓨터 승리!!";
                    else if (comX.equals("바위"))
                        resultX = "플레이어 승리!!";
                    else        // 컴퓨터가 보를 낸 경우
                        resultX = "비겼어요!!";
                }
                if (resultX.equals("플레이어 승리!!")){
                    if(userX.equals("가위")){
                        resultInt=resultInt+15;
                    }
                    else if(userX.equals("바위")){
                        resultInt=resultInt+5;
                    }
                    else if(userX.equals("보")){
                        resultInt=resultInt+25;
                    }
                }
                else if (resultX.equals("컴퓨터 승리!!")){
                    if(comX.equals("가위")){
                        resultInt=resultInt-15;
                    }
                    else if(comX.equals("바위")){
                        resultInt=resultInt-5;
                    }
                    else if(comX.equals("보")){
                        resultInt=resultInt-25;
                    }
                }
                result.setText(resultX);    // 승패 결과를 화면에 표시
                progress.setProgress(resultInt) ;
                textA.setText(" "+resultInt);
                break;

            case R.id.buttonB:
                if (resultInt >= 100) {
                    toast=Toast.makeText(getApplicationContext(),"you win"+"\n"+"저장되었습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, offsetX,offsetY);
                    toast.show();

                    intent= new Intent(getApplicationContext(),
                            Next.class
                    );
                    if (resultInt>=50){
                        intent.putExtra("승자","플레이어");
                        intent.putExtra("점수",resultInt);
                        intent.putExtra("day","y-m-d");

                    }
                    else{
                        intent.putExtra("승자","컴퓨터");
                        intent.putExtra("점수",resultInt);

                    }
                    startActivity(intent);


                }
                else if(resultInt<=0){
                    toast=Toast.makeText(getApplicationContext(),"you lose"+"\n"+"저장되었습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, offsetX,offsetY);
                    toast.show();

                    intent= new Intent(getApplicationContext(),
                            Next.class
                    );
                    if (resultInt>=50){
                        intent.putExtra("승자","플레이어");
                        intent.putExtra("점수",resultInt);
                        intent.putExtra("day","y-m-d");
                    }
                    else{
                        intent.putExtra("승자","컴퓨터");
                        intent.putExtra("점수",resultInt);
                    }
                    startActivity(intent);

                }




                comX = "";           // 컴퓨터가 낸 걸 보관하는 장소
                userX = "";          // 사람이 낸 걸 보관하는 장소
                resultX = "";        // 승패 결과를 보관하는 장소
                // 컴퓨터 가위 바위 보 선택
                r = new Random();
                f = r.nextFloat();
                if(f<0.333){
                    comX = "가위";
                } else if (f<0.666){
                    comX = "바위";
                } else {
                    comX = "보";
                }
                computer.setText(comX);     // 컴퓨터가 낸 걸 화면에 표시

                // 사람 가위 바위 보 선택 (누른 버튼에 따라)
                switch(v.getId()) {
                    case R.id.buttonA:
                        userX = "가위";
                        break;
                    case R.id.buttonB:
                        userX = "바위";
                        break;
                    case R.id.buttonC:
                        userX = "보";
                        break;
                }
                user.setText(userX);    // 사람이 낸 걸 화면에 표시

                // 컴퓨터랑 사람의 승패 결정
                if(userX.equals("가위")){     // 사람이 가위를 낸 경우
                    if(comX.equals("가위"))
                        resultX = "비겼어요!!";
                    else if (comX.equals("바위"))
                        resultX = "컴퓨터 승리!!";
                    else        // 컴퓨터가 보를 낸 경우
                        resultX = "플레이어 승리!!";
                } else if(userX.equals("바위")){  // 사람이 바위를 낸 경우
                    if(comX.equals("가위"))
                        resultX = "플레이어 승리!!";
                    else if (comX.equals("바위"))
                        resultX = "비겼어요!!";
                    else        // 컴퓨터가 보를 낸 경우
                        resultX = "컴퓨터 승리!!";
                } else if(userX.equals("보")){       // 사람이 보를 낸 경우
                    if(comX.equals("가위"))
                        resultX = "컴퓨터 승리!!";
                    else if (comX.equals("바위"))
                        resultX = "플레이어 승리!!";
                    else        // 컴퓨터가 보를 낸 경우
                        resultX = "비겼어요!!";
                }
                if (resultX.equals("플레이어 승리!!")){
                    if(userX.equals("가위")){
                        resultInt=resultInt+15;
                    }
                    else if(userX.equals("바위")){
                        resultInt=resultInt+5;
                    }
                    else if(userX.equals("보")){
                        resultInt=resultInt+25;
                    }
                }
                else if (resultX.equals("컴퓨터 승리!!")){
                    if(comX.equals("가위")){
                        resultInt=resultInt-15;
                    }
                    else if(comX.equals("바위")){
                        resultInt=resultInt-5;
                    }
                    else if(comX.equals("보")){
                        resultInt=resultInt-25;
                    }
                }
                result.setText(resultX);    // 승패 결과를 화면에 표시
                progress.setProgress(resultInt) ;
                textA.setText(" "+resultInt);
                break;

            case R.id.buttonC:
                if (resultInt >= 100) {
                    toast=Toast.makeText(getApplicationContext(),"you win"+"\n"+"저장되었습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, offsetX,offsetY);
                    toast.show();

                    intent= new Intent(getApplicationContext(),
                            Next.class
                    );
                    if (resultInt>=50){
                        intent.putExtra("승자","플레이어");
                        intent.putExtra("점수",resultInt);
                        intent.putExtra("day","y-m-d");

                    }
                    else{
                        intent.putExtra("승자","컴퓨터");
                        intent.putExtra("점수",resultInt);

                    }
                    startActivity(intent);


                }
                else if(resultInt<=0){
                    toast=Toast.makeText(getApplicationContext(),"you lose"+"\n"+"저장되었습니다", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, offsetX,offsetY);
                    toast.show();

                    intent= new Intent(getApplicationContext(),
                            Next.class
                    );
                    if (resultInt>=50){
                        intent.putExtra("승자","플레이어");
                        intent.putExtra("점수",resultInt);
                        intent.putExtra("day","y-m-d");
                    }
                    else{
                        intent.putExtra("승자","컴퓨터");
                        intent.putExtra("점수",resultInt);
                    }
                    startActivity(intent);

                }





                comX = "";           // 컴퓨터가 낸 걸 보관하는 장소
                userX = "";          // 사람이 낸 걸 보관하는 장소
                resultX = "";        // 승패 결과를 보관하는 장소
                // 컴퓨터 가위 바위 보 선택
                r = new Random();
                f = r.nextFloat();
                if(f<0.333){
                    comX = "가위";
                } else if (f<0.666){
                    comX = "바위";
                } else {
                    comX = "보";
                }
                computer.setText(comX);     // 컴퓨터가 낸 걸 화면에 표시

                // 사람 가위 바위 보 선택 (누른 버튼에 따라)
                switch(v.getId()) {
                    case R.id.buttonA:
                        userX = "가위";
                        break;
                    case R.id.buttonB:
                        userX = "바위";
                        break;
                    case R.id.buttonC:
                        userX = "보";
                        break;
                }
                user.setText(userX);    // 사람이 낸 걸 화면에 표시

                // 컴퓨터랑 사람의 승패 결정
                if(userX.equals("가위")){     // 사람이 가위를 낸 경우
                    if(comX.equals("가위"))
                        resultX = "비겼어요!!";
                    else if (comX.equals("바위"))
                        resultX = "컴퓨터 승리!!";
                    else        // 컴퓨터가 보를 낸 경우
                        resultX = "플레이어 승리!!";
                } else if(userX.equals("바위")){  // 사람이 바위를 낸 경우
                    if(comX.equals("가위"))
                        resultX = "플레이어 승리!!";
                    else if (comX.equals("바위"))
                        resultX = "비겼어요!!";
                    else        // 컴퓨터가 보를 낸 경우
                        resultX = "컴퓨터 승리!!";
                } else if(userX.equals("보")){       // 사람이 보를 낸 경우
                    if(comX.equals("가위"))
                        resultX = "컴퓨터 승리!!";
                    else if (comX.equals("바위"))
                        resultX = "플레이어 승리!!";
                    else        // 컴퓨터가 보를 낸 경우
                        resultX = "비겼어요!!";
                }
                if (resultX.equals("플레이어 승리!!")){
                    if(userX.equals("가위")){
                        resultInt=resultInt+15;
                    }
                    else if(userX.equals("바위")){
                        resultInt=resultInt+5;
                    }
                    else if(userX.equals("보")){
                        resultInt=resultInt+25;
                    }
                }
                else if (resultX.equals("컴퓨터 승리!!")){
                    if(comX.equals("가위")){
                        resultInt=resultInt-15;
                    }
                    else if(comX.equals("바위")){
                        resultInt=resultInt-5;
                    }
                    else if(comX.equals("보")){
                        resultInt=resultInt-25;
                    }
                }
                result.setText(resultX);    // 승패 결과를 화면에 표시
                progress.setProgress(resultInt) ;
                textA.setText(" "+resultInt);
                break;










































        }





    }



    public class MyGridAdapter extends BaseAdapter {
        Context context;
        int layout;
        int img[];
        LayoutInflater inf;

        public MyGridAdapter(Context C){
            //(Context context, int layout, int[] img) {
            //this.context = context;
            //this.layout = layout;
            //this.img = img;
            //inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            context =C;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return posterID[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null)
//                convertView = inf.inflate(layout, null);
//            ImageView iv = (ImageView) convertView.findViewById(R.id.imageView1);
//            iv.setImageResource(img[position]);
//            return convertView;
            ImageView imageview =new ImageView(context);
            imageview.setLayoutParams(new GridView.LayoutParams(400,400));
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);
            imageview.setPadding(1,1,1,1);

            imageview.setImageResource(posterID[position]);

            final int pos=position;
            imageview.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    View dialogView=(View) View.inflate(MainActivity.this,R.layout.dialog,null);
                    AlertDialog.Builder dlg =new AlertDialog.Builder(MainActivity.this);
                    AlertDialog.Builder next =new AlertDialog.Builder(MainActivity.this);

                    final ImageView ivPoster =(ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);

                    dlg.setTitle("큰 포스터");
                    dlg.setIcon(R.drawable.ic_launcher_foreground);
                    dlg.setView(dialogView);

                    //dlg.setPositiveButton("예",
                    //        new DialogInterface.OnClickListener() {
                    //            public void onClick(DialogInterface dialog, int which) {
                    //                Toast.makeText(getApplicationContext(),"예를 선택했습니다.",Toast.LENGTH_LONG).show();
                    //            }
                    //        });
                    //dlg.setNegativeButton("아니오",
                    //        new DialogInterface.OnClickListener() {
                    //            public void onClick(DialogInterface dialog, int which) {
                    //                Toast.makeText(getApplicationContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
                    //            }
                    //        });
                    ivPoster.setImageResource(posterID[pos]);



                    dlg.setNegativeButton("닫기",null);
                    dlg.show();


                }


            });


            return imageview;
        }
    }

    private GridView.OnItemClickListener gridviewOnItemClickListener
            = new GridView.OnItemClickListener() {

        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {

        }
    };




    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        ListAdapter oAdapter;

        if(resultCode==RESULT_OK){
            Cursor cursor = getContentResolver().query(data.getData(), new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER},null,null,null);
            cursor.moveToFirst();
            sName =cursor.getString(0);
            sNumber= cursor.getString(1);
            cursor.close();
        }
        super.onActivityResult(requestCode,resultCode,data);
        textView1.setText("추가된 이름 : "+sName);
        textView2.setText("추가된 번호 : "+sNumber);



        sub_strNumber.add(String.valueOf(sNumber));
        sub_strName.add(String.valueOf(sName));
        sub_strNation.add(String.valueOf("한국"));

        ItemData oItem = new ItemData();
        oItem.name = sName;
        oItem.nation="한국";
        oItem.imgld = R.drawable.a2;
        oItem.number = sNumber;
        oItem.onClickListener = this;

        System.out.println(strNumber);
        k=k+1;
        if (k%10==1) {
            oItem.imgld = R.drawable.a1;
        }
        else if(k%10==2){
            oItem.imgld = R.drawable.a2;
        }
        else if(k%10==3){
            oItem.imgld = R.drawable.a3;
        }
        else if(k%10==4){
            oItem.imgld = R.drawable.a4;
        }
        else if(k%10==5){
            oItem.imgld = R.drawable.a5;
        }
        else if(k%10==6){
            oItem.imgld = R.drawable.a6;
        }
        else if(k%10==7){
            oItem.imgld = R.drawable.a7;
        }
        else if(k%10==8){
            oItem.imgld = R.drawable.a8;
        }
        else if(k%10==9){
            oItem.imgld = R.drawable.a9;
        }
        else if(k%10==0){
            oItem.imgld = R.drawable.a10;
        }
        else{
            oItem.imgld=R.drawable.china;
        }
        oData.add(oItem);


        m_oListView = (ListView)findViewById(R.id.listView);
        oAdapter = new ListAdapter(oData);
        m_oListView.setAdapter(oAdapter);






    }









    private void requestPermission(String permission){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)){
            Toast.makeText(activity, "Phone state permission allows us to get phone number. Please allow it for additional functionality.", Toast.LENGTH_LONG).show();
        }
        ActivityCompat.requestPermissions(activity, new String[]{permission},PERMISSION_REQUEST_CODE);
    }


    private boolean checkPermission(String permission){
        if (Build.VERSION.SDK_INT >= 23) {
            int result = ContextCompat.checkSelfPermission(activity, permission);
            if (result == PackageManager.PERMISSION_GRANTED){
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }


    void show()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog Title");
        builder.setMessage("AlertDialog Content");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"예를 선택했습니다.",Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }

















}

