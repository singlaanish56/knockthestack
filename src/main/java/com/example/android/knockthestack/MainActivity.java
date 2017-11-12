package com.example.android.knockthestack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.knockthestack.R;

import java.util.Random;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    TextView displaytextview;
    TextView toptextview;
    TextView secondtextview;
    TextView firsttextview;
    TextView goldtextview;
    ImageView diceimage;
    int toptextvalue=0;
    int secondtextvalue=0;
    int firsttextvalue=0;
    int turndicescore=0;
    int goldvalue=0;
    int goldchallengevalue=0;
    int userturnscore=0;
    int computerdicescore=0;
    int goldmaxvalue=150;
    String whoseturn;
    Button rollButton;
    Button challengeButton;
    Random random = new Random();
    int [] diceNum = {R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    Stack<Integer> gold = new Stack();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displaytextview = (TextView) findViewById(R.id.displaytextview);
         toptextview = (TextView) findViewById(R.id.toptextview);
         secondtextview = (TextView) findViewById(R.id.secondtextview);
         firsttextview = (TextView) findViewById(R.id.firsttextview);
        goldtextview = (TextView) findViewById(R.id.goldtextview);
         diceimage = (ImageView) findViewById(R.id.diceimage);
         rollButton = (Button) findViewById(R.id.rollbutton);
         challengeButton = (Button) findViewById(R.id.challengebutton);

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               onlyuserroll();

            }
        });

        challengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                challenge();

            }
        });

        for(int i=1;i<=50;i++){
            gold.push(i);
        }



    }
    //to intialize the floor values as per the levels
        public void startgame(String level){

        if(level.equalsIgnoreCase("Easy"))//easy level
            goldtextview.setText("100");
        goldvalue =100;
        goldchallengevalue=10;


        {
          //  displaytextview.setText(" WElCOME PLAYER !!");

            toptextview.setText("100");
            toptextvalue=100;


            secondtextview.setText((int)gold.peek());
            secondtextvalue=(int)gold.peek();


             firsttextview.setText("00");
             firsttextvalue=00;


        }

        if(level.equalsIgnoreCase("Medium"))//mediom level
            goldtextview.setText("70");
        goldvalue= 70;
        goldchallengevalue=10;


        {
            displaytextview.setText(" WElCOME PLAYER !!");

            toptextview.setText("100");
            toptextvalue=100;

            secondtextview.setText((int)gold.peek());
            secondtextvalue=(int)gold.peek();

            firsttextview.setText("00");
             firsttextvalue=00;


        }
        if(level.equalsIgnoreCase("Hard"))//hard level
            goldtextview.setText("50");
        goldvalue=50;
        goldchallengevalue=10;
        {
            displaytextview.setText(" WElCOME PLAYER !!");

            toptextview.setText("100");
            toptextvalue=100;

            secondtextview.setText((int)gold.peek());
            secondtextvalue=(int)gold.peek();

             firsttextview.setText("00");
            firsttextvalue=00;

        }

        // stack();
    }

    //main method where the game runs
    public void stack(){
        int i;
        // topmaxvalue=toptextvalue;
        // secondmaxvalue=secondtextvalue; //firstmaxvalue=firsttextvalue;//store the max value a floor can store



        if(whoseturn.equalsIgnoreCase("computer")){ //for computer's turn
            while((int)gold.peek()!=100 || turndicescore!=0){
                pushstack(gold);
                turndicescore--;
            }
            secondtextview.setText((int)(gold.peek()));

            if ((int)gold.peek()==100){
            write("Computer Wins");
            }
            //implement the winning dialog

        }
        if(whoseturn.equalsIgnoreCase("user")) { //for user's turn
            while(!gold.empty() && (int)gold.peek()!=0 || turndicescore!=0){
                popstack(gold);
                turndicescore--;
            }
            secondtextview.setText((int)(gold.peek()));

            if (!gold.empty() && (int)gold.peek()==0){
                write("You Win");
            }
            //implement the winning dialog
        }
        if(goldvalue==0){
            write("Computer Wins");
        }
        if(goldvalue>goldmaxvalue){
            write("You Win");
        }

    }
    //after clicking thr roll button
    public void roll(){
        int dicenum = 1+random.nextInt(6);
        diceimage.setImageDrawable(getDrawable(diceNum[dicenum-1]));
        turndicescore=dicenum;
        //userturn();
    }
    //when the user only clicks roll
    public void onlyuserroll(){
        int dicenum = 1+random.nextInt(6);
        diceimage.setImageDrawable(getDrawable(diceNum[dicenum-1]));
        turndicescore=dicenum;
        whoseturn="user";
        stack();
        computerturn();


    }
    //after clicking challenge button
    public void challenge(){
        displaytextview.setText("You Challenged, Roll the Dice");
        goldvalue -=goldchallengevalue;
        goldtextview.setText("Gold Value :"+goldvalue);
        //userturn();
        roll();
        userturnscore= turndicescore;
        displaytextview.setText("Computer's Turn");
        buttonoff();
        roll();
        computerdicescore=turndicescore;
        if (userturnscore>computerdicescore){
            userturnscore *=userturnscore;
            goldvalue +=userturnscore;
            goldtextview.setText("Gold Value :"+goldvalue);
            turndicescore=userturnscore;
            whoseturn ="user";
            stack();
        }
        if (userturnscore<computerdicescore){
            computerdicescore *=computerdicescore;
            turndicescore=computerdicescore;
            whoseturn="computer";
            stack();
        }
        if(userturnscore==computerdicescore){
            goldvalue +=goldchallengevalue;
            goldtextview.setText("Gold Value :"+goldvalue);
            buttonon();

        }





    }
    //public void userturn(){
    //displaytextview.setText("Your Turn");
    // rollButton.setEnabled(true);
    // challengeButton.setEnabled(true);
    // whoseturn="user";
    //stack();

    //}
    public void computerturn(){
        buttonoff();
        displaytextview.setText("Computer's Turn");
        whoseturn="computer";
        int dicenum = 1+random.nextInt(6);
        diceimage.setImageDrawable(getDrawable(diceNum[dicenum-1]));
        turndicescore=dicenum;
        stack();
        displaytextview.setText("Your Turn");
        buttonon();
    }

    public void buttonon(){
        rollButton.setEnabled(true);
        challengeButton.setEnabled(true);
    }
    public void buttonoff(){
        rollButton.setEnabled(false);
        challengeButton.setEnabled(false);
    }



    public void pushstack(Stack gold) {
        int a =  (Integer)gold.peek();
        gold.push(a+1);

    }
    public void popstack (Stack gold) {
        if(!gold.empty()) {
            gold.pop();
        }
    }
     public void write (String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
     }
}

