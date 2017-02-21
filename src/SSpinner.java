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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Gabriel Jadderson on 21-02-2017.
 */
public class SSpinner extends JComponent
{
    public static final Dimension SIZE_SSPINNER = new Dimension(100, 70);
    public static final Dimension SIZE_SLIDER = new Dimension(SIZE_SSPINNER.width, 20);

    int value = 0;

    public SSpinner()
    {
        this.setPreferredSize(SIZE_SSPINNER);
        this.setLayout(new FlowLayout());
        final JLabel label = new JLabel("Sleep Time");
        this.add(label);
        final JLabel spinner = new JLabel();
        final JSlider slider = new JSlider(0, 100);
        slider.setValue(42);
        slider.setPreferredSize(SIZE_SLIDER);
        slider.setFocusable(false);
        spinner.setFocusable(false);
        spinner.setEnabled(false);
        this.value = slider.getValue();
        spinner.setText(String.valueOf(slider.getValue()));
        slider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                JSlider s = (JSlider) e.getSource();
                spinner.setText(String.valueOf(s.getValue()));
                value = s.getValue();
            }
        });
        this.add(slider);
        this.add(spinner);
    }

    public int getValue()
    {
        return this.value;
    }
}
