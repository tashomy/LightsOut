import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
    public static int xsize;
    public static int ysize;
    public static boolean notDone = true;

    public static void main(String[] args) {
        do {
            setXandY();
            if (!notDone) { //na samom kraju prije izlaska iz igre poruka
                JOptionPane.showMessageDialog(null, "Hvala sto ste igrali!");
                notDone = false;
            } else if (xsize == 0 || ysize == 0) { //u slucaju da se zada nula redova ili kolona
                JOptionPane.showMessageDialog(null, "Broj kolona ili redova ne moze biti nula");
                //setXandY();
            } else { //kreiranje samog prozora
                LightsOutJava game = new LightsOutJava(xsize, ysize);
                game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                while (!game.gameDone) { //ovo stavljamo da nam ne bi odmah iskakao prozor pobjeda
                }
                if (JOptionPane.showConfirmDialog(null, "Pobjeda!\nNova igra?", "Lights Out",
                        JOptionPane.YES_NO_OPTION) != 0) {
                    JOptionPane.showMessageDialog(null, "Hvala sto ste igrali!");
                    notDone = false;
                }
                game.dispose();
            }
        } while (notDone);

    }

    public static void setXandY() {// Kreira dijaloge da pita korisnika koju velicinu tabele zeli
        String xInput = null;
        String yInput = null;
        try {
            xInput = JOptionPane.showInputDialog("Unesite broj redova koji zelite");
            xsize = Integer.parseInt(xInput);
            yInput = JOptionPane.showInputDialog("Unesite broj kolona koji zelite");
            ysize = Integer.parseInt(yInput);
        } catch (NumberFormatException e) {
            if (xInput == null || yInput == null) {
                notDone = false;
            } else {
                JOptionPane.showMessageDialog(null, "Dozvoljeno je koristiti samo prirodne brojeve");
                setXandY();
            }
        }
    }
}