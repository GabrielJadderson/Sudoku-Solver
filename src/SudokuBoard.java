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
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriel Howard Jadderson on 06-11-2016.
 */
public class SudokuBoard extends JPanel
{
    public static ArrayList<JLabel> sudokuDigits = new ArrayList<>();
    public static Color color = Color.black;
    public static ArrayList<JLabel> staticDigits = new ArrayList<>();

    public SudokuBoard(Field field)
    {
        setPreferredSize(new Dimension(Field.SIZE * 15, Field.SIZE * 15));
        setLayout(new GridLayout(Field.SIZE, Field.SIZE));
        setBackground(Color.WHITE);

        for (int i = 0; i < Field.SIZE * Field.SIZE; i++)
        {
            JLabel l = new JLabel("X");
            sudokuDigits.add(l);
            l.setForeground(new Color(50, 50, 50));
            add(l);
        }
        sudokuDigits.trimToSize();
    }

    @Override
    protected void paintComponent(Graphics g) //hard coded :(
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(170, 170, 170));
        g2.setStroke(new BasicStroke(1));
        g2.drawLine(0, 14, getWidth() - 10, 14);
        g2.drawLine(0, 29, getWidth() - 10, 29);
        g2.drawLine(0, 59, getWidth() - 10, 59);
        g2.drawLine(0, 74, getWidth() - 10, 74);
        g2.drawLine(0, 104, getWidth() - 10, 104);
        g2.drawLine(0, 119, getWidth() - 10, 119);
        g2.drawLine(10, 2, 10, getHeight() - 4);
        g2.drawLine(24, 2, 24, getHeight() - 4);
        g2.drawLine(55, 2, 55, getHeight() - 4);
        g2.drawLine(70, 2, 70, getHeight() - 4);
        g2.drawLine(100, 2, 100, getHeight() - 4);
        g2.drawLine(115, 2, 115, getHeight() - 4);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(0, 44, getWidth() - 10, 44);
        g2.drawLine(0, 89, getWidth() - 10, 89);
        g2.drawLine(40, 2, 40, getHeight() - 4);
        g2.drawLine(85, 2, 85, getHeight() - 4);
    }

    public synchronized void updateDigits(Field field)
    {
        int counter = 0;
        for (int i = 0; i <= 8; i++)
        {
            for (int j = 0; j <= 8; j++)
            {
                sudokuDigits.get(counter).setText("" + field.getModel()[i][j]);
                counter++;
            }
        }
        validate();
        updateUI();
        repaint();
    }

    synchronized void assignRandomColor()
    {
        int[] randomColors = Sudoku.generateRandomColor();
        assignColor(randomColors[0], randomColors[1], randomColors[2]);
    }

    synchronized void assignColor(int r, int g, int b)
    {
        color = new Color(r, g, b);
        for (JLabel l : sudokuDigits)
        {
            l.setForeground(color);
        }
        repaint();
    }

    synchronized void assignStartColor(int r, int g, int b)
    {
        for (JLabel l : sudokuDigits)
        {
            if (l.getText().contains("0"))
            {
            } else
            {
                staticDigits.add(l);
                l.setForeground(new Color(r, g, b));
                l.repaint();
            }
        }
        repaint();
    }

    synchronized void updateNonStaticDigit(int r, int g, int b)
    {
        for (JLabel l : sudokuDigits)
        {
            if (!staticDigits.contains(l))
            {
                l.setForeground(new Color(r, g, b));
                l.repaint();
            }
        }
        repaint();
    }

    /**
     * use this for fun
     */
    synchronized void updateNonStaticDigit()
    {
        for (JLabel l : sudokuDigits)
        {
            if (!staticDigits.contains(l))
            {
                l.setForeground(getRandomColor());
                l.repaint();
            }
        }
        repaint();
    }


    synchronized Color getRandomColor()
    {
        Random rand = new Random();
        return new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }
}
