import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Minesweeper {

	public static void main(String[] args) {
		Queue<String[][]> input = new LinkedList<String[][]>();
		Scanner scanner = new Scanner(System.in);
		int tempH = -1;
		int tempW = -1;
		while (tempH != 0 && tempW != 0) {
			tempH = scanner.nextInt();
			tempW = scanner.nextInt();
			if (tempW == 0 && tempH == 0) {
				continue;
			}
			scanner.nextLine();
			String[][] temp = new String[tempH][tempW];
			for (int i = 0; i < tempH; i++) {
				String line = scanner.nextLine();
				for (int j = 0; j < tempW; j++) {
					if (line.charAt(j) == '.') {
						temp[i][j] = "0";
					} else {
						temp[i][j] = "*";
					}
				}
			}
			input.add(temp);
		}
		int fieldNum = 1;
		while (!input.isEmpty()) {
			String[][] mineMap = input.poll();
			for (int i = 0; i < mineMap.length; i++) {
				for (int j = 0; j < mineMap[0].length; j++) {
					if (mineMap[i][j].equals("*")) {
						incrementAround(mineMap, i, j);
					}
				}
			}
			System.out.println("Field #" + fieldNum + ":");
			printMineMap(mineMap);
			System.out.println();
		}
	}

	private static void incrementAround(String[][] mineMap, int i, int j) {
		for (int i2 = i - 1; i2 <= i + 1; i2++) {
			for (int j2 = j - 1; j2 <= j + 1; j2++) {
				if (isInBounds(mineMap, i2, j2) && !mineMap[i2][j2].equals("*")) {
					mineMap[i2][j2] = "" + (Integer.parseInt(mineMap[i2][j2]) + 1);
				}
			}
		}
	}

	private static void printMineMap(String[][] mineMap) {
		for (int i = 0; i < mineMap.length; i++) {
			for (int j = 0; j < mineMap[i].length; j++) {
				System.out.print(mineMap[i][j]);
			}
			System.out.println();
		}
	}

	private static boolean isInBounds(String[][] mineMap, int i, int j) {
		return i >= 0 && j >= 0 && i < mineMap.length && j < mineMap[0].length;
	}
}