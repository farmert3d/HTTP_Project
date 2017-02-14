import java.io.*;
import java.net.*;
class TCPClient {
  public static void main(String argv[]) throws Exception {
      String sentence;
      URL url = new URL(argv[0]);
      String host = url.getHost();
      String fileName = url.getFile();
      String newFileName = argv[1];
      String data = "";
      Socket clientSocket = new Socket(host, 80);
      BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      sentence = "GET " + fileName + " HTTP/1.0\r\n";
      sentence += "Host: " + host + "\r\n";
      sentence += "Connection: close\r\n";
      sentence += "\r\n";
      outToServer.writeBytes(sentence);
      char[] buffer = new char[1000];
      int count = 0;
      String m = "";
      boolean bool = false;
      while((count = inFromServer.read(buffer, 0, buffer.length)) > -1)
      {
          System.out.println(String.valueOf(buffer));
          m += String.valueOf(buffer);

      }
      FileWriter thisFile = new FileWriter(newFileName);
      BufferedWriter bw = new BufferedWriter(thisFile);
      bw.write(m.substring(m.indexOf('<')));
      bw.close();
      clientSocket.close();

  }
}
