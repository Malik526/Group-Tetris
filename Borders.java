import javax.swing.*;
import java.awt.*;

/**
 * Created by maliksmac on 11/25/16.
 */


public class Borders extends JPanel {

    public Borders(){ }

    protected Color[][] border;



    public void gameInterface() {
        Tetraminos piece = new Tetraminos();
        border = new Color[12][24];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {
                if (i == 0 || i == 11 || j == 22) {
                    border[i][j] = Color.GRAY;
                } else {
                    border[i][j] = Color.BLACK;
                }
            }
        }
        piece.newPiece();
    }

    public void deleteRow(int row) {
        for (int j = row-1; j > 0; j--) {
            for (int i = 1; i < 11; i++) {
                border[i][j+1] = border[i][j];

            }
        }
    }



}


