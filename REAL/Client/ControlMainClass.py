# -*-coding: utf-8 -*-

import platform
import os
import pyautogui

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
        print "Operation system is: ",self.osName
        print "PC name is: ",self.pcName

        self.mouseMap=['left','middle','right']

        self.controller=pyautogui
    def __del__(self):
        pass

    def command(self,data):
        if chr(data[0])=='m':
            if chr(data[1])=='p':
                self.controller.mouseDown(data[2],data[3],self.mouseMap[data[4]]) # x,y,b
                pass
            elif chr(data[1])=='m':
                self.controller.moveTo( (data[2]*100)+data[3], (data[4]*100)+data[5]) # x,y
                pass
            elif chr(data[1])=='s':
                if chr(data[2])=='u':
                    self.controller.scroll(10)
                    pass
                else:
                    self.controller.scroll(-10)
                    pass
                pass
            else:
                self.controller.mouseUp(data[2],data[3],self.mouseMap[data[4]]) # x,y,b
                pass
        else:
            if chr(data[1])=='p':
                self.controller.keyDown(self.controller.KEYBOARD_KEYS[data[2]])
                pass
            else:
                self.controller.keyUp(data[2])
                pass
        pass
