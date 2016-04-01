package com.example.totalcontrol;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnGenericMotionListener;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MouseKeyboardInputActivity extends Activity implements
		OnGenericMotionListener, android.view.View.OnKeyListener {

	public View view;
	private Socket socket;
	
	private long posta;
	private long valueLong;
	private float valueFloat;
	private float postValueFloat;
	private int []pressingKey;
	
	private int controlIndex;
	
	private float[] varXY;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mousekeyboardinput);
		view = (View) findViewById(R.id.mousekeyboarinputLayout);
		view.setFocusable(true);
		view.setOnGenericMotionListener(this);
		view.setOnKeyListener(this);
		pressingKey=new int[3];
		socket = new Socket();
		socket.start();
		varXY=new float[2];
		synchronizeXY(0);
		Log.i("���콺 Ű���� �ν� ��Ƽ��Ƽ", "�ʱ�ȭ �Ϸ�");
	}
	
	public void synchronizeXY(int index)
	{
		int [] XY=new int[2];
		controlIndex=index;
		if(socket.socketforaccept.pcSizeX[index]!=0 && socket.socketforaccept.pcSizeX!=null && socket.socketforaccept.pcSizeY[index]!=0 && socket.socketforaccept.pcSizeY!=null  && view.getWidth()!=0 && view.getHeight()!=0)
		{
			XY[0]=socket.socketforaccept.pcSizeX[index];
			XY[1]=socket.socketforaccept.pcSizeY[index];
			
			varXY[0]=(socket.socketforaccept.pcSizeX[index]/view.getWidth())+1;
			varXY[1]=(socket.socketforaccept.pcSizeY[index]/view.getHeight())+1;
			if(varXY[0]==0)
			{
				varXY[0]=1;
			}
			if(varXY[1]==0)
			{
				varXY[1]=1;	
			}
		}
		else
		{
			varXY[0]=1;
			varXY[1]=1;		
		}
	}

	// ���콺 �̺�Ʈ ó��
	@SuppressLint("NewApi")
	@Override
	public boolean onGenericMotion(View v, MotionEvent event) { //���콺 �̺�Ʈ (�������� ù��° ���� 0==���콺 1==Ű����, �ι�° ���� ������ ��° ���� 0==��Ŭ�� 1==��Ŭ�� 3==��Ŭ�� 4==���̵�  0==���콺 �̵� 
		// TODO Auto-generated method stub
		if (event.getToolType(0) == MotionEvent.TOOL_TYPE_MOUSE) {
			float x = event.getAxisValue(MotionEvent.AXIS_X);
			float y = event.getAxisValue(MotionEvent.AXIS_Y);
			
			String str;
			int clickFlag = 0;
			
			if(socket.socketforaccept.synchronizeFlag[controlIndex]==0)
			{
				synchronizeXY(controlIndex);
				socket.socketforaccept.synchronizeFlag[controlIndex]=1;
			}
	
			
			switch (event.getButtonState()) {
			case MotionEvent.BUTTON_SECONDARY: // ��Ŭ��
				Log.i("Right Click",
						"(" + String.valueOf(x)+"*"+String.valueOf(varXY[0])+"="+String.valueOf(x*varXY[0]) + "," + String.valueOf(y)+"*"+String.valueOf(varXY[1])+"="+ String.valueOf(y*varXY[1]) + ")"
								+ "Right Click");
				str = "0 " + String.valueOf(x*varXY[0]) + " " + String.valueOf(y*varXY[1]) + " "
						+ "2\0";
				socket.index = controlIndex;
				socket.buff = str.getBytes();
				socket.flag = 0;
				clickFlag = 1;
				break;
			case MotionEvent.BUTTON_TERTIARY: // �� Ŭ��
				Log.i("Wheel Click",
						"(" + String.valueOf(x)+"*"+String.valueOf(varXY[0])+"="+String.valueOf(x*varXY[0]) + "," + String.valueOf(y)+"*"+String.valueOf(varXY[1])+"="+ String.valueOf(y*varXY[1]) + ")"
								+"Wheel Click");
				str = "0 " + String.valueOf(x*varXY[0]) + " " + String.valueOf(y*varXY[1]) + " "
						+ "3\0";
				socket.index = controlIndex;
				socket.buff = str.getBytes();
				socket.flag = 0;
				clickFlag = 1;
				break;
			}
			if (clickFlag == 0) {
				if ((valueLong=event.getDownTime())!=posta) {  //��Ŭ��
					Log.i("Left Click",
							"(" + String.valueOf(x)+"*"+String.valueOf(varXY[0])+"="+String.valueOf(x*varXY[0]) + "," + String.valueOf(y)+"*"+String.valueOf(varXY[1])+"="+ String.valueOf(y*varXY[1]) + ")"
									+ "Left Click");
					str = "0 " + String.valueOf(x*varXY[0]) + " " + String.valueOf(y*varXY[1])
							+ " " + "1\0";
					socket.index = controlIndex;
					socket.buff = str.getBytes();
					socket.flag = 0;
					clickFlag = 1;
					posta=valueLong;
				}
			}
			
			if (clickFlag == 0) {
				if ((valueFloat = event.getAxisValue(MotionEvent.AXIS_VSCROLL)) != 0) {  //���� ���̵�
					Log.i("Wheel move", String.valueOf(valueFloat));
					str = "0 " + String.valueOf(valueFloat) + " 0 " + "4\0";
					socket.index = controlIndex;
					socket.buff = str.getBytes();
					socket.flag = 0;
					clickFlag = 1;
				}
			}

			if (clickFlag == 0) {   //�̵�
				Log.i("Mouse move", "(" + String.valueOf(x)+"*"+String.valueOf(varXY[0])+"="+String.valueOf(x*varXY[0]) + "," + String.valueOf(y)+"*"+String.valueOf(varXY[1])+"="+ String.valueOf(y*varXY[1]) + ")");
				str = "0 " + String.valueOf(x*varXY[0]) + " " + String.valueOf(y*varXY[1]) + " "
						+ "0\0";
				socket.index = controlIndex;
				socket.buff = str.getBytes();
				socket.flag = 0;
			}

		}
		return false;
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		String str;
		int scanCode = event.getScanCode();
		int keycode = keyCode;
		
		if(socket.socketforaccept.synchronizeFlag[controlIndex]==0)
		{
			synchronizeXY(controlIndex);
			socket.socketforaccept.synchronizeFlag[controlIndex]=1;
		}

		if (pressingKey[0] == 1 && pressingKey[1] == 1) {
			if (scanCode == 2 || scanCode == 3 || scanCode == 4
					|| scanCode == 5 || scanCode == 6) {
				synchronizeXY(scanCode-2);
				Log.i("controlConvert", String.valueOf(controlIndex)+"���� ��ȯ");
			}

			else {
				switch (event.getAction()) {
				case KeyEvent.ACTION_DOWN: // ������
					Log.i("down",
							String.valueOf(event.getScanCode() + ","
									+ String.valueOf(keycode)));

					switch (scanCode) {
					case 29:
						pressingKey[0] = 1;
						break;
					case 42:
						pressingKey[1] = 1;
						break;
					}

					str = "1 " + String.valueOf(scanCode) + " 1 " + "0\0";
					socket.index = controlIndex;
					socket.buff = str.getBytes();
					socket.flag = 0;
					break;
				case KeyEvent.ACTION_UP: // ����
					Log.i("up",
							String.valueOf(event.getScanCode() + ","
									+ String.valueOf(keycode)));

					switch (scanCode) {
					case 29:
						pressingKey[0] = 0;
						break;
					case 42:
						pressingKey[1] = 0;
						break;
					}

					str = "1 " + String.valueOf(scanCode) + " 0 " + "0\0";
					socket.index = controlIndex;
					socket.buff = str.getBytes();
					socket.flag = 0;
					break;

				}
			}
		} else {
			switch (event.getAction()) {
			case KeyEvent.ACTION_DOWN: // ������
				Log.i("Keyboard down",
						String.valueOf(event.getScanCode() + ","
								+ String.valueOf(keycode)));

				switch (scanCode) {
				case 29:
					pressingKey[0] = 1;
					break;
				case 42:
					pressingKey[1] = 1;
					break;
				}

				str = "1 " + String.valueOf(scanCode) + " 1 " + "0\0";
				socket.index = controlIndex;
				socket.buff = str.getBytes();
				socket.flag = 0;
				break;
			case KeyEvent.ACTION_UP: // ����
				Log.i("Keyboard up",
						String.valueOf(event.getScanCode() + ","
								+ String.valueOf(keycode)));

				switch (scanCode) {
				case 29:
					pressingKey[0] = 0;
					break;
				case 42:
					pressingKey[1] = 0;
					break;
				}

				str = "1 " + String.valueOf(scanCode) + " 0 " + "0\0";
				socket.index = controlIndex;
				socket.buff = str.getBytes();
				socket.flag = 0;
				break;

			}
		}
		return false;
		
	}

}
