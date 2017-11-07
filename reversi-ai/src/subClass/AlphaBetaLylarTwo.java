package subClass;

import jp.takedarts.reversi.Board;
import jp.takedarts.reversi.Piece;
import nuralNetwork.ReversiPerceptron;

public class AlphaBetaLylarTwo {

	// コンストラクタ
	ReversiPerceptron reversiPerceptron;

	public AlphaBetaLylarTwo() {
		this.reversiPerceptron = new ReversiPerceptron();
	}

	public double _getMaxValue(Board board, Piece piece) {

		// 次に置ける場所の中で、もっとも評価の高い場所を探す
		Piece enemy = Piece.opposite(piece);
		Board playBoard4;
		Board playBoard5 = new Board(board.getBoard());
		Board playBoard6;
		Board tryBoardLylarTwo = new Board(board.getBoard());
		boolean loop1 = true;
		boolean loop2 = true;
		double pervalueThird = -100;
		double pervalueJudge = -100;
		double pervalueSecond = 100;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				// 置けるかどうかを確認し、置けないのなら何もしない(4手目)
				if (tryBoardLylarTwo.isEnablePosition(i, j, enemy)) {

					tryBoardLylarTwo.putPiece(i, j, enemy);

					// 相手が置いた盤面を別変数に格納
					playBoard4 = new Board(tryBoardLylarTwo.getBoard());
					playBoard6 = new Board(tryBoardLylarTwo.getBoard());

					pervalueThird = -100;

					loop1: if (loop1) {
						for (int k = 0; k < 8; k++) {
							for (int l = 0; l < 8; l++) {

								// 置けるかどうかを確認し、置けないのなら何もしない（5手目）
								if (playBoard4.isEnablePosition(k, l, piece)) {

									// 末端
									playBoard4.putPiece(k, l, piece);

									Board boardcal = new Board(playBoard4.getBoard());

									// 自分が置いた後の盤面の評価値をニューラルネットワークにより算出
									pervalueJudge = reversiPerceptron.ReversiPerceptronCreate(boardcal, piece);

									if ((pervalueSecond != 100) && (pervalueJudge >= pervalueSecond)) {
										loop1 = false;
										loop2 = false;
										break loop1;
									}

									// より評価値の高い値で更新していく
									if (pervalueThird < pervalueJudge) {
										pervalueThird = pervalueJudge;
									}
									playBoard4 = new Board(playBoard6.getBoard());
								}
							}
						}
					}

					loop1 = true;

					if ((pervalueSecond > pervalueThird) && (loop2)) {

						pervalueSecond = pervalueThird;
					}
					tryBoardLylarTwo = new Board(playBoard5.getBoard());
				}
				loop2 = true;
			}
		}
		return pervalueSecond;

	}

}
