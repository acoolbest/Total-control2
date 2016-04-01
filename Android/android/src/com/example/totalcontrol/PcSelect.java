package com.example.totalcontrol;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PcSelect extends Activity{
	
	private ArrayList<String> arraylistforlistview;  //SocketForAcceptŬ������ �ִ� PC�̸��� ������ ���� ArrayList�ʵ�
	private ArrayAdapter<String> adapter;  //listView�� �־��� ArrayAdapter
	private ListView listview; //PC�̸��� ǥ���� ����Ʈ��
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pcselect);
		arraylistforlistview = new ArrayList<String>();  //pC�̸��� ���� ArrayList�� �����.				
		arraylistforlistview=SocketForAccept.returnPcNameArrayList();  //PC�̸��� ��´�.(returnPcNameArrayList()�޼ҵ尡 pc�̸����� �������ش�)
		adapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,arraylistforlistview);  //listView�� �־��ֱ� ���� Adapter�� PC�̸����� �־��ش�.
		listview=(ListView)findViewById(R.id.pcselectLayout);  //xml�� ����Ʈ�並 �����Ѵ�.
		listview.setAdapter(adapter);  //listview�� adapter�� �ִ´�.
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);  // listview�� ��带 �����Ѵ�. CHOICE_MODE_SINGLE�� �ѹ���ġ�� �ǹ��Ѵ�.
		
		listview.setOnItemClickListener(new OnItemClickListener() {  //listview�� �̱�Ŭ���Ҷ�

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {    //[position�� listview�� ����� �׸��� ��ġ�ߴ��� ��Ÿ��
				// TODO Auto-generated method stub
				try {
					new MagicPacketSend(position);  //Ŭ���� �׸� ���ؼ� ������ŶŬ������ �����ϰ� ������Ŷ�� �����Ѵ�.
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
	

}
