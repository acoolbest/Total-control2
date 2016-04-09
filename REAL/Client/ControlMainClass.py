# -*-coding: utf-8 -*-

import platform
import os
import pyautogui
from pymouse import PyMouse
from pykeyboard import PyKeyboard


"""
Mouse push             : m p (1,2,3)       1:left, 2:middle, 3:right
Mouse release          : m r (1,2,3)    1:left, 2:middle, 3:right
Mouse move             : m m (upper*100)+lower , (upper*100)+lower
Mouse scroll up        : m s u
Mouse scroll down      : m s d

Keyboard down          : k p (0~)
Keyboard up            : k u (0~)
"""

class ControlMainClass():
    def __init__(self):
        self.osName=platform.system()
        self.pcName=os.environ['computername']
        self.screenSize=pyautogui.size()
        print "Operation system is: ",self.osName
        print "PC name is: ",self.pcName

        self.mouseMap=['left','middle','right']

        self.controller=pyautogui
        self.pyMouse=PyMouse()
        self.pyKeyboard=PyKeyboard()
        #self.mousePressflag=False


    def __del__(self):
        pass

    def command(self,data):
        if data[0]=='m':
            x=(ord(data[3])*100)+ord(data[4])
            y=(ord(data[5])*100)+ord(data[6])
            #print "x:",x," y:",y
            #self.controller.moveTo( x, y) # x,y
            self.pyMouse.move(x,y)
            if data[1]=='p':
                #self.controller.mouseDown(x,y,self.mouseMap[ord(data[2])]) # x,y,b
                #print "press"
                self.pyMouse.press(x,y,ord(data[2]))
                pass
            elif data[1]=='r' and ord(data[2])!=0:
                #self.controller.mouseUp(x,y,self.mouseMap[ord(data[2])]) # x,y,b
                #print "release"
                self.pyMouse.release(x,y,ord(data[2]))
                pass
            if data[7]=='s':
                if data[8]=='u':
                    self.controller.scroll(10)
                    pass
                else:
                    self.controller.scroll(-10)
                    pass

            pass
        else:
            if data[1]=='p':
                self.controller.keyDown(self.controller.KEYBOARD_KEYS[ord(data[2])])
                pass
            else:
                self.controller.keyUp(self.controller.KEYBOARD_KEYS[ord(data[2])])
                pass
        pass
