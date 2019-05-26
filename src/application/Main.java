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
import java.util.HashMap;
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

                  if (dices.get(intID).isLocked()) {
                      dices.get(intID).setLocked(false);

                      btn.setEffect(null);

                    //  buttons.get(intID).setEffect(null);
                  } else {
                      dices.get(intID).setLocked(true);
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
                      if (dices.get(id).isLocked()) {
                      } else {
                          buttons.get(id).getStyleClass().clear();
                          buttons.get(id).getStyleClass().add("dice" + dices.get(id).rollTheDice());
                      }
                      id++;
                  }

                  HashMap<Integer, Integer> diceValue = new HashMap();

                  // set the whole hashmap to zero (otherwise it would be "null"
                  for (int i=1; i < 7; i++) {
                      diceValue.put(i, 0);
                  }

                  // set value into the hashmap

                  for (int i=0; i < dices.size(); i++) {
                      int val = dices.get(i).getRandomNum();
                      if (diceValue.get(val) == null) {
                          diceValue.put(val, 0);
                      }
                      diceValue.put(val, diceValue.get(val) + 1);
                  }

                  System.out.println("einer " + diceValue.get(1));
                  System.out.println("zweier " + diceValue.get(2));
                  System.out.println("dreier " + diceValue.get(3));
                  System.out.println("vierer " + diceValue.get(4));
                  System.out.println("fünfer " + diceValue.get(5));
                  System.out.println("sechser " + diceValue.get(6));

                  // Three of A Kind

                  Boolean threeOfAKind =false;
                  Integer threeOfAKindValue = 0;

                  for (int i=1; i < 7; i++) {
                      if (diceValue.get(i) >= 3){
                          threeOfAKind = true;
                          threeOfAKindValue = i * 3;
                          break;
                      } else {
                          threeOfAKind = false;

                      }
                  }

                  System.out.println("threeofaKind? " + threeOfAKind + " , " + threeOfAKindValue);

                  // Four of A Kind

                  Boolean fourOfAKind =false;
                  Integer fourOfAKindValue = 0;

                  for (int i=1; i < 7; i++) {
                      if (diceValue.get(i) >= 4){
                          fourOfAKind = true;
                          fourOfAKindValue = i * 4;
                          break;
                      } else {
                          fourOfAKind = false;

                      }
                  }

                  System.out.println("fourOfaKind? " + fourOfAKind + " , " + fourOfAKindValue);

                  // Yatzy

                  Boolean Yatzy =false;

                  for (int i=1; i < 7; i++) {
                      if (diceValue.get(i) >= 5){
                          Yatzy = true;
                          break;
                      } else {
                          Yatzy = false;

                      }
                  }

                  System.out.println("yatzy? " + Yatzy);

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
