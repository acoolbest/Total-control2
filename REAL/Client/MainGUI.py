from Tkinter import *
import SettingGUI
import SocketClass


class MainGUI():
    def __init__(self):
        self.app=Tk()
        self.app.title('Total control v1.0')
        self.connectButton=Button(self.app,text='connect')
        self.disconnectButton=Button(self.app,text='disconnect')
        self.settingButton=Button(self.app,text='setting')
        self.connectButton.grid(row=0,column=0)
        self.disconnectButton.grid(row=0,column=1)
        self.settingButton.grid(row=0,column=2)
        self.connectButton.bind('<Button-1>', self.connectButtonCallback)
        self.disconnectButton.bind('<Button-1>',self.disconnectButtonCallback)
        self.settingButton.bind('<Button-1>', self.settingButtonCallback)
        self.autoWindowSize()

        self.sock=SocketClass.SocketClass()

        self.app.mainloop()
        pass

    def __del__(self):
        pass

    def connectButtonCallback(self,event):
        self.sock.socketConnect()
        self.sock.start()

    def disconnectButtonCallback(self,event):
        self.sock.socketDisconnect()

    def settingButtonCallback(self,event):
        SettingGUI.SettingGUI(self.app)

    def autoWindowSize(self):
        self.app.update()
        buttons=[len(self.connectButton.cget('text')),len(self.disconnectButton.cget('text')),len(self.settingButton.cget('text'))]
        buttonWidth= max(buttons)*2
        self.connectButton.config(width=buttonWidth)
        self.disconnectButton.config(width=buttonWidth)
        self.settingButton.config(width=buttonWidth)
        pass