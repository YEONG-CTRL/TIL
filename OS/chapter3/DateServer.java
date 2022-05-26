import java.net.*;
import java.io.*;
public class DateServer {
    public static void main(String[] args) throws Exception {
ServerSocket server = new ServerSocket(6013); // 포트번호 6013로 request보내봐바!
        while (true) {
            System.out.println("listening..");
            Socket client = server.accept(); // request 대기중(리스닝)
            PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                        System.out.println("new client connected!");


            /* write the 현재시간 to the socket */
            pout.println(new java.util.Date().toString() + "만나서 반가워!");
            
            /* close the socket and resume listening for connections */
            client.close();
            // 요청에 따른 전송했으니 방금 만든 client Socket을 닫음

        }
    }
}