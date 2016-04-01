package com.example.totalcontrol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;

import android.app.Activity;
import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.util.Log;
import android.widget.Toast;

public class LaunchButton extends Activity{
	
	private static int firstSocketCreateFlag;  //������ ���° ����°��� �˸��� �÷���  0�̸� ���ʷ� ���� ,1�̸� ó������°� �ƴ�
	private static int postSocketPort;  //������ ���ϻ����Ҷ� ��� ��Ʈ��ȣ�� �����ϰ� ����(�������� �ٽ� ������ ���� ������ ��Ʈ��ȣ�� ������ �Ǵ��ϱ� ����)
	private Socket socket;  //��������� ����Ŭ����
	private MainActivity parent;  //�θ�Ŭ����(36���ٿ� Intent�� �����Ҷ� MainActivity�� context�� �ʿ��ؼ� �������) 
	private int port;  //���ϻ����� ���� ��Ʈ��ȣ
	private Intent nextActivity;  //���� �������� ������ intent
	private boolean checkPort;  //checkInputPort�޼ҵ忡�� �����ϴ� ��
	
	
	public LaunchButton(MainActivity parent) {
		this.parent = parent;  //�θ�Ŭ����(MainActivity)�� ������.
		checkPort = true;  //������ true�� 

		if (firstSocketCreateFlag == 0) {  //���ʿ��� ����� ���´�.
			try {
				port = Integer.parseInt(this.parent.editText.getText()
						.toString());  //�θ�Ŭ����(MainActivity)���� ���� ��Ʈ�� �����Ѵ�.
				socket = new Socket(port);  //���� ��Ʈ�� ���ϻ���
				firstSocketCreateFlag = 1;  //�������� ���� �б�� �ȵ������� 1�� �����Ѵ�.
				postSocketPort = port;  //������Ʈ�� ������Ʈ�� �ִ´�.
				nextActivity = new Intent(parent.getApplicationContext(),
						MouseKeyboardInputActivity.class);  //�̱��� �����ϸ� �θ�Ŭ������ context�� �̿��ؼ� MouseKeyboardInputActivityŬ������ ������ ���� �������� ������ش�.
			} catch (SocketException e) {
				Toast.makeText(parent.getApplicationContext(), "���ϻ�������", 500) 
						.show();  //���ϻ����� �����ϸ� MainActivity ���� "���ϻ�������"��� Toast�޼����� ���.
				// socket.closeSocket();
				checkPort = false;//���ϻ������ж�°��� �Է��Ѵ�.
			} catch (Exception e) {
				// e.printStackTrace();
				Toast.makeText(parent.getApplicationContext(), "���� �Է����ּ���.",
						500).show();  //��Ʈ���� �Է��� �ȵ������� "���� �Է����ּ���"toast�޼����� ���.
				checkPort = false;  //��Ʈ�Է��� ���ٴ� ���� �˸��� �÷��׸� false�� �Ѵ�.
			}
			Log.i("ù��° ��ưŬ����", "��ưŬ���� �۾� �Ϸ�");
		} else {  //launch��ư�� ���ʷ� ������ �ƴҶ�
				try {
					port = Integer.parseInt(this.parent.editText.getText()
							.toString());  //�Ȱ��� ��Ʈ��ȣ�� �����Ѵ�.
					if(port==postSocketPort)  //���� �Ǵ� ������ MainActivity���� �޾ƿԴ� ��Ʈ��ȣ�� ������ ���Ѵ�.
					{
						SocketForAccept.socket.close();  //��Ʈ�� ������ ������ �������� ������ �����ϰ�
						socket = new Socket(port,1);  //���� ��Ʈ�� ���� �����.(�������� ���ڰ��� ���ʿ� ������ ���鶧�� �ٸ���.)
						nextActivity = new Intent(parent.getApplicationContext(),
								MouseKeyboardInputActivity.class);  //������ ��� ��Ƽ��Ƽ�� ������ְ� ��
					
						Log.i("�ι�° ���� ��ưŬ����", "��ưŬ���� �۾� �Ϸ�");
					}
					else  //���࿡ Launch��ư�� ���ʷ� ������ �ƴϸ鼭 ���� ��Ʈ�� ���� ��Ʈ���� �ٸ����
					{
						SocketForAccept.socket.close();   //���� ������ �����Ѵ�.
						socket = new Socket(port);  //������ ���� ���� �����.
						postSocketPort = port;  //������Ʈ�� ������Ʈ�� �����.
						nextActivity = new Intent(parent.getApplicationContext(),
								MouseKeyboardInputActivity.class);  //������ ������ ��Ƽ��Ƽ�� �����. ��
						Log.i("�ι�° �ٸ� ù��° ��ưŬ����", "��ưŬ���� �۾� �Ϸ�");
					}
				} catch (SocketException e) {
					Toast.makeText(parent.getApplicationContext(), "���ϻ�������", 500)
							.show();
					// socket.closeSocket();
					checkPort = false;
				} catch (Exception e) {
					// e.printStackTrace();
					Toast.makeText(parent.getApplicationContext(), "���� �Է����ּ���.",
							500).show();
					checkPort = false;
				}
		}
	}

	public Intent getNextActivity()  //MainActivity ���� ������ ��� ��Ƽ��Ƽ�� ����� �˾ư������� ȣ���ϴ� �޼ҵ�
	{
		return nextActivity;
	}
	
	public boolean checkInputPort()  //���������� ��Ʈ�Է��� �����ߴ��� Ȯ���ϱ� ���ؼ� MainActivity���� ȣ���ϴ� �޼ҵ�
	{
		return checkPort;
	}
}
