import java.io.*;

public class LanzadorGame {
    public static void main(String[] args) {
        File f = new File("src/main/resources/entrada.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String opcion = br.readLine();

            String classpath = System.getProperty("java.class.path");
            ProcessBuilder proceso =
                    new ProcessBuilder("java", "-cp", classpath, "GameLauncher", opcion);

            proceso.inheritIO();

            try {
                Process p = proceso.start();
                p.waitFor();
                System.out.println("Proceso lanzado con éxito");
            } catch (IOException | InterruptedException e) {
                System.out.println("Error al lanzar el proceso: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
