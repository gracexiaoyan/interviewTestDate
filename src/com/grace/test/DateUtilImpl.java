package com.grace.test;

/**
 * a utility class that calculate the difference between to dates
 * @author Grace Xiaoyan Wang
 *
 */
public class DateUtilImpl implements IDateUtil {
	
	/**
	 * return the difference between two dates
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	@Override
	public int diff(Date firstDate, Date secondDate) {
		if(firstDate.equals(secondDate)){
			return 0;
		}
		// get the days from 1st Jan 1 AD to the given date
		int firstDays = diffFromAD(firstDate);
		int secondDays = diffFromAD(secondDate);
		
		return secondDays - firstDays;
	}
	
	/**
	 * return the days from 1st Jan 1 AD to the given date
	 * return negative if the given date is before AD
	 * @param date
	 * @return
	 */
	private int diffFromAD(Date date){
		int iYYYY = Integer.parseInt(date.getyYYYY());
		int iM = Integer.parseInt(date.getmM());
		int iD = Integer.parseInt(date.getdD());
		if(date.isAD()){
			// the amount of days
			int amountDays = (iYYYY - 1) * 365;
			// get the amount of leap years from 0 to the given year, exclude year 0.
			int leapYears = getLeapYears(iYYYY);
			// every leap year has extra 1 day
			amountDays = amountDays + leapYears;
			
			// calculate the days in the given year
			int dayOfCurrentYear = iD;
			if(date.isLeapYear()){
				for(int i = 0; i < iM; i++){
					dayOfCurrentYear += Date.MONTHS_LEAP[i];
				}
			}
			else{
				for(int i = 0; i < iM; i++){
					dayOfCurrentYear += Date.MONTHS[i];
				}
			}
			amountDays = amountDays + dayOfCurrentYear;
			return amountDays - 1;
		}
		else{
			// the amount of whole years
			int amountWholeYears = iYYYY - 1;
			// the amount of days
			int amountDays = amountWholeYears * 365;
			// get the amount of leap years from 1 AD to the given year.
			int leapYears = getLeapYears(amountWholeYears);
			// if amountWholeYears is greater than 0, then should plus 1 because 1 BC is a leap year
			if(amountWholeYears > 0){
				leapYears = leapYears + 1;
			}
			// every leap year has extra 1 day
			amountDays = amountDays + leapYears;
						
			// calculate the days in the given year
			int dayOfCurrentYear = 0;
			if(date.isLeapYear()){
				dayOfCurrentYear = Date.MONTHS_LEAP[iM] - iD + 1;
				for(int i = iM+1; i < 12; i++){
					dayOfCurrentYear += Date.MONTHS_LEAP[i];
				}
			}
			else{
				dayOfCurrentYear = Date.MONTHS[iM] - iD + 1;
				for(int i = iM+1; i < 12; i++){
					dayOfCurrentYear += Date.MONTHS[i];
				}
			}	
			amountDays = amountDays + dayOfCurrentYear;
			return -amountDays;
		}
	}
	
	/**
	 * get the amount of leap years from 1 AD to the given year.
	 * @param year
	 * @return
	 */
	private int getLeapYears(int year){
		// the amount of years which are divisible by 4
		int leapYears = year / 4;
		// the amount of years which are divisible by 100
		int divBy100 = year / 100;
		// the amount of years which are divisible by 400
		int divBy400 = year / 400;
		
		// return the amount of leap years
		return leapYears - divBy100 + divBy400;
	}

}
