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
import java.awt.*;

/**
 * Created by Gabriel Jadderson on 08/11/16.
 */
public final class Constants //singleton
{
    public static Thread sudokuThread;
    public static Runnable SudokuRunnable;
    public static Field field = new Field(); //The Field class represents the Sudoku puzzle
    public static JFrame q = new JFrame();
    public static String fileName = ""; //the name of the file to solve, used by JFileChooser.
    public static final String APP_TITLE = "Sudoku Solver"; //the name of the application
    public static final Dimension dimension = new Dimension(185, 280); //the dimension which holds the width and height of our app.
    public static SudokuBoard sudokuBoard; //the sudoku board container, responsible for graphically displaying the board.
    public static JButton solve; //Jbutton declared statically. Not safe, but if used correctly can be edited during runtime.
    public static JButton load;
    public static JButton save;
    public static SSpinner spinner;
    public static boolean isSolved = false; // isSolved is true when the board is solved and false when a new file is loaded.
}
