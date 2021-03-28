package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ticTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<>();
	static ArrayList<Integer> cpuPositions = new ArrayList<>();

	public static void main(String[] args) {
		
		char [][] gameBoard = {{' ','|',' ','|',' '},
				{'-','+',' ','+','-'},
				{' ','|',' ','|',' '},
				{'-','+',' ','+','-'},
				{' ','|',' ','|',' '}};
		
		showGameBoard(gameBoard);
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter the position between 1-9: ");
			int pos = sc.nextInt();
			while(playerPositions.contains(pos) || cpuPositions.contains(pos)) {
				System.out.println("Position Taken! Enter a valid Position!");
				pos = sc.nextInt();
			}
			placePiece(gameBoard, pos, "player");
			
			String result = checkWinner();
			if(result.length()>0) {
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int cpuPos = rand.nextInt(9)+1;
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				cpuPos = rand.nextInt(9)+1;
			}
			placePiece(gameBoard, cpuPos, "cpuPlayer");
			
			showGameBoard(gameBoard);
			
			result = checkWinner();
			if(result.length()>0) {
				System.out.println(result);
				break;
			}
		}
	}
	
	public static void showGameBoard(char[][] gameBoard) {
		for(char[] row : gameBoard) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(char[][] gameBoard, int pos, String user) {
		char symbol = ' ';
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		}
		else if(user.equals("cpuPlayer")) {
			symbol = '0';
			cpuPositions.add(pos);
		}
		switch (pos) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	
	public static String checkWinner() {
		
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List bottomRow = Arrays.asList(7,8,9);
		List leftCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);
		List rightCol = Arrays.asList(3,6,9);
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(3,5,7);
		
		List<List> winners = new ArrayList<List>();
		winners.add(topRow);
		winners.add(midRow);
		winners.add(bottomRow);
		winners.add(leftCol);
		winners.add(midCol);
		winners.add(rightCol);
		winners.add(cross1);
		winners.add(cross2);
		
		for(List l : winners) {
			if(playerPositions.containsAll(l)) {
				return "You won!";
			}
			else if(cpuPositions.containsAll(l)) {
				return "CPU won!";
			}
			else if(playerPositions.size() + cpuPositions.size() == 9){
				return "CATS!";
			}
		}
		
		return "";
	}
}
