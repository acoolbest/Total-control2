#pragma once
#include "afxcmn.h"
#include "afxwin.h"
#include <stdlib.h>
#include <stdio.h>

// SettingDlg ��ȭ �����Դϴ�.

class SettingDlg : public CDialogEx
{
	DECLARE_DYNAMIC(SettingDlg)

public:
	SettingDlg(CWnd* pParent = NULL);   // ǥ�� �������Դϴ�.
	virtual ~SettingDlg();
	FILE *input;
	CString getIp();
	int getPort();

// ��ȭ ���� �������Դϴ�.
	enum { IDD = IDD_SETTINGDLG };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV �����Դϴ�.

	DECLARE_MESSAGE_MAP()
public:
	CString ip;
	int port;
	afx_msg void OnBnClickedOk();
	CIPAddressCtrl m_addressControl;
	CEdit m_port;
};
