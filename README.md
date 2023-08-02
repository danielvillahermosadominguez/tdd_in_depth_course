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

