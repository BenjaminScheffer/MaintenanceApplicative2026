package trivia;

import printer.ConsolePrinter;
import printer.Printer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game implements IGame {
   public static final int NB_QUESTION = 50;
   public static final String POP = "Pop";
   public static final String SCIENCE = "Science";
   public static final String SPORTS = "Sports";
   public static final String ROCK = "Rock";
   public static final int BOARD_SIZE = 12;

   private final List<Player> players = new ArrayList<>();
   private final LinkedList<String> popQuestions = new LinkedList<>();
   private final LinkedList<String> scienceQuestions = new LinkedList<>();
   private final LinkedList<String> sportsQuestions = new LinkedList<>();
   private final LinkedList<String> rockQuestions = new LinkedList<>();

   private int currentPlayerIndex = 0;
   private boolean isGettingOutOfPenaltyBox;
   private final Printer printer = new ConsolePrinter();

   public Game() {
      initQuestions();
   }

   private void initQuestions() {
      for (int i = 0; i < NB_QUESTION; i++) {
         popQuestions.addLast("Pop Question " + i);
         scienceQuestions.addLast("Science Question " + i);
         sportsQuestions.addLast("Sports Question " + i);
         rockQuestions.addLast("Rock Question " + i);
      }
   }

   public boolean add(String playerName) {
      players.add(new Player(playerName));
      printer.print(playerName + " was added");
      printer.print("They are player number " + players.size());
      return true;
   }

   private Player getCurrentPlayer() {
      return players.get(currentPlayerIndex);
   }

   public void roll(int roll) {
      Player player = getCurrentPlayer();
      printer.print(player + " is the current player");
      printer.print("They have rolled a " + roll);

      if (player.isInPenaltyBox()) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            printer.print(player + " is getting out of the penalty box");
            movePlayerAndAsk(player, roll);
         } else {
            printer.print(player + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }
      } else {
         movePlayerAndAsk(player, roll);
      }
   }

   private void movePlayerAndAsk(Player player, int roll) {
      player.move(roll, BOARD_SIZE);
      printer.print(player + "'s new location is " + player.getPlace());
      askCurrentQuestion();
   }

   public void askCurrentQuestion() {
      printer.print("The category is " + currentCategory());
      askQuestion();
   }

   private void askQuestion() {
      switch(currentCategory()) {
         case POP:
            printer.print(popQuestions.removeFirst());
            break;
         case SCIENCE:
            printer.print(scienceQuestions.removeFirst());
            break;
         case SPORTS:
            printer.print(sportsQuestions.removeFirst());
            break;
         default:
            printer.print(rockQuestions.removeFirst());
            break;
      }
   }


   private String currentCategory() {
      int position = (getCurrentPlayer().getPlace() - 1) % 4;
      switch (position) {
         case 0: return POP;
         case 1: return SCIENCE;
         case 2: return  SPORTS;
         default: return ROCK;
      }
   }

   public boolean handleCorrectAnswer() {
      Player player = getCurrentPlayer();

      if (player.isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
         nextPlayer();
         return true;
      }

      printer.print("Answer was correct!!!!");
      player.addGold();
      printer.print(player + " now has " + player.getPurse() + " Gold Coins.");

      boolean winner = didPlayerWin();
      nextPlayer();

      return winner;
   }

   public boolean wrongAnswer() {
      Player player = getCurrentPlayer();
      printer.print("Question was incorrectly answered");
      printer.print(player + " was sent to the penalty box");
      player.setInPenaltyBox(true);

      nextPlayer();
      return true;
   }

   private void nextPlayer() {
      currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
   }

   private boolean didPlayerWin() {
      return !(getCurrentPlayer().getPurse() == 6);
   }

}