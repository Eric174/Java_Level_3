package InOut;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws ClassNotFoundException {
        try(ServerSocket server = new ServerSocket(8000)) {
            server.setSoTimeout(0);
            Socket clientSocket = server.accept();
            ObjectInputStream deserializer = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream serializer = new ObjectOutputStream(clientSocket.getOutputStream());

            //получаем объект от клиента
            Human human = (Human) deserializer.readObject();
            System.out.println(human.getAllInfo());

            //отправляем обратно клиенту
            serializer.writeObject(human);
            serializer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
