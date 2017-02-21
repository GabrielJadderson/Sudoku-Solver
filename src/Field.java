/*
 * MIT License
 *
 * Copyright (c) 2017 Gabriel Jadderson
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * semi-Abstract Data Type for Sudoku playing field
 */
public class Field
{

    public static final int SIZE = 9;

    private int model[][];

    public Field()
    {
        // make new array of size SIZExSIZE
        this.model = new int[SIZE][SIZE]; //9*9 2d array = 81
        // initialize with empty cells
        init(SIZE - 1, SIZE - 1);
    }

    public void init(int i, int j)
    {
        if (i < 0)
        {
            // all rows done!
        } else if (j < 0)
        {
            // this row done - go to next!
            init(i - 1, SIZE - 1);
        } else
        {
            this.clear(i, j);
            init(i, j - 1);
        }
    }

    public void fromFile(String fileName)
    {
        try
        {
            Scanner sc = new Scanner(new File(fileName));
            fromScanner(sc, 0, 0);
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }

    private void fromScanner(Scanner sc, int i, int j)
    {
        if (i >= SIZE)
        {
            // all rows done!
        } else if (j >= SIZE)
        {
            // this row done - go to next!
            fromScanner(sc, i + 1, 0);
        } else
        {
            try
            {
                int val = Integer.parseInt(sc.next());
                this.model[i][j] = val;
            } catch (NumberFormatException e)
            {
                // skip this cell
            }
            fromScanner(sc, i, j + 1);
        }
    }

    public String toString()
    {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < SIZE; i++)
        {
            if (i % 3 == 0)
            {
                res.append("+-------+-------+-------+\n");
            }
            for (int j = 0; j < SIZE; j++)
            {
                if (j % 3 == 0)
                {
                    res.append("| ");
                }
                int val = this.model[i][j];
                res.append(val > 0 ? val + " " : "  ");
            }
            res.append("|\n");
        }
        res.append("+-------+-------+-------+");
        return res.toString();
    }


    /**
     * returns false if the value val cannot be placed at
     * row i and column j. returns true and sets the cell
     * to val otherwise.
     */
    public boolean tryValue(int val, int i, int j)
    {
        if (!checkRow(val, i))
        {
            return false;
        }
        if (!checkCol(val, j))
        {
            return false;
        }
        if (!checkBox(val, i, j))
        {
            return false;
        }
        this.model[i][j] = val;
        return true;
    }

    /**
     * checks if the cell at row i and column j is empty,
     * i.e. whether it contains 0
     */
    public boolean isEmpty(int i, int j)
    {
        if (i < SIZE && j < SIZE)
            if (this.model[i][j] == 0)
                return true;
        return false;
    }

    /**
     * sets the cell at row i and column j to be empty, i.e.,
     * to be 0
     */
    public void clear(int i, int j)
    {
        if (i < SIZE && j < SIZE)
            this.model[i][j] = 0;
    }

    /**
     * checks if val is an acceptable value for the row i
     */
    private boolean checkRow(int val, int i)
    {
        if (i < SIZE)
            for (int k = 0; k < SIZE; k++)
                if (model[i][k] == val)
                    return false;
        return true;
    }

    /**
     * checks if val is an acceptable value for the column j
     */
    private boolean checkCol(int val, int j)
    {
        if (j < SIZE)
            for (int k = 0; k < SIZE; k++)
                if (model[k][j] == val)
                    return false;
        return true;
    }

    /**
     * checks if val is an acceptable value for the box around
     * the cell at row i and column j
     */
    private boolean checkBox(int val, int i, int j)
    {
        if (i < SIZE && j < SIZE)
        {
            for (int r = 0; r < 3; r++)
                for (int c = 0; c < 3; c++)
                    if (model[((i / 3) * 3) + r][((j / 3) * 3) + c] == val)
                        return false;
            return true;
        }
        return false;
    }

    public int[][] getModel()
    {
        return this.model;
    }

    public void reset()
    {
        this.model = new int[SIZE][SIZE];
        init(SIZE - 1, SIZE - 1);
        System.gc();
    }
}
