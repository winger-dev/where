package com.answerofgod.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.answerofgod.jmcdr.R;

/**
 * Created by user on 2015-12-01.
 */
public class chkId extends Activity implements View.OnClickListener{
    EditText id;
    static TextView description;
    Button chkId,useId;
    static boolean chkok=false;
    String chkid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.id_layout);
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        int width = (int) (display.getWidth() * 0.7); //Display 사이즈의 70%



        getWindow().getAttributes().width = width;


        init();
    }
    void init(){
        id=(EditText)findViewById(R.id.id);
        description=(TextView)findViewById(R.id.description);
        chkId=(Button)findViewById(R.id.chkId);
        useId=(Button)findViewById(R.id.useId);
        chkId.setOnClickListener(this);
        useId.setOnClickListener(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chkId:
                chkid=id.getText().toString();
                Log.e("id", chkid);
                if(chkid.length()!=0){
                    sql_control.idChk(chkid);
                }else{
                    Toast.makeText(getApplication(),"ID를 입력해 주세요.",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.useId:
                if(chkok){
                    Intent intent = new Intent();
                    intent.putExtra("ok",true);
                    intent.putExtra("id",chkid);
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    setResult(RESULT_CANCELED);
                }

                break;
        }
    }


    static public void chkidresult(String result){  //id 중복체크 결과
        Log.e("chkidresult",result);
        if(result.contains("false")&&!result.contains("{")){
            Log.e("chkidresult","false");
            description.setText("사용이 가능합니다.");
            chkok=true;
        }else{
            Log.e("chkidresult","true");
            description.setText("중복된 ID입니다.");
            chkok=false;
        }

    }

}
