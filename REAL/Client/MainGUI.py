from Tkinter import *
import SettingGUI


class MainGUI():
    def __init__(self):
        self.app=Tk()
        self.app.title('Total control v1.0')
        connectButton=Button(self.app,text='connect')
        disconnectButton=Button(self.app,text='disconnect')
        settingButton=Button(self.app,text='setting')
        connectButton.grid(row=0,column=0)
        disconnectButton.grid(row=0,column=1)
        settingButton.grid(row=0,column=2)

        settingButton.bind('<Button-1>', self.settingButtonCallback)
        self.app.mainloop()
        pass

    def __del__(self):
        pass

    def settingButtonCallback(self,event):
        SettingGUI.SettingGUI(self.app)
        pass