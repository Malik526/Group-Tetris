import javax.management.loading.PrivateClassLoader;
import javax.smartcardio.TerminalFactory;
import java.awt.*;

/**
 * Created by maliksmac on 11/25/16.
 */
public class Score extends TetroMoves {

    private long score;


    public long addScore() {


        switch (rowsCleared) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 500;
                break;
            case 4:
                score += 800;
                break;
        }
        return score;
    }

    public long getScore(){return score;}

}

