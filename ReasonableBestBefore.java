package techpuzzles;

import java.util.Arrays;
import java.util.Scanner;

public class ReasonableBestBefore {

	private Scanner stdin = new Scanner(System.in);

	public void run() {
		String date = stdin.nextLine();
		String[] dates = date.split("/");
		int[] dated = new int[3];
		if (date.matches("\\d{1,4}/{1}\\d{1,4}/{1}\\d{1,4}")) {
			for (int i = 0; i < dated.length; i++)
				dated[i] = Integer.parseInt(dates[i]);
			dated = findSmallest(dated);
			if (dated != null)
				formatPrint(dated);
			else
				System.out.println(date + " is illegal");
		} else {
			System.out.println(date + " is illegal");
		}
	}

	public boolean isLeapYear(int year) {
		boolean valid = false;
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			valid = true;
		return valid;
	}

	public void formatPrint(int[] dates) {
		String year = String.valueOf(dates[0]), month = String.valueOf(dates[1]), day = String.valueOf(dates[2]);
		if (dates[0] < 10)
			year = "200" + dates[0];
		else if (dates[0] < 100)
			year = "20" + dates[0];
		if (dates[1] < 10)
			month = "0" + dates[1];
		if (dates[2] < 10)
			day = "0" + dates[2];
		System.out.println(year + "-" + month + "-" + day);
	}

	public int[] findSmallest(int[] dates) {
		Arrays.sort(dates);
		int[] smallest = new int[3];
		int[] combo1 = { dates[0], dates[1], dates[2] };
		int[] combo2 = { dates[0], dates[2], dates[1] };
		int[] combo3 = { dates[1], dates[0], dates[2] };
		int[] combo4 = { dates[1], dates[2], dates[0] };
		int[] combo5 = { dates[2], dates[0], dates[1] };
		int[] combo6 = { dates[2], dates[1], dates[0] };

		if (isValid(combo1))
			smallest = combo1;
		else if (isValid(combo2))
			smallest = combo2;
		else if (isValid(combo3))
			smallest = combo3;
		else if (isValid(combo4))
			smallest = combo4;
		else if (isValid(combo5))
			smallest = combo5;
		else if (isValid(combo6))
			smallest = combo6;
		else
			smallest = null;
		return smallest;
	}

	public boolean isValid(int[] array) {
		boolean valid = false;
		if ((array[0] >= 0 && array[0] <= 99 || array[0] >= 2000 && array[0] <= 2999) && (array[1] > 0 && array[1] <= 12) && (array[2] > 0 && checkDays(array[0], array[1], array[2]))) 
			valid = true;
		return valid;
	}

	public boolean checkDays(int year, int month, int day) {
		boolean valid = true;
		month--;
		int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (day > days[month])
			valid = false;
		if (month == 1 && isLeapYear(year))
			if (day > days[month] + 1)
				valid = false;
			else
				valid = true;
		return valid;
	}

	public static void main(String[] args) {
		ReasonableBestBefore rbb = new ReasonableBestBefore();
		rbb.run();
	}
}