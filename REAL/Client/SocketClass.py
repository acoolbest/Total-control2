import socket
import ControlClass
import threading

class SocketClass(threading.Thread):

    def __init__(self):
        threading.Thread.__init__(self)
        #self.socketConnect()


    def __del__(self):
        """
        if self.sock!=None:
            self.sock.close()
            """
        pass

    def run(self):
        ControlClass.ControlClass()
        pass

    def socketConnect(self):
        self.sock=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        self.sock.setsockopt(socket.SOL_SOCKET,socket.SO_REUSEADDR,1)
        self.addr=('192.168.0.1',6000)
        self.sock.connect(self.addr)


if __name__=="__main__":
    SocketClass().start()

