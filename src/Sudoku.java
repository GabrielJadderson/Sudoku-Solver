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

import javax.swing.*;
import java.util.Random;

/**
 * Created by Gabriel Jadderson on 08/11/16.
 */
public class Sudoku
{
    static int depth = 0;
    static int computations = 0;

    static int[] randomStartColor;

    public static void main(String[] args)
    {
        if (args.length > 0)
        {
            runPart1(args);
        } else
        {
            runPart2();
        }
    }

    public static void runPart1(String[] args)
    {
        Constants.field.fromFile(args[0]);
        try
        {
            solve(Constants.field, 0, 0);
        } catch (SolvedException e)
        {
            System.out.println(Constants.field);
            System.out.println("Max recursive depth: " + depth);
            System.out.println("Number computations: " + computations);
        }
    }

    public static void runPart2()
    {
        try
        {
            SwingUtilities.invokeAndWait(new Runnable()
            {
                @Override
                public void run()
                {
                    new GUI();
                }
            });

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void solve(Field f, int i, int j) throws SolvedException
    {
        depth++;
        if (j == Field.SIZE)
        {
            j = 0;
            i = i + 1;
            if (i == Field.SIZE)
            {
                throw new SolvedException(); //making use of the SolvedException class, but not neccessary.
            }
        }
        if (!f.isEmpty(i, j))
            solve(f, i, j + 1);
        else
        {
            for (int k = 1; k < Field.SIZE + 1; k++)
            {
                ++computations;
                if (f.tryValue(k, i, j))
                {
                    Visualize(f);
                    solve(f, i, j + 1);
                }
            }
            f.clear(i, j);
        }
    }

    static void Visualize(Field f)
    {
        try
        {
            if (Constants.spinner != null)
            {
                Thread.sleep(Constants.spinner.getValue());
            }
            Constants.sudokuBoard.updateDigits(f);
            //Constants.sudokuBoard.updateNonStaticDigit();
            Constants.sudokuBoard.updateNonStaticDigit(randomStartColor[0], randomStartColor[1], randomStartColor[2]);
        } catch (Exception e)
        {
        }
    }

    static void generateRandomStartColor()
    {
        randomStartColor = generateRandomColor();
    }

    static int[] generateRandomColor()
    {
        int[] arr = new int[3];
        Random random = new Random();
        int max = 210;
        int min = 60;
        int randomNum1 = random.nextInt((max - min) + 1) + min;
        int randomNum2 = random.nextInt((max - min) + 1) + min;
        int randomNum3 = random.nextInt((max - min) + 1) + min;
        arr[0] = randomNum1; //why 200? because the bkg is white and the contrast becomes too high; hard to see the numbers.
        arr[1] = randomNum2;
        arr[2] = randomNum3;
        return arr;
    }


}
