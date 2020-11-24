import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

            String message_distant ="";
            Socket mySocket;
            BufferedReader buffin;
            PrintWriter pout;

            InetAddress serverAddress;
            String serverName = "127.0.0.1";

            try {
                serverAddress = InetAddress.getByName(serverName);
                System.out.println("Get the address of the server : "+ serverAddress);


                //try to connect to the server
                mySocket = new Socket(serverAddress,45007);

                System.out.println("We got the connexion to  "+ serverAddress);


                AskQuestions();
                //get an input stream from the socket to read data from the server
                buffin = new BufferedReader (new InputStreamReader(mySocket.getInputStream()));

                //get an output stream to send data to the server
                pout = new PrintWriter(mySocket.getOutputStream());

                int filesize = 722676;

                byte[] mybytearray = new byte[filesize];
                InputStream is = mySocket.getInputStream();

                int play = 1;

                if (play == 0) {
                    // save in a file
                    int current = 0;
                    int bytesRead = 0;
                    FileOutputStream fos = new FileOutputStream("C:\\test\\cv.pdf");
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    do {
                        bytesRead = is.read(mybytearray, current, mybytearray.length - current);
                        if (bytesRead >= 0)
                            current += bytesRead;
                    } while (bytesRead > -1);

                    System.out.println("I read : " + current + " bytes");
                    bos.write(mybytearray, 0, mybytearray.length);
                    bos.flush();
                    bos.close();
                }
                else
                {
                    // play the stream
                    InputStream bufferedIn = new BufferedInputStream(is);
                    SimpleAudioPlayer player = new SimpleAudioPlayer(bufferedIn);
                    player.play();
                    Thread.sleep(10000);
                }
                mySocket.close();






                //send back the message to the server to kill it
                pout.close();
                buffin.close();
                mySocket.close();

                System.out.println("\nTerminate program...");


            }catch (UnknownHostException e) {

                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
                System.out.println("server connection error, dying.....");

            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    public static void  AskQuestions() throws IOException {

        int message;
        Scanner saisie = new Scanner(System.in);

        System.out.println("Bonjour et bienvenue dans la plateforme de stream !");

        System.out.println("Que voulez-vous faire ?\n 1: Écouter de la musique\n 2: Écouter la playlist d'une autre personne \n" +
                " 3: Quitter");

        message = saisie.nextInt();


        switch (message){

            case 1:
                System.out.println("Quel style de musique voulez-vous écouter ? \n 1: Reggaeton\n 2: Rap\n 3: Rock");
                message = saisie.nextInt();
                switch (message){
                    case 1:
                        System.out.println("Voici la list de musique du genre Reggeaton :");
                        //list of Reggeaton


                        break;
                    case 2:
                        System.out.println("Voici la list de musique du genre Rap :");
                        // list of Rap

                        break;
                    case 3:
                        System.out.println("Voici la list de musique du genre Rock :");
                        // list of Rock

                        break;
                }
            case 2:


                break;
            case 3:
        //        mySocket.close();
                break;

        }




    }
    }
