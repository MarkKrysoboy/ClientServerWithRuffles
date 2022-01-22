import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8082;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            Scanner scanner = new Scanner(System.in);
            String resp;

            resp = in.readLine();
            System.out.println(resp);
            do {
                out.println(scanner.nextLine());
                resp = in.readLine();
                System.out.println(resp);
            } while (!resp.endsWith("glad to talk to you!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
