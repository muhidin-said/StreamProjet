import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class AcceptClientD implements Runnable {

    private Socket clientSocketOnServer;
    private int clientNumber;
    private String Song;
    private String [] listPath ={"C:\\test\\Reggeaton\\j-balvin-reggaeton.wav",
            "C:\\test\\Reggeaton\\eddy-lover-y-el-roockie-olvidemos.wav", "C:\\test\\Reggeaton\\Ozuna-Mi-Nina.wav",
    "C:\\test\\Rap\\2pac-changes.wav","C:\\test\\Rap\\50-cent-best-friend.wav","C:\\test\\Rap\\Pop-Smoke-Dior.wav",
    "C:\\test\\Rock\\bon-jovi-livin-on-a-prayer.wav","C:\\test\\Rock\\nirvana-smells-like-teen-spirit.wav",
    "C:\\test\\Rock\\queen-we-will-rock-you.wav"};
    int song;
    int genre ;

    //Constructor
    public AcceptClientD(Socket clientSocketOnServer, int clientNo, int genre, int song) {
        this.clientSocketOnServer = clientSocketOnServer;
        this.clientNumber = clientNo;
        this.song = song;
        this.genre = genre;

    }

    public AcceptClientD(Socket clientSocketOnServer, int clientNo, String Song){
        this.clientSocketOnServer = clientSocketOnServer;
        this.clientNumber = clientNo;
        this.Song = Song;
    }

    //overwrite the thread run()
    public void run() {

        try {
            System.out.println("Client Nr " + clientNumber + " is connected "+ clientSocketOnServer.getInetAddress() +" - "+ clientSocketOnServer.getPort() );

            System.out.println("Socket is available for connection " + clientSocketOnServer);

            File fileToSend = new File("C:\\test\\");

            if (genre == 1 && song == 1){
                fileToSend = new File(listPath[0]);
            }
            else if (genre == 1 && song == 2){
                fileToSend = new File(listPath[1]);
            }
            else if (genre == 1 && song == 3){
                fileToSend = new File(listPath[2]);
            }
            else if (genre == 2 && song == 1){
                fileToSend = new File(listPath[3]);
            }
            else if (genre == 2 && song == 2){
                fileToSend = new File(listPath[4]);
            }
            else if (genre == 2 && song == 3){
                fileToSend = new File(listPath[5]);
            }
            else if (genre == 3 && song == 1){
                fileToSend = new File(listPath[6]);
            }
            else if (genre == 3 && song == 2){
                fileToSend = new File(listPath[7]);
            }
            else if (genre == 3 && song == 3){
                fileToSend = new File(listPath[8]);
            }

            else if(Song!= "") {
                for (int i = 0; i < listPath.length; i++) {
                    if (listPath[i].contains(Song)) {
                        fileToSend = new File(listPath[i]);
                        break;
                    }
                }
            }

            System.out.println(fileToSend);
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
            Thread.sleep(300000);
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