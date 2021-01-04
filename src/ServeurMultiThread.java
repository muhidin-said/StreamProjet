import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

public class ServeurMultiThread {
    public static void main(String[] args) throws IOException {

        Socket srvSocket = null;
        InetAddress localAddress = null;
        ServerSocket mySkServer;
        String interfaceName = "eth1";
        ArrayList<String> musique = new ArrayList<String>();
        String s = "";
        String[][] listMusique = new String[][]
                {{"j-balvin-reggaeton", "eddy-lover-y-el-roockie-olvidemos", "Ozuna-Mi-Nina"},
                        {"2pac-changes", "50-cent-best-friend", "Pop-Smoke-Dior"},
                        {"bon-jovi-livin-on-a-prayer", "nirvana-smells-like-teen-spirit", "queen-we-will-rock-you"}};

        int ClientNo = 1;
        int genre = 0;
        int choice = 0;
        int song = 0;

        try {
            NetworkInterface ni = NetworkInterface.getByName(interfaceName);
            Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress ia = inetAddresses.nextElement();

                if (!ia.isLinkLocalAddress()) {
                    if (!ia.isLoopbackAddress()) {
                        System.out.println(ni.getName() + "->IP: " + ia.getHostAddress());
                        localAddress = ia;
                    }
                }
            }

            //Warning : the backlog value (2nd parameter is handled by the implementation
            mySkServer = new ServerSocket(45007, 10, localAddress);
            System.out.println("Default Timeout :" + mySkServer.getSoTimeout());
            System.out.println("Used IpAddress :" + mySkServer.getInetAddress());
            System.out.println("Listening to Port :" + mySkServer.getLocalPort());


            //wait for a client connection
            while (true) {
                Socket clientSocket = mySkServer.accept();
                System.out.println("connection request received");

                BufferedReader buffin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter pout = new PrintWriter(clientSocket.getOutputStream());
                String content = buffin.readLine();
                //  System.out.println(content);
                switch (content) {
                    case "1":
                        choice = Integer.parseInt(content);
                        pout = new PrintWriter(clientSocket.getOutputStream());
                        content = buffin.readLine();
                        switch (content) {
                            case "1":
                                genre = 1;
                                break;
                            case "2":
                                genre = 2;
                                break;
                            case "3":
                                genre = 3;
                                break;
                            default:
                        }

                        pout = new PrintWriter(clientSocket.getOutputStream());
                        content = buffin.readLine();

                        switch (content) {
                            case "1":
                                song = 1;
                                break;
                            case "2":
                                song = 2;
                                break;
                            case "3":
                                song = 3;
                                break;
                        }

                        System.out.println();

                        Thread t = new Thread(new AcceptClientD(clientSocket, ClientNo, genre, song));

                        musique.add(ClientNo + ": " + "Client " + (ClientNo) + " - " + clientSocket.getInetAddress()
                                + " - " + clientSocket.getPort() + " - " +listMusique[genre - 1][song - 1]);
                        ClientNo++;

                        //starting the thread
                        t.start();
                        System.out.println("Client: " + ClientNo + " écoute " + listMusique[genre - 1][song - 1]);

                        break;
                    case "2": {

                        for (int i = 0; i < musique.size(); i++) {
                            System.out.println(musique.get(i));

                            pout = new PrintWriter(clientSocket.getOutputStream());
                            pout.println(musique.get(i));
                            pout.flush();


                        }

                        pout = new PrintWriter(clientSocket.getOutputStream());
                        content = buffin.readLine();

                        switch (content) {
                            case "1":
                                for (int i = 0; i < listMusique.length; i++) {
                                    for (int j = 0; j < listMusique.length; j++) {
                                        if (musique.get(0).contains(listMusique[i][j])) {
                                            s = listMusique[i][j];
                                            break;
                                        }
                                    }

                                }
                                t = new Thread(new AcceptClientD(clientSocket, ClientNo, s));
                                ClientNo++;
                                t.start();
                                System.out.println("Client: " + ClientNo + " écoute " + s);
                                break;
                            case "2":
                                for (int i = 0; i < listMusique.length; i++) {
                                    for (int j = 0; j < listMusique.length; j++) {
                                        if (musique.get(1).contains(listMusique[i][j])) {
                                            s = listMusique[i][j];
                                            break;
                                        }
                                    }
                                }
                                t = new Thread(new AcceptClientD(clientSocket, ClientNo, s));
                                ClientNo++;
                                t.start();
                                System.out.println("Client: " + ClientNo + " écoute " + s);
                                break;
                            case "3":
                                for (int i = 0; i < listMusique.length; i++) {
                                    for (int j = 0; j < listMusique.length; j++) {
                                        if (musique.get(2).contains(listMusique[i][j])) {
                                            s = listMusique[i][j];
                                            break;
                                        }
                                    }
                                }
                                t = new Thread(new AcceptClientD(clientSocket, ClientNo, s));
                                ClientNo++;
                                t.start();
                                System.out.println("Client: " + ClientNo + " écoute " + s);
                                break;
                            case "4":
                                for (int i = 0; i < listMusique.length; i++) {
                                    for (int j = 0; j < listMusique.length; j++) {
                                        if (musique.get(3).contains(listMusique[i][j])) {
                                            s = listMusique[i][j];
                                            break;
                                        }
                                    }
                                }
                                t = new Thread(new AcceptClientD(clientSocket, ClientNo, s));
                                ClientNo++;
                                t.start();
                                System.out.println("Client: " + ClientNo + " écoute " + s);
                                break;
                            case "5":
                                for (int i = 0; i < listMusique.length; i++) {
                                    for (int j = 0; j < listMusique.length; j++) {
                                        if (musique.get(4).contains(listMusique[i][j])) {
                                            s = listMusique[i][j];
                                            break;
                                        }
                                    }
                                }
                                t = new Thread(new AcceptClientD(clientSocket, ClientNo, s));
                                ClientNo++;
                                t.start();
                                System.out.println("Client: " + ClientNo + " écoute " + s);
                                break;
                            case "6":
                                for (int i = 0; i < listMusique.length; i++) {
                                    for (int j = 0; j < listMusique.length; j++) {
                                        if (musique.get(5).contains(listMusique[i][j])) {
                                            s = listMusique[i][j];
                                            break;
                                        }
                                    }
                                }
                                t = new Thread(new AcceptClientD(clientSocket, ClientNo, s));
                                t.start();
                                ClientNo++;
                                System.out.println("Client: " + ClientNo + " écoute " + s);
                                break;
                            default:
                                System.out.println("Not a valid response");
                        }
                    }
                    break;
                    default:
                        System.out.println("Not a valid response");
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}