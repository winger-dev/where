package com.answerofgod.Login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.answerofgod.jmcdr.R;

/**
 * Created by user on 2015-11-16.
 */
public class Main extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init(){

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
                .setMessage("로그아웃 하시겠습니까?")
                .setPositiveButton("로그아웃", new DialogInterface.OnClickListener()
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
