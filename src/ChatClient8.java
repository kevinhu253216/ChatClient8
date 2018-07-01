
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient8 extends Frame {

    TextField tfTxt = new TextField();
    TextArea taContext = new TextArea();
    Socket s =null;
    DataOutputStream dos =null; //

    public static void main(String[] args){
        ChatClient8 cc = new ChatClient8();
        cc.launchFrame();
    }

    public void launchFrame(){
        setLocation(300,300);
        setSize(300,300);
        add(taContext, BorderLayout.NORTH);
        add(tfTxt,BorderLayout.SOUTH);
        pack();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        tfTxt.addActionListener(new TFListener());
        setVisible(true);
        connect();
    }

    public void connect(){
        try{
            s = new Socket("127.0.0.1",8888);
            dos = new DataOutputStream(s.getOutputStream()); // ///
System.out.println("Connected!");
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException E){
            disconnect();
            E.printStackTrace();
        }
    }

    public void disconnect(){
        try{
            dos.close();
            s.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private class TFListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String str = tfTxt.getText().trim();
            taContext.setText(str);
            tfTxt.setText("");
            try{
                dos.writeUTF(str);
                dos.flush();
            //    dos.close();
            }catch (IOException e1){
                e1.printStackTrace();
            }
        }
    }

}
