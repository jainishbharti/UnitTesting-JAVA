package com.datagrokr;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisplayName("When runing mathUtils")
class MathUtilsTest {
	
	MathUtils mathUtils;
	TestInfo testInfo;
	TestReporter testReporter;
	
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This need to be run before all");
	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter= testReporter;
		mathUtils = new MathUtils();
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags" + testInfo.getTags());

	}

	
	@AfterEach
	void cleanup() {
	}
	
//	@Test
//	@DisplayName("testing add method numbers")
//	void testAdd() {
//		assertEquals(2, mathUtils.add(1, 1), "should return right sum");
//	}

	@Nested
	@DisplayName("add method")
	@Tag("Math")
	class AddTest{
		
		@Test
		@DisplayName("adding two positive numbers")
		void testAddPositive() {
			int expected = 2;
			int actual = mathUtils.add(1, 1);
			assertEquals(expected, actual, () -> "should return sum " + expected + "but returned " + actual );
		}
		
		@Test
		@DisplayName("adding two negative numbers")
		void testAddNegative() {
			assertEquals(-2, mathUtils.add(-1, -1), "should return right sum");
		}

	}
	
	@Test
	@DisplayName("multiply method")
	@Tag("Math")
	void testMultiply() {
		assertAll(
				() -> assertEquals(1, mathUtils.multiply(1, 1)),
				() -> assertEquals(0, mathUtils.multiply(0, 1)),
				() -> assertEquals(10, mathUtils.multiply(10, 1))
				);
	}
	
	@Test
//	@EnabledOnOs(OS.LINUX)
	@Tag("Math")
	void testDivide() {
		boolean isServerUp = true;
		
		assumeTrue(isServerUp);
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1,0),"Divide by zero should throw");	
	}
	
	@RepeatedTest(3)
	@Tag("Circle")
	void testComputeCircleArea(RepetitionInfo repetionInfo) {
//		repetitionInfo.get
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "Should return right circle area");
	}
	
	@Test
	@Disabled
	@DisplayName("Testing disabled")
	void testDisabled() {
		fail("This test should be disabled");
	}
	
	

}
