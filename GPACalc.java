import java.util.*;

public class GPACalc {

    private static ArrayList<Integer> credits;
    private static ArrayList<Double> grades;
    private static Scanner sc;
    private static Map<String, Double> letterToNumber;
    private static boolean exit = false;

    public GPACalc() {}

    public static void initializeConstant() {
	letterToNumber = new HashMap<String, Double>();
	letterToNumber.put("A+", 4.3);
	letterToNumber.put("A", 4.0);
	letterToNumber.put("A-", 3.7);
	letterToNumber.put("B+", 3.3);
	letterToNumber.put("B", 3.0);
	letterToNumber.put("B-", 2.7);
	letterToNumber.put("C+", 2.3);
	letterToNumber.put("C", 2.0);
	letterToNumber.put("C-", 1.7);
	letterToNumber.put("D+", 1.3);
	letterToNumber.put("D", 1.0);
	letterToNumber.put("D-", 0.7);
	letterToNumber.put("F", 0.0);
    }

    public static void main(String args[]) {
	initializeConstant();
	credits = new ArrayList<Integer>();
	grades = new ArrayList<Double>();
	while (!exit) {
	    readNext();
	}
    }

    public static double calculate() {
	double ret = 0.0;
	double totalCredits = 0.0;
	for (int i = 0; i < credits.size(); i++) {
	    ret += (((double) credits.get(i)) * grades.get(i));
	    totalCredits += (double) credits.get(i);
	}
	
	return ret / totalCredits;
    }

    public static void readNext() {
	if (sc == null) {
	    sc = new Scanner(System.in).useDelimiter("\n");
	}
	System.out.print("Type credits and grades: ");
	String command = sc.next();
	processInput(command);
    }

    public static void processInput(String command) {
	String commandUp = command.toUpperCase();
	if (commandUp.equals("F") || commandUp.equals("X")) {
	    double finalGPA = calculate();
	    System.out.println("Result: " + Double.toString(finalGPA));
	    exit = true;
	} else if (commandUp.equals("L")) {
	    for (int i = 0; i < credits.size(); i++) {
		System.out.println("#Credit: " + credits.get(i) + " Grade: "
				   + grades.get(i));
	    }
	    if (credits.size() == 0) {
		System.out.println("No input available!");
	    }
	} else {
	    if (commandUp.length() > 2) {
		String credit = commandUp.substring(0, 1);
		String grade = commandUp.substring(2);
		int creditInt;
		double gradeDouble;

		try {
		    creditInt = Integer.parseInt(credit);
		} catch (Exception e) {
		    System.out.println("Wrong input! Try again.");
		    return;
		}

		if (letterToNumber.containsKey(grade)) {
		    gradeDouble = letterToNumber.get(grade);
		} else {
		    try {
			gradeDouble = Double.parseDouble(grade);
		    } catch (Exception e) {
			System.out.println("Wrong input! Try again.");
			return;
		    }
		}
		
		credits.add(new Integer(creditInt));
		grades.add(new Double(gradeDouble));
	    }
	}
    }

}
