package duke.ui;

public enum ErrorMsgWithParams {
    EMPTY_DESCRIPT(Constants.PARAM_PLACEHOLDER + " cannot have an empty " + Constants.PARAM_PLACEHOLDER + "."),
    EMPTY_DESCRIPT_AND_OR_DATE("The description and date of a " + Constants.PARAM_PLACEHOLDER + " cannot be empty."),
    BAD_DATE_FORMAT_WITH_PARAMS(Constants.PARAM_PLACEHOLDER + " is ill formatted. Example: "
            + Constants.PARAM_PLACEHOLDER + " return book /by 2/12/2019 1800");

    private static class Constants {
        public static final String PARAM_PLACEHOLDER = "#";
    }

    private String description = null;

    ErrorMsgWithParams(String desc) {
        this.description = desc;
    }

    /**
     * Returns description of error message (with parameters embedded inside).
     * CAUTION: Please pay attention to the PARAM_PLACEHOLDER used as this function
     * does not account for this placeholder in the parameter provided.
     *
     * @param parameters parameters to replace the PARAM_PLACEHOLDER
     * @return error message
     */
    public String getDescription(String... parameters) {
        String parameterizedDescription = description;
        for (String parameter : parameters) {
            if (parameterizedDescription.contains(Constants.PARAM_PLACEHOLDER)) {
                parameterizedDescription = parameterizedDescription
                        .replaceFirst(Constants.PARAM_PLACEHOLDER, parameter);
            }
        }

        return parameterizedDescription;
    }
}
