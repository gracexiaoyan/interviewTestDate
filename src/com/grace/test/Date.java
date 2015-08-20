package com.grace.test;

import java.security.InvalidParameterException;

public class Date {
	// days of month in common year
	public static final int[] MONTHS = {31,28,31,30,31,30,31,31,30,31,30,31};
	// days of month in leap year
	public static final int[] MONTHS_LEAP = {31,29,31,30,31,30,31,31,30,31,30,31};
	// define the max year for practical use
	public static final int MAX_YEAR = 9999;
	// define the min year
	public static final int MIN_YEAR = 1;
	// define the ERA
	public static final int AD = 1;
	public static final int BC = 0;
	
	// JANUARY
    public final static int JANUARY = 0;
    // FEBRUARY
    public final static int FEBRUARY = 1;
    // MARCH
    public final static int MARCH = 2;
    // APRIL
    public final static int APRIL = 3;
    // MAY
    public final static int MAY = 4;
    // JUNE
    public final static int JUNE = 5;
    // JULY
    public final static int JULY = 6;
    // AUGUST
    public final static int AUGUST = 7;
    // SEPTEMBER
    public final static int SEPTEMBER = 8;
    // OCTOBER
    public final static int OCTOBER = 9;
    // NOVEMBER
    public final static int NOVEMBER = 10;
    // DECEMBER
    public final static int DECEMBER = 11;
	
	private String dD;
	private String mM;
	private String yYYYY;
	private int era;
	
	
	/**
	 * constructor
	 * @param dD day, start from 1
	 * @param mM month, start from 0. 
	 * @param yYYYY year
	 */
	public Date(String dD, String mM, String yYYYY) throws InvalidParameterException{
		this.yYYYY = yYYYY;
		this.mM = mM;
		this.dD = dD;
		this.era = AD;
		valid();
	}
	
	/**
	 * constructor
	 * @param dD day, start from 1
	 * @param mM month, start from 0. 
	 * @param yYYYY year
	 */
	public Date(int dD, int mM, int yYYYY) throws InvalidParameterException{
		this.yYYYY = String.valueOf(yYYYY);
		this.mM = String.valueOf(mM);
		this.dD = String.valueOf(dD);
		this.era = AD;
		valid();
	}
	
	/**
	 * constructor
	 * @param dD day, start from 1
	 * @param mM month, start from 0. 
	 * @param yYYYY year
	 * @param era the era, either is AD or BC
	 */
	public Date(String dD, String mM, String yYYYY, int era) throws InvalidParameterException{
		this.yYYYY = yYYYY;
		this.mM = mM;
		this.dD = dD;
		this.era = era;
		valid();
	}
	
	/**
	 * constructor
	 * @param dD day, start from 1
	 * @param mM month, start from 0. 
	 * @param yYYYY year
	 */
	public Date(int dD, int mM, int yYYYY, int era) throws InvalidParameterException{
		this.yYYYY = String.valueOf(yYYYY);
		this.mM = String.valueOf(mM);
		this.dD = String.valueOf(dD);
		this.era = era;
		valid();
	}
	
	/**
	 * return true if the date is a valid date
	 * @return
	 */
	private void valid() throws InvalidParameterException{
		int iYYYY, iM, iD;
		try{
			iYYYY = Integer.parseInt(getyYYYY());
			iM = Integer.parseInt(getmM());
			iD = Integer.parseInt(getdD());
		}
		catch(NumberFormatException e){
			throw new InvalidParameterException("The parameters should be numbers.");
		}
		// return false if the year is out of the range defined in Date
		if(iYYYY < Date.MIN_YEAR || iYYYY > Date.MAX_YEAR){
			throw new InvalidParameterException("The year parameter should be from 1 to 9999.");
		}
		// return false if the month number is invalid
		if(iM < JANUARY || iM > DECEMBER){
			throw new InvalidParameterException("The month parameter should be from 0 to 11.");
		}
		// return false if the day number is less than 1
		if(iD < 1){
			throw new InvalidParameterException("The day parameter should be greater than 1.");
		}
		// return false if the day number is greater than the max day of the month
		if(isLeapYear() && iD > Date.MONTHS_LEAP[iM]){
			throw new InvalidParameterException("The day parameter should be equal or less than " + Date.MONTHS_LEAP[iM] + ".");
		}
		if(!isLeapYear() && iD > Date.MONTHS[iM]){
			throw new InvalidParameterException("The day parameter should be equal or less than " + Date.MONTHS[iM] + ".");
		}
		if(getEra() != AD && getEra() != BC){
			throw new InvalidParameterException("The era parameter should be 0 or 1.");
		}
	}
	
	/**
	 * return true if the year is leap year, otherwise return false. 
	 * To be simplified, just use Gregorian system
	 * @return
	 */
	public boolean isLeapYear(){
		int year = Integer.parseInt(getyYYYY());
		if(!isAD()){
			year = year - 1;
		}
		if ((year & 3) != 0) {
		    return false;
		}
		return (year%100 != 0) || (year%400 == 0);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Date)){
			return false;
		}
		Date objDate = (Date)obj;
		if(getdD().equals(objDate.getdD()) 
				&& getmM().equals(objDate.getmM()) 
				&& getyYYYY().equals(objDate.getyYYYY())
				&& getEra() == objDate.getEra()){
			return true;
		}
		return false;
	}
	
	/**
	 * return ture if the date era is AD
	 * @return
	 */
	public boolean isAD(){
		return era == AD;
	}

	public String getdD() {
		return dD;
	}

	public String getmM() {
		return mM;
	}

	public String getyYYYY() {
		return yYYYY;
	}

	public int getEra() {
		return era;
	}
	
}
