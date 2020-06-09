package com.answerofgod.Login;

import android.util.Log;

/**
 * Created by user on 2015-12-01.
 */
public class sql_control {


    public static void pwUpdate(String id,String pw){ //비번 변경하기
        controlMysql pwchange=new controlMysql(id,pw,0);
        controlMysql.active=true;
        pwchange.start();
    }
    static public void removeDevice(String id,String no){    //장비 삭제하기


        Log.e("removeDevice", id + "," + no);
        controlMysql delinfo=new controlMysql(id,no,1);
        controlMysql.active=true;
        delinfo.start();
    }
    static public void get_onedayevent(String id,String date){    //장비 삭제하기


        Log.e("get_onedayevent", id + "," + date);
        controlMysql onedayevent=new controlMysql(id,date,2);
        controlMysql.active=true;
        onedayevent.start();
    }
    static public void get_sumconsumption(String id,String date){    //전력사용량 합 가져오기


        Log.e("get_onedayevent", id + "," + date);
        controlMysql onedayevent=new controlMysql(id,date,3);
        controlMysql.active=true;
        onedayevent.start();
    }

    static public void get_userInfo(String id,int type){ //사용자 정보가져오기
        controlMysql getinfo=new controlMysql(id,type);
        controlMysql.active=true;
        getinfo.start();
    }
    static public void deviceUpdate(String id,String serial,String info,String company,String name,String no,int type){    //장비수정하기
        controlMysql updateinfo=new controlMysql(id,serial,info,company,name,no,1);
        controlMysql.active=true;
        updateinfo.start();
    }
    static public void userUpdate(String id,String name,String age,String phone,String mail,String address){    //회원정보 수정하기
        controlMysql updateinfo=new controlMysql(id,name,age,phone,mail,address);
        controlMysql.active=true;
        updateinfo.start();
    }
    static public void userRegist(String id,String name,String age,String phone,String mail,String address,String pw){    //회원 가입하기
        controlMysql registinfo=new controlMysql(id,name,age,phone,mail,address,pw);
        controlMysql.active=true;
        registinfo.start();
    }
    static public void maketable(String id){    //회원 가입하기
        controlMysql addTable=new controlMysql(id,5);
        controlMysql.active=true;
        addTable.start();
    }
    static public void deviceAdd(String id,String serial,String info,String company,String name,int type){    //장비추가하기
        controlMysql addinfo=new controlMysql(id,serial,info,company,name,type);
        controlMysql.active=true;
        addinfo.start();
    }
    static public void idChk(String id){    //장비추가하기
        controlMysql idchk=new controlMysql(id,2);
        controlMysql.active=true;
        idchk.start();
    }
    static public void get_consumption(String id,String serial,String date){    //전력 사용량 가져오기
        controlMysql getconsumption=new controlMysql(id,serial,date);
        controlMysql.active=true;
        getconsumption.start();
    }

}
