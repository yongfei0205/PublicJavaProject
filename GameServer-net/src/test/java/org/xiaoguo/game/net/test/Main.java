package org.xiaoguo.game.net.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.xiaoguo.game.net.NetConstants;

public class Main {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 8888);
			 //��ȡ������������    
            DataInputStream input = new DataInputStream(socket.getInputStream());    
            //��������˷�������    
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
            out.writeShort(NetConstants.MAGIC_HEADER);
            out.writeInt(8);
            out.writeInt(123);
            out.writeInt(99);
            out.flush();
            try {
				Thread.sleep(999L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            out.writeShort(NetConstants.MAGIC_HEADER);
            out.writeInt(8);
            out.writeInt(123);
            out.writeInt(99);
            out.flush();
            out.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
