package com.answerofgod.Login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.answerofgod.jmcdr.R;

public class Login extends Activity implements View.OnClickListener{
    DialogInterface manageDialog = null;

    static EditText userId,userPw;
    Button Btnlogin;
    TextView regist,findpw;
    static public boolean login_state=false;
    static boolean pw_ok=false,id_ok=false;
    static String getpw="",getid="";
    final int code_chkid=1000;
    static String tempName="",tempAge="",tempPhone="",tempMail="",tempAddress="";
    static public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    void init(){
        userId=(EditText)findViewById(R.id.userId);
        userPw=(EditText)findViewById(R.id.userPw);
        Btnlogin=(Button)findViewById(R.id.Btnlogin);
        regist=(TextView)findViewById(R.id.regist);
        findpw=(TextView)findViewById(R.id.findpw);
        Btnlogin.setOnClickListener(this);
        regist.setOnClickListener(this);
        findpw.setOnClickListener(this);
        mContext=this;

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Btnlogin:
                if(userId.getText().toString()!=null&&userPw.getText().toString()!=null) {

                    login_proc(login_state);
                }else
                    Toast.makeText(this, "ID와 PASSWORD를 모두 입력해 주세요.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.regist:
                    regist();
                break;
            case R.id.findpw:

                break;
        }
    }
    void login_proc(boolean login){ //로그인
        if(!login){
            String id=userId.getText().toString();
            String pw=userPw.getText().toString();
            loginMysql idchk=new loginMysql(id,pw);
            loginMysql.active=true;
            idchk.start();
        }else{

        }
    }
    void regist(){ //회원가입 다이얼로그

        AlertDialog.Builder builder2;
        builder2=new AlertDialog.Builder(this);
        builder2.setTitle("회원 가입")
                .setMessage("회원 가입정보를 모두 입력해 주세요.");

        final LayoutInflater inflater=getLayoutInflater();
        View layout=inflater.inflate(R.layout.user_layout, null);
        builder2.setView(layout);
        final Button id=(Button)layout.findViewById(R.id.UserId);



        final EditText name=(EditText)layout.findViewById(R.id.UserName);
        final Button pw=(Button)layout.findViewById(R.id.UserPw);

        final EditText age=(EditText)layout.findViewById(R.id.UserAge);
        final EditText phone=(EditText)layout.findViewById(R.id.UserPhone);
        final EditText mail=(EditText)layout.findViewById(R.id.UserMail);
        final EditText address=(EditText)layout.findViewById(R.id.UserAddress);
        if(id_ok){
            id.setText(getid);
        }else{
            id.setText("버튼을 누르세요.");
        }if(pw_ok){
            pw.setText("비밀번호 입력완료");
        }else{
            pw.setText("비밀번호 설정하기");
        }if(tempName.length()!=0){
            name.setText(tempName);
        }if(tempAge.length()!=0){
            age.setText(tempAge);
        }if(tempPhone.length()!=0){
            phone.setText(tempPhone);
        }if(tempMail.length()!=0){
            mail.setText(tempMail);
        }if(tempAddress.length()!=0){
            address.setText(tempAddress);
        }
        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempName=name.getText().toString();
                tempAge=age.getText().toString();
                tempPhone=phone.getText().toString();
                tempMail=mail.getText().toString();
                tempAddress=address.getText().toString();
                Intent i = new Intent(mContext,chkId.class);
                startActivityForResult(i, code_chkid);


            }
        });
        pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempName=name.getText().toString();
                tempAge=age.getText().toString();
                tempPhone=phone.getText().toString();
                tempMail=mail.getText().toString();
                tempAddress=address.getText().toString();
                set_password();
            }
        });


        builder2.setPositiveButton("가입 하기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String infoId=id.getText().toString();
                String infoName=name.getText().toString();
                String infoAge=age.getText().toString();
                String infoPhone=phone.getText().toString();
                String infoMail=mail.getText().toString();
                String infoAddress=address.getText().toString();
                if(infoName.length()!=0&&infoAge.length()!=0&&infoPhone.length()!=0&&infoMail.length()!=0&&infoAddress.length()!=0&&getpw.length()!=0){
                    sql_control.userRegist(infoId,infoName, infoAge, infoPhone, infoMail, infoAddress, getpw);

                }else{
                    Toast.makeText(getApplication(),"정보를 모두 입력해 주세요.",Toast.LENGTH_SHORT).show();

                    regist();
                }
            }
        }).setNegativeButton("돌아가기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getid=getpw=tempName=tempAge=tempPhone=tempMail=tempAddress="";
                Toast.makeText(getApplication(),"회원가입이 취소되었습니다.",Toast.LENGTH_SHORT).show();
            }
        }).create();
        manageDialog=builder2.show();
    }
    static public void regist_result(String result){    //회원가입 결과
        Log.e("regist_result", result);
        controlMysql.active=false;
        if(result.contains("true")){
            sql_control.maketable(getid);
            //Toast.makeText(mContext,"회원가입에 성공하였습니다.",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext,"회원가입에 실패하였습니다.",Toast.LENGTH_SHORT).show();
        }
    }
    static public void regist2_result(String result){    //회원가입 결과
        Log.e("regist_result", result);
        controlMysql.active=false;
        if(result.contains("true")){

            Toast.makeText(mContext,"회원가입에 성공하였습니다.",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext,"회원가입에 실패하였습니다.",Toast.LENGTH_SHORT).show();
        }
    }


    private void set_password() { //사용자 비밀번호 입력 다이얼로그
        getpw="";
        pw_ok = false;
        final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("비밀번호 변경하기")
                .setMessage("사용할 비밀번호를 연속으로 입력해 주세요.");
        final LayoutInflater inflater=getLayoutInflater();
        View layout=inflater.inflate(R.layout.pw_layout, null);
        builder2.setView(layout);
        final EditText pw1=(EditText)layout.findViewById(R.id.pw1);
        final EditText pw2=(EditText)layout.findViewById(R.id.pw2);
        builder2.setPositiveButton("사용하기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String infoPw1=pw1.getText().toString();
                String infoPw2=pw2.getText().toString();
                Log.e("pw",infoPw1+infoPw2);
                if(infoPw1.length()!=0){
                    if(infoPw1.equals(infoPw2)) {
                        pw_ok = true;
                        getpw = infoPw1;
                        manageDialog.dismiss();
                        regist();
                    }else {
                        Toast.makeText(getApplication(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        set_password();
                    }
                }else{
                    Toast.makeText(getApplication(),"비밀번호를 모두 입력해 주세요.",Toast.LENGTH_SHORT).show();
                    set_password();
                }
            }
        }).setNegativeButton("돌아가기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplication(),"비밀번호가 입력 되지 않았습니다.",Toast.LENGTH_SHORT).show();
            }
        }).create().show();
    }

    static public void result_login(String result,String pw,String name){
        loginMysql.active=false;
        if(result.equals("false"))
            Toast.makeText(mContext,"사용자 ID가 없습니다.",Toast.LENGTH_SHORT).show();
        else{
            if(pw.equals(result)) {
                Toast.makeText(mContext, name + "님 로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext,Main.class);
                intent.putExtra("name",name);
                intent.putExtra("id",userId.getText().toString());
                userId.setText("");
                userPw.setText("");
                mContext.startActivity(intent);

            }else
                Toast.makeText(mContext,"PW가 틀렸습니다.",Toast.LENGTH_SHORT).show();
        }

       // Toast.makeText(mContext,result,Toast.LENGTH_SHORT).show();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case code_chkid:
                    if(data.getExtras().getBoolean("ok")){
                        getid=data.getExtras().getString("id");

                        id_ok=true;
                    }
                    manageDialog.dismiss();
                    regist();
                    break;
            }
        }else{
            Toast.makeText(this,"작업이 취소되었습니다.",Toast.LENGTH_SHORT).show();
        }

    }



    /**
     * Called when the activity has detected the user's press of the back
     * key.  The default implementation simply finishes the current activity,
     * but you can override this to do whatever you want.
     */
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setTitle(getString(R.string.app_name))
                .setMessage("종료 하시겠습니까?")
                .setPositiveButton("종료", new DialogInterface.OnClickListener()
                {

                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                    {
                        //Intent intent=new Intent(getApplication(),Login.class);
                        //startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {

            }}) .create().show();
    }
}
