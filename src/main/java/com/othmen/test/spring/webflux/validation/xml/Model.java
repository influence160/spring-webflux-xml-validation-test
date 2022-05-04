package com.othmen.test.spring.webflux.validation.xml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Model {

    @NotNull
    @Size(min = 3, max = 3)
    private String id;

    // this annotation will is disabled in validation.xml
    @NotNull
    private final String alwaysNull = null;

    public static Model withId(String id) {
        return new Model().id(id);
    }

    public Model id(String id) {
        setId(id);
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return String.format("{Model : {id : %s}}", id);
    }
}
