# -*-coding: utf-8 -*-

import platform
import os
import pyautogui
from pymouse import PyMouse
from pykeyboard import PyKeyboard


"""
mouse
0 1 2  3  4  5  6 7 8
M p 1 ux lx uy ly s u
  r 2               d
    3

M:mouse
P:press
R:release
1: left
2:middle
3:right
Ux:upper x
Ly:lower x
Uy:upper y
Ly:lower y
S:scroll
U:up
D:down


Keyboard down          : k p (0~)
Keyboard up            : k u (0~)
"""

class ControlMainClass():
    def __init__(self):
        self.osName = platform.system()
        self.pcName = os.environ['computername']
        self.screenSize = pyautogui.size()
        print "Operation system is: ", self.osName
        print "PC name is: ", self.pcName

        self.mouseMap = ['left', 'middle', 'right']

        self.controller = pyautogui
        self.pyMouse = PyMouse()
        self.pyKeyboard = PyKeyboard()
        self.keyboardMap = {}
        self.initKeyboardMap()

    def __del__(self):
        pass

    def initKeyboardMap(self):
        self.keyboardMap[3] = ' '
        self.keyboardMap[10] = '\''
        self.keyboardMap[15] = ','
        self.keyboardMap[16] = '-'
        self.keyboardMap[17] = '.'
        self.keyboardMap[18] = '/'
        self.keyboardMap[19] = '0'
        self.keyboardMap[20] = '1'
        self.keyboardMap[21] = '2'
        self.keyboardMap[22] = '3'
        self.keyboardMap[23] = '4'
        self.keyboardMap[24] = '5'
        self.keyboardMap[25] = '6'
        self.keyboardMap[26] = '7'
        self.keyboardMap[27] = '8'
        self.keyboardMap[28] = '9'
        self.keyboardMap[30] = ';'
        self.keyboardMap[32] = '='
        self.keyboardMap[36] = '['
        self.keyboardMap[37] = '\\'
        self.keyboardMap[38] = ']'
        self.keyboardMap[41] = '`'
        self.keyboardMap[42] = 'a'
        self.keyboardMap[43] = 'b'
        self.keyboardMap[44] = 'c'
        self.keyboardMap[45] = 'd'
        self.keyboardMap[46] = 'e'
        self.keyboardMap[47] = 'f'
        self.keyboardMap[48] = 'g'
        self.keyboardMap[49] = 'h'
        self.keyboardMap[50] = 'i'
        self.keyboardMap[51] = 'j'
        self.keyboardMap[52] = 'k'
        self.keyboardMap[53] = 'l'
        self.keyboardMap[54] = 'm'
        self.keyboardMap[55] = 'n'
        self.keyboardMap[56] = 'o'
        self.keyboardMap[57] = 'p'
        self.keyboardMap[58] = 'q'
        self.keyboardMap[59] = 'r'
        self.keyboardMap[60] = 's'
        self.keyboardMap[61] = 't'
        self.keyboardMap[62] = 'u'
        self.keyboardMap[63] = 'v'
        self.keyboardMap[64] = 'w'
        self.keyboardMap[65] = 'x'
        self.keyboardMap[66] = 'y'
        self.keyboardMap[67] = 'z'
        self.keyboardMap[75] = self.pyKeyboard.alt_l_key
        self.keyboardMap[76] = self.pyKeyboard.alt_r_key
        self.keyboardMap[78] = self.pyKeyboard.backspace_key
        self.keyboardMap[90] = self.pyKeyboard.control_l_key
        self.keyboardMap[91] = self.pyKeyboard.control_r_key
        self.keyboardMap[93] = self.pyKeyboard.delete_key
        self.keyboardMap[94] = self.pyKeyboard.delete_key
        self.keyboardMap[96] = self.pyKeyboard.down_key
        self.keyboardMap[97] = self.pyKeyboard.end_key
        self.keyboardMap[98] = self.pyKeyboard.enter_key
        self.keyboardMap[99] = self.pyKeyboard.escape_key
        self.keyboardMap[102] = self.pyKeyboard.function_keys[1]
        self.keyboardMap[103] = self.pyKeyboard.function_keys[10]
        self.keyboardMap[104] = self.pyKeyboard.function_keys[11]
        self.keyboardMap[105] = self.pyKeyboard.function_keys[12]
        self.keyboardMap[113] = self.pyKeyboard.function_keys[2]
        self.keyboardMap[119] = self.pyKeyboard.function_keys[3]
        self.keyboardMap[120] = self.pyKeyboard.function_keys[4]
        self.keyboardMap[121] = self.pyKeyboard.function_keys[5]
        self.keyboardMap[122] = self.pyKeyboard.function_keys[6]
        self.keyboardMap[123] = self.pyKeyboard.function_keys[7]
        self.keyboardMap[124] = self.pyKeyboard.function_keys[8]
        self.keyboardMap[125] = self.pyKeyboard.function_keys[9]
        self.keyboardMap[129] = self.pyKeyboard.hangul_key
        self.keyboardMap[132] = self.pyKeyboard.home_key
        self.keyboardMap[141] = self.pyKeyboard.left_key
        self.keyboardMap[146] = '0'
        self.keyboardMap[147] = '1'
        self.keyboardMap[148] = '2'
        self.keyboardMap[149] = '3'
        self.keyboardMap[150] = '4'
        self.keyboardMap[151] = '5'
        self.keyboardMap[152] = '6'
        self.keyboardMap[153] = '7'
        self.keyboardMap[154] = '8'
        self.keyboardMap[155] = '9'
        self.keyboardMap[156] = self.pyKeyboard.num_lock_key

        self.keyboardMap[157] = self.pyKeyboard.page_down_key
        self.keyboardMap[158] = self.pyKeyboard.page_up_key
        self.keyboardMap[160] = self.pyKeyboard.page_down_key
        self.keyboardMap[161] = self.pyKeyboard.page_up_key

        self.keyboardMap[170] = self.pyKeyboard.right_key
        self.keyboardMap[171] = self.pyKeyboard.scroll_lock_key

        self.keyboardMap[175] = self.pyKeyboard.shift_l_key
        self.keyboardMap[176] = self.pyKeyboard.shift_r_key

        self.keyboardMap[180] = self.pyKeyboard.tab_key
        self.keyboardMap[181] = self.pyKeyboard.up_key

        pass

    def command(self,data):
        if data[0] == 'm':
            x = (ord(data[3])*100)+ord(data[4])
            y = (ord(data[5])*100)+ord(data[6])
            #print "x:",x," y:",y
            #self.controller.moveTo( x, y) # x,y
            self.pyMouse.move(x, y)
            if data[1] == 'p':
                #self.controller.mouseDown(x,y,self.mouseMap[ord(data[2])]) # x,y,b
                #print "press"
                self.pyMouse.press(x, y,ord(data[2]))
                pass
            elif data[1] == 'r' and ord(data[2]) != 0:
                #self.controller.mouseUp(x,y,self.mouseMap[ord(data[2])]) # x,y,b
                #print "release"
                self.pyMouse.release(x, y, ord(data[2]))
                pass
            if data[7] == 's':
                if data[8] == 'u':
                    #self.controller.scroll(10)
                    self.pyMouse.scroll(vertical=10)
                    pass
                else:
                    #self.controller.scroll(-10)
                    self.pyMouse.scroll(vertical=-10)
                    pass
            pass
        else:
            print data[1], ord(data[2])
            if data[1]=='p' and ord(data[2])!=0:
                #self.controller.keyDown(self.controller.KEYBOARD_KEYS[ord(data[2])])
                print ord(data[2])
                self.pyKeyboard.press_key(self.keyboardMap[ord(data[2])])
                pass
            elif data[1]=='r' and ord(data[2])!=0:
                #self.controller.keyUp(self.controller.KEYBOARD_KEYS[ord(data[2])])
                self.pyKeyboard.release_key(self.keyboardMap[ord(data[2])])
                pass
        pass
