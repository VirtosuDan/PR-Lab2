package com.company;

import com.company.Transport.ProtocolLogic;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class Client{

    public static void main(String[] args) throws IOException, ParseException {
        ProtocolLogic.RCV_PORT =17019;
        ProtocolLogic.SEND_PORT = 17017;
        ProtocolLogic.IP_ADDRESS = InetAddress.getLocalHost();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            ProtocolLogic.receive();
            System.out.println("Enter your message: ");
            String message = input.readLine();
            ProtocolLogic.send(message);
        }
    }
}



