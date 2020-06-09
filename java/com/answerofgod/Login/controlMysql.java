package com.answerofgod.Login;

import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by user on 2015-11-13.
 */
public class controlMysql extends Thread {

    public static boolean active=false;
    Handler mHandler;
    String url=null;
    int gettype=0;


    public controlMysql(String id, String name, String age, String phone, String mail, String address){ //사용자 정보 업데이트 (6 String)
        mHandler=new Handler();
        String userinfo_url="http://118.47.55.71/android/updateUser.php?id=";
        String userId=id;
        String nameuser="&name="+name;
        String ageuser="&age="+age;
        String phoneuser="&phone="+phone;
        String mailuser="&mail="+mail;
        String addressuser="&address="+address;
        url=userinfo_url+userId+nameuser+ageuser+phoneuser+mailuser+addressuser;
        gettype=0;
    }
    public controlMysql(String id, String serial, String info, String company, String name, String no, int type){ //장비 업데이트 (6 String)
        String userdeviceinfo_url="http://118.47.55.71/android/updateDevice.php?id=";
        mHandler=new Handler();
        String userId=id;
        String nodevice="&no="+no;
        String serialdevice="&serial="+serial;
        String infodevice="&infodevice="+info;
        String companydevice="&companydevice="+company;
        String namedevice="&namedevice="+name;
        gettype=1;
        url=userdeviceinfo_url+userId+infodevice+serialdevice+companydevice+namedevice+nodevice;
    }

    controlMysql(String id, String name, String age, String phone, String mail, String address, String pw){  //사용자 가입 (7 String)
        String insert_userinfo_url="http://118.47.55.71/android/insertUser.php?id=";
        mHandler=new Handler();
        String userId=id;
        String nameuser="&name="+name;
        String ageuser="&age="+age;
        String phoneuser="&phone="+phone;
        String mailuser="&mail="+mail;
        String addressuser="&address="+address;
        String pwuser="&pw="+pw;
        url=insert_userinfo_url+userId+nameuser+ageuser+phoneuser+mailuser+addressuser+pwuser;
        gettype=3;
    }
    public controlMysql(String id, String serial, String info, String company, String name, int type){ //장비추가  (5 String)
        String userdeviceinfo_url="http://118.47.55.71/android/addDevice.php?id=";
        mHandler=new Handler();
        String userId=id;
        String serialdevice="&serial="+serial;
        String infodevice="&infodevice="+info;
        String companydevice="&companydevice="+company;
        String namedevice="&namedevice="+name;
        url=userdeviceinfo_url+userId+infodevice+serialdevice+companydevice+namedevice;
        gettype=4;
    }
    public controlMysql(String id, String no, int type){     //비밀번호 정보 업데이트,장비삭제,특정일 이벤트 가져오기 (2 String)
        String pwinfo_url="http://118.47.55.71/android/updatePw.php?id=";
        String userdeviceinfo_url="http://118.47.55.71/android/removeDevice.php?id=";
        String onedayeventinfo_url="http://118.47.55.71/android/getonedayevent.php?id=";
        String sumconsumption_url="http://118.47.55.71/android/getsumconsumption.php?id=";
        mHandler=new Handler();
        String userId=id;


        Log.e("active", active + "");

        switch(type){
            case 0:
                String userPw="&pw="+no;
                url=pwinfo_url+userId+userPw;
                gettype=2;
                break;
            case 1:
                String nouser="&no="+no;
                url=userdeviceinfo_url+userId+nouser;
                gettype=5;
                break;
            case 2:
                String oneday="&date="+no;
                url=onedayeventinfo_url+userId+oneday;
                gettype=12;
                break;
            case 3:
                String date="&date="+no;
                url=sumconsumption_url+userId+date;
                gettype=13;
                break;


        }


    }
    public controlMysql(String id, String serial, String date){     //전력사용량 가져오기 (3 String)
        String userdeviceinfo_url="http://118.47.55.71/android/getconsumption.php?id=";
        mHandler=new Handler();
        String userId=id;
        String table="&table="+id+"_"+serial+"_"+date.replace("-","").substring(0,6);
        String filter="&filter="+date;
        Log.e("active", active + "");
        url=userdeviceinfo_url+userId+table+filter;
        gettype=11;

    }

    public controlMysql(String id,int type){     //사용자/장비 정보 가져오기/idchk/event가져오기/drel table 생성 (1 String)
        String userinfo_url="http://118.47.55.71/android/getuserinfo.php?id=";
        String userdeviceinfo_url="http://118.47.55.71/android/getuserdeviceinfo.php?id=";
        String idchk_url="http://118.47.55.71/android/chkid.php?id=";
        String eventinfo_url="http://118.47.55.71/android/geteventinfo.php?id=";
        String addUserTable_url="http://118.47.55.71/android/addUserTable.php?id=";
        mHandler=new Handler();
        String userId=id;

        switch(type){
            case 0:
                url=userinfo_url+userId;
                gettype=6;
                break;
            case 1:
                url=userdeviceinfo_url+userId;
                gettype=7;
                break;
            case 2:
                url=idchk_url+userId;
                gettype=8;
                break;
            case 3:
                url=userdeviceinfo_url+userId;
                gettype=9;
                break;
            case 4:
                url=eventinfo_url+userId;
                gettype=10;
                break;
            case 5:
                url=addUserTable_url+userId;
                gettype=14;
                break;
        }


    }


    /**
     * Calls the <code>run()</code> method of the Runnable object the receiver
     * holds. If no Runnable is set, does nothing.
     *
     * @see Thread#start
     */
    @Override
    public void run() {
        super.run();
        if(active){
            Log.e("gettype",gettype+","+url);
            StringBuilder jsonHtml = new StringBuilder();
            try {
                URL phpUrl = new URL(url);

                HttpURLConnection conn = (HttpURLConnection)phpUrl.openConnection();

                if ( conn != null ) {
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);

                    if ( conn.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        while ( true ) {
                            String line = br.readLine();
                            if ( line == null )
                                break;
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            show(jsonHtml.toString());
        }



    }

    void show(final String result){
        mHandler.post(new Runnable(){

            @Override
            public void run() {
                //Log.e("result",gettype+result);

                switch (gettype){

                    case 3:
                        Login.regist_result(result);
                        break;

                    case 8:
                        chkId.chkidresult(result);
                        break;
                    
                    case 14:
                        Login.regist2_result(result);//drel 테이블생성
                        break;
                    default:
                        break;
                }
            }
       });

    }


}
