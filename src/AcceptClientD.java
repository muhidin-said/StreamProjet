import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class AcceptClientD implements Runnable {

    private Socket clientSocketOnServer;
    private int clientNumber;
    private String [][] listSong;
    int song;
    int genre ;

    //Constructor
    public AcceptClientD(Socket clientSocketOnServer, int clientNo, int genre, int song) {
        this.clientSocketOnServer = clientSocketOnServer;
        this.clientNumber = clientNo;
        this.song = song;
        this.genre = genre;

    }

    public AcceptClientD(Socket clientSocketOnServer, int clientNo, String[][] listSong){
        this.clientSocketOnServer = clientSocketOnServer;
        this.clientNumber = clientNo;
        this.listSong = listSong;
    }

    //overwrite the thread run()
    public void run() {

        try {
            System.out.println("Client Nr " + clientNumber + " is connected");

            System.out.println("Socket is available for connection " + clientSocketOnServer);

            File fileToSend = new File("C:\\test\\");

            if (genre == 1 && song == 1){
                fileToSend = new File("C:\\test\\Reggeaton\\j-balvin-reggaeton.wav");
            }
            else if (genre == 1 && song == 2){
                fileToSend = new File("C:\\test\\Reggeaton\\eddy-lover-y-el-roockie-olvidemos.wav");
            }
            else if (genre == 1 && song == 3){
                fileToSend = new File("C:\\test\\Reggeaton\\Ozuna-Mi-Nina.wav");
            }
            else if (genre == 2 && song == 1){
                fileToSend = new File("C:\\test\\Rap\\2pac-changes.wav");
            }
            else if (genre == 2 && song == 2){
                fileToSend = new File("C:\\test\\Rap\\50-cent-best-friend.wav");
            }
            else if (genre == 2 && song == 3){
                fileToSend = new File("C:\\test\\Rap\\Pop-Smoke-Dior.wav");
            }
            else if (genre == 3 && song == 1){
                fileToSend = new File("C:\\test\\Rock\\bon-jovi-livin-on-a-prayer.wav");
            }
            else if (genre == 3 && song == 2){
                fileToSend = new File("C:\\test\\Rock\\nirvana-smells-like-teen-spirit.wav");
            }
            else if (genre == 3 && song == 3){
                fileToSend = new File("C:\\test\\Rock\\queen-we-will-rock-you.wav");
            }

           // else if(listSong)


           // File fileToSend = new File("C:\\test\\audio.wav");
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