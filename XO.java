package Q3;

import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class XO extends JFrame implements MouseListener{
    final int size = 4;
    final int screenSize = 200*size;
    private int turnCount;
    private String player[] = new String[2];
    private String boardArray[][] = new String[size][size];
    JPanel screen = new JPanel();
    public XO() {
        this.turnCount = 0;
        this.player[0] = "X";
        this.player[1] = "O";
        for (int i = 0;i < size;i++){
            for (int j = 0;j < size;j++){
                this.boardArray[i][j] = " ";
            }
        }
        // GUI
        this.setTitle("XO Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setResizable(false);

        screen.setPreferredSize(new Dimension(screenSize, screenSize));
        screen.addMouseListener(this);
        this.add(screen);
        this.pack();
        this.setVisible(true);
    }
    public void addPosition(int x,int y) {
        if (this.boardArray[y][x] == " ") {
            this.boardArray[y][x] = this.player[0];
            this.turnCount++;
            this.changePlayer();
        }
    }
    private void changePlayer() {
        String holder = this.player[0];
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
        final String condition[] = new String[size];
        String checkList[][] = new String[size*2+2][size];
        for (int i = 0;i < size;i++) {
            condition[i] = this.player[1]; // condition = {'x','x','x'}
        }
        for (int i = 0;i < (size*2+2);i++) {
            if (i < size) { // i = {0,1,2,3}
                checkList[i] = this.boardArray[i];
            } else if (i < (size*2)) { // i = {4,5,6,7}
                for (int j = 0;j < size;j++) {
                    checkList[i][j] = this.boardArray[j][i-size];
                    // System.out.printf("i:%d ,j:%d ,check:%s\n",i,j,checkList[i][j]);
                }
            } else if (i < (size*2+1)){ // i = 8
                for (int j = 0;j < size;j++){
                    checkList[i][j] = this.boardArray[j][j];
                }
            } else { // i = 9
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
        if (this.turnCount == (size*size)) {
            ans[0] = true;
            ans[1] = true;
        }
        return ans;
    }
    public String[] getPlayer(){
        return this.player;
    }
    // ----------------Mouse----------------
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX()/200;
        int y = e.getY()/200;
        System.out.println("clicked");
        System.out.println(x);
        System.out.println(y);
        addPosition(x, y);
        displayBoard();
        this.repaint();
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    
    }
    @Override
    public void mouseExited(MouseEvent e) {
    
    }
    void Draw_game(){
        screen.removeAll();
        Graphics2D g2d = (Graphics2D) screen.getGraphics();
        g2d.setStroke(new BasicStroke(2));
        for (int i=0;i<size;i++){
            g2d.setColor(Color.black);
            g2d.drawLine(0, i*200, screenSize, i*200);
            for (int j=0;j<size;j++){
                g2d.setColor(Color.black);
                g2d.drawLine(j*200, 0, j*200, screenSize);
                g2d.setColor(Color.blue);
                g2d.setFont(new Font("Calibri", Font.PLAIN, 150));
                g2d.drawString(boardArray[i][j],60+(200*j),150+(200*i));
            }
        }
    }
    public void paint(Graphics g){
        super.paint(g);
        Draw_game();
    }
    public static void main(String[] args) {
        new XO();
    }
}
