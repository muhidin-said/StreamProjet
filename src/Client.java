import javax.sound.sampled.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {


    protected String[][] listMusique = new String[][]
            {{"J-Balvin - Reggaeton", "Eddy Lover y El Roockie - OlvieDemos", "Ozuna - Mi-Nina"},
                    {"2pac - Changes", "50 Cent - Best Friends", "Pop Smoke - Dior"},
                    {"Bon Jovi - Livin' On A Prayer", "Nirvana - Smells Like Teen Spirit ", "Queen - We Will Rock You"}};

    public static void main(String[] args) {
        int choice;
        int genre;
        int song = 0;
        String message = null;
        String message_distant = "";
        Socket mySocket;
        BufferedReader buffin;
        PrintWriter pout;
        InetAddress serverAddress;
        String serverName = "127.0.0.1";
        String[][] listMusique = new String[][]
                {{"j-balvin-reggaeton", "eddy-lover-y-el-roockie-olvidemos", "Ozuna-Mi-Nina"},
                        {"2pac-changes", "50-cent-best-friend", "Pop-Smoke-Dior"},
                        {"bon-jovi-livin-on-a-prayer", "nirvana-smells-like-teen-spirit", "queen-we-will-rock-you"}};

        Client c = new Client();
        try {
            serverAddress = InetAddress.getByName(serverName);
            System.out.println("Get the address of the server : " + serverAddress);


            //try to connect to the server
            mySocket = new Socket(serverAddress, 45007);

            System.out.println("We got the connexion to  " + serverAddress);

            Scanner saisie = new Scanner(System.in);


            System.out.println("Bonjour et bienvenue dans la plateforme de stream !");

            System.out.println("Que voulez-vous faire ?\n "+
                    "1: Écouter de la musique\n " +
                    "2: Écouter la playlist d'une autre personne \n" +
                    " 3: Quitter");

            choice = saisie.nextInt();
        if (choice == 2){
            pout = new PrintWriter(mySocket.getOutputStream());
            pout.println(choice);
            pout.flush();
            System.out.println("Choissisez la playlist d'une personne :");
            buffin = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));

            while((message = buffin.readLine()) != null) {
                System.out.println(message);
            }

        }
        else if (choice == 3){

        }
        else if (choice == 1) {
            c.ListenSong(choice, mySocket);
            InputStream is = mySocket.getInputStream();
            InputStream bufferedIn = new BufferedInputStream(is);
            SimpleAudioPlayer player = new SimpleAudioPlayer(bufferedIn);
            player.play();
            c.PlayAudio(player);

        }

            System.out.println("Voulez-vous écouter une autre musique ou écouter la playlist d'un client ? \n" +
                    "1: Écouter une autre musique\n" +
                    "2: Écouter la playlist d'un client\n" +
                    "3: Quitter");

            choice = saisie.nextInt();
            if (choice == 2){
                pout = new PrintWriter(mySocket.getOutputStream());
                pout.println(choice);
                pout.flush();
                System.out.println("Voici la playlist d'un (des) client(s) :");
                buffin = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));

                while((message = buffin.readLine()) != null) {
                    System.out.println(message);
                }
                System.out.println("Choissez la playlist a écouter :");

            }
            else if (choice == 3){

            }
            else if (choice == 1) {
                c.ListenSong(choice, mySocket);
                InputStream is = mySocket.getInputStream();
                InputStream bufferedIn = new BufferedInputStream(is);
                SimpleAudioPlayer player = new SimpleAudioPlayer(bufferedIn);
                player.play();
                c.PlayAudio(player);

            }


            //get an input stream from the socket to read data from the server
            buffin = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));

            //get an output stream to send data to the server
            pout = new PrintWriter(mySocket.getOutputStream());

           // int filesize = 722676;

           // byte[] mybytearray = new byte[filesize];


            int play = 1;
/*
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
            } else { */
                // play the stream


            mySocket.close();


            //send back the message to the server to kill it
            pout.close();
            buffin.close();
            mySocket.close();

            System.out.println("\nTerminate program...");


        } catch (UnknownHostException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("server connection error, dying.....");

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void ListenSong(int choice, Socket mySocket) throws IOException {
        Scanner saisie = new Scanner(System.in);
        if(choice == 1){
            PrintWriter  pout = new PrintWriter(mySocket.getOutputStream());
            pout.println(choice);
            pout.flush();
            System.out.println("Quel style de musique voulez-vous écouter ? \n 1: Reggaeton\n 2: Rap\n 3: Rock");
            int genre = saisie.nextInt();
            pout = new PrintWriter(mySocket.getOutputStream());
            pout.println(genre);
            pout.flush();
            switch (genre) {
                case 1:
                    System.out.println("Voici la list de musique du genre Reggeaton : ");
                    for (int j = 0; j < listMusique[0].length; j++) {
                        System.out.println((j + 1) + ". " + listMusique[0][j]);
                    }
                    //list of Reggaeton
                    System.out.println("Veuillez choisir une musique à écouter (1-2-3)");
                    int song = saisie.nextInt();
                    pout = new PrintWriter(mySocket.getOutputStream());
                    pout.println(song);
                    pout.flush();
                    switch (song) {
                        case 1:
                            System.out.println("vous écoutez : " + listMusique[0][0]);
                            break;
                        case 2:
                            System.out.println("vous écoutez : " + listMusique[0][1]);
                            break;
                        case 3:
                            System.out.println("vous écoutez : " + listMusique[0][2]);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Voici la liste de musique du genre Rap :");
                    for (int j = 0; j < listMusique[0].length; j++) {
                        System.out.println((j + 1) + ". " + listMusique[1][j]);
                    }
                    System.out.println("Veuillez choisir une musique à écouter (1-2-3)");
                    song = saisie.nextInt();
                    pout = new PrintWriter(mySocket.getOutputStream());
                    pout.println(song);
                    pout.flush();
                    switch (song) {
                        case 1:
                            System.out.println("vous écoutez : " + listMusique[1][0]);
                            break;
                        case 2:
                            System.out.println("vous écoutez : " + listMusique[1][1]);
                            break;
                        case 3:
                            System.out.println("vous écoutez : " + listMusique[1][2]);
                            break;
                    }
                    break;
                case 3:

                    System.out.println("Voici la list de musique du genre Rock :");
                    for (int j = 0; j < listMusique[0].length; j++) {
                        System.out.println((j + 1) + ". " + listMusique[2][j]);
                    }
                    System.out.println("Veuillez choisir une musique à écouter (1-2-3)");
                    song = saisie.nextInt();
                    pout = new PrintWriter(mySocket.getOutputStream());
                    pout.println(song);
                    pout.flush();
                    switch (song) {
                        case 1:
                            System.out.println("vous écoutez : " + listMusique[2][0]);
                            break;
                        case 2:
                            System.out.println("vous écoutez : " + listMusique[2][1]);
                            break;
                        case 3:
                            System.out.println("vous écoutez : " + listMusique[2][2]);
                            break;
                    }
                    break;

            }
        }
    }
    public void PlayAudio(SimpleAudioPlayer player) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Scanner scanner = new Scanner(System.in);
        String response = "";


        while(!response.equals("Q")) {
            System.out.println("P = play, S = Stop, Q = Quit");
            System.out.print("Enter your choice: ");

            response = scanner.next();
            response = response.toUpperCase();

            switch(response) {
                case ("P"): player.play();
                    break;
                case ("S"): player.pause();
                    break;
                case ("R"): player.resetAudioStream();
                    break;
                default: System.out.println("Not a valid response");
            }

        }
        System.out.println("Byeeee!");
    }
}


