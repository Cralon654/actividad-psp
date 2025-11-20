import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorKart {

    // Variables estáticas para mantener el estado del inventario
    // entre diferentes conexiones de clientes.
    private static int kilosPlatanos = 300;
    private static int kilosCaparazones = 150;

    public static void main(String[] args) {
        int puerto = 666;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor Mario Kart escuchando en el puerto " + puerto);

            while (true) {
                // Esperar a que un cliente se conecte
                Socket clienteSocket = serverSocket.accept();

                // Crear flujos de entrada/salida
                DataInputStream entrada = new DataInputStream(clienteSocket.getInputStream());
                DataOutputStream salida = new DataOutputStream(clienteSocket.getOutputStream());

                // Leer la opción enviada por el cliente
                int opcion = entrada.readInt();
                String respuesta = procesarOpcion(opcion);

                // Enviar la respuesta al cliente
                salida.writeUTF(respuesta);

                // Cerrar la conexión con este cliente específico (según requisitos)
                clienteSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String procesarOpcion(int opcion) {
        String mensaje = "";

        switch (opcion) {
            case 1: // Consultar Plátanos
                mensaje = "Kilos de plátanos disponibles: " + kilosPlatanos;
                break;
            case 2: // Consultar Caparazones
                mensaje = "Kilos de caparazones disponibles: " + kilosCaparazones;
                break;
            case 3: // Recoger Plátanos (+1)
                kilosPlatanos++;
                mensaje = "Se ha recogido 1 kg de plátanos. Te quedan " + kilosPlatanos + " kg.";
                break;
            case 4: // Usar Plátanos (-1)
                if (kilosPlatanos > 0) {
                    kilosPlatanos--;
                    mensaje = "Se ha usado 1 kg de plátanos. Te quedan " + kilosPlatanos + " kg.";
                } else {
                    mensaje = "No quedan plátanos para usar.";
                }
                break;
            case 5: // Recoger Caparazones (+1)
                kilosCaparazones++;
                mensaje = "Se ha recogido 1 kg de caparazones. Te quedan " + kilosCaparazones + " kg.";
                break;
            case 6: // Usar Caparazones (-1)
                if (kilosCaparazones > 0) {
                    kilosCaparazones--;
                    mensaje = "Se ha usado 1 kg de caparazones. Te quedan " + kilosCaparazones + " kg.";
                } else {
                    mensaje = "No quedan caparazones para usar.";
                }
                break;
            default:
                mensaje = "Opción inválida en el menú.";
                break;
        }
        return mensaje;
    }
}