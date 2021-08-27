import java.io.Console;
import java.util.Arrays;
public class XO {
    final int size = 3;
    private int turnCount;
    private char player[] = new char[2];
    private char boardArray[][] = new char[size][size];
    public XO() {
        this.turnCount = 0;
        this.player[0] = 'X';
        this.player[1] = 'O';
        for (int i = 0;i < size;i++){
            for (int j = 0;j < size;j++){
                this.boardArray[i][j] = ' ';
            }
        }
    }
    public void addPosition() {
        Console console = System.console();
        String consoleInput = console.readLine();
        int pos = Integer.parseInt(consoleInput);
        int x = (pos-1)%3;
        int y = (pos-1)/3;
        if (this.boardArray[y][x] == ' ') {
            this.boardArray[y][x] = this.player[0];
            this.turnCount++;
            this.changePlayer();
        }
        else {
            System.out.println("Can not put mark on same position");
        }
    }
    private void changePlayer() {
        char holder = this.player[0];
        this.player[0] = this.player[1];
        this.player[1] = holder;
    }
    public void displayBoard() {
        for (int i = 0;i < size;i++) {
            for (int j = 0;j < size;j++) {
                System.out.printf("(%s)",this.boardArray[i][j]);
            }
            System.out.println("");
        }
    }
    public boolean[] checkWinner() {
        boolean[] ans = {false,false};
        final char condition[] = new char[size];
        char checkList[][] = new char[8][size];
        for (int i = 0;i < size;i++) {
            condition[i] = this.player[1]; // condition = {'x','x','x'}
        }
        for (int i = 0;i < (size*2+2);i++) {
            if (i < size) { // i = {0,1,2}
                checkList[i] = this.boardArray[i];
            } else if (i < (size*2)) { // i = {3,4,5}
                for (int j = 0;j < size;j++) {
                    checkList[i][j] = this.boardArray[j][i-3];
                    // System.out.printf("i:%d ,j:%d ,check:%s\n",i,j,checkList[i][j]);
                }
            } else if (i < (size*2+1)){ // i = 6
                for (int j = 0;j < size;j++){
                    checkList[i][j] = this.boardArray[j][j];
                }
            } else { // i = 7
                for (int j = 0;j < size;j++){
                    checkList[i][j] = this.boardArray[j][(size-1)-j];
                }
            }
        }
        for (int i = 0;i < (size*2+2);i++) {
            // System.out.printf("%d : %s%s%s\n",i,checkList[i][0],checkList[i][1],checkList[i][2]);
            // check condition
            if (Arrays.equals(checkList[i], condition)) {
                ans[0] = true;
                return ans;
            }
        }
        // Draw check
        if (this.turnCount == 9) {
            ans[0] = true;
            ans[1] = true;
        }
        return ans;
    }
    public char[] getPlayer(){
        return this.player;
    }
    public static void main(String[] args) {
        XO game = new XO();
        game.displayBoard();

        while (!game.checkWinner()[0]) {
            System.out.printf("%s turn : ",game.getPlayer()[0]);
            game.addPosition();
            game.displayBoard();
        }
        if (game.checkWinner()[1]) {
            System.out.println("Draw");
        } else {
            System.out.printf("Winner is %s \n", game.getPlayer()[1]);
        }
    }
}
