import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args){
        boolean started ;
        try{
            ServerSocket ss = new ServerSocket(8888);
            started = true;// Server is started
            while (started){
                boolean bConnected ;
                Socket s = ss.accept();
System.out.println("Client is connected!");
                bConnected =true; // connect is successful!
                DataInputStream dis = new DataInputStream(s.getInputStream());

                while(bConnected) { //无限次接受 只要有就读
                    String str = dis.readUTF();
                    System.out.println(str);
                 }
                dis.close();
            }
        }catch (IOException E){
            E.printStackTrace();
        }
    }
}
