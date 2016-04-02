from Tkinter import *

app=Tk()
connectButton=Button(app,text='connect')
disconnectButton=Button(app,text='disconnect')
settingButton=Button(app,text='setting')
connectButton.grid(row=0,column=0)
disconnectButton.grid(row=0,column=1)
settingButton.grid(row=0,column=2)
app.mainloop()