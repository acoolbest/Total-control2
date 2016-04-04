# -*-coding: utf-8 -*-

import platform
import os
import WinKeyboardControl
import WinMouseControl

class ControlMainClass():
    def __init__(self):
        self.osName=platform.system()
        self.pcName=os.environ['computername']
        print "Operation system is: ",self.osName
        print "PC name is: ",self.pcName

        self.mouse=None
        self.keyboard=None
        if self.osName=='Windows':
            self.keyboard=WinKeyboardControl.WinKeyboardControl()
            self.mouse=WinMouseControl.WinMouseControl()

        else:
            #리눅스 컨트롤러
            pass

    def __del__(self):
        pass

    def command(self,data):
        data=data.split(' ')
        pass
