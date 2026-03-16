package trivia;

import printer.ConsolePrinter;
import printer.Printer;

import java.util.ArrayList;
import java.util.LinkedList;

// REFACTOR ME
public class Game implements IGame {
   public static final int NB_QUESTION = 50;
   ArrayList players = new ArrayList();
   int[] places = new int[6];
   int[] purses = new int[6];
   boolean[] inPenaltyBox = new boolean[6];

   LinkedList popQuestions = new LinkedList();
   LinkedList scienceQuestions = new LinkedList();
   LinkedList sportsQuestions = new LinkedList();
   LinkedList rockQuestions = new LinkedList();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   Printer printer = new ConsolePrinter();

   public Game() {
      initQuestion();
   }

   public void initQuestion(){
      for (int i = 0; i < NB_QUESTION; i++) {
         popQuestions.addLast("Pop Question " + i);
         scienceQuestions.addLast("Science Question " + i);
         sportsQuestions.addLast("Sports Question " + i);
         rockQuestions.addLast( "Rock Question " + i);
      }
   }

   public boolean add(String playerName) {
      places[howManyPlayers()] = 1;
      purses[howManyPlayers()] = 0;
      inPenaltyBox[howManyPlayers()] = false;
      players.add(playerName);

      printer.print(playerName + " was added");
      printer.print("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      printer.print(players.get(currentPlayer) + " is the current player");
      printer.print("They have rolled a " + roll);

      if (inPenaltyBox[currentPlayer]) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            printer.print(players.get(currentPlayer) + " is getting out of the penalty box");
            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 12) places[currentPlayer] = places[currentPlayer] - 12;

            printer.print(players.get(currentPlayer)
                               + "'s new location is "
                               + places[currentPlayer]);
            printer.print("The category is " + currentCategory());
            askQuestion();
         } else {
            printer.print(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         places[currentPlayer] = places[currentPlayer] + roll;
         if (places[currentPlayer] > 12) places[currentPlayer] = places[currentPlayer] - 12;

         printer.print(players.get(currentPlayer)
                            + "'s new location is "
                            + places[currentPlayer]);
         printer.print("The category is " + currentCategory());
         askQuestion();
      }

   }

   private void askQuestion() {
      switch(currentCategory()) {
         case "Pop" :
            printer.print(popQuestions.removeFirst().toString());
            break;
         case "Science":
            printer.print(scienceQuestions.removeFirst().toString());
            break;
         case "Sports":
            printer.print(sportsQuestions.removeFirst().toString());
            break;
         default:
            printer.print(rockQuestions.removeFirst().toString());
            break;
      }
   }


   private String currentCategory() {
      int position = (places[currentPlayer] - 1) % 4;
      switch (position) {
         case 0: return "Pop";
         case 1: return "Science";
         case 2: return  "Sports";
         default: return "Rock";
      }
   }

   public boolean handleCorrectAnswer() {

      if (inPenaltyBox[currentPlayer] && !isGettingOutOfPenaltyBox) {
         nextPlayer();
         return true;
      }

      printer.print("Answer was correct!!!!");
      purses[currentPlayer]++;
      printer.print(players.get(currentPlayer)
              + " now has "
              + purses[currentPlayer]
              + " Gold Coins.");

      boolean winner = didPlayerWin();
      nextPlayer();

      return winner;
   }

   private void nextPlayer() {
      currentPlayer++;
      if (currentPlayer == players.size()) {
         currentPlayer = 0;
      }
   }

   public boolean wrongAnswer() {
      printer.print("Question was incorrectly answered");
      printer.print(players.get(currentPlayer) + " was sent to the penalty box");
      inPenaltyBox[currentPlayer] = true;

      nextPlayer();
      return true;
   }


   private boolean didPlayerWin() {
      return !(purses[currentPlayer] == 6);
   }
}
