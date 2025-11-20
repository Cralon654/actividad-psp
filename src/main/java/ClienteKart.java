import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteKart {

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 666;
        Scanner scanner = new Scanner(System.in);

        // Mostramos el menú
        System.out.println("--- INVENTARIO MARIO KART ---");
        System.out.println("1. Consultar plátanos");
        System.out.println("2. Consultar caparazones");
        System.out.println("3. Recoger plátanos (+)");
        System.out.println("4. Usar plátanos (-)");
        System.out.println("5. Recoger caparazones (+)");
        System.out.println("6. Usar caparazones (-)");
        System.out.print("Elige una opción: ");

        int opcion = scanner.nextInt();

        try {
            // Conectar al servidor
            Socket socket = new Socket(host, puerto);

            // Crear flujos
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            DataInputStream entrada = new DataInputStream(socket.getInputStream());

            // 1. Enviar opción al servidor
            salida.writeInt(opcion);

            // 2. Recibir respuesta del servidor
            String respuesta = entrada.readUTF();

            // 3. Mostrar respuesta
            System.out.println("SERVIDOR: " + respuesta);

            // Cerrar recursos
            socket.close();

        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }
}