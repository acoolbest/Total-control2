package com.example.nelicia.totalcontrol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnGenericMotionListener,View.OnKeyListener {

    private ServerClass server;
    private final int port=6000;
    private TextView ipTextview,keybd,mouse;
    private View view;

    private  HashMap<Integer,Integer> keyMap;
    private  HashMap<Integer,Integer> specialKeyMap;

    private void setKeyMap()
    {
        keyMap=new HashMap<Integer,Integer>();
        //keyMap.put(9,0);// \t
        keyMap.put(61,180); //tab

        //keyMap.put(10,1);  //\n
        keyMap.put(66,98);  //enter

        keyMap.put(62,3);  //  space
        //keyMap.put(33,4);  //  !
        //keyMap.put(34,5);  //  "
        //keyMap.put(35,6);  //  #
        //keyMap.put(36,7);  //  $
        //keyMap.put(37,8);  //  %
        //keyMap.put(38,9);  //  &
        keyMap.put(75,10);  //  '
        //keyMap.put(40,11);  //  (
        //keyMap.put(41,12);  //  )
        //keyMap.put(42,13);  //  *
        //keyMap.put(43,14);  //  +
        keyMap.put(55,15);  //  ,
        keyMap.put(69,16);  //  -
        keyMap.put(56,17);  //  .
        keyMap.put(76,18);  //  /
        keyMap.put(7,19);  //  0
        keyMap.put(8,20);  //  1
        keyMap.put(9,21);  //  2
        keyMap.put(10,22);  //  3
        keyMap.put(11,23);  //  4
        keyMap.put(12,24);  //  5
        keyMap.put(13,25);  //  6
        keyMap.put(14,26);  //  7
        keyMap.put(15,27);  //  8
        keyMap.put(16,28);  //  9
        //keyMap.put(58,29);  //  :
        keyMap.put(74,30);  //  ;
        //keyMap.put(60,31);  //  <
        keyMap.put(70,32);  //  =
        //keyMap.put(62,33);  //  >
        //keyMap.put(63,34);  //  ?
        //keyMap.put(64,35);  //  @

        /*
        keyMap.put(65,42);  //  A
        keyMap.put(66,43);  //  B
        keyMap.put(67,44);  //  C
        keyMap.put(68,45);  //  D
        keyMap.put(69,46);  //  E
        keyMap.put(70,47);  //  F
        keyMap.put(71,48);  //  G
        keyMap.put(72,49);  //  H
        keyMap.put(73,50);  //  I
        keyMap.put(74,51);  //  J
        keyMap.put(75,52);  //  K
        keyMap.put(76,53);  //  L
        keyMap.put(77,54);  //  M
        keyMap.put(78,55);  //  N
        keyMap.put(79,56);  //  O
        keyMap.put(80,57);  //  P
        keyMap.put(81,58);  //  Q
        keyMap.put(82,59);  //  R
        keyMap.put(83,60);  //  S
        keyMap.put(84,61);  //  T
        keyMap.put(85,62);  //  U
        keyMap.put(86,63);  //  V
        keyMap.put(87,64);  //  W
        keyMap.put(88,65);  //  X
        keyMap.put(89,66);  //  Y
        keyMap.put(90,67);  //  Z
        */

        keyMap.put(91,36);  //  [
        keyMap.put(92,37);  //  \
        keyMap.put(93,38);  //  ]
        keyMap.put(94,39);  //  ^
        keyMap.put(95,40);  //  _
        keyMap.put(68,41);  //  `

        keyMap.put(29,42);  // a
        keyMap.put(30,43);  //b
        keyMap.put(31,44);  //c
        keyMap.put(32,45);  //d
        keyMap.put(33,46);  //e
        keyMap.put(34,47);  //f
        keyMap.put(35,48);  //g
        keyMap.put(36,49);  //h
        keyMap.put(37,50);  //i
        keyMap.put(38,51);  //j
        keyMap.put(39,52);  //k
        keyMap.put(40,53);  //l
        keyMap.put(41,54);  //m
        keyMap.put(42,55);  //n
        keyMap.put(43,56);  //o
        keyMap.put(44,57);  //p
        keyMap.put(45,58);  //q
        keyMap.put(46,59);  //r
        keyMap.put(47,60);  //s
        keyMap.put(48,61);  //t
        keyMap.put(49,62);  //u
        keyMap.put(50,63);  //v
        keyMap.put(51,64);  //w
        keyMap.put(52,65);  //x
        keyMap.put(53,66);  //y
        keyMap.put(54,67);  //z

        //keyMap.put(123,68);  //{
        //keyMap.put(124,69);  //|
        //keyMap.put(125,70);  //}
        //keyMap.put(126,71);  //~


        keyMap.put(19, 181);   //up
        keyMap.put(20,96);   //down
        keyMap.put(21,141);   //left
        keyMap.put(22,170);   //right

        keyMap.put(57,75);   //Lalt
        keyMap.put(58,76);   //Ralt

        keyMap.put(59,175);   //Lshift
        keyMap.put(60,176);  //Rshift

        keyMap.put(67,78);   //backspace

        keyMap.put(92,158);  //pageUP
        keyMap.put(93,157);  //pageDown

        keyMap.put(111,99);   //esc

        keyMap.put(112,93);   //del

        keyMap.put(113,90);   //Lctrl
        keyMap.put(114,91);   //Rctrl

        keyMap.put(122,132);   //home
        keyMap.put(123,97);  //end
        keyMap.put(124,133);   //insert

        keyMap.put(131,76);   //f1
        keyMap.put(132,113);   //f2
        keyMap.put(133,119);   //f3
        keyMap.put(134,120);   //f4
        keyMap.put(135,121);   //f5
        keyMap.put(136,122);   //f6
        keyMap.put(137,123);   //f7
        keyMap.put(138,124);   //f8
        keyMap.put(139,125);   //f9
        keyMap.put(139,103);   //f10
        keyMap.put(140,104);   //f11
        keyMap.put(141,105);   //f12

        keyMap.put(143,156);  //numlock
        keyMap.put(144,146);   //num0
        keyMap.put(145,147);   //num1
        keyMap.put(146,148);   //num2
        keyMap.put(147,149);   //num3
        keyMap.put(148,150);   //num4
        keyMap.put(149,151);   //num5
        keyMap.put(150,152);   //num6
        keyMap.put(151,153);   //num7
        keyMap.put(152,154);   //num8
        keyMap.put(153,155);   //num9

        keyMap.put(265,129);  //hangul




        /*
        specialKeyMap=new HashMap<Integer,Integer>();

        specialKeyMap.put(19,181);   //up
        specialKeyMap.put(20,96);   //down
        specialKeyMap.put(21,141);   //left
        specialKeyMap.put(22,170);   //right

        specialKeyMap.put(57,75);   //Lalt
        specialKeyMap.put(58,76);   //Ralt

        specialKeyMap.put(59,175);   //Lshift
        specialKeyMap.put(60,176);  //Rshift

        specialKeyMap.put(67,78);   //backspace

        specialKeyMap.put(92,158);  //pageUP
        specialKeyMap.put(93,157);  //pageDown

        specialKeyMap.put(111,99);   //esc

        specialKeyMap.put(112,93);   //del

        specialKeyMap.put(113,90);   //Lctrl
        specialKeyMap.put(114,91);   //Rctrl

        specialKeyMap.put(122,132);   //home
        specialKeyMap.put(123,97);  //end
        specialKeyMap.put(124,133);   //insert

        specialKeyMap.put(131,76);   //f1
        specialKeyMap.put(132,113);   //f2
        specialKeyMap.put(133,119);   //f3
        specialKeyMap.put(134,120);   //f4
        specialKeyMap.put(135,121);   //f5
        specialKeyMap.put(136,122);   //f6
        specialKeyMap.put(137,123);   //f7
        specialKeyMap.put(138,124);   //f8
        specialKeyMap.put(139,125);   //f9
        specialKeyMap.put(139,103);   //f10
        specialKeyMap.put(140,104);   //f11
        specialKeyMap.put(141,105);   //f12

        specialKeyMap.put(143,156);  //numlock
        specialKeyMap.put(144,146);   //num0
        specialKeyMap.put(145,147);   //num1
        specialKeyMap.put(146,148);   //num2
        specialKeyMap.put(147,149);   //num3
        specialKeyMap.put(148,150);   //num4
        specialKeyMap.put(149,151);   //num5
        specialKeyMap.put(150,152);   //num6
        specialKeyMap.put(151,153);   //num7
        specialKeyMap.put(152,154);   //num8
        specialKeyMap.put(153,155);   //num9

        specialKeyMap.put(265,129);  //hangul
        */












    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ipTextview=(TextView)findViewById(R.id.ip);
        ipTextview.setText(getLocalIpAddress());

        setKeyMap();

        keybd=(TextView)findViewById(R.id.keybd);
        mouse=(TextView)findViewById(R.id.mouse);
        view=(View)findViewById(R.id.rootlayout);
        view.setOnGenericMotionListener(this);
        view.setOnKeyListener(this);

        view.setFocusable(true);

    }

    public String getLocalIpAddress()
    {
        final String IP_NONE = "N/A";
        final String WIFI_DEVICE_PREFIX = "eth";

        String LocalIP = IP_NONE;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        if( LocalIP.equals(IP_NONE) )
                            LocalIP = new String(inetAddress.getAddress().toString());
                        else if( intf.getName().startsWith(WIFI_DEVICE_PREFIX) )
                            LocalIP = new String(inetAddress.getAddress().toString());
                    }
                }
            }
        } catch (SocketException e) {
            Log.e("MainActivity", "getLocalIpAddress Exception:" + e.toString());
        }
        return LocalIP;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem connectMenu=menu.add(0,1,0,"Connect"); //group id, item id, order ,text
        MenuItem disconnectMenu=menu.add(0,2,0,"Disconnect"); //group id, item id, order ,text
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case 1:

                LinearLayout dialogLayout= new LinearLayout(this);
                final EditText editText=new EditText(this);
                editText.setText(String.valueOf(port));
                editText.setMinWidth(300);
                dialogLayout.addView(editText);

                new AlertDialog.Builder(this).setTitle("port입력").setView(dialogLayout).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        server = ServerClass.getInstance(Integer.valueOf(editText.getText().toString()));
                        server.start();
                        Toast.makeText(getBaseContext(), "connect port:" + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }).show();

                break;
        }
        return false;
    }


    @Override
    public boolean onGenericMotion(View v, MotionEvent event) {

        byte [] buffer= new byte[9];
        buffer[0]='m';
        buffer[1]='r';
        buffer[2]=1;
        switch(event.getButtonState())
        {
            case MotionEvent.BUTTON_PRIMARY:
                buffer[1]='p';
                buffer[2]=1;
                break;
            case MotionEvent.BUTTON_TERTIARY:
                buffer[1]='p';
                buffer[2]=2;
                break;
            case MotionEvent.BUTTON_SECONDARY:

                buffer[1]='p';
                buffer[2]=3;
                break;
        }

        switch(event.getAction())
        {

            case MotionEvent.ACTION_SCROLL:

                buffer[7]='s';
                if(event.getAxisValue(MotionEvent.AXIS_VSCROLL)>0)
                {
                    buffer[8]='u';
                }
                else
                {
                    buffer[8]='d';
                }
                break;
        }

        int x=(int)event.getX();
        int y=(int)event.getY();
        buffer[3]=(byte)(x/100);
        buffer[4]=(byte)(x%100);
        buffer[5]=(byte)(y/100);
        buffer[6]=(byte)(y%100);

        Log.i("mouse",String.valueOf(buffer[0])+" "+String.valueOf(buffer[1])+" "+String.valueOf(buffer[2])+" "+String.valueOf(buffer[3])+" "+String.valueOf(buffer[4])+" "+String.valueOf(buffer[5])+" "+String.valueOf(buffer[6])+" "+String.valueOf(buffer[7])+" "+String.valueOf(buffer[8]));


        server.send(buffer);
        view.setFocusable(true);


        return true;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        byte[] buffer=new byte[9];
        buffer[0]='k';


        if (keyCode!=0) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.i("keyboard down", "getUnicodeChar(ascii) :" + keyMap.get(keyCode));
                buffer[1] = 'p';
                buffer[2] = keyMap.get(keyCode).byteValue();
            } else if (event.getAction() == KeyEvent.ACTION_UP) {
                Log.i("keyboard up", "getUnicodeChar(ascii) :" + keyMap.get(keyCode));
                buffer[1] = 'u';
                buffer[2] = keyMap.get(keyCode).byteValue();
            }
        }




        server.send(buffer);

        view.setFocusable(true);

        return true;
    }
}
