package hexlet.code.formatters;

import hexlet.code.Node;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import static hexlet.code.formatters.StylishFormat.STYLISH_NAME;
import static hexlet.code.formatters.PlainFormat.PLAIN_NAME;
import static hexlet.code.formatters.JsonFormat.JSON_NAME;

public class Formatter {
    private static final Map<String, Format> FORMATS = Map.of(
            STYLISH_NAME, new StylishFormat(),
            PLAIN_NAME, new PlainFormat(),
            JSON_NAME, new JsonFormat()
    );

    /**
     * Метод преобразует данные в выбранный/поддерживаемый формат.
     *
     * @param diffData данные для преобразования
     * @param format формат выходных данных
     * @return строка в выбранном формате
     * @throws IOException если формат не поддерживается
     */
    public static String format(TreeMap<String, Node> diffData, String format) throws IOException {
        Format formatter = getFormat(format);
        if (formatter == null) {
            throw new IOException("Unsupported format: " + format);
        }
        return formatter.generate(diffData);
    }

    /**
     * Возвращает форматтер для указанного формата.
     *
     * @param format имя формата
     * @return объект Format или null, если формат не найден
     */
    public static Format getFormat(String format) {
        return FORMATS.get(format);
    }
}