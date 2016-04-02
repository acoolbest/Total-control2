from pykeyboard import PyKeyboard

class KeyboardControl():
    
    keyboardObject=''
    def __init__(self):
        self.keyboardObject=PyKeyboard()
        print "keyboard init success"
        pass
    
    def __del__(self):
        pass
    
    def keyClick(self,ch):
        if ch!='caps_lock':
            self.keyboardObject.tap_key(ch)
        else:
            self.keyboardObject.tap_key(self.keyboardObject.caps_lock_key)
    
    def keyPress(self,ch):
        if ch=='shift_left':
            self.keyboardObject.press_key(self.keyboardObject.shift_l_key)
        elif ch=='shift_right':
            self.keyboardObject.press_key(self.keyboardObject.shift_r_key)
        elif ch=='ctrl_left':
            self.keyboardObject.press_key(self.keyboardObject.control_l_key)
        elif ch=='ctrl_right':
            self.keyboardObject.press_key(self.keyboardObject.control_r_key)
        elif ch=='alt_left':
            self.keyboardObject.press_key(self.keyboardObject.alt_l_key)
        elif ch=='alt_right':
            self.keyboardObject.press_key(self.keyboardObject.alt_r_key)
        elif ch=='escape':
            self.keyboardObject.press_key(self.keyboardObject.escape_key)
        elif ch=='delete':
            self.keyboardObject.press_key(self.keyboardObject.backspace_key)
        elif ch=='tab':
            self.keyboardObject.press_key(self.keyboardObject.tab_key)
        elif ch=='enter':
            self.keyboardObject.press_key(self.keyboardObject.enter_key)
        elif ch=='hangul':
            pass
        else:
            self.keyboardObject.press_key(ch)
        pass
    
    def keyRelease(self,ch):
        if ch=='shift_left':
            self.keyboardObject.release_key(self.keyboardObject.shift_l_key)
        elif ch=='shift_right':
            self.keyboardObject.release_key(self.keyboardObject.shift_r_key)
        elif ch=='ctrl_left':
            self.keyboardObject.release_key(self.keyboardObject.control_l_key)
        elif ch=='ctrl_right':
            self.keyboardObject.release_key(self.keyboardObject.control_r_key)
        elif ch=='alt_left':
            self.keyboardObject.release_key(self.keyboardObject.alt_l_key)
        elif ch=='alt_right':
            self.keyboardObject.release_key(self.keyboardObject.alt_r_key)
        elif ch=='escape':
            self.keyboardObject.release_key(self.keyboardObject.escape_key)
        elif ch=='delete':
            self.keyboardObject.release_key(self.keyboardObject.backspace_key)
        elif ch=='tab':
            self.keyboardObject.release_key(self.keyboardObject.tab_key)
        elif ch=='enter':
            self.keyboardObject.release_key(self.keyboardObject.enter_key)
        elif ch=='hangul':
            pass
        else:
            self.keyboardObject.release_key(ch)
        pass


if __name__=="__main__":
    keyboard=KeyboardControl()
    keyboard.keyClick('a')
