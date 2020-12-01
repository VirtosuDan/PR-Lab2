## Current Status : not finished

Currently , I have the following features implemented in my project :
1. A ProtocolLogic class that has send and received methods (created atop UDP)
2. A PacketLogic class that creates the packets as JSON , parse that JSON . Also in this class I implemented the error detection using checksum(CRC2)
3. AES class that is just a draft , in order to make the traffic encrypted. It is not finished , I need to get a shared connection key between client and server .
4. Client and Server classes that implements all above . 
