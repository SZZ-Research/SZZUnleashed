package diff;

import org.incava.diffj.element.Differences;
import org.incava.diffj.io.JavaFile;
import org.incava.diffj.lang.DiffJException;

import java.util.HashSet;
import java.util.Set;

public class JavaFileExtension extends JavaFile {
    public JavaFileExtension(String contents) throws DiffJException {
        super(null, "label", contents, "1.8");
    }

    public CompilationUnitExtension compile() throws DiffJException {
        return new CompilationUnitExtension(super.compile().getAstCompUnit());
    }

    public Set<Integer> affectedLineNumbers(JavaFileExtension fileToCompare) throws DiffJException {
        CompilationUnitExtension fromCompUnit = compile();
        CompilationUnitExtension toCompUnit = fileToCompare.compile();
        Differences differences = fromCompUnit.diff(toCompUnit);
        Set<Integer> affectedLines = new HashSet<>();
        differences.getFileDiffs().forEach(it -> {
                affectedLines.add(it.getSecondLocation().getStart().getLine());
                affectedLines.add(it.getSecondLocation().getEnd().getLine());
            }
        );

        return affectedLines;
    }
}
