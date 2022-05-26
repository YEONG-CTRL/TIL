import java.net.*;
import java.io.*;
public class DateClient {
    public static void main(String[] args) throws Exception {
/* make connection to server socket */
        Socket socket = new Socket("127.0.0.1", 6013); // 자기자신, 6013포트에 소켓 생성
        InputStream in = socket.getInputStream(); // socket의 inputstream받아와서
        BufferedReader br = new BufferedReader(new InputStreamReader(in)); // 버퍼에서 readline해줌
        // 위에서 서버에서 보내준 데이터를 받는 것
/* read date from the socket */
        String line = null;
        while ((line = br.readLine()) != null)
            System.out.println(line); // 이를 출력
// 다쓰고나면 닫아줌
        socket.close();
    }
}