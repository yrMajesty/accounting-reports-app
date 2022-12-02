import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Reader {

    public static String readFileContentsOrNull(String path) {

        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
    public static String readMonthNumber(int i) {
        if (i < 10) {
            String reader = Reader.readFileContentsOrNull("resources/m.20210" + i + ".csv");
            return reader;
        } else {
            String reader = Reader.readFileContentsOrNull("resources/m.2021" + i + ".csv");
            return reader;
        }

    }


}