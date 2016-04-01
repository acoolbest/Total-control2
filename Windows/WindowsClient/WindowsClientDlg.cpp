
// WindowsClientDlg.cpp : ���� ����
//

#include "stdafx.h"
#include "WindowsClient.h"
#include "WindowsClientDlg.h"
#include "afxdialogex.h"



#ifdef _DEBUG
#define UM_RECEIVE WM_USER+11
#define WM_ICON_NOTIFY WM_USER+10
#define new DEBUG_NEW
#endif


// ���� ���α׷� ������ ���Ǵ� CAboutDlg ��ȭ �����Դϴ�.

class CAboutDlg : public CDialogEx
{
public:
	CAboutDlg();

// ��ȭ ���� �������Դϴ�.
	enum { IDD = IDD_ABOUTBOX };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV �����Դϴ�.

// �����Դϴ�.
protected:
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialogEx(CAboutDlg::IDD)
{
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialogEx)
END_MESSAGE_MAP()


// CWindowsClientDlg ��ȭ ����



CWindowsClientDlg::CWindowsClientDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CWindowsClientDlg::IDD, pParent)
{
	mIP = _T("127.0.0.1");   //Create�Ҷ� IP�� ��밡���� ������
	mPort = 6000;  //������Ʈ������ ������ �ٸ������� ���ְų� Create()���� ù��° ���ڸ� 0U�� �Ұ�
	message = _T("hello");

	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);

}

void CWindowsClientDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CWindowsClientDlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDC_BUTTON1, &CWindowsClientDlg::OnBnClickedButton1)
	ON_BN_CLICKED(IDC_BUTTON2, &CWindowsClientDlg::OnBnClickedButton2)
//	ON_WM_SIZE()
	ON_MESSAGE(WM_ICON_NOTIFY, OnTrayNotification)
	ON_MESSAGE(UM_RECEIVE,OnReceive)
	ON_COMMAND(ID_FILE_EXIT, &CWindowsClientDlg::OnFileExit)
	ON_COMMAND(ID_FILE_SHOW, &CWindowsClientDlg::OnFileShow)

//	ON_WM_SHOWWINDOW()
ON_WM_SIZE()
ON_BN_CLICKED(IDC_BUTTON3, &CWindowsClientDlg::OnBnClickedButton3)
END_MESSAGE_MAP()


// CWindowsClientDlg �޽��� ó����

BOOL CWindowsClientDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// �ý��� �޴��� "����..." �޴� �׸��� �߰��մϴ�.

	// IDM_ABOUTBOX�� �ý��� ��� ������ �־�� �մϴ�.
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != NULL)
	{
		BOOL bNameValid;
		CString strAboutMenu;
		bNameValid = strAboutMenu.LoadString(IDS_ABOUTBOX);
		ASSERT(bNameValid);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// �� ��ȭ ������ �������� �����մϴ�. ���� ���α׷��� �� â�� ��ȭ ���ڰ� �ƴ� ��쿡��
	//  �����ӿ�ũ�� �� �۾��� �ڵ����� �����մϴ�.
	SetIcon(m_hIcon, TRUE);			// ū �������� �����մϴ�.
	SetIcon(m_hIcon, FALSE);		// ���� �������� �����մϴ�.

	// TODO: ���⿡ �߰� �ʱ�ȭ �۾��� �߰��մϴ�.

	if(!m_TrayIcon.Create(this,WM_ICON_NOTIFY,_T("Ʈ���� ������ ����"),NULL,IDR_MAINFRAME))
	{
		return -1;
	}
	m_TrayIcon.SetIcon(IDR_MAINFRAME);

	
	m_My.m_pDlg=this;
	
	BOOL bRet=m_My.Create(0U,SOCK_DGRAM,FD_READ | FD_WRITE);
	//BOOL bRet = m_My.Create(m_port,SOCK_DGRAM,FD_READ | FD_WRITE);  
	if (bRet != TRUE)       
	{              
		UINT uErr = GetLastError();              
		TCHAR szError[256];              
		wsprintf(szError, L"Server Receive Socket Create() failed: %d", uErr);              
		AfxMessageBox(szError);       
	}

	return TRUE;  // ��Ŀ���� ��Ʈ�ѿ� �������� ������ TRUE�� ��ȯ�մϴ�.
}

void CWindowsClientDlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialogEx::OnSysCommand(nID, lParam);
	}
}

// ��ȭ ���ڿ� �ּ�ȭ ���߸� �߰��� ��� �������� �׸�����
//  �Ʒ� �ڵ尡 �ʿ��մϴ�. ����/�� ���� ����ϴ� MFC ���� ���α׷��� ��쿡��
//  �����ӿ�ũ���� �� �۾��� �ڵ����� �����մϴ�.

void CWindowsClientDlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // �׸��⸦ ���� ����̽� ���ؽ�Ʈ�Դϴ�.

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// Ŭ���̾�Ʈ �簢������ �������� ����� ����ϴ�.
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// �������� �׸��ϴ�.
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
	}
}

// ����ڰ� �ּ�ȭ�� â�� ���� ���ȿ� Ŀ���� ǥ�õǵ��� �ý��ۿ���
//  �� �Լ��� ȣ���մϴ�.
HCURSOR CWindowsClientDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}



void CWindowsClientDlg::OnBnClickedButton1()  //connect ��ư Ŭ�����Դϴ�.
{

	m_Setting.input=fopen("settingIO.txt","rt");//setting������ �����ϰ� �ִ� ������ ����.
	char *ip=(char*)malloc(sizeof(char)*16);  //�����Ǹ� �о��
	memset(ip,0,10);  
	mPort=10000;
	fscanf(m_Setting.input,"%s %d",ip,&mPort);  //��Ʈ�� �о��
	
	fclose(m_Setting.input);  //���� ����
	if(ip==NULL || mPort==10000)  //�߸������� �ַ�ȣ��
	{
		wsprintf(szError, _T("IP or Port not insert"));
			AfxMessageBox(szError);
	}
	else  //�ƴϸ� ����
	{
		int i;
		unsigned char *addressBuffer=m_My.GetMACAddress();//�� ������ ����
		unsigned char buff[160];  //���ۿ� ����
		LPDWORD nSize=new DWORD(MAX_COMPUTERNAME_LENGTH+1);//�� �ǽ��̸������� ���庯��
		pcName=new WCHAR();  //�ǽ��̸� ���庯�� ����
		GetComputerName(pcName,nSize); //�ǽ��̸� ����
		int pcXSize=GetSystemMetrics(SM_CXFULLSCREEN); //��������� ����
		int pcYSize=GetSystemMetrics(SM_CYFULLSCREEN);  //���������� ����

		for(i=0;i<wcslen(pcName);i++)
		{
			buff[i]=pcName[i];  //�ǽ� �̸��� ���ۿ� �������
		}
		buff[i++]='_';
		for(int j=0;j<6;j++)
		{
			buff[i++]=addressBuffer[j]/10;  //�����ǿ��� .���� ���еǴ� �� �ڸ��� 2�ڸ� ���� 1�ڸ� ���� �����ؼ� 2�ε����� �� �� ������
			buff[i++]=addressBuffer[j]%10;
		}
		buff[i++]='_';
		buff[i++]=pcXSize/100;  //�ǽ� ����� �����ؼ� �������
		buff[i++]=pcXSize%100;
		buff[i++]='_';
		buff[i++]=pcYSize/100;  //�ǽ� ����� �����ؼ� �������
		buff[i++]=pcYSize%100;
		buff[i++]='_';
		SOCKADDR_IN saddr;
		memset(&saddr,0,sizeof(saddr));
		saddr.sin_family=AF_INET;
		saddr.sin_addr.s_addr=inet_addr(ip); //ip   
		saddr.sin_port=htons(mPort);  //��Ʈ

		//BOOL boolean=TRUE;// (WOL����뺯��)
		//m_My.SetSockOpt(SO_BROADCAST,(char*)&boolean,sizeof BOOL);  //��ε��ɽ�Ʈ�� ��ȯ(wol �����)

		m_My.Connect((SOCKADDR*)&saddr,sizeof(saddr));  //���� �������
		//	m_My.SendTo(buff,sizeof(buff),(SOCKADDR*)&saddr,sizeof(saddr));
		m_My.Send(buff,sizeof(buff),0);  //���� ��ȣ ����
		//m_My.SendTo(buff,102,mPort);
		unsigned char buff2[160]={0,};  //���ſ� ����
		Sleep(1000);
		m_My.Receive(buff2,sizeof(buff2),0);  //����
		//����ɽÿ� �ǵ��ƿ��½�ȣ�� ���� (CAsyncSocket connect UDPŬ���� �� receive�� ����ŷ)

		if(buff2[0]!=NULL)
		{
			wsprintf(szError, _T("Server Connect Success"));
			AfxMessageBox(szError);

			////////////////////////////////////
			//�����κ�
		}
		else
		{
			wsprintf(szError, _T("Server Connect Fail"));
			AfxMessageBox(szError);
		}
	}
}


