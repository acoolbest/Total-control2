from Tkinter import *
import SettingGUI


class MainGUI():
    def __init__(self):
        self.app=Tk()
        connectButton=Button(self.app,text='connect')
        disconnectButton=Button(self.app,text='disconnect')
        settingButton=Button(self.app,text='setting')
        connectButton.grid(row=0,column=0)
        disconnectButton.grid(row=0,column=1)
        settingButton.grid(row=0,column=2)

        self.app.mainloop()
        pass

    def __del__(self):
        pass
