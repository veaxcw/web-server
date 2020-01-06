package listener;

import http.HttpResponseDataBuilder;
import lombok.Data;

import java.io.*;
import java.net.Socket;

/**
 * @author chengwei
 */
@Data
public class ServerEventListener  implements Runnable,WebSocketListener {

    private Socket client;

    public ServerEventListener(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("current thread :" + Thread.currentThread().getName());
        this.doRead();
    }

    private void doRead() {

        try (InputStream inputStream = client.getInputStream();
             OutputStream out = client.getOutputStream()) {BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
             byte[] bytes = new byte[inputStream.available()];

            String info;
            while ( (info = buffer.readLine()) != null) {
                System.out.println(info);
                builder.append(info);
            }
            System.out.println("client:"+ builder);
            PrintStream printStream = new PrintStream(out);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            printStream.println("HTTP/1.1 200 OK");
            printStream.println("Content-Type: text/html; charset=utf-8");
            printStream.println("<h1>这是响应报文</h1>");

            out.write("HTTP/1.1 200 OK".getBytes());

            out.flush();
            Thread.sleep(400);
            System.out.println("response: OK");

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }

    }


    @Override
    public HttpResponseDataBuilder onHttpRequestReceived(Socket conn) {
        return null;
    }
}
