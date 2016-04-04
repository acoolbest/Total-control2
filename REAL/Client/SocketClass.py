import socket
import ControlMainClass
import threading

class SocketClass(threading.Thread):

    def __init__(self):
        threading.Thread.__init__(self)
        self.sock=None


    def __del__(self):
        if self.sock!=None:
            self.sock.close()
            self.sock=None
        pass

    def run(self):
        self.socketConnect()
        controller=ControlMainClass.ControlMainClass()
        while True:
            try:
                controller.command(self.sock.recv(1024))
            except socket.error as m:
                print m
        pass

    def socketConnect(self):
        print "socket connect start"
        self.sock=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        self.sock.setsockopt(socket.SOL_SOCKET,socket.SO_REUSEADDR,1)

        self.addr=[]
        file=open('setting.set','rt')
        import os
        if os.path.getsize('setting.set'):
            self.addr.append(file.readline().strip())
            self.addr.append(int(file.readline().strip()))
        file.close()

        print self.addr
        self.sock.connect(tuple(self.addr))

        print "socket connect end"

    def socketDisconnect(self):
        print "socket disconnect start"
        if self.sock!=None:
            self.sock.close()
            self.sock=None
        else:
            print "socket is None"

        print "socket disconnect end"
        pass


if __name__=="__main__":
    SocketClass().start()

