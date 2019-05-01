package net.zibady.task.kindgeek_test.exception;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String exception) {
        super(exception);
    }
}
