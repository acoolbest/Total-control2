
// WindowsClient.h : PROJECT_NAME ���� ���α׷��� ���� �� ��� �����Դϴ�.
//

#pragma once

#ifndef __AFXWIN_H__
	#error "PCH�� ���� �� ������ �����ϱ� ���� 'stdafx.h'�� �����մϴ�."
#endif

#include "resource.h"		// �� ��ȣ�Դϴ�.


// CWindowsClientApp:
// �� Ŭ������ ������ ���ؼ��� WindowsClient.cpp�� �����Ͻʽÿ�.
//

class CWindowsClientApp : public CWinApp
{
public:
	CWindowsClientApp();

// �������Դϴ�.
public:
	virtual BOOL InitInstance();

// �����Դϴ�.

	DECLARE_MESSAGE_MAP()
};

extern CWindowsClientApp theApp;