import java.awt.*;
/**
 * Created by maliksmac on 11/27/16.
 */
public class TetroMoves extends Tetraminos {


    public boolean collidesAt(int x, int y, int rotation) {
    for (Point p : Tetraminos[currentPiece][rotation]) {
        if (border[p.x + x][p.y + y] != Color.BLACK) {
            return true;
        }
    }
    return false;
}

    public void rotate(int i) {
        int newRotation = (rotation + i) % 4;
        if (newRotation < 0) {
            newRotation = 3;
        }
        if (!collidesAt(pieceOrigin.x, pieceOrigin.y, newRotation)) {
            rotation = newRotation;
        }
        repaint();
    }

    public void move(int i) {
        if (!collidesAt(pieceOrigin.x + i, pieceOrigin.y, rotation)) {
            pieceOrigin.x += i;
        }
        repaint();
    }

    // Drops the piece one line or fixes it to the well if it can't drop
    public void dropDown() {
        if (!collidesAt(pieceOrigin.x, pieceOrigin.y + 1, rotation)) {
            pieceOrigin.y += 1;
        } else {
            fixToWell();
        }
        repaint();
    }

    // Make the dropping piece part of the well, so it is available for
    // collision detection.
    public void fixToWell() {
        for (Point p : Tetraminos[currentPiece][rotation]) {
            border[pieceOrigin.x + p.x][pieceOrigin.y + p.y] = tetraminoColors[currentPiece];
        }
        clearRows();
        newPiece();
    }

    public void clearRows() {
        boolean gap;
        Score addUpScore = new Score();


        for (int j = 21; j > 0; j--) {
            gap = false;
            for (int i = 1; i < 11; i++) {
                if (border[i][j] == Color.BLACK) {
                    gap = true;
                    break;
                }
            }
            if (!gap) {

                deleteRow(j);
                j += 1;
                rowsCleared += 1;
                addUpScore.addScore();
                System.out.println(rowsCleared);
                System.out.println(addUpScore.addScore());

            }

        }


    }

    // Draw the falling piece
    public void drawPiece(Graphics g) {
        g.setColor(tetraminoColors[currentPiece]);
        for (Point p : Tetraminos[currentPiece][rotation]) {
            g.fillRect((p.x + pieceOrigin.x) * 26,
                    (p.y + pieceOrigin.y) * 26,
                    25, 25);
        }
    }


    public void paintComponent(Graphics g) {
        Score gameScore = new Score();
        // Color borders
        g.fillRect(0, 0, 26 * 12, 26 * 23);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {
                g.setColor(border[i][j]);
                g.fillRect(26 * i, 26 * j, 25, 25);
            }
        }

        // Display the score
        g.setColor(Color.WHITE);
        g.drawString("" + gameScore.getScore(), 19 * 12, 25);

        // Draw the currently falling piece
        if ((collidesAt(pieceOrigin.x, pieceOrigin.y, rotation))) {
            g.setColor(Color.WHITE);
            g.drawString("Game Over", 19 * 8, 250);

        } else {
            drawPiece(g);
        }
    }

}
