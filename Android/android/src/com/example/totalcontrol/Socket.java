package com.example.totalcontrol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;

import android.util.Log;

public class Socket extends Thread {
	
	public SocketForAccept socketforaccept;  //Ŭ���̾�Ʈ�� ������ ���� Ŭ����
	private DatagramPacket sender;  //�۽��� ���� Datagram
	public int index;  //�۽��� PC������ �ε�������
	public int flag;  //flag�� 0�϶� �۽��Ѵ�.
	public byte[] buff;  //���ۿ� ����
	
	public Socket()  //MainActivity�� �����ϰ� ������ Ŭ�������� socket�� �ʿ��Ҷ� ȣ���ؾߵǴ� ������
	{
		socketforaccept=new SocketForAccept();  //���ſ� Ŭ���� ����
		Log.i("����", "���ú�����ʱ�ȭ�Ϸ�");
		socketforaccept.start();  //���� ������ ����
		Log.i("����", "������ ������");
		flag=1;  //�÷��� 1�� �ʱ�ȭ�ؼ� ���۾ȵǰ� ����
	}
	
	public Socket(int port,int second) throws SocketException   //������ ���� ��Ʈ�� ����� ���� ȣ���ؾߵǴ� ������
	{
		socketforaccept=new SocketForAccept(port,1);  //SocketForAcceptŬ�������� �������� ���� ��Ʈ�� ������ �����ȴٰ� �˸�
		Log.i("����", "���ú���ϻ����Ϸ�");
	}
	
	public Socket(int port) throws SocketException  //  ��Ʈ��ȣ�� ������ȣ�� �ٸ����� ���ʷ� socket�� �����ɶ� ȣ���ؾߵǴ� ������
	{
		socketforaccept=new SocketForAccept(port);  //���ſ� socket�� ���ʷ� ����
		Log.i("����", "���ú���ϻ����Ϸ�");
	}
	public void run()  //�۽ź� ������
 {
		while (!this.isInterrupted()) {  //flag�� 0�϶� �۽��ϰ�  ���� Ŭ������ ��� interr��pt�� �ɸ��� �ڵ����� �����.
			if (flag == 0) {
				send(buff, index);  //�۽��Լ� ȣ��
				flag = 1;
			}
		}
	}
	public void send(byte[] buff,int index)   //socketforaccept Ŭ������ ������ �ִ� Ŭ���̾�Ʈ pc���� ������ ������� �����͸� �۽��Ѵ�.
	{
		if (socketforaccept.address[index] != null
				&& socketforaccept.portForReceiveSend[index] != 0) {  //socketforaccept�� ä������ ���� �ε������� �����ϸ� �ȵǹǷ� ����ϱ� ���� �ڵ�
			sender = new DatagramPacket(buff, buff.length,  //������ �����Ϳ� �����ͱ���
					socketforaccept.address[index],  //Ŭ���̾�Ʈ pc ip�ּ�
					socketforaccept.portForReceiveSend[index]);  //Ŭ���̾�Ʈ pc port��ȣ
			try {
				socketforaccept.socket.send(sender);  //����!!!
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void closeSocket()
	{
		this.interrupt();
		socketforaccept.closeSocket();
	}
	
}
