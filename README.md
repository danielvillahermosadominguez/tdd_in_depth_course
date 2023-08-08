# TDD in depth course

## Setup your test doubles

We are going to use mockito https://site.mockito.org/

We have added to the pom.xml the following dependency:

```xml
<!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.4.0</version>
    <scope>test</scope>
</dependency>

```

## Task: ATM Unit tests

- With the class Account, I think we are not verifying any behaviour. This is a class without
  any behaviour. So the test hasn't any value.
- Regarding the WelcomeScreen:
    - I have used characterization test for the happy path, trying to understand the behaviour
    - The edge cases cannot be tested (at least without changing the production code) because they
      have infinite loops when there is an error.
      NOTE: Maybe with awaitlaty we could test by using testing with thread. I didn't get it. But you
      have some commented code.
    - The code, sometimes is confuse, because we can see some methods which don't do what they say to do.

## Task: ATM Acceptance test

- I think the most difficult is to test a feature which doesn't exist in the code. Really,
  the acceptance criteria is not covered for this production code. The acceptance test has
  verified this.
- Other difficult part is to test the input and the output. I have emulated the input and I
  have catched the output with a ByteArrayInputStream and ByteArrayOutputStream, restoring the
  system output and input after each test.
- The Acceptance test is failing because the right reason. The feature is not implemented yet.

## Kata: Roman Numerals

TPP: https://en.wikipedia.org/wiki/Transformation_Priority_Premise
**Transformations**

- ({} → nil) no code at all → code that employs nil
- (nil → constant)
- (constant → constant+) a simple constant to a more complex constant
- (constant → scalar) replacing a constant with a variable or an argument
- (statement → statements) adding more unconditional statements.
- (unconditional → if) splitting the execution path
- (scalar → array)
- (array → container)
- (statement → tail-recursion)
- (if → while)
- (statement → non-tail-recursion)
- (expression → function) replacing an expression with a function or algorithm
- (variable → assignment) replacing the value of a variable.
- (case) adding a case (or else) to an existing switch or if

## Mars Rover Kata

Develop an API that moves a rover around on a grid.
Rules.

1. You are given the initial starting point (0,0,N)
2. 0,0 are X,Y co-ordinates on a grid of (10,10)
3. N is the direction it is facing (i.e. N, S, E, W)
4. L and R allow the rover to rotate left and right.
5. M allows the rover to move one point in the current direction
6. The rover receives a char array of commands e.g. RMMLM and returns the finishing point after the moves e.g 2:1:N
7. The rover wraps around if it reaches the end of the grid.
8. The grid may have obstacles. If a given sequence of commands encounters an obstacle, the rover moves up to the las
   possible point and reports the obstacle e.g. 0:2:2:N


