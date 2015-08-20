package com.grace.test;

import java.security.InvalidParameterException;

import org.junit.Assert;
import org.junit.Test;

public class DateUnitTest {
	DateUtilImpl dateUtil = new DateUtilImpl();
	
	@Test(expected = InvalidParameterException.class) 
	public void testWrongDate(){
		// set the wrong year
		Date date = new Date(1, Date.JANUARY, 0);
	}
	
	@Test(expected = InvalidParameterException.class) 
	public void testWrongLeapDate(){
		// set the wrong leap year
	    Date date = new Date(29, Date.FEBRUARY, 3);
	}
	
	@Test(expected = InvalidParameterException.class) 
	public void testWrongLargeYear(){
		// set the year greater the MAX_YEAR
		Date date = new Date(1, Date.JANUARY, 10000);
	}
	
	@Test(expected = InvalidParameterException.class) 
	public void testWrongEra(){
		// set the era neither 0 nor 1
		Date date = new Date(1, Date.JANUARY, 1999, 2);
	}
	
	@Test(expected = InvalidParameterException.class) 
	public void testWrongDay(){
		// set wrong day
		Date date = new Date(31, Date.APRIL, 2015);
	}
	
	@Test
	public void testEqualDate(){
		// set equal dates
		Date date1 = new Date(10,Date.AUGUST,1999);
	    Date date2 = new Date(10,Date.AUGUST,1999);
	    int result = dateUtil.diff(date1, date2);
	    Assert.assertEquals(0, result);
	}
	
	@Test
	public void testEqualBCDate(){
		// set equal dates before 1 AD
		Date date1 = new Date(10,Date.JULY,2999, Date.BC);
	    Date date2 = new Date(10,Date.JULY,2999, Date.BC);
	    int result = dateUtil.diff(date1, date2);
	    Assert.assertEquals(0, result);
	}
	
	@Test
	public void testNormalDate(){
		// set the normal dates
		Date date1 = new Date(10,Date.APRIL,2015);
	    Date date2 = new Date(20,Date.APRIL,2015);
	    int result = dateUtil.diff(date1, date2);
	    Assert.assertEquals(10, result);
	}
	
	@Test
	public void testStringConstructor(){
		// set the normal dates using constructor having String parameters
		Date date1 = new Date("10","3","2015");
	    Date date2 = new Date("20","3","2015");
	    int result = dateUtil.diff(date1, date2);
	    Assert.assertEquals(10, result);
	}
	
	@Test
	public void testDatesOverLeapYear(){
		// set the dates that over a leap year
		Date date1 = new Date(10,Date.MARCH,4);
	    Date date2 = new Date(10,Date.FEBRUARY,4);
	    int result = dateUtil.diff(date1, date2);
	    Assert.assertEquals(-29, result);
	}
	
	@Test
	public void testDatesOverYear(){
		// set the dates that over many years
		Date date1 = new Date(10,Date.MARCH,2000);
	    Date date2 = new Date(10,Date.FEBRUARY,1);
	    int result = dateUtil.diff(date1, date2);
	    Assert.assertEquals(-730149, result);
	}
	
	@Test
	public void testDatesOverLargestYears(){
		/**
		 * set the dates that over the largest years
		 * the days from 1st Jan 1 AD to 31st Dec 9999 AD:
		 * 9999*365=3649635; 
		 * years divisible by 4=2499; 
		 * years divisible by 100 but not divisible by 400=75
		 * so the leap years=2424
		 * then the days = 3649635 + 2424 = 3652059
		 * 
		 * the days from 1st Jan 9999 BC to 31st Dec 1 BC:
		 * should be 3652059+1 because 1 BC is a leap year
		 * 
		 * the result should be 3652059+3652060-1
		 */
		Date date1 = new Date(1,Date.JANUARY,9999, Date.BC);
	    Date date2 = new Date(31,Date.DECEMBER,9999);
	    int result = dateUtil.diff(date1, date2);
	    Assert.assertEquals(7304118, result);
	}
	
	@Test
	public void testDatesFromBCToAD(){
		/**
		 * set the dates from BC to AD
		 * The sequence of years at the transition from BC to AD is
	     * ..., 2 BC, 1 BC, 1 AD, 2 AD,...
		 */
		Date date1 = new Date(31,Date.DECEMBER,1, Date.BC);
	    Date date2 = new Date(1,Date.JANUARY,1);
	    int result = dateUtil.diff(date1, date2);
	    Assert.assertEquals(1, result);
	}
	
	@Test
	public void testLeapYearBeforeAD(){
		/**
		 * 1 BC is a leap year
		 * from 1st Feb 1 BC to 1st Jan 1 AD should be 366
		 */
		Date date1 = new Date(1,Date.JANUARY,1, Date.BC);
	    Date date2 = new Date(1,Date.JANUARY,1);
	    int result = dateUtil.diff(date1, date2);
	    Assert.assertEquals(366, result);
	}
	
	@Test
	public void testDatesBeforeAD(){
		Date date1 = new Date(1,Date.JANUARY,1, Date.BC);
	    Date date2 = new Date(1,Date.JANUARY,2, Date.BC);
	    int result = dateUtil.diff(date1, date2);
	    Assert.assertEquals(-365, result);
	}
}
