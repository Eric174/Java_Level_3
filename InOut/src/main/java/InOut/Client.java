package InOut;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws ClassNotFoundException {
        try (Socket socket = new Socket("127.0.0.1", 8000)) {
            ObjectOutputStream serializer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream deserializer = new ObjectInputStream(socket.getInputStream());
            Human human = new Human(10, "Eric", "fatbase");

            //отправляем на сервер
            serializer.writeObject(human);
            serializer.flush();

            //читаем обратно от сервера
            Human thatHuman = (Human) deserializer.readObject();
            System.out.println(thatHuman.getAllInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
