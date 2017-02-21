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
