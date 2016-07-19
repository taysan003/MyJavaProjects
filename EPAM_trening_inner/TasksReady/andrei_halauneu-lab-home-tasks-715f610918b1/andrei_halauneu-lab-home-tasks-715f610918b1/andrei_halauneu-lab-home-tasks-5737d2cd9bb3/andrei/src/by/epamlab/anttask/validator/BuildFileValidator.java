package by.epamlab.anttask.validator;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;


/**
 * Created by Andrei Halauneu on 05.06.16
 */
public class BuildFileValidator extends Task {
    private static final String NAME_PATTERN = "[A-Za-z-]+";
    private static final String MAIN_NAME = "main";
    private boolean checkDefault;
    private boolean checkDepends;
    private boolean checkNames;
    private Project project;
    private List<BuildFile> buildFiles = new ArrayList<BuildFile>();

    /**
     * Setting class field
     * @param project value
     */
        public void setProject(final Project project) {
            this.project = project;
        }

    /**
     * Setting class field
     * @param checkDefault value
     */
    public void setCheckDefault(final boolean checkDefault) {
        this.checkDefault = checkDefault;
    }
    /**
     * Setting class field
     * @param checkDepends value
     */
    public void setCheckDepends(final boolean checkDepends) {
        this.checkDepends = checkDepends;
    }


    /**
     * Setting class field
     * @param checkNames value
     */
    public void setCheckNames(final boolean checkNames) {
        this.checkNames = checkNames;
    }

    /**
     * Creating  object and storing into collection
     * @return BuildFile object
     */
    public BuildFile createBuildFile() {
        BuildFile buildFile = new BuildFile();
        buildFiles.add(buildFile);
        return buildFile;
    }

    /**
     * Validation logic
     */
    @Override
    public void execute() {

        for (BuildFile buildFile : buildFiles) {
            project.log("Validating " + buildFile.getPath() + "...");
            Project newProject = getNewProject(buildFile.getPath());
            if (checkNames) {
                namesValidation(newProject);
            }
            if (checkDefault) {
                defaultTargetValidation(newProject);
            }
            if (checkDepends) {
                dependenciesValidator(newProject);
            }

        }
    }

    /**
     * Creating and configuring new project with required parameters
     * @param path -path to the analyzable buildfile
     * @return project - project object
     */
    private Project getNewProject(final String path) {
        File file = new File(path);
        Project project = new Project();
        ProjectHelper.configureProject(project, file);
        return project;
    }
    /**
     * Validating target names according requirements
     * @trows BuildException for invalid name
     * @param newProject - validating project
     */
    private void namesValidation(final Project newProject) {
        project.log("Name validation...");
        Collection<Target> targets = newProject.getTargets().values();
        for (Target target : targets) {
            if (target.getName().isEmpty()) {
                continue;
            }
            if (!(target.getName().matches(NAME_PATTERN))) {
                throw new BuildException(target.getName() + " is invalid!");
            }
        }
        project.log("Name validation successful.");
    }

    /**
     * Checking project for existence on default target
     *
     *@throws BuildException when project have no default target
     * @param newProject - analyzable Project
     */
    private void defaultTargetValidation(final Project newProject) {
        project.log("Default target validation...");
        if (newProject.getDefaultTarget() == null) {
            throw new BuildException("Can't find default target!");
        } else {
            project.log("Default target validation successful.");

        }
    }

    /**
     * Check project for absence main target as dependent
     *
     * @param newProject - analyzing project
     */
    private void dependenciesValidator(final Project newProject) {

            project.log("Dependencies validation...");
            Hashtable<String, Target> targets = newProject.getTargets();

            for (Target target : targets.values()) {
                if (target.getName().equals(MAIN_NAME)) {
                    continue;
                }
                if (target.getDependencies().hasMoreElements()) {
                    throw new BuildException(String.format("%s target contains no dependencies!",
                            MAIN_NAME));
                }
            }
            project.log("Dependencies validation successful.");
        }



    /**
     * Inner class
     * used for object creation
     * describes build file object
     */

    public class BuildFile {
        private String path;

        /**
         * Default constructor.
         */
        public BuildFile() { }

        /**
         * @return  path
         */
        public String getPath() {
            return path;
        }


        /**
         * Setting path
         * @param path
         */
        public void setPath(final String path) {
            this.path = path;
        }
    }
}

