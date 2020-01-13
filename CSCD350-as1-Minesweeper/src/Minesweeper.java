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
					temp[i][j] = "" + line.charAt(j);
				}
			}
			input.add(temp);
		}
		int fieldNum = 1;
		while (!input.isEmpty()) {
			String[][] mineMap = input.poll();
			for (int i = 0; i < mineMap.length; i++) {
				for (int j = 0; j < mineMap[0].length; j++) {
					if (mineMap[i][j].equals(".")) {
						mineMap[i][j] = "" + getMineCount(mineMap, i, j);
					}
				}
			}
			System.out.println("Field #" + fieldNum + ":");
			printMineMap(mineMap);
			System.out.println();
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

	private static int getMineCount(String[][] mineMap, int i, int j) {
		int mineCount = 0;
		for (int i2 = i - 1; i2 <= i + 1; i2++) {
			for (int j2 = j - 1; j2 <= j + 1; j2++) {
				if (isMine(mineMap, i2, j2)) {
					mineCount++;
				}
			}
		}
		return mineCount;
	}

	private static boolean isMine(String[][] mineMap, int i, int j) {
		return i >= 0 && j >= 0 && i < mineMap.length && j < mineMap[0].length && mineMap[i][j].equals("*");
	}
}