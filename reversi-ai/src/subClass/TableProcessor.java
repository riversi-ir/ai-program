package subClass;

import jp.takedarts.reversi.Board;
import jp.takedarts.reversi.Piece;
import jp.takedarts.reversi.Position;
import jp.takedarts.reversi.Processor;

public class TableProcessor extends Processor {
	/**
	 * 評価テーブル。
	 */
	private static int[][] _VALUES = new int[][] { { 120, -20, 20, 5, 5, 20, -20, 120 },
			{ -20, -40, -5, -5, -5, -5, -40, -20 }, { 20, -5, 15, 3, 3, 15, -5, 20 }, { 5, -5, 3, 3, 3, 3, -5, 5 },
			{ 5, -5, 3, 3, 3, 3, -5, 5 }, { 20, -5, 15, 3, 3, 15, -5, 20 }, { -20, -40, -5, -5, -5, -5, -40, -20 },
			{ 120, -20, 20, 5, 5, 20, -20, 120 } };


	/**
	 * 手番が来たときに、次の手を決定するメソッド。
	 *
	 * @param board
	 *            盤面の状態
	 * @param piece
	 *            自分が打つ駒
	 * @param thinkingTime
	 *            思考時間
	 * @return 次の手を置く場所
	 */
	@Override
	public Position nextPosition(Board board, Piece piece, long thinkingTime) {
		// 次に置ける場所の中で、もっとも評価の高い場所を探す
		int value = -100;
		int x = -1;
		int y = -1;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.isEnablePosition(i, j, piece) && _VALUES[i][j] > value) {
					value = _VALUES[i][j];
					x = i;
					y = j;
				}
			}
		}

		// 置く場所をログに出力
		log(String.format("next -> (%d, %d) : %d", x, y, value));

		// 置く場所をPositionオブジェクトに変換して返す
		return new Position(x, y);
	}

	/**
	 * この人工知能の名前を返す。
	 *
	 * @return 人工知能の名前
	 */
	@Override
	public String getName() {
		return "評価テーブルを使ったプログラム";
	}
}
