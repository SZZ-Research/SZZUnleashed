package diff;

import net.sourceforge.pmd.lang.java.ast.ASTCompilationUnit;
import org.incava.diffj.compunit.CompilationUnit;
import org.incava.diffj.compunit.Imports;
import org.incava.diffj.compunit.Package;
import org.incava.diffj.element.Differences;
import org.incava.diffj.type.Types;

public class CompilationUnitExtension extends CompilationUnit {
    public CompilationUnitExtension(ASTCompilationUnit compUnit) {
        super(compUnit);
    }

    public Differences diff(CompilationUnit toCompUnit) {
        if (toCompUnit == null) {
            return null;
        }

        Differences differences = new Differences();

        Package fromPackage = getPackage();
        Package toPackage = toCompUnit.getPackage();
        fromPackage.diff(toPackage, differences);

        Imports fromImports = getImports();
        Imports toImports = toCompUnit.getImports();
        fromImports.diff(toImports, differences);

        Types fromTypes = getTypes();
        Types toTypes = toCompUnit.getTypes();
        fromTypes.diff(toTypes, differences);

        return differences;
    }
}