void CWindowsClientDlg::OnBnClickedButton2()  //disconnect Ŭ�����Դϴ�.
{
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�. Ŭ���� ���ÿ� �������
	m_TrayIcon.RemoveIcon();
	m_My.Close();
	this->OnOK();
}


//void CWindowsClientDlg::OnSize(UINT nType, int cx, int cy)
//{
//	CDialogEx::OnSize(nType, cx, cy);
//	ShowWindow(SW_HIDE);
//	// TODO: ���⿡ �޽��� ó���� �ڵ带 �߰��մϴ�.
//
//}

LRESULT CWindowsClientDlg::OnTrayNotification(WPARAM wParam,LPARAM lParam)  //Ʈ���̾����� ������
{
	CMenu menu, *pSubMenu;

	if(LOWORD(lParam)==WM_RBUTTONUP)
	{
		if(!menu.LoadMenu(IDR_MENU1))
		{
			return 0;
		}
		if(!(pSubMenu=menu.GetSubMenu(0)))
		{
			return 0;
		}

		CPoint pos;
		GetCursorPos(&pos);
		SetForegroundWindow();
		pSubMenu->TrackPopupMenu(TPM_RIGHTALIGN, pos.x, pos.y, this);
		menu.DestroyMenu();
	}

	else if(LOWORD(lParam)==WM_LBUTTONDBLCLK)
	{
		//SendMessage(WM_COMMAND,ID_APP_ABOUT);
		ShowWindow(SW_SHOWNORMAL);
	}
	return 1;
}

void CWindowsClientDlg::OnFileExit()
{
	// TODO: ���⿡ ��� ó���� �ڵ带 �߰��մϴ�.
	OnOK();
}


void CWindowsClientDlg::OnFileShow()
{
	// TODO: ���⿡ ��� ó���� �ڵ带 �߰��մϴ�.
	ShowWindow(SW_SHOWNORMAL);

}

void CWindowsClientDlg::OnSize(UINT nType, int cx, int cy)
{
	CDialogEx::OnSize(nType, cx, cy);

	// TODO: ���⿡ �޽��� ó���� �ڵ带 �߰��մϴ�.
	if(nType==SIZE_RESTORED)
	{
		ShowWindow(SW_SHOW);
	}
	else
	{
		ShowWindow(SW_HIDE);
		
	}
}

