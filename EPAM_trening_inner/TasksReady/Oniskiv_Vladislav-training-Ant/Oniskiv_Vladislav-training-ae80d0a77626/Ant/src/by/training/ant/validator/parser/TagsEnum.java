package by.training.ant.validator.parser;

/**
 * The enum Tags enum.
 */
public enum TagsEnum {
    /**
     * Property tags enum.
     */
    PROPERTY("property"),
    /**
     * Macrodef tags enum.
     */
    MACRODEF("macrodef"),
    /**
     * Target tags enum.
     */
    TARGET("target"),
    /**
     * Taskdef tags enum.
     */
    TASKDEF("taskdef"),
    /**
     * Element tags enum.
     */
    ELEMENT("element"),
    /**
     * Attribute tags enum.
     */
    ATTRIBUTE("attribute");

    private String value;

    TagsEnum(final String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
