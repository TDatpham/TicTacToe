package com.example.tictactoev1;

public class TicTacToe {
    public  static final int SIZE =3;
    int [][] game;
    int turn;

    public TicTacToe(){
        game = new int[SIZE][SIZE];
        resetGame();
    }

    public  int play(int row, int col){
        int currentTurn = turn;
        if (row>=0&&col>=0&&row<SIZE&&col<SIZE&&game[row][col]==0){
            game[row][col]=turn;
            if (turn ==1)
                turn=2;
            else
                turn =1;
            return currentTurn;
        }
        else
            return 0;
    }
    //Kiem tra theo dong
    int checkRows(){
        for (int row=0;row<SIZE;row++){
            if (game[row][0]!=0&&game[row][0]==game[row][1]&&game[row][1]==game[row][2])
                return game[row][0];
        }
        return 0;
    }
    //Kiem tra theo cot
    int checkColumns(){
        for (int col=0;col<SIZE;col++){
            if (game[0][col]!=0&&game[0][col]==game[1][col]&&game[1][col]==game[2][col])
                return game[0][col];
        }
        return 0;
    }

    //Kiem tra theo duong cheo
    int checkDiagonals(){
        if (game[0][0]!=0&&game[0][0]==game[1][1]&&game[1][1]==game[2][2])
                return game[0][0];
        if (game[0][2]!=0&&game[0][2]==game[1][1]&&game[1][1]==game[2][0])
            return game[0][2];
        return 0;
    }
    //Xac dinh nguoi thang
    public int whoWon(){
        int rows=checkRows();
        if (rows>0)
            return rows;
        int columns=checkColumns();
        if (columns>0)
            return columns;
        int diagonals=checkDiagonals();
        if (diagonals>0)
            return diagonals;
        return 0;
    }
    //Kiem tra trang thai choi
    public boolean canNotPlay(){
        boolean result=true;
        for (int row=0;row<SIZE;row++) {
            for (int col = 0; col < SIZE; col++) {
                if (game[row][col]==0)
                    result= false;
            }
        }
        return result;
    }
    //Kiem tra ket thuc tro choi
    public boolean isGameOver(){
        return canNotPlay()||(whoWon()>0);
    }
    //Xuat ra ket qua
    public String result(){
        if (whoWon()>0)
            return "Nguoi choi "+whoWon()+" thang";
        else if (canNotPlay())
            return "Ket qua hoa";
        else
            return "Dang choi";
    }
    //Reset game lai tu dau
    public void resetGame(){
        for (int row=0;row<SIZE;row++) {
            for (int col = 0; col < SIZE; col++) {
                game[row][col]=0;
            }
        }
        turn =1;
    }
}
