package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.List;

import static javafx.geometry.Pos.CENTER;

public class Main extends Application {

    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

    public static List<Dice> dices = new ArrayList<>();
    List<Button> buttons = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
      try {

          dices.add(new Dice());
          dices.add(new Dice());
          dices.add(new Dice());
          dices.add(new Dice());
          dices.add(new Dice());

          // Root Node - Layout der Szene

          HBox root = new HBox();
          root.setPrefSize(500.0, 70.0);
          root.setSpacing(5.0);
          root.setAlignment(CENTER);

          // Elemente erstellen

          buttons.add(new Button());
          buttons.add(new Button());
          buttons.add(new Button());
          buttons.add(new Button());
          buttons.add(new Button());
          Button buttonRoll = new Button();

          // setze Beleuchtung für gedrückte Taste

          Lighting lightingGray = new Lighting();
          lightingGray.setDiffuseConstant(1.0);
          lightingGray.setDiffuseConstant(1.0);
          lightingGray.setSpecularConstant(0.0);
          lightingGray.setSpecularExponent(0.0);
          lightingGray.setSurfaceScale(0.0);
          lightingGray.setLight(new Light.Distant(45, 45, Color.GRAY));

          // ID

          buttons.get(0).setId("0");
          buttons.get(1).setId("1");
          buttons.get(2).setId("2");
          buttons.get(3).setId("3");
          buttons.get(4).setId("4");

          buttons.get(0).setPrefSize(64.0, 64.0);
          buttons.get(1).setPrefSize(64.0, 64.0);
          buttons.get(2).setPrefSize(64.0, 64.0);
          buttons.get(3).setPrefSize(64.0, 64.0);
          buttons.get(4).setPrefSize(64.0, 64.0);
          buttonRoll.setPrefSize(64.0, 64.0);

          buttons.get(0).getStyleClass().add("defaultDice");
          buttons.get(1).getStyleClass().add("defaultDice");
          buttons.get(2).getStyleClass().add("defaultDice");
          buttons.get(3).getStyleClass().add("defaultDice");
          buttons.get(4).getStyleClass().add("defaultDice");
          buttonRoll.getStyleClass().add("roll");


          // Elemente klickbar machen (Eventhandler)


          EventHandler<ActionEvent> diceEvent = new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {

                  Button btn = (Button) event.getSource();
                  String stringID = btn.getId();

                  // cast String ID to Integer
                  int intID = Integer.parseInt(stringID);

                  if (dices.get(intID).locked) {
                      (dices.get(intID).locked) = false;

                      btn.setEffect(null);

                    //  buttons.get(intID).setEffect(null);
                  } else {
                      (dices.get(intID).locked) = true;
                      btn.setEffect(lightingGray);
                  }
              }
          };

          // diveEvent den Buttons zuweisen

          buttons.get(0).setOnAction(diceEvent);
          buttons.get(1).setOnAction(diceEvent);
          buttons.get(2).setOnAction(diceEvent);
          buttons.get(3).setOnAction(diceEvent);
          buttons.get(4).setOnAction(diceEvent);

            // Funktion des Würfeln Buttons

          buttonRoll.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {

                  int id = 0;
                  while (id <= 4) {
                      if (dices.get(id).locked) {
                      } else {
                          buttons.get(id).getStyleClass().clear();
                          buttons.get(id).getStyleClass().add("dice" + dices.get(id).rollTheDice());
                      }
                      id++;
                  }

                  // Arraylist for Dicevalue

                  ArrayList<Integer> ones = new ArrayList<Integer>();
                  ArrayList<Integer> twos = new ArrayList<Integer>();
                  ArrayList<Integer> threes = new ArrayList<Integer>();
                  ArrayList<Integer> fours = new ArrayList<Integer>();
                  ArrayList<Integer> fives = new ArrayList<Integer>();
                  ArrayList<Integer> sixes = new ArrayList<Integer>();

                  ArrayList<Integer> diceArray = new ArrayList<Integer>();

                  diceArray.add(dices.get(0).randomNum);
                  diceArray.add(dices.get(1).randomNum);
                  diceArray.add(dices.get(2).randomNum);
                  diceArray.add(dices.get(3).randomNum);
                  diceArray.add(dices.get(4).randomNum);

                  for (int i = 0; i < diceArray.size(); i++) {
                      if (diceArray.get(i) == 1) ones.add(1);
                      if (diceArray.get(i) == 2) twos.add(1);
                      if (diceArray.get(i) == 3) threes.add(1);
                      if (diceArray.get(i) == 4) fours.add(1);
                      if (diceArray.get(i) == 5) fives.add(1);
                      if (diceArray.get(i) == 6) sixes.add(1);
                  }

                  System.out.println(ones.size());
                  System.out.println(twos.size());
                  System.out.println(threes.size());
                  System.out.println(fours.size());
                  System.out.println(fives.size());
                  System.out.println(sixes.size());
                  System.out.println("*******************************");

                  //Full House

                  Boolean Fullhouse;

                  if ((ones.size() >= 3 || twos.size() >= 3 || threes.size() >= 3 ||     // Three of one kind
                          fours.size() >= 3 || fives.size() >= 3 || sixes.size() >= 3) &&
                          (ones.size() >= 2 || twos.size() >= 2 || threes.size() >= 2 ||    // Two of the other
                                  fours.size() >= 2 || fives.size() >= 2 || sixes.size() >= 2)) {
                      Fullhouse = true;
                  } else {
                      Fullhouse = false;
                  }

                  System.out.println("Fullhouse?");
                  System.out.println(Fullhouse);


                  // Small straight / large straight

                  Boolean smallStraight;


                      if ((ones.size() > 0 && twos.size() > 0 && threes.size() > 0 && fours.size() > 0) ||
                              (twos.size() > 0 && threes.size() > 0 && fours.size() > 0 && fives.size() > 0) ||
                              (threes.size() > 0 && fours.size() > 0 && fives.size() > 0 && sixes.size() > 0)) {
                          smallStraight = true;
                      } else {
                          smallStraight = false;
                      }

                  System.out.println("SmallStraight?");
                  System.out.println(smallStraight);

                  Boolean largeStraight;

                      if ((ones.size() > 0 && twos.size() > 0 && threes.size() > 0 && fours.size() > 0 && fives.size() > 0) ||
                              (twos.size() > 0 && threes.size() > 0 && fours.size() > 0 && fives.size() > 0 && sixes.size() > 0)) {
                          largeStraight = true;
                      } else {
                          largeStraight = false;
                      }

                  System.out.println("largeStraight?");
                  System.out.println(largeStraight);

                  }
          });

          // Element zum root Node hinzufügen

          root.getChildren().add(buttons.get(0));
          root.getChildren().add(buttons.get(1));
          root.getChildren().add(buttons.get(2));
          root.getChildren().add(buttons.get(3));
          root.getChildren().add(buttons.get(4));
          root.getChildren().add(buttonRoll);

          // Szene erstellen
          Scene scene = new Scene(root,500 ,70);
          scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
          scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
          primaryStage.setScene(scene);
          // X Position
          primaryStage.setX((screenSize.getWidth() / 2) -  130 );
          primaryStage.setY(20.0);
          primaryStage.show();
      }
      catch (Exception e){
          e.printStackTrace();
      }

    }

    public static void main(String[] args) {
        launch(args);
        }
    }
