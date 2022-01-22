import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        int port = 8082;

        while (true) {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("New connection accepted");


            out.println("Hi client, whats your name?");
            final String name = in.readLine();
            out.println(String.format("Hi %s, how old are you?", name));

            try {
                final int age = Integer.parseInt(in.readLine());
                if (age < 18) {
                    out.println(String.format("%s, since you are under 18 years old, you will be redirected to the " +
                            "children's segment of the network. Do you agree (yes/no)?", name));
                } else {
                    out.println(String.format("%s, You're old enough, I'm giving you unlimited access. Do you agree (yes/no)?",
                            name));
                }
            } catch (NumberFormatException e) {
                out.println(String.format("%s, since you don't distinguish a number from a string, you will be " +
                                "redirected to the children's segment of the network. Do you agree (yes/no)?",
                        name));
            }

            if (in.readLine().equals("yes")) {
                out.println(String.format("OK, %s, you're connected. What do you want to find?",
                        name));
            } else {
                out.println("Well, if you don't agree, I will filter the result anyway. What do you want to find?");
            }

            out.println(String.format("%s, I 'm already looking \"" + in.readLine() + "\"... Enter \"end\" if you get tired of waiting.",
                    name));

            if (in.readLine().equals("end")) {
                out.println(String.format("Goodbye, %s, glad to talk to you!",
                        name));
            } else {
                out.println(String.format("I don't now, what do you want. Goodbye, %s, glad to talk to you!",
                        name));
            }
            clientSocket.close();
            serverSocket.close();
        }
    }

}
