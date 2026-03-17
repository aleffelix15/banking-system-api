package estrutura;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Persistencia {

    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();

    public static ArrayList<Cliente> carregarClientes(String arquivo) {
        File f = new File(arquivo);
        if(!f.exists() || f.length() == 0) return new ArrayList<>(); // arquivo vazio ou não existe

        try (Reader reader = new FileReader(arquivo)) {
            return gson.fromJson(reader, new TypeToken<ArrayList<Cliente>>(){}.getType());
        } catch (IOException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarClientes(ArrayList<Cliente> clientes, String arquivo) {
        try (Writer writer = new FileWriter(arquivo)) {
            gson.toJson(clientes, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }
}
