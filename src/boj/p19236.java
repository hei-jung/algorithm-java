package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p19236 {

	static int n = 4;

	// 방향: ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int dx[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 0, -2, -2, -2, 0, 2, 2, 2 };

	// 물고기 정보
	static int grid[][] = new int[4][8];

	// 출력: 상어가 먹을 수 있는 물고기 번호의 합의 최댓값
	static int answer = 0;

	public static int max(int a, int b) {
		return a > b ? a : b;
	}

	public static int[][] deepcopy(int[][] arr) {
		int arr_cpy[][] = new int[4][8];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				arr_cpy[i][j] = arr[i][j];
			}
		}
		return arr_cpy;
	}

	// 물고기 위치, 방향 찾기
	public static int[][] find(int grid[][]) {
		int arr[][] = new int[17][3];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j += 2) {
				if (grid[i][j] > 0) {
					int f = grid[i][j];
					arr[f][0] = i;
					arr[f][1] = j;
					arr[f][2] = grid[i][j + 1];
				}
			}
		}
		return arr;
	}

	public static int[][] fish(int grid[][]) {
		int pos[][] = find(grid);

		for (int i = 1; i <= 16; i++) {
			if (pos[i][0] == 0 && pos[i][1] == 0 && pos[i][2] == 0) {
				continue;
			}

			// i번 물고기 처음 위치 및 방향
			int x = pos[i][0];
			int y = pos[i][1];
			int d = pos[i][2];

			for (;;) {
				// 이동 후 위치
				int nx = x + dx[d];
				int ny = y + dy[d];
				if ((nx >= 0) && (nx <= 3) && (ny >= 0) && (ny <= 6) && (grid[nx][ny] != -1)) {
					int ni = grid[nx][ny];
					// 위치 정보 갱신
					pos[ni][0] = x;
					pos[ni][1] = y;
					pos[i][0] = nx;
					pos[i][1] = ny;
					// 위치 교환
					grid[x][y] = grid[nx][ny];
					grid[x][y + 1] = grid[nx][ny + 1];
					grid[nx][ny] = i;
					grid[nx][ny + 1] = d;
					break;
				}
				d = (d % 8) + 1;
			}
		}
		return grid;
	}

	public static void bt(int x, int y, int s, int[][] grid) {
		// 물고기 먹기
		s += grid[x][y];
		answer = max(answer, s);
		grid[x][y] = -1;

		// 물고기 이동
		grid = fish(grid);

		// 상어 이동
		int d = grid[x][y + 1];
		for (int i = 1; i < 4; i++) {
			int nx = x + dx[d] * i;
			int ny = y + dy[d] * i;
			if ((nx >= 0) && (nx <= 3) && (ny >= 0) && (ny <= 6) && (grid[nx][ny] > 0)) {
				grid[x][y] = 0;
				bt(nx, ny, s, deepcopy(grid));
			}
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 8; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bt(0, 0, 0, grid);

		System.out.println(answer); // 정답 출력

	}

}
