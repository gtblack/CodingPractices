import java.util.*;

public class GPACalc {

    private static List<Integer> credits;
    private static List<Double> grades;
    private static Scanner sc;
    private static Map<String, Double> letterToNumber;
    private static boolean exit = false;

    public GPACalc() {}

    /**
     * Initializes the Map for storing letter and corresponding numbers
     * so that the letters can be easily converted later.
     */
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

    public static void main(String[] args) {
	initializeConstant();
	credits = new ArrayList<Integer>();
	grades = new ArrayList<Double>();
	// continue reading input until exit command
	while (!exit) {
	    readNext();
	}
    }

    /**
     * Calculate the score before exiting.
     * @return the calculated GPA to be displayed
     */
    public static double calculate() {
	double ret = 0.0;
	double totalCredits = 0.0;
	for (int i = 0; i < credits.size(); i++) {
	    ret += (((double) credits.get(i)) * grades.get(i));
	    totalCredits += (double) credits.get(i);
	}
	
	return ret / totalCredits;
    }

    /**
     * Use the Scanner object to look for commands or data
     * and feed the input for processing
     */
    public static void readNext() {
	if (sc == null) {
	    sc = new Scanner(System.in).useDelimiter("\n");
	}
	System.out.print("Type credits and grades: ");
	String command = sc.next();
	processInput(command);
    }

    /**
     * Process the given input and act accordingly.
     * @param command User's command or data input
     */
    public static void processInput(String command) {
	String commandUp = command.toUpperCase();
	// X and Q signals that the user would like to exit the program
	if (commandUp.equals("Q") || commandUp.equals("X")) {

	    double finalGPA = calculate();
	    System.out.println("Result: " + Double.toString(finalGPA));
	    exit = true;

	// L signals that the user would like to see all the inputs until now
	} else if (commandUp.equals("L")) {

	    for (int i = 0; i < credits.size(); i++) {
		System.out.println("#Credit: " + credits.get(i) + " Grade: "
				   + grades.get(i));
	    }

	    if (credits.size() == 0) {
		System.out.println("No input available!");
	    }
	    
	} else if (commandUp.equals("H") || commandUp.equals("HELP")) {
	    String helpInfo =
		"+++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n"
		+ "The format of the input should be as follows:\n"
		+ "The first number is the number of credits for a course.\n"
		+ "The second number is the grade for the course.\n"
		+ "Letter grades are also accepted.\n\n"
		+ "The following examples are legitimate inputs:\n"
		+ "\"4 A+\", \"4 3.5\", \"2 C-\", \"3 3.6\"\n\n"
		+ "Commands: \n"
		+ "x|q     Calculate the GPA and exit the program\n"
		+ "l       List all the inputs\n"
		+ "h|help  Display help information\n\n"
		+ "+++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
	    System.out.println(helpInfo);
	} else {
	    // as for grades, the input should be #Credit Grade
	    // e.g. "4 A+", "4 3.5", "2 C-", "3 3.6"
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

	    } else {
		System.out.println("Wrong input! Try again.");
	    }
	}
    }

}
