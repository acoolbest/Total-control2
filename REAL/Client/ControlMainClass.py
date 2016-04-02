import platform
import os
import KeyboardControl
import MouseControl

class ControlMainClass():
    def __init__(self):
        self.osName=platform.system()
        self.pcName=os.environ['computername']
        print "Operation system is: ",self.osName
        print "PC name is: ",self.pcName

        self.keyboard=KeyboardControl.KeyboardControl()
        self.mouse=MouseControl.MouseControl()

        pass

    def __del__(self):
        pass

    def windowsControl(self):
        pass

    def linuxControl(self):
        pass
