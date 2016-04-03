from Tkinter import *

class SettingGUI(Toplevel):
    def __init__(self,parent):
        Toplevel.__init__(self)
        self.title('Setting')
        self.startupCheckFlag=IntVar()
        self.ipEntryString=StringVar()
        self.portEntryString=StringVar()

        self.ipEntry=Entry(self,textvariable=self.ipEntryString)
        self.portEntry=Entry(self,textvariable=self.portEntryString)

        self.okButton=Button(self,text='OK')
        self.cancelButton=Button(self,text='cancel')

        self.startupCheck=Checkbutton(self,text='startup',variable=self.startupCheckFlag)

        self.ipEntry.grid(row=0,column=0)
        self.portEntry.grid(row=1,column=0)
        self.okButton.grid(row=0,column=1)
        self.cancelButton.grid(row=1,column=1)
        self.startupCheck.grid(row=2,column=0)

        self.okButton.bind('<Button-1>',self.okButtonCallback)
        self.loadSetting()
        pass

    def __del__(self):
        pass

    def okButtonCallback(self,evnet):
        print 'okButton push'
        file=open('setting.set','wt')
        file.write(self.ipEntry.get()+'\n')
        file.write(self.portEntry.get()+'\n')
        file.write(str(self.startupCheckFlag.get()))

        print "ip: ",self.ipEntry.get()
        print "port: ",self.portEntry.get()
        print self.startupCheckFlag.get()
        file.close()

    def loadSetting(self):
        file=open('setting.set','rt')
        import os
        if os.path.getsize('setting.set'):
            self.ipEntryString.set(file.readline().strip())
            self.portEntryString.set(file.readline().strip())
            self.startupCheckFlag.set(int(file.readline()))
        file.close()

        pass


