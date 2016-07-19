package by.gsu.epamlab.task;

import org.apache.tools.ant.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Andrei.
 * @version 1.0
 * A validator for Ant buildfile.
 */
public class BuildFileValidator extends Task {
    private boolean checkDepends;
    private boolean checkDefault;
    private boolean checkNames;
    private Project project;
    private List<BuildFile> buildFiles = new ArrayList<BuildFile>();

    private static final String DEFAULT_TARGET = "main";
    private static final String NAMES_PATTERN = "[a-zA-Z-]+";

    /**
     * Set a flag to check a dependencies or not.
     * @param checkDepends flag to set.
     */
    public void setCheckdepends(final boolean checkDepends) {
        this.checkDepends = checkDepends;
    }

    /**
     * Set a flag to check a default target of the project or not.
     * @param checkDefault flag to set.
     */
    public void setCheckdefault(final boolean checkDefault) {
        this.checkDefault = checkDefault;
    }

    /**
     * Set a flag to check a names of a targets or not.
     * @param checkNames flag to set.
     */
    public void setChecknames(final boolean checkNames) {
        this.checkNames = checkNames;
    }

    @Override
    public void setProject(final Project project) {
        this.project = project;
    }

    @Override
    public void execute() throws BuildException {
        for (BuildFile buildFile : buildFiles) {
            project.log("\nFile " + buildFile.getLocation() + " is being validated.");
            Project buildProject = configureBuildProject(buildFile.getLocation());
            if (checkDefault) {
                validateDefaultTarget(buildProject);
            }
            if (checkDepends) {
                validateDependencies(buildProject);
            }
            if (checkNames) {
                validateNames(buildProject);
            }
            project.log("\nValidation complete.");
        }
    }

    /**
     * Creates a new buildfile object for check, add it to the List and returns it.
     * @return buildfile object.
     */
    public BuildFile createBuildFile() {
        BuildFile buildFile = new BuildFile();
        buildFiles.add(buildFile);
        return buildFile;
    }

    private Project configureBuildProject(final String path) {
        File file = new File(path);
        Project newProject = new Project();
        ProjectHelper.configureProject(newProject, file);
        return newProject;
    }

    private void validateDependencies(final Project buildProject) {
        project.log("\nDependencies are being validated...");
        Map<String, Target> targets = buildProject.getTargets();
        for (Target target : targets.values()) {
            if (DEFAULT_TARGET.equals(target.getName())) {
                continue;
            }
            if (target.getDependencies().hasMoreElements()) {
                throw new BuildException("Target " + target.getName()
                        + " has dependencies. Validation failed.");
            }
        }
        project.log("OK.");
    }

    private void validateDefaultTarget(final Project buildProject) {
        project.log("\nDefault target is being validated...");
        String defaultTarget = buildProject.getDefaultTarget();
        if (defaultTarget != null) {
            project.log("OK. Default target is " + defaultTarget + ".");
        } else {
            throw new BuildException("Buildfile has no any default target.");
        }
    }

    private void validateNames(final Project buildProject) {
        project.log("\nNames is being validated...");
        for (Target target : buildProject.getTargets().values()) {
            if (target.getName().isEmpty()) {
                continue;
            }
            if (!isValid(target.getName(), NAMES_PATTERN)) {
                throw new BuildException("Name is empty. Validation failed.");
            }
        }
        project.log("OK.");
    }

    /**
     * Attempts to match the given name against the given regular expression.
     * @param name name of the target.
     * @param pattern regular expression to match.
     */
    private boolean isValid(final String name, final String pattern) {
        return Pattern.matches(pattern, name);
    }

    /**
     * Ant buildfile.
     */
    public class BuildFile {
        private String location;

        /**
         * Get path of the file.
         * @return path.
         */
        public String getLocation() {
            return location;
        }

        /**
         * Set path of the file.
         * @param location new path.
         */
        public void setLocation(final String location) {
            this.location = location;
        }
    }
}
