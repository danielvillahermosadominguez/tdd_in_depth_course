package org.example;

public class SystemUnderTestExampleClass {
    private final ExampleCollaborator collaborator;

    public SystemUnderTestExampleClass(ExampleCollaborator collaborator) {
        this.collaborator = collaborator;
    }

    public int methodToTest() {
        return this.collaborator.method();
    }
}
