import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LightsOutJava extends JFrame {
    private Container window;
    private JButton[][] buttonList;
    public volatile boolean gameDone = false;

    public LightsOutJava(int xsize, int ysize) {// Postavljamo dimenzije redova i kolona
        super("Lights Out");
        buttonList = new JButton[xsize][ysize];
        window = getContentPane();
        window.setLayout(new GridLayout(0, ysize));
        for (int i = 0; i < buttonList.length; i++) {// Pravimo polja i dodajemo listenere
            for (int j = 0; j < buttonList[i].length; j++) {
                buttonList[i][j] = new JButton("");
                buttonList[i][j].setBackground(Color.white);
                buttonList[i][j].addActionListener(new ButtonPress());
                window.add(buttonList[i][j]);
            }
        }
        setSize(ysize * 100, xsize * 100);// Postavljamo velicinu prozora koja zavisi od same tabele

        Random ranGen = new Random();// Generisemo nasumican boolean koji ce da generise nasumicnu tabelu ali ce
        // osigurati da je nju moguce rijesiti
        for (JButton[] buttonrow : buttonList) {
            for (JButton button : buttonrow) {
                boolean temp = ranGen.nextBoolean();
                if (temp) {
                    button.doClick();
                }
            }
        }
        window.repaint();
        setVisible(true);
    }

    private class ButtonPress implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ea) {
            boolean done = false;
            int x = 0;
            int y = 0;
            //trazimo pritisnuto polje
            for (int i = 0; i < buttonList.length; i++) {
                for (int j = 0; j < buttonList[i].length; j++) {
                    if (((JButton) ea.getSource()).equals(buttonList[i][j])) {
                        done = true;
                        //System.out.println(i+" | "+j);
                        x = i;
                        y = j;
                        break;
                    }
                }
                if (done) {
                    break;
                }
            }
            switchColor(buttonList[x][y]);
            if (x > 0) {// Provjeravamo ima li prostora sa lijeve strane izabranog polja
                switchColor(buttonList[x - 1][y]);
            }
            if (x < buttonList.length - 1) {// Provjeravamo ima li prostora sa desne strane izabranog polja
                switchColor(buttonList[x + 1][y]);
            }
            if (y > 0) {// Provjeravamo ima li prostora sa gornje strane izabranog polja
                switchColor(buttonList[x][y - 1]);
            }
            if (y < buttonList[x].length - 1) {// Provjeravamo ima li prostora sa donje strane izabranog polja
                switchColor(buttonList[x][y + 1]);
            }
            if (checkWin()) {// Provjerava pobjedu i mijenja vrijednost gameDone a boju polja postavlja na zelenu
                for (JButton[] buttonrow : buttonList) {
                    for (JButton button : buttonrow) {
                        button.setBackground(Color.green);
                        button.setEnabled(false);
                        gameDone = true;
                    }
                }
            }

        }

        private boolean checkWin() {// Provjerava da li su sva polja crna
            // TODO Auto-generated method stub
            for (JButton[] buttonrow : buttonList) {
                for (JButton button : buttonrow) {
                    if (button.getBackground() == Color.white) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void switchColor(JButton button) {// Koristimo za mijenjanje boje iz bijele u crnu i obrnuto
            if (button.getBackground().equals(Color.black)) {
                button.setBackground(Color.white);
            } else {
                button.setBackground(Color.black);
            }
        }
    }
}