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

## ATM - TDD II: Account Balance. Specific requirements decisions

The decisions are based on the original code. Where we can see the balance is an integer in the
Domain entity "Account"

```java
public class Account {
    ..
    private Integer balance;

    public Account(String accountNumber, Integer balance) {
        ..
        this.balance = balance;
    }

    ..

    public String balance() {
        return balance.toString();
    }
}
```

To apply TDD for this change, I think we should reuse the Acceptation Test. In my case, I have seen this
acceptance test doesn't work because the code is not working because there are several things not implemented.

In addition, I think it makes not sense to apply TDD to anemic classes which they didn't have any behaviour. That
is:

- Account class. It is an entity class, there is not behaviour.
- Balance class. (The)

