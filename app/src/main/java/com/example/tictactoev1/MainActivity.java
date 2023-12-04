package com.example.tictactoev1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    Button[][] buttons;
    TicTacToe ticTacToe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ticTacToe=new TicTacToe();
        buildGuiByCode();
    }
    public void buildGuiByCode(){
        //Lay kich thuoc man hinh
        Point size=new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w=size.x/TicTacToe.SIZE;

        //Xay dung GridLayout de tao ban co
        GridLayout gridLayout  = new GridLayout(this);
        gridLayout.setRowCount(TicTacToe.SIZE);
        gridLayout.setColumnCount(TicTacToe.SIZE);

        //Tao cac button va add vao gridLayout
        buttons = new Button[TicTacToe.SIZE][TicTacToe.SIZE];
        ButtonHandler buttonHandler= new ButtonHandler();
        for (int row=0;row<TicTacToe.SIZE;row++) {
            for (int col = 0; col < TicTacToe.SIZE; col++) {
                buttons[row][col]=new Button(this);
                buttons[row][col].setOnClickListener(buttonHandler);
                buttons[row][col].setTextSize((int)(w*.2));
                gridLayout.addView(buttons[row][col],w,w);
            }
        }
        setContentView(gridLayout);
    }
    void update(int row, int col){
        int play=ticTacToe.play(row,col);
        if (play==1)
            buttons[row][col].setText("X");
        else if (play==2)
            buttons[row][col].setText("O");
        if (ticTacToe.isGameOver()){
            showDialog(ticTacToe.result());
        }
    }
    public void showDialog(String result){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Ket qua tro choi");
        alert.setMessage(result+". Ban co muon choi lai?");
        PlayDialog playDialog = new PlayDialog();
        alert.setPositiveButton("Yes", playDialog);
        alert.setNegativeButton("No", playDialog);
        alert.show();
    }
    void resetButton(){
        for (int row=0;row<TicTacToe.SIZE;row++) {
            for (int col = 0; col < TicTacToe.SIZE; col++) {
                buttons[row][col].setText("");
            }
        }
    }
    private class ButtonHandler implements View.OnClickListener{
        public void onClick(View v){
            for (int row=0;row<TicTacToe.SIZE;row++) {
                for (int col = 0; col < TicTacToe.SIZE; col++) {
                    if (v==buttons[row][col])
                        update(row, col);
                }
            }
        }
    }
    private class PlayDialog implements DialogInterface.OnClickListener{
        public void onClick(DialogInterface dialog, int id){
            if (id==-1) { //Click vao nut Yes
                ticTacToe.resetGame();
                resetButton();
            }
            else if(id==-2){//click vao nut No
                MainActivity.this.finish();
            }
        }
    }
}