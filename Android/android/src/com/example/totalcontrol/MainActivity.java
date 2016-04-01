package com.example.totalcontrol;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends Activity implements OnClickListener {

	public Button WolButton;  //wol��ư ������ ������ Ŭ����
	public Button launchButton;  //�������� ����ϴ� Launch��ư�� ������ ������ Ŭ����
	public EditText editText;  //��Ʈ��ȣ �Է��ϴ� �ؽ�Ʈ ������
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //xml�̶� ����
        
        launchButton =(Button) findViewById(R.id.LaunchButton);  //Lauch ��ư ���� 
        launchButton.setOnClickListener(this);  //��ưŬ���Ҷ� �Ͼ ���۰� ��������
        WolButton=(Button) findViewById(R.id.WOLButton);  //wol ��ư ����
        WolButton.setOnClickListener(this);  //��ư Ŭ���� �Ͼ ���۰� ����
        
        editText=(EditText)findViewById(R.id.PortEdit);  //����Ʈ������(��Ʈ��ȣ����) ����
        Log.i("���� ��Ƽ��Ƽ", "���ο�Ƽ��Ƽ �ʱ�ȭ �Ϸ�");
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
		case R.id.LaunchButton:   //Launch��ư ������ ����
			LaunchButton lb=new LaunchButton(this);  //Launch��ư Ŭ���ÿ� ������ ������ Ŭ������ ����
			if(lb.checkInputPort()==true)  //checkInputPort�޼ҵ�� ��Ʈ�Է��� �Ǿ����� or ���������� �����Ͽ����� Ȯ���ϴ� �޼ҵ�
			{
				Intent nextActivity=lb.getNextActivity();  //����  �������� �Ѿ�� ���� �ڵ�
				startActivity(nextActivity);  //��Ƽ��Ƽ ���� �޼ҵ�
				break;
			}
			break;
		case R.id.WOLButton:  //wol��ư ������ ����
			Intent nextActivity=new Intent(getApplicationContext(),
					PcSelect.class);  //wol��ư�� ������ PcSelect��Ƽ��Ƽ�� �Ѿ��.
			startActivity(nextActivity);  //��Ƽ��Ƽ ���� �޼ҵ�
			break;
		}
	}
	

}
