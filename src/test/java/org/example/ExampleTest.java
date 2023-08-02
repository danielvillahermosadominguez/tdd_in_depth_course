package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExampleTest {
    
  @Test
  public void shouldAnswerWithTrue() {
    assertTrue(true);
  }

  @Test
  public void shouldAnExampleWithMockito() {
    //Arrange
    ExampleCollaborator collaborator = mock(ExampleCollaborator.class);
    SystemUnderTestExampleClass sut = new SystemUnderTestExampleClass(collaborator);
    when(collaborator.method()).thenReturn(1);

    //Act
    int result = sut.methodToTest();

    //Assert
    assertEquals(1, result);
  }
}

