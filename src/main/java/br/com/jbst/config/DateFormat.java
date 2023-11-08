package br.com.jbst.config;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    public static void main(String[] args) {
        // Obtenha a data atual
        Date dataAtual = new Date();

        // Crie um formato de data personalizado
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");

        // Formate a data usando o formato personalizado
        String dataFormatada = sdf.format(dataAtual);

        // Imprima a data formatada
        System.out.println("Data formatada: " + dataFormatada);
    }
}
