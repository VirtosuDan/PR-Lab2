package com.company.Transport;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.BitSet;

public class PacketLogic {

    public static int checksum(String str) {
        return crc32(str.getBytes());
    }

    public static int crc32(byte[] data) {
        BitSet bitSet = BitSet.valueOf(data);
        int crc32 = 0xFFFFFFFF;
        for (int i = 0; i < data.length * 8; i++) {
            if (((crc32 >>> 31) & 1) != (bitSet.get(i) ? 1 : 0))
                crc32 = (crc32 << 1) ^ 0x04C11DB7;
            else
                crc32 = (crc32 << 1);
        }
        crc32 = Integer.reverse(crc32);
        return crc32 ^ 0xFFFFFFFF;
    }

    public static int checkSumVerifier(String sentMessage , String receivedMessage){
        int checksum1 = checksum(sentMessage);
        String convertToString = String.valueOf(checksum1);
        if(convertToString.equals(receivedMessage)) {
            System.out.println("Received succesfuly");
            return 1;
        }
        return 0;
    }

    public static String createPacket(String message , int checkSumValue){
        JSONObject json = new JSONObject();
        json.put("checksum",checkSumValue);
        json.put("message",message);
        return json.toString();
    }
    public static String[] parseJSON(String jsonString) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonString);
        JSONObject object = (JSONObject) obj;
        String checksum =  object.get("checksum").toString();
        String message = (String) object.get("message");
        String[] finalValue =
                {message,
                checksum};
        return finalValue;
    }


}
