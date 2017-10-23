package nuralNetwork;

import jp.takedarts.reversi.Board;

public class ReversiPerceptron {
	/**
	 * 多層パーセプトロンの実装
	 *
	 * @author Kamata
	 * @param args
	 */
	public static void main(String[] args) {
		new ReversiPerceptron();
	}

	/**
	 * 処理関数
	 *
	 * @return
	 */
	public double ReversiPerceptronCreate(Board board) {

		// 多層パーセプトロンの作成
		MultiLayerPerceptron mlp = new MultiLayerPerceptron(64, 120, 1);
		return EvaluationValueCalculation(120, 1, board, mlp);

	}

	/**
	 * 評価値算出
	 *
	 * @param x
	 * @param answer
	 * @return
	 */
	public double EvaluationValueCalculation(int middleNumber, int outputNumber, Board board,
			MultiLayerPerceptron mlp) {

		float[] in = null; // 盤面を保持する配列
		String BoardValue = null; // 盤面の値を一時的に格納する文字列
		String[] BoardValueArry = null; // 盤面の値を一時的に格納する文字型配列
		float[] h = new float[middleNumber]; // 中間層の出力
		float[] o = new float[outputNumber]; // 出力層の出力

		// 盤面の状態を取得
		BoardValue = board.getBoardString();

		// 文字列配列化
		BoardValueArry = BoardValue.split(",", 0);

		// double型の配列へ変換
		in = new float[BoardValueArry.length];

		for (int intCnt = 0; intCnt < BoardValueArry.length; intCnt++) {
			in[intCnt] = Float.parseFloat(BoardValueArry[intCnt]) / 10;
		}

		// 出力値を推定：中間層の出力計算
		for (int j = 0; j < middleNumber; j++) {
			h[j] = mlp.middleNeurons[j].outputMiddle(in);
		}

		// 出力値を推定：出力層の出力計算
		for (int j = 0; j < outputNumber; j++) {
			o[j] = mlp.outputNeurons[j].output(h);
		}

		return o[0];

	}

}
