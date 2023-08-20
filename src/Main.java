import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class Main {

    /*
    meter lista
    cambiar a entero
    meter a un array
    hacer la suma prefija (como ya lo habias hecho en el codigo pasado)
    Filewriter
     */
    public static void main(String[] args) {
            String ruta = "src/car_sales.csv"; // Reemplaza con el nombre de tu archivo
            String rutaSalida = "src/suma_prefija.csv"; // Reemplaza con la ruta del archivo de salida

        List<String> contentList = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
                String linea;

                while ((linea = reader.readLine()) != null) {
                    int signoDolar = linea.indexOf('$');

                    if (signoDolar != -1 && signoDolar < linea.length() - 1) {
                        String despSigno = linea.substring(signoDolar + 1);
                        contentList.add(despSigno);
                    }
                }
                // Convertir la lista de strings a un arreglo de doubles
                double[] doubleArray = new double[contentList.size()];
                for (int i = 0; i < contentList.size(); i++) {
                    doubleArray[i] = Double.parseDouble(contentList.get(i));
                }

                // Calcular la suma prefija
                double[] sumaPrefija = new double[doubleArray.length];
                double sumaAcumulada = 0.0;
                for (int i = 0; i < doubleArray.length; i++) {
                    sumaAcumulada += doubleArray[i];
                    sumaPrefija[i] = sumaAcumulada;
                }


                // Formatear y guardar la suma prefija en un archivo CSV
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaSalida))) {
                    DecimalFormat df = new DecimalFormat("#.##");
                    for (double valor : sumaPrefija) {
                        writer.write(df.format(valor));
                        writer.newLine(); // Nueva línea para cada valor
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}

