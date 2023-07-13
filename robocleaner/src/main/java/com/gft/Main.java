package com.gft;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Triplet;

public class Main {
	public static void main(String[] arg) {
		String[] strArray1 = new String[] {"....X","X....","...X."};
//		String[] strArray2 = new String[] {"..X","X..",".X.","..."};
		Integer result = new Solution().doSome(strArray1);
		System.out.println(result);
	}

}

class Solution {
	
	Integer counters = 0;
	public Integer doSome(String[] R) {
		char[][] matrix = new char[R.length][R[0].toCharArray().length];
		
		for (int row=0; row < matrix.length; row++) {
			char[] rows = R[row].toCharArray();
			for (int col=0; col < matrix[row].length; col++) {
				matrix[row][col] = rows[col]; 
			}
		}
		
		printMatrix(matrix);
		
		try {
			solve(matrix, 0, 0, "r", new ArrayList<>());
		} catch (InterruptedException e) {}

		
		return counters;
			
	}
	
	public void solve(char[][] matrix, int i, int j, String direction, List<String> visited) throws InterruptedException {
		try {
			if (visited.size() > (matrix.length * matrix[0].length)) throw new InterruptedException();
			if (matrix[i][j] == '.') {
				if (!isVisited(i,j,visited)) ++counters;
				visited.add(buildVisited(i,j));
				Triplet<Integer, Integer, String> t = getIJ(i, j, direction);
				solve(matrix, (int)t.getValue0(), (int)t.getValue1(), t.getValue2(), visited);
			} else if (matrix[i][j] == 'X') {
				Triplet<Integer, Integer, String> t = turnIJ(i, j, direction);
				solve(matrix, (int)t.getValue0(), (int)t.getValue1(), t.getValue2(), visited);
			}
		} catch (IndexOutOfBoundsException ex) {
			Triplet<Integer, Integer, String> t = checkBorder(i, j, direction, matrix);
			solve(matrix, (int)t.getValue0(), (int)t.getValue1(), t.getValue2(), visited);
		}
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Triplet<Integer, Integer, String> getIJ(Integer i, Integer j, String direction) {
		if (direction.equals("r")) return new Triplet(i, ++j, direction);
		else if (direction.equals("d")) return new Triplet(++i, j, direction);
		else if (direction.equals("l")) return new Triplet(i, --j, direction);
		else return new Triplet(--i, j, direction);
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Triplet<Integer, Integer, String> turnIJ(Integer i, Integer j, String direction) {
		if (direction.equals("r")) return new Triplet(++i, --j, "d");
		else if (direction.equals("d")) return new Triplet(--i, --j, "l");
		else if (direction.equals("l")) return new Triplet(--i, ++j, "u");
		else return new Triplet(--i, ++j, "r");
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Triplet<Integer, Integer, String> checkBorder(Integer i, Integer j, String direction, char[][] matrix) {
		if (i > matrix.length) return new Triplet(--i, --j, "l");
		else if (i < 0) return new Triplet(++i, ++j, "r");
		else if (j > matrix[0].length) return new Triplet(++i, --j, "d");
		else return new Triplet(--i, ++j, "u");
	}
	
	private String buildVisited(int i, int j) {
		Integer a = Integer.valueOf(i);
		Integer b = Integer.valueOf(j);
		return new StringBuilder(a.toString()).append(b.toString()).toString();
	}
	
	private Boolean isVisited(int i, int j, List<String> visited) {
		Integer a = Integer.valueOf(i);
		Integer b = Integer.valueOf(j);
		String couple = new StringBuilder(a.toString()).append(b.toString()).toString();
		return visited.contains(couple);
	}
	
	private void printMatrix(char[][] matrix) {
		for (int row=0; row < matrix.length; row++) {
			for (int col=0; col < matrix[row].length; col++) {
				System.out.print(matrix[row][col] + " "); 
			}
			System.out.println();
		}
	}
	
	
}