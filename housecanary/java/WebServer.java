import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;

public class WebServer {

    public static void main(String[] args) throws Exception {
        new WebServer(1000L, 1).run();
    }

    private final RateLimiter rateLim;
    private final static DateTimeFormatter dtFormat=
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

    public WebServer(long rateLimitMS, int rateLimitRequests)  {
        rateLim=new RateLimiter(rateLimitMS, rateLimitRequests);
    }
    public void run() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
        server.createContext("/", e->handle(e));
        server.setExecutor(null);
        server.start();
    }

    private void handle(HttpExchange t) throws IOException {
        final String response;
        final int status;
        final long waitTime=rateLim.checkTime();
        if (waitTime==0L) {
            final String date=dtFormat.format(ZonedDateTime.now());
            response = "{\"time\": \""+date+"\"}";
            status=200;
        } else {
            response = "{\"wait_ms\": "+waitTime+"}";
            status=418;
        }
        t.sendResponseHeaders(status, response.length());
        try (OutputStream os = t.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}
