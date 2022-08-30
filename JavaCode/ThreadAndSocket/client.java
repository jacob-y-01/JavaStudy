import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static java.net.NetworkInterface.getNetworkInterfaces;

public class client {
    public static void main(String[] args) throws IOException {
        Socket s=new Socket("172.16.217.192",3333);
        OutputStream os= s.getOutputStream();
        os.write("我是嘟嘟大魔王".getBytes(StandardCharsets.UTF_8));
        os.close();
        s.close();
        ServerSocket ss = new ServerSocket(3333,0xFFFF);
        while(true){
            getNetworkInterfaces();

            // 监听连接对象
            Socket accept = ss.accept();

            // 监听到了连接，加入线程池

            // 获取对象accept的消息流
            InputStream is = accept.getInputStream();

            // 处理流
            byte[] b = new byte[1024];
            int len = is.read(b);
            String ms = new String(b, 0, len);
            System.out.println(ms);

        }
    }
}
