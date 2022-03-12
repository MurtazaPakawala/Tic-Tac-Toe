package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
    var PLAYER=true
    var BoardStatus =Array(3){IntArray(3)}
    var TURN=0;
    lateinit var board : Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(btn1,btn2,btn3),
            arrayOf(btn4,btn5,btn6),
            arrayOf(btn7,btn8,btn9)

        )

        for (i in board){
            for (button in i)
            {
                button.setOnClickListener(this)
            }
        }

        initializeBoard()

        btnReset.setOnClickListener{
            initializeBoard()
        }

    }

    private fun initializeBoard() {
        TURN=0;
            for(i in 0..2)
            {
                for(j in 0..2)
                {
                    board[i][j].isEnabled=true;
                    board[i][j].text=""
                    BoardStatus[i][j]=-1
                }
            }
        tvDisplay.text="Turn of X"
        PLAYER=true

    }

    override fun onClick(p0: View) {
            when(p0.id)
            {
                R.id.btn1->{
                    updateBoard(0,0,player=PLAYER)
                }
                R.id.btn2->{
                    updateBoard(0,1,player=PLAYER)
                }
                R.id.btn3->{
                    updateBoard(0,2,player=PLAYER)
                }
                R.id.btn4->{
                    updateBoard(1,0,player=PLAYER)
                }
                R.id.btn5->{
                    updateBoard(1,1,player=PLAYER)
                }
                R.id.btn6->{
                    updateBoard(1,2,player=PLAYER)
                }
                R.id.btn7->{
                    updateBoard(2,0,player=PLAYER)
                }
                R.id.btn8->{
                    updateBoard(2,1,player=PLAYER)
                }
                R.id.btn9->{
                    updateBoard(2,2,player=PLAYER)
                }

            }
        checkWinner()
    }

    private fun checkWinner() {
        //check row
        for(i in 0..2)
        {
            if(BoardStatus[i][0]==BoardStatus[i][1]&&BoardStatus[i][0]==BoardStatus[i][2]&&BoardStatus[i][0]!=-1)
            {
                if(BoardStatus[i][0]==1)
                {
                    tvDisplay.text="X is the Winner!!"
                    disableBtn()
                }else{
                    tvDisplay.text="0 is the Winner!!"
                    disableBtn()
                }
            }
        }
        // check for column
        for(i in 0..2)
        {
            if(BoardStatus[0][i]==BoardStatus[1][i]&&BoardStatus[0][i]==BoardStatus[2][i]&&BoardStatus[0][i]!=-1)
            {
                if(BoardStatus[0][i]==1)
                {
                    tvDisplay.text="X is the Winner!!"
                    disableBtn()
                }else{
                    tvDisplay.text="0 is the Winner!!"
                    disableBtn()
                }
            }
        }
        //checking for the diagnol
        // first i=j diagnol
        if(BoardStatus[0][0]==BoardStatus[1][1]&&BoardStatus[0][0]==BoardStatus[2][2]&&BoardStatus[0][0]!=-1)
        {
            if(BoardStatus[0][0]==1)
            {
                tvDisplay.text="X is the Winner!!"
                disableBtn()
            }else{
                tvDisplay.text="0 is the Winner!!"
                disableBtn()
            }
        }
        // checking second diagnol
        if(BoardStatus[0][2]==BoardStatus[1][1]&&BoardStatus[0][2]==BoardStatus[2][0]&&BoardStatus[0][2]!=-1)
        {
            if(BoardStatus[0][2]==1)
            {
                tvDisplay.text="X is the Winner!!"
                disableBtn()
            }else{
                tvDisplay.text="0 is the Winner!!"
                disableBtn()
            }
        }
    }

    private fun disableBtn() {
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                board[i][j].isEnabled=false;
            }
        }
    }

    private fun updateBoard(row: Int, col: Int, player: Boolean) {
        TURN++
        var text= if(player) "X" else "0"
        var score=if(player) 1 else 0
        BoardStatus[row][col]=score
        board[row][col].text=text
        board[row][col].isEnabled=false
        PLAYER=!PLAYER
        text= if(text=="X") "0" else "X"
        tvDisplay.text="the next turn is of ${text}"
        if(TURN==9)
        {
            tvDisplay.text="Match is DRAW!!!"
            disableBtn()
        }
    }

}