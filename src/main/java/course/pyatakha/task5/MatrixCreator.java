package course.pyatakha.task5;

public class MatrixCreator {

  public static int[][] createMatrix(int rowNumber, int columnNumber) {
    int[][] matrix = new int[rowNumber][columnNumber];

    for(int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        matrix[i][j] = (int) (Math.random() * 100000);
      }
    }

    System.out.println("matrix created");

    return matrix;
  }
}
