/*
 * cette application a pour but de construire une matrice a partir d un tableau
pour qu on puisse comparer les matrices
@author group 4
@date 13-03-2020
 */

package Model;

/**
 * Class for representing matrices.
 */
public class Matrice {

    

                 // tableau M par N


public int numRows;                 // m -> nombre de lignes de la matrice
    public int numCols;              // n -> nombre de colonne de la matrice
    public double[][] data;
    public Matrice() {
    }

    /**
     * Creates an empty matrice with dimension (M=numRows, N=numCols). All values of the
     * newly created matrice are initialised to 0.
     * @param numRows  the number of rows (M)
     * @param numCols  the number of columns (N)
     */
    public Matrice(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        data = new double[numRows][numCols];
    }


    /**
     * Creer une matrice tableau 2D.
     * @param data Two dimensional array holding the content of the matrice.
     */
    public Matrice(double[][] data) {
        this.numRows = data.length;
        this.numCols = data[0].length;
        this.data = new double[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                this.data[i][j] = data[i][j];
            }
        }
    }


    /**
     * Copy constructor to create a copy of a given matrice.
     * @param other  the matrice to be copied.
     */
    public Matrice(Matrice other) {
        this.numRows = other.numRows;
        this.numCols = other.numCols;
        this.data = other.data;
    }


    /**
     * Returns a new matrice which is the sum of the two given matrices. If A and B are two matrices,
     * then the new matice C is obtained as follows: C = A.additionner(B).
     * a
     * @param other  the second matrice (B in the above description)
     * @return Sum of the two matrices (C in the above description)
     * @throws RuntimeException is thrown if the two matrices do not have the same dimensions.
     */
    public Matrice additionner(Matrice other) throws RuntimeException {
        int numRows1 = this.numRows;
        int numCols1 = this.numCols;
        int numRows2 = other.numRows;
        int numCols2 = other.numCols;
        // First make sure that the two matrices have the same dimension...
        if (numRows1 != numRows2 || numCols1 != numCols2) {
            throw new RuntimeException("Les deux matrices n'ont pas la meme dimension");
        }
        // Create a new matrice. Its content will be sum of the two matrices...
        Matrice newMatrice = new Matrice(numRows1, numCols1);
        for (int row = 0; row < numRows1; row++) {
            for (int col = 0; col < numCols1; col++) {
                newMatrice.data[row][col] = this.data[row][col] + other.data[row][col];
            }
        }
        return newMatrice;
    }


    /**
     * Methode pour soustraire deux matrices. If A and B are two matrices, then 
     * the new matice C is obtained as follows: C = A.soustraction(B).
     * @param other the matrice to be soustracted from the current matrice.
     * @return      the soustraction of the given matrice from the current matrice.
     * @throws RuntimeException is thrown if the two matrices do not have the same dimensions.
     */
    public Matrice soustraction(Matrice other) throws RuntimeException {
        int numRows1 = this.numRows;
        int numCols1 = this.numCols;
        int numRows2 = other.numRows;
        int numCols2 = other.numCols;
        // First make sure that the two matrices have the same dimension...
        if (numRows1 != numRows2 || numCols1 != numCols2) {
            throw new RuntimeException("Les deux matrices n'ont pas la meme dimension");
        }
        // Create a new matrice. Its content will be difference of the two matrices...
        Matrice newMatrice = new Matrice(numRows1, numCols1);
        for (int row = 0; row < numRows1; row++) {
            for (int col = 0; col < numCols1; col++) {
                newMatrice.data[row][col] = this.data[row][col] - other.data[row][col];
            }
        }
        return newMatrice;
    }



    /**
     * Methode pour multiplier deux matrices. If A and B are two matrices, then 
     * the new matice C is obtained as follows: C = A.multiplication(B).
     * @param other  the matrice to be multiplied with the current matrice.
     * @return       the product of the current matrice with the given matrice.
     * @throws RuntimeException  is thrown if the dimensions of the two matrices do not correspond.
     */
    public Matrice multiplication(Matrice other) throws RuntimeException {
        int numRows1 = this.numRows;
        int numCols1 = this.numCols;
        int numRows2 = other.numRows;
        int numCols2 = other.numCols;
        // First make sure that the dimensions of the two matrices are compatible...
        if (numCols1 != numRows2) {
            throw new RuntimeException("Les deux matrices ne sont pas compatible pour la multiplication");
        }
        Matrice newMatrice = new Matrice(numRows1, numCols2);
        for (int row1 = 0; row1 < numRows1; row1++) {
            for (int col1 = 0; col1 < numCols1; col1++) {
                for (int idx = 0; idx < numCols1; idx++) {
                    newMatrice.data[row1][col1] += this.data[row1][idx] * other.data[idx][col1];
                }
            }
        }
        return newMatrice;
    }



    /**
     * Calcule la transposé de la matrice actuelle.
     * @return    la matrice transposé
     */
    public Matrice transpose() {
        int numRows = this.numRows;
        int numCols = this.numCols;
        Matrice newMatrice = new Matrice(numCols, numRows);
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                newMatrice.data[row][col] = this.data[col][row];
            }
        }
        return newMatrice;
    }


    /**
     * Calcule le determinant du la matrice actuelle.
     * @return   Determinant de la matrice actuelle.
     * @throws RuntimeException  si la matrice actuelle n'est pas carrée.
     */
    public double determinant() throws RuntimeException {
        int numRows = this.numRows;
        int numCols = this.numCols;
        if (numRows != numCols) {
            throw new RuntimeException("Dimension invalide, la matrice doit etre carrée");
        }
        if (numRows == 2) {
            return this.data[0][0] * this.data[1][1] - this.data[0][1] * this.data[1][0];
        }
        double det = 0;
        for (int col = 0; col < numRows; col++) {
            det += Math.pow(-1, col) * this.data[0][col] * this.cofacteur(0, col).determinant();
        }
        return det;
    }


    /**
     * Calcule le cofacteur de la matrice actuelle.
     * @param rowSkip  la ligne à ne pas utiliser.
     * @param colSkip  la cologne ä ne pas utiliser.
     * @return         le cofacteur de la matrice actuelle.
     */
    public Matrice cofacteur(int rowSkip, int colSkip) {
        int numRows = this.numRows;
        int numCols = this.numCols;
        Matrice newMatrice = new Matrice(numRows - 1, numCols - 1);
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (row != rowSkip && col != colSkip) {
                    newMatrice.data[row < rowSkip ? row : row - 1][col < colSkip ? col : col - 1] = this.data[row][col];
                }
            }
        }
        return newMatrice;
    }



    /**
     * Calcule l'inverse de la matrice actuelle.
     * @return l'inverse de la matrice actuelle.
     * @throws RuntimeException  si la matrice actuelle n'est pas carrée.
     */
    public Matrice inverse() throws RuntimeException {
        int numRows = this.numRows;
        int numCols = this.numCols;
        if (numRows != numCols) {
            throw new RuntimeException("Dimension invalide, la matrice doit etre carrée");
        }
        Matrice newMatrice = new Matrice(numRows, numCols);
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                newMatrice.data[row][col] = Math.pow(-1, row + col) * this.cofacteur(row, col).determinant();
            }
        }
        if (newMatrice.determinant()== 0) {
            throw new RuntimeException("la matrice A n'est pas inversible");
        }
        double det = 1.0 / newMatrice.determinant();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col <= row; col++) {
                double temp = newMatrice.data[row][col];
                newMatrice.data[row][col] = newMatrice.data[col][row] * det;
                newMatrice.data[col][row] = temp * det;
            }
        }
        return newMatrice;
    }
    
    public Matrice multiplicationScalaire(double Lambda) throws RuntimeException {
        int numRows1 = this.numRows;
        int numCols1 = this.numCols;
        double lambda = Lambda;
        
                // Creation de la matrice
        Matrice newMatrice = new Matrice(numRows1, numCols1);
        for (int row = 0; row < numRows1; row++) {
            for (int col = 0; col < numCols1; col++) {
                newMatrice.data[row][col] = this.data[row][col]*lambda;
            }
        }
        return newMatrice;
    }


    /**
     * Default method toString is overridden in order to display the contents of a matrice.
     * @return  Representation of the matrice as String
     */
    @Override
    public String toString() {
        String result = "";
        int numCols = this.numCols;
        for (double[] rowData : this.data) {
            for (int col = 0; col < numCols; col++) {
                result += rowData[col] + "\t";
            }
            result += "\n";
        }
        return result;
    }

}