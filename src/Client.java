import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try(Socket socket = new Socket("localhost", 5555)){

            BufferedReader echo = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter sendEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            String input;
            String response;

            do{

                System.out.print("Send a message to the server: ");
                input = scanner.nextLine();
                sendEcho.println(input);
                if(!input.equals("exit")){
                    response = echo.readLine();
                    System.out.println("Server response: "+response);
                }

            }while(!input.equals("exit"));
        }
        catch(IOException ex){

            System.out.println("Client error: "+ex.getMessage());
        }

    }
}
