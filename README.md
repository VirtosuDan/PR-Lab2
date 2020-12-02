## Network Programming Lab2

## Tasks:
1)Implement a protocol atop UDP, with error checking and retransmissions. Limit the number of retries for retransmission.

2)Make the connection secure, using either a CA to get the public key of the receiver and encrypt data with it, or using Diffie-Helman to get a shared connection key between client and server, ensure that the traffic is encrypted.

3)Regarding the application-level protocol, you have 3 options:

  - make an FTP-like protocol for data transfer, thus you will need to ensure data splitting and in-order delivery and reassembly at the destination. The protocol must support URIs, file creation and update (PUT), file fetching (GET) and metadata retrieval (OPTIONS)
  
  - make a protocol based on the workings (state machine) of an ATM
  
  - make a protocol based on the workings (state machine) of a stationary telephone
  
 ## Implementation:

Currently , I have the following features implemented in my project :
1. A ProtocolLogic class that has send and received methods (created atop UDP) . In this class I have basicaly the operations that client and server can operate (send and receive data )
2. A PacketLogic class that creates the packets as JSON , parse that JSON . Also in this class I implemented the error detection using checksum(CRC2) , use to check the integrity of the packets.
3. Security packet in which I have AES class and PublicKeyEncryption class , in order to ensure that the traffic is encrypted . The implementation isn't finished , because now Client and Server do not communicate between them the secret key .
4. Client and Server classes that implements all above . They can send messages between them , an important thing is that if one side send a message it needs to wait for a response in order to be able to sent a new message .

## I know that isn't much implemented , but it's my work .
