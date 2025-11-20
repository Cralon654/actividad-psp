import java.io.*;

public class LanzadorGameMod {
    public static void main(String[] args) {
        File f = new File("src/main/resources/entrada.txt");
        File salida = new File("src/main/resources/salida.txt");
        File errores = new File("src/main/resources/error.txt");
        salida.canWrite();
        errores.canWrite();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String opcion = br.readLine();

            String classpath = System.getProperty("java.class.path");
            ProcessBuilder proceso =
                    new ProcessBuilder("java", "-cp", classpath, "GameLauncher", opcion);
            try {
                proceso.redirectOutput(salida);
                Process p = proceso.start();
                p.waitFor();
                System.out.println("Proceso lanzado con éxito");
            } catch (IOException | InterruptedException e) {
                proceso.redirectOutput(errores);
                proceso.redirectErrorStream(true);
                System.out.println("Error al lanzar el proceso: " + e.getMessage());
            } finally {
                br.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
