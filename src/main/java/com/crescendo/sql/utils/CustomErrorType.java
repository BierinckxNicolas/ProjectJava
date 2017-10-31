package com.crescendo.sql.utils;

/** Custom error handling
 * @author Groep 5
 */
public class CustomErrorType {
    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
