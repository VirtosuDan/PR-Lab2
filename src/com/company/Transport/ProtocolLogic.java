package com.company.Transport;
import com.company.Security.AES;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.net.*;

public class ProtocolLogic {
    public static int RCV_PORT;
    public static int SEND_PORT;
    public static InetAddress IP_ADDRESS;

    public static void send(String sendData) throws IOException {
        String message = AES.encrypt(sendData,"sha512");
        PacketLogic packetLogic = new PacketLogic();
        int checksum = packetLogic.checksum(message);
        String finalMessage = packetLogic.createPacket(message,checksum);
        byte [] send = finalMessage.getBytes();
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket sendPacket = new DatagramPacket(send, send.length, IP_ADDRESS, SEND_PORT);
        socket.send(sendPacket);
        socket.close();
    }

    public static void receive() throws IOException, ParseException {
        DatagramSocket socket = new DatagramSocket(RCV_PORT);
        byte[] receivedData = new byte [1024];
        DatagramPacket receivedPacket = new DatagramPacket(receivedData,receivedData.length, IP_ADDRESS, RCV_PORT);
        System.out.println("Waiting for Message....");
        socket.receive(receivedPacket);
        String received = new String(receivedPacket.getData(),0,receivedPacket.getLength());
        String receivedEncrypted = PacketLogic.parseJSON(received)[0];// informatia
        PacketLogic.checkSumVerifier(receivedEncrypted,PacketLogic.parseJSON(received)[1]);//checksumul
        String decrypted = AES.decrypt(receivedEncrypted,"sha512");
        //System.out.println("Encrypted message is:"+receivedEncrypted);
        System.out.println("Received message is: "+decrypted);
        socket.close();
    }
}
