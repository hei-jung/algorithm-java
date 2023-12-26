package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p12100 {

	static int N;
	static int answer;

	public static int[][] up(int[][] board) {
		for (int c = 0; c < N; c++) {

			int r = 0;

			for (int i = 0; i < N; i++) {
				if (board[i][c] != 0) {
					int tmp = board[i][c];
					board[i][c] = 0;

					if (board[r][c] == 0) {
						board[r][c] = tmp;
					} else if (board[r][c] == tmp) {
						board[r][c] += tmp;
						r++;
					} else {
						r++;
						board[r][c] = tmp;
					}
				}
			}
		}
		return board;
	}

	public static int[][] down(int[][] board) {
		for (int c = 0; c < N; c++) {

			int r = N - 1;

			for (int i = N - 1; i >= 0; i--) {
				if (board[i][c] != 0) {
					int tmp = board[i][c];
					board[i][c] = 0;

					if (board[r][c] == 0) {
						board[r][c] = tmp;
					} else if (board[r][c] == tmp) {
						board[r][c] += tmp;
						r--;
					} else {
						r--;
						board[r][c] = tmp;
					}
				}
			}
		}
		return board;
	}

	public static int[][] left(int[][] board) {
		for (int r = 0; r < N; r++) {

			int c = 0;

			for (int i = 0; i < N; i++) {
				if (board[r][i] != 0) {
					int tmp = board[r][i];
					board[r][i] = 0;

					if (board[r][c] == 0) {
						board[r][c] = tmp;
					} else if (board[r][c] == tmp) {
						board[r][c] += tmp;
						c++;
					} else {
						c++;
						board[r][c] = tmp;
					}
				}
			}
		}
		return board;
	}

	public static int[][] right(int[][] board) {
		for (int r = 0; r < N; r++) {

			int c = N - 1;

			for (int i = N - 1; i >= 0; i--) {
				if (board[r][i] != 0) {
					int tmp = board[r][i];
					board[r][i] = 0;

					if (board[r][c] == 0) {
						board[r][c] = tmp;
					} else if (board[r][c] == tmp) {
						board[r][c] += tmp;
						c--;
					} else {
						c--;
						board[r][c] = tmp;
					}
				}
			}
		}
		return board;
	}
	
	public static int[][] deepcopy(int[][] board){
		int[][] new_board = new int[N][N];
		for (int i=0; i< N;i++) {
			for(int j=0;j<N;j++) {
				new_board[i][j]=board[i][j];
			}
		}
		return new_board; 
	}

	public static void bt(int[][] board, int depth) {
		if (depth == 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] > answer) {
						answer = board[i][j];
					}
				}
			}
			return;
		}
		bt(up(deepcopy(board)), depth + 1);
		bt(down(deepcopy(board)), depth + 1);
		bt(left(deepcopy(board)), depth + 1);
		bt(right(deepcopy(board)), depth + 1);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int board[][] = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bt(board, 0);
		System.out.println(answer);
	}

}
