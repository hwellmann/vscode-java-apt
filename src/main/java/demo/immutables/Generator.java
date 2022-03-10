package demo.immutables;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Generator {

    private static final String CODE = """
package demo.immutables.pkg;

import org.immutables.value.Value;

@Value.Immutable
public interface Something000  {

    String anything(); 
    String anythingElse(); 
}            
            """;

    private static void generateClass(int numPkg, int numClass) throws IOException {
        String pkg = String.format("pkg%03d", numPkg);
        String classSuffix = String.format("%03d", numClass);
        String code = CODE.replaceAll("pkg", pkg);
        code = code.replaceAll("000", classSuffix);
        Paths.get("src/main/java/demo/immutables", pkg).toFile().mkdirs();
        Path path = Paths.get("src/main/java/demo/immutables", pkg, "Something" + classSuffix + ".java");
        Files.write(path, List.of(code), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws IOException {
        for (int numPkg = 1; numPkg <= 5; numPkg++) {
            for (int numClass = 1; numClass <= 100; numClass++) {
                generateClass(numPkg, numClass);
            }
        }
    }
}
