package sudoku.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.lang.Thread.sleep;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import sudoku.controller.ButtonController;
import sudoku.model.Game;
import sudoku.model.UpdateAction;

/**
 * This class draws the button panel and reacts to updates from the model.
 *
 * @author Eric Beijer
 */
public class ButtonPanel extends JPanel implements Observer {

    private final JButton btnNew, btnCheck, btnExit, cbHelp2;   // Used buttons.
    private ButtonGroup bgNumbers;          // Group for grouping the toggle buttons.
    private final JToggleButton[] btnNumbers, btnLevels;            // Used JToggleButton
    private JLabel labelSteps;
    private final JLabel labelTimer;

    public JLabel getLabelSteps() {
        return labelSteps;
    }

    public void setLabelSteps(JLabel labelSteps) {
        this.labelSteps = labelSteps;
    }

    /**
     * Constructs the panel and arranges all components.
     */
    public ButtonPanel() {
        super(new BorderLayout());

        JPanel pnlAlign = new JPanel();
        pnlAlign.setLayout(new BoxLayout(pnlAlign, BoxLayout.PAGE_AXIS));
        add(pnlAlign, BorderLayout.NORTH);

        JPanel pnlTimer = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlTimer.setBorder(BorderFactory.createTitledBorder(" Timer "));
        pnlAlign.add(pnlTimer);

        labelTimer = new JLabel("Timer");
        labelTimer.setText("00:00:00:000");
        pnlTimer.add(labelTimer);

        JPanel pnlOptions = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.setBorder(BorderFactory.createTitledBorder(" Options "));
        pnlAlign.add(pnlOptions);

        btnNew = new JButton("Reset");
        btnNew.setFocusable(false);
        pnlOptions.add(btnNew);

        btnCheck = new JButton("Submit");
        btnCheck.setFocusable(false);
        pnlOptions.add(btnCheck);

        btnExit = new JButton("Exit");
        btnExit.setFocusable(false);
        pnlOptions.add(btnExit);

        JPanel pnlLevel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlLevel.setBorder(BorderFactory.createTitledBorder(" Level "));
        pnlAlign.add(pnlLevel);

        bgNumbers = new ButtonGroup();
        btnLevels = new JToggleButton[5];
        for (int i = 0; i < 5; i++) {
            btnLevels[i] = new JToggleButton("Level " + (i + 1));
            btnLevels[i].setFocusable(false);
            bgNumbers.add(btnLevels[i]);
            pnlLevel.add(btnLevels[i]);
        }

        JPanel pnlSteps = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlSteps.setBorder(BorderFactory.createTitledBorder(" Total Steps "));
        pnlAlign.add(pnlSteps);

        labelSteps = new JLabel("0");
        labelSteps.setFocusable(false);
        pnlSteps.add(labelSteps);

        JPanel pnlNumbers = new JPanel();
        pnlNumbers.setLayout(new BoxLayout(pnlNumbers, BoxLayout.PAGE_AXIS));
        pnlNumbers.setBorder(BorderFactory.createTitledBorder(" Numbers "));
        pnlAlign.add(pnlNumbers);

        JPanel pnlNumbersHelp = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(pnlNumbersHelp);

        cbHelp2 = new JButton("Hint");
        cbHelp2.setFocusable(false);
        pnlNumbersHelp.add(cbHelp2);

        JPanel pnlNumbersNumbers = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(pnlNumbersNumbers);

        bgNumbers = new ButtonGroup();
        btnNumbers = new JToggleButton[9];
        for (int i = 0; i < 9; i++) {
            btnNumbers[i] = new JToggleButton("" + (i + 1));
            btnNumbers[i].setPreferredSize(new Dimension(40, 40));
            btnNumbers[i].setFocusable(false);
            bgNumbers.add(btnNumbers[i]);
            pnlNumbersNumbers.add(btnNumbers[i]);
        }
    }

    /**
     * Method called when model sends update notification.
     *
     * @param o The model.
     * @param arg The UpdateAction.
     */
    @Override
    public void update(Observable o, Object arg) {
        switch ((UpdateAction) arg) {
            case NEW_GAME:
            case CHECK:
                bgNumbers.clearSelection();
                break;
            case CANDIDATES:
                setSteps((Game) o);
                break;
        }
    }

    /**
     * Adds controller to all components.
     *
     * @param buttonController Controller which controls all user actions.
     */
    public void setController(ButtonController buttonController) {
        btnNew.addActionListener(buttonController);
        btnCheck.addActionListener(buttonController);
        btnExit.addActionListener(buttonController);
        cbHelp2.addActionListener(buttonController);
        for (int i = 0; i < 9; i++) {
            btnNumbers[i].addActionListener(buttonController);
        }
        for (int i = 0; i < 5; i++) {
            btnLevels[i].addActionListener(buttonController);
        }
    }

    /**
     * 
     * @param game 
     */
    public void setSteps(Game game) {
        labelSteps.setText("" + game.getSteps());
    }

    /**
     * 
     * @param game 
     */
    public void setSteps2(Game game) {
        labelSteps.setText("" + game.getSteps2());
    }

    /**
     * 
     * @param game 
     */
    public void resetSteps(Game game) {
        game.setSteps(0);
        game.setMilliseconds(0);
        game.setSeconds(0);
        game.setMinutes(0);
        game.setHours(0);
        labelSteps.setText("" + game.resetSteps());
    }

    /**
     * 
     * @param game 
     */
    public void setTimer(Game game) {
        Thread time = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    try {
                        sleep(1);
                        labelTimer.setText(game.getHours() + ":" + game.getMinutes() + ":" + game.getSeconds() + ":" + game.getMilliseconds());
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        time.start();
    }
}
