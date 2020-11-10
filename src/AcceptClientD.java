import java.io.*;
import java.net.Socket;

public class AcceptClientD implements Runnable {

    private Socket clientSocketOnServer;
    private int clientNumber;

    //Constructor
    public AcceptClientD(Socket clientSocketOnServer, int clientNo) {
        this.clientSocketOnServer = clientSocketOnServer;
        this.clientNumber = clientNo;

    }

    //overwrite the thread run()
    public void run() {

        try {
            System.out.println("Client Nr " + clientNumber + " is connected");
            System.out.println("Socket is available for connection" + clientSocketOnServer);
            File fileToSend = new File("C:\\test\\audio.wav");
            System.out.println("file size : " + fileToSend.length());
            byte[] mybyteArray = new byte[(int) fileToSend.length()];
            FileInputStream fis = new FileInputStream(fileToSend);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(mybyteArray, 0, mybyteArray.length);

            OutputStream os = clientSocketOnServer.getOutputStream();
            System.out.println("sending file");
            os.write(mybyteArray, 0, mybyteArray.length);
            os.flush();
            Thread.sleep(30000);
            os.close();


            clientSocketOnServer.close();
            Thread.sleep(3000);
            System.out.println("end of connection to the client " + clientNumber);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}