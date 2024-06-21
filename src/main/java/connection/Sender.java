package connection;


import commands.Command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


/**
 *  Class {@code Request} отвечает за запрос клиента на сервер
 */
public class Sender {

    /**
     * Датаграмма-сокет, посылающий запрос
     */
    private static DatagramSocket socket;
    /**
     * Адрес сервера
     */
    private final InetAddress address;
    /**
     * Порт сервера
     */
    private final int port;
    /**
     * Время на запрос
     */
    private final static int socketTimeout = 4000;

    /**
     * Конструктор класса
     * @param address server address
     * @param port server port
     * @throws SocketException, если возникает ошибка доступа к сокету
     */
    public Sender(InetAddress address, int port) throws SocketException {
        socket = new DatagramSocket();
        socket.setSoTimeout(socketTimeout);
        this.address = address;
        this.port = port;
    }

    public void setBufferSize(int size) throws SocketException {
        socket.setReceiveBufferSize(size);
        socket.setSendBufferSize(size);
    }

    /**
     * Метод отправки запроса
     * @param bytes request
     * @throws IOException , если есть ошибка отправления
     */
    public void send(byte[] bytes) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, this.address, this.port);
        socket.send(datagramPacket);
        System.out.print("");
    }

    /**
     * Метод получения запроса
     * @return String response
     * @throws IOException, если есть ошибка получения
     */
    public Response receive() throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[1024 * 1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
        return (Response)objectInputStream.readObject();
    }
    public Response sendAndReceive(Command command){
        var request = new Request(command);
        try {
            send(Serializer.serialize(request));
            return receive();
        } catch (IOException | ClassNotFoundException e){
            return null;
        }
    }
}