LRESULT CWindowsClientDlg::OnReceive(WPARAM wParam,LPARAM lParam)  //������ ���ź��Դϴ�.
{

	int mouseX,mouseY;
	int mouseState;
	char *px,*py,*ps;;
	char *ptr;
	char buff[160];
	m_My.Receive(buff,sizeof(buff),0);    //������ �޴´�.
	
	ptr=strtok(buff," ");
	px=strtok(NULL," ");
	py=strtok(NULL," ");
	ps=strtok(NULL," ");
	mouseX=atoi(px);
	mouseY=atoi(py);
	mouseState=atoi(ps);

	if(*ptr=='0')  //���콺
	{
		if(mouseState==1)  // ��Ŭ��
		{
			SetCursorPos(mouseX,mouseY);  //�̵�
			mouse_event(MOUSEEVENTF_LEFTDOWN,mouseX,mouseY,0,0);  //���콺 ����Ű�� ����
			mouse_event(MOUSEEVENTF_LEFTUP,mouseX,mouseY,0,0);   //���콺 ����Ű�� ��
		}
		else if(mouseState==2)  //��Ŭ��
		{
			SetCursorPos(mouseX,mouseY);  //�̵�
			mouse_event(MOUSEEVENTF_RIGHTDOWN,mouseX,mouseY,0,0);  //���콺 ��Ŭ��Ű ����
			mouse_event(MOUSEEVENTF_RIGHTUP,mouseX,mouseY,0,0);   //���콺 ��Ŭ��Ű ��
		}
		else if(mouseState==4)  //���̵�
		{
			mouse_event(MOUSEEVENTF_WHEEL,postx,posty,mouseX,0);  //���̵�
		}
		else
		{
			SetCursorPos(mouseX,mouseY);  //�׳� �̵�����
		}
		postx=mouseX;  //���� ��ǥ�� ������ǥ����
		posty=mouseY;  //���� ��ǥ�� ������ǥ����
	}
	else if(*ptr=='1') //Ű����
	{
		if(mouseY==0)  //������
		{
			keybd_event(transScan(mouseX),mouseX,0,0);
		}
		else if(mouseX==1)  //����
		{
			keybd_event(transScan(mouseX),mouseX,KEYEVENTF_KEYUP,0);
		}
	}

	return 0L;
}

int CWindowsClientDlg::transScan(int scancode)  //Ű���� ��ĵ�ڵ�κ�ȯ�Լ� (�̿ϼ�)
{
	if(scancode==11)
	{
		return scancode+37;
	}
	else if(2<=scancode && scancode<=10)
	{
		return scancode+47;
	}
	else
	{
		switch(scancode)
		{
		case 16:
			return 81;
			break;
		case 17:
			return 87;
			break;
		case 18:
			return 69;
			break;
		case 19:
			return 82;
			break;
		case 20:
			return 84;
			break;
		case 21:
			return 89;
			break;
		case 22:
			return 85;
			break;
		case 23:
			return 73;
			break;
		case 24:
			return 79;
			break;
		case 25:
			return 80;
			break;
		case 30:
			return 65;
			break;
		case 31:
			return 83;
			break;
		case 32:
			return 68;
			break;
		case 33:
			return 70;
			break;
		case 34:
			return 71;
			break;
		case 35:
			return 72;
			break;
		case 36:
			return 74;
			break;
		case 37:
			return 75;
			break;
		case 38:
			return 76;
			break;
		case 44:
			return 90;
			break;
		case 45:
			return 88;
			break;
		case 46:
			return 67;
			break;
		case 47:
			return 86;
			break;
		case 48:
			return 66;
			break;
		case 49:
			return 78;
			break;
		case 50:
			return 77;
			break;
		case 12:
			return 45;
			break;
		case 13:
			return 43; 
			break;
		case 26:
			return 91;
			break;
		case 27:
			return 93;
			break;
		case 39:
			return 58;
			break;
		case 40:
			return 34;
			break;
		case 51:
			return 44;
			break;


		}
	}
}
void CWindowsClientDlg::OnBnClickedButton3()  //setting��ư�� ������ settingŬ������ ȣ���
{
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.

	m_Setting.DoModal();  //settingâ�� ������!!!!!!!!!
}
