package by.training.ant.validator;

import by.training.ant.validator.parser.DOMParser;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Build file validator.
 */
public class BuildFileValidator extends Task {
    private boolean checkDepends;
    private boolean checkDefault;
    private boolean checkNames;
    private String location;
    private List<BuildFile> buildFile = new ArrayList<BuildFile>();

    /**
     * Sets check depends.
     *
     * @param checkDepends the check depends
     */
    public void setCheckDepends(final boolean checkDepends) {
        this.checkDepends = checkDepends;
    }

    /**
     * Sets check default.
     *
     * @param checkDefault the check default
     */
    public void setCheckDefault(final boolean checkDefault) {
        this.checkDefault = checkDefault;
    }

    /**
     * Sets check names.
     *
     * @param checkNames the check names
     */
    public void setCheckNames(final boolean checkNames) {
        this.checkNames = checkNames;
    }

    /**
     * Execute metod.
     *
     * @throws BuildException the build exception
     */
    public void execute() throws BuildException {
        DOMParser dom = new DOMParser(checkDepends, checkDefault, checkNames);
        for (BuildFile file : buildFile) {
            if (dom.validation(file.getLocation())) {
                log("The file " + file.getLocation() + " is valid", Project.MSG_INFO);
            } else {
                log("The file " + file.getLocation() + " is not valid", Project.MSG_INFO);
            }
        }
    }

    /**
     * Create build file build file.
     *
     * @return the build file
     */
    public BuildFile createBuildFile() {
        BuildFile file = new BuildFile();
        buildFile.add(file);
        return file;
    }
}

