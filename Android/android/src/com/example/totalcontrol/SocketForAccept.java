package com.example.totalcontrol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.util.Log;
import android.widget.Toast;

public class SocketForAccept extends Thread {

	private int port;  //���� Ŭ���� �����ÿ��� �������ָ� ��
	public static DatagramSocket socket;  //Ŭ�������� �����Ҽ� �ֵ��� static ������.
	private DatagramPacket receiver; 
	private DatagramPacket sender;
	private static int addressTop;
	private static int portTop;
	private static int pcNameTop;
	private static int macAddressTop;
	private static int pcSizeXTop;
	private static int pcSizeYTop;
	private static int synchronizeFlagTop;
	public byte[] buff;  //���۴� ���� ��� �޶����Ƿ� static����
	
	public static InetAddress[] address;
	public static int[] portForReceiveSend;  //Ŭ���̾�Ʈ���� port�� ����
	public static String[] pcName;  //Ŭ���̾�Ʈ���� �̸��� ����
	public static String[] macAddress;  //Ŭ���̾�Ʈ���� mac�ּҸ� ����
	public static int[] pcSizeX;  //Ŭ���̾�Ʈ���� ����� �������� ����
	public static int[] pcSizeY;  //Ŭ���̾�Ʈ���� ����� ��������� ����
	public static int[] synchronizeFlag;  //MouseKeyboardInputActivity���� �ȵ���̵� ����������� Ŭ���̾�ƮPC�� ����� ����� ����ȭ ���״��� ���θ� �Ǻ��Ҷ� ����� �ʵ�
	
	
	public SocketForAccept() {   //MainActivity�̿��� Ŭ�������� ȣ���ϴ� ������
		buff = new byte[160];
	}

	
	public SocketForAccept(int port,int second) throws SocketException  //MainActibity���� Launch��ư�� �ι��̻� ������ �� ȣ��Ǵ� ������
	{
		this.port = port;
		socket = new DatagramSocket(this.port);
	}
	public SocketForAccept(int port) throws SocketException  {   //�̰� ���ʷ� ���ϻ����Ҷ� ����ϴ� ������ port���� �Ѱ�����Ѵ�.
		this.port = port;
		socket = new DatagramSocket(this.port);
		
		address=new InetAddress[5];
		pcName=new String[5];
		macAddress=new String[5];
		pcSizeX=new int[5];
		pcSizeY=new int[5];
		synchronizeFlag=new int[5];
		addressTop=0;
		portTop=0;
		pcNameTop=0;
		macAddressTop=0;
		pcSizeXTop=0;
		pcSizeYTop=0;
		synchronizeFlagTop=0;
		portForReceiveSend=new int[5];
		
	}

	public void closeSocket()  //������,���ϴݴ� �޼ҵ�
	{
		this.interrupt();
		socket.close();
	}
	
	public static ArrayList<String> returnPcNameArrayList()  //pc�̸��� �����ϴ� �޼ҵ�
	{
		ArrayList<String> str =new ArrayList<String>();
		
		for(int i=0;i<pcNameTop;i++)
		{
			str.add(pcName[i]);
		}
		return str;
		
	}
	
	public void run()
	{
		while (!this.isInterrupted()) {  //���ͷ�Ʈ�� ������ �����.
			receiver = new DatagramPacket(buff, buff.length); //���ſ� Datagram����
			try {
				socket.receive(receiver);  //���Ź޴´�.
				int flag = 0;
				for (int i = 0; i < addressTop; i++) {  //�̹� address�迭�� �ִ� ������ �����Ѵ�.
					if (address[i] == receiver.getAddress()) {
						flag = 1;
						Log.i("����", "�̹��ִ°�");
						break;
					}
				}
				if (flag == 0) {  //���°��̸� ��� ���� Ŭ���̾�Ʈ�� ������ �� ������ ��� �迭�鿡 ����ش�.
					address[addressTop++] = receiver.getAddress();
					portForReceiveSend[portTop++] = receiver.getPort();
					sender= new DatagramPacket(buff, buff.length,receiver.getAddress(),receiver.getPort());
					
					String str=new String(buff);
					String temp;
					pcName[pcNameTop++]=str.split("_")[0];
					macAddress[macAddressTop++]=str.split("_")[1];
					temp=str.split("_")[2];
					
					pcSizeX[pcSizeXTop]=temp.getBytes()[0]*100;
					pcSizeX[pcSizeXTop++]+=temp.getBytes()[1];
					temp=str.split("_")[3];
					
					pcSizeY[pcSizeYTop] = temp.getBytes()[0]*100;
					pcSizeY[pcSizeYTop++] +=temp.getBytes()[1];
					synchronizeFlag[synchronizeFlagTop++]=0;
					socket.send(sender);
					Log.i("����", String.valueOf(pcNameTop-1)+"���ż���"+","+pcName[pcNameTop-1]+","+macAddress[macAddressTop-1]+","+String.valueOf(pcSizeX[pcSizeXTop-1])+","+String.valueOf(pcSizeY[pcSizeYTop-1]));
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.i("����", "���Ž���");
			}
		}
	}
	
}