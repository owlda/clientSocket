package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class ClientUDP {

    public static void main(String[] args) {
             try{
                 InetAddress address = InetAddress.getLocalHost();
                 DatagramSocket datagramSocket = new DatagramSocket();

                 Scanner scanner = new Scanner(System.in);
                 String input;
                 do{
                     System.out.println("Data to transfer: ");
                     input = scanner.nextLine();
                     byte[] buffer = input.getBytes();

                     DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
                     datagramSocket.send(packet);
                     byte[] bufferRec = new byte[250];
                     DatagramPacket packetRec = new DatagramPacket(bufferRec, bufferRec.length);
                     datagramSocket.receive(packetRec);
                     System.out.println("Server sent: "+ new String(bufferRec, 0, bufferRec.length));
                 }
                 while(!input.equals("exit"));
             }catch (SocketException ex){
                 System.out.println("Socket Exception: "+ ex.getMessage());
             }catch (IOException e){
                 System.out.println("IOException: "+ e.getMessage());
             }
    }
}
