
// WindowsClientDlg.h : ��� ����
//

#pragma once
//#include "trayicon.h"
#include "TrayIcon.h"
#include "KMy.h"
#include "SettingDlg.h"
#include <stdio.h>

// CWindowsClientDlg ��ȭ ����
class CWindowsClientDlg : public CDialogEx
{
// �����Դϴ�.
public:
	CWindowsClientDlg(CWnd* pParent = NULL);	// ǥ�� �������Դϴ�.

// ��ȭ ���� �������Դϴ�.
	enum { IDD = IDD_WINDOWSCLIENT_DIALOG };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV �����Դϴ�.


// �����Դϴ�.
protected:
	HICON m_hIcon;

	// ������ �޽��� �� �Լ�
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnBnClickedButton1();
	afx_msg void OnBnClickedButton2();
//	afx_msg void OnSize(UINT nType, int cx, int cy);
	afx_msg LRESULT OnTrayNotification(WPARAM wParam,LPARAM lParam);
	CTrayIcon m_TrayIcon;
	afx_msg void OnFileExit();
	afx_msg void OnFileShow();
//	afx_msg void OnShowWindow(BOOL bShow, UINT nStatus);
//	afx_msg void OnShowWindow(BOOL bShow, UINT nStatus);
	afx_msg void OnSize(UINT nType, int cx, int cy);
	afx_msg LRESULT OnReceive(WPARAM wParam,LPARAM lParam);
	int transScan(int scancode);
public:

	int postx,posty;
	SettingDlg m_Setting;
	KMy m_My;
	char *ip;
	int mPort;
	CString message;
	CString mIP;
	TCHAR szError[256];
	LPWSTR pcName;
	afx_msg void OnBnClickedButton3();
};
