import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

// svetlana kozubenko
public class Minesweaper {

	public static void main(String[] args) throws FileNotFoundException {

		ArrayList<char[][]> fields = loadPage();
		ArrayList<char[][]> solutions = new ArrayList<char[][]>();

		for (int i = 0; i < fields.size(); i++) {
			char[][] field = fields.get(i);
			char[][] solution = calculate(field);
			solutions.add(solution);
		}

		for (char[][] solution : solutions) {
			print(System.out, solution);
			System.out.println();
		}
	}

	public static int adjCell(char[][] feild, int n, int m) {
		
		if (feild.length <= n || feild[0].length <= m)
			return 0;

		if (n < 0 || m < 0)
			return 0;

		return feild[n][m] == '*' ? 1 : 0;

	}

	public static int calcNum(char[][] feild, int n, int m) {
		
		int num = 0;

		num += adjCell(feild, n - 1, m);
		num += adjCell(feild, n - 1, m - 1);
		num += adjCell(feild, n - 1, m + 1);
		num += adjCell(feild, n + 1, m);
		num += adjCell(feild, n + 1, m + 1);
		num += adjCell(feild, n + 1, m - 1);
		num += adjCell(feild, n, m + 1);
		num += adjCell(feild, n, m - 1);

		return num;
	}

	public static char[][] calculate(char[][] feild) {
		
		char[][] solution = new char[feild.length][feild[0].length];

		for (int row = 0; row < feild.length; row++) {

			for (int col = 0; col < feild[row].length; col++) {
				
				if (feild[row][col] == '*') {
					solution[row][col] = '*';
					continue;
				}
				
				int c = calcNum(feild, row, col);
				solution[row][col] = (c + "").charAt(0);
			}
		}

		return solution;
	}

	public static ArrayList<char[][]> loadPage() throws FileNotFoundException {
		
		ArrayList<char[][]> list = new ArrayList<char[][]>();
		Scanner inputFile = new Scanner(System.in);

		while (inputFile.hasNext()) {
			int n = inputFile.nextInt(10);
			int m = inputFile.nextInt(10);

			if (n == 0 || m == 0)
				continue;
			
			if (n == 0 && m ==0)
				break;
			
			inputFile.nextLine();
			char[][] temp = loadFeild(inputFile, n, m);
			list.add(temp);
		}

		inputFile.close();
		return list;
	}
	

	public static char[][] loadFeild(Scanner inputFile, int n, int m) {
		
		char[][] feild = new char[n][m];

		for (int row = 0; row < n; row++) {
			String line = inputFile.nextLine();

			for (int col = 0; col < m; col++) {
				feild[row][col] = line.charAt(col); // read next char & save it into feild[][]
			}
		}

		return feild;
	}

	public static void print(PrintStream stream, char[][] feild) {
		
		for (int row = 0; row < feild.length; row++) {

			for (int col = 0; col < feild[row].length; col++) {
				stream.print(feild[row][col]);
			}
			
			stream.println();
		}
	}
}
