package com.jsonballadares;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("When running MathUtils")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class MathUtilsTest {

    MathUtils mathUtils;
    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeAll
    static void beforeAllInit(){
        System.out.println("This needs to run before all");
    }

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter){
        System.out.println("init");
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        mathUtils = new MathUtils();
    }

    @AfterEach
    void cleanup(){
        System.out.println("Cleaning Up....");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After all");
    }

    @Test
    @DisplayName("Testing Add Method")
    @Tag("Math")
    void testAdd() {
        int expected = 2;
        int actual = mathUtils.add(1,1);
        Assertions.assertEquals(expected,actual,"the add message should add two numbers");
    }

    @Test
    @Tag("Math")
    void testDivide() {
        assertThrows(ArithmeticException.class,() -> mathUtils.divide(1,0),"divide by zero should thow");
    }

    @RepeatedTest(3)
    @Tag("Circle")
    void testComputeCircleArea(RepetitionInfo repetitionInfo) {
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        double expected = 314.1592653589793;
        double actual = mathUtils.computeCircleArea(10);
        Assertions.assertEquals(expected,actual,"should return right circle area");
    }

    @Test
    @Disabled
    @DisplayName("TDD method. should not run")
    void testDisabled(){
        fail("This test should be disabled");
    }

    @Nested
    @DisplayName("add method")
    @Tag("Math")
    class AddTest{
        @Test
        @DisplayName("when adding two positive numbers")
        void testAddPositive(){
            assertEquals(2,mathUtils.add(1,1),"should return the right sum");
        }

        @Test
        @DisplayName("when adding two negative numbers")
        void testAddNegative(){
            int expected = -2;
            int actual = mathUtils.add(-1,-1);
            assertEquals(expected,actual,() -> "should return the sum " + expected + " but returned " + actual);
        }
    }

    @Test
    @DisplayName("multiply method")
    @Tag("Math")
    void testMultiply(){
        System.out.println("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
        assertAll(
                () -> assertEquals(4,mathUtils.multiply(2,2)),
                () -> assertEquals(0,mathUtils.multiply(2,0)),
                () -> assertEquals(-2,mathUtils.multiply(2,-1))
        );
    }
}