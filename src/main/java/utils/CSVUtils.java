package utils;

import models.Usuario;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.List;

public class CSVUtils {
    private static final String HEADER = "id,nome,email,data_nascimento,data_cadastro";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static void exportarUsuarios(List<Usuario> usuarios, String caminhoArquivo) {
        Path caminho = Path.of(caminhoArquivo);

        try (BufferedWriter writer = Files.newBufferedWriter(caminho, StandardCharsets.UTF_8)) {
            writer.write(HEADER);
            writer.newLine();

            for (Usuario usuario : usuarios) {
                writer.write(toCsvLine(usuario));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao exportar CSV: " + e.getMessage(), e);
        }
    }

    private static String toCsvLine(Usuario usuario) {
        return String.join(",",
                valueOf(usuario.getId()),
                escape(usuario.getNome()),
                escape(usuario.getEmail()),
                formatDate(usuario.getDataNascimento()),
                formatDate(usuario.getDataCadastro())
        );
    }

    private static String valueOf(Object value) {
        return value == null ? "" : value.toString();
    }

    private static String formatDate(java.util.Date date) {
        return date == null ? "" : DATE_FORMAT.format(date);
    }

    private static String escape(String value) {
        if (value == null) {
            return "";
        }

        String escaped = value.replace("\"", "\"\"");
        if (escaped.contains(",") || escaped.contains("\"") || escaped.contains("\n") || escaped.contains("\r")) {
            return "\"" + escaped + "\"";
        }
        return escaped;
    }
}
