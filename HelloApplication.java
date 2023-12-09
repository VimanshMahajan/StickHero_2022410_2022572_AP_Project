package com.example._2022410_2022572_stickherogame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Random;


// SINGLETON DESIGN PATTERN USED, TO ENSURE ONLY ONE PLAYER OBJECT IS PRESENT THROUGHOUT THE GAME.
// Added swords for bonus -> if player touches sword, he gets killed.

public class HelloApplication extends Application {
    private static boolean up_down_flag = false;
    private Stage primaryStage;
    private static int score = 0;

    public static int getScore() {
        return score;
    }

    private static int highScore;
    boolean playermovenow=false;
    static Stage stage;


    @Override
    public void start(Stage primaryStage) {

        Group root= new Group();
        ImageView imageView = new ImageView("/background.jpg");
        imageView.setFitHeight(800);
        imageView.setFitWidth(800);
        root.getChildren().add(imageView);
        Scene scene= new Scene(root, 800, 800);
        stage= new Stage();
        this.primaryStage= stage;
        stage.setResizable(false);
        stage.setTitle("StickHero by Riya and Vimansh ");
        Image icon= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img.png")));
        stage.getIcons().add(icon);

        int cherry = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("CherryCount.txt"));
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                cherry = Integer.parseInt(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Player.setNumTotalCherries(cherry);

        Text text1= new Text();
        text1.setText("STICK HERO ");
        text1.setX(200);
        text1.setY(200);
        text1.setFont(Font.font("Verdana", 70));
        text1.setFill(Color.BLACK);
        Circle playCircle= new Circle();
        playCircle.setCenterX(400);
        playCircle.setCenterY(400);
        playCircle.setRadius(100);
        playCircle.setFill(Color.RED);
        Button playText= new Button();
        playText.setText("PLAY");
        playText.setLayoutX(337);
        playText.setLayoutY(360);
        playText.setFont(Font.font("Verdana", 35));
        playText.setTextFill(Color.WHITE);
        playText.setBackground(Background.fill(Color.RED));
        playText.setOnAction(actionEvent -> {
            Scene gameScene= createScene2();
            stage.setScene(gameScene);
        });
        Image image1= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/stickhero.png")));
        ImageView stickhero= new ImageView(image1);
        stickhero.setX(327);
        stickhero.setY(600);
        stickhero.setFitHeight(200);
        stickhero.setFitWidth(150);

        stage.setWidth(800);
        stage.setHeight(800);

        root.getChildren().add(text1);
        root.getChildren().add(playCircle);
        root.getChildren().add(playText);
        root.getChildren().add(stickhero);
        stage.setScene(scene);
        stage.show();

    }
    private static double getRandomOffset(double firstPlatformWidth) {
        double minGap = 100; // Minimum gap between the platforms
        double randomOffset = new Random().nextDouble() * (600 - firstPlatformWidth - minGap);
        return randomOffset + firstPlatformWidth + minGap;
    }

    private static double getRandomWidth() {
        return 50 + (150) * new Random().nextDouble();
    }
    private Scene createScene1(){
        Group root= new Group();
        ImageView imageView = new ImageView("/background.jpg");
        imageView.setFitHeight(800);
        imageView.setFitWidth(800);
        root.getChildren().add(imageView);
        Scene scene= new Scene(root, 800, 800);
        stage= new Stage();
        //this.primaryStage= stage;
        stage.setResizable(false);
        stage.setTitle("StickHero by Riya and Vimansh ");
        Image icon= new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("/img.png")));
        stage.getIcons().add(icon);

        int cherry = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("CherryCount.txt"));
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                cherry = Integer.parseInt(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Player.setNumTotalCherries(cherry);


        Text text1= new Text();
        text1.setText("STICK HERO ");
        text1.setX(200);
        text1.setY(200);
        text1.setFont(Font.font("Verdana", 70));
        text1.setFill(Color.BLACK);
        Circle playCircle= new Circle();
        playCircle.setCenterX(400);
        playCircle.setCenterY(400);
        playCircle.setRadius(100);
        playCircle.setFill(Color.RED);
        Button playText= new Button();
        playText.setText("PLAY");
        playText.setLayoutX(337);
        playText.setLayoutY(360);
        playText.setFont(Font.font("Verdana", 35));
        playText.setTextFill(Color.WHITE);
        playText.setBackground(Background.fill(Color.RED));
        playText.setOnAction(actionEvent -> {
            Scene gameScene= createScene2();
            stage.setScene(gameScene);
        });
        Image image1= new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("/stickhero.png")));
        ImageView stickhero= new ImageView(image1);
        stickhero.setX(327);
        stickhero.setY(600);
        stickhero.setFitHeight(200);
        stickhero.setFitWidth(150);

        stage.setWidth(800);
        stage.setHeight(800);

        root.getChildren().add(text1);
        root.getChildren().add(playCircle);
        root.getChildren().add(playText);
        root.getChildren().add(stickhero);
        stage.setScene(scene);
        stage.show();
        return scene;
    }
    List<Platform> initialPlatforms;
    Rectangle firstPlatform = new Rectangle();
    Rectangle secondPlatform = new Rectangle();

    private Scene createScene2() {
        Group root = new Group();
        ImageView imageView = new ImageView("/background.jpg");
        imageView.setFitHeight(800);
        imageView.setFitWidth(800);
        root.getChildren().add(imageView);
        Scene scene2 = new Scene(root, 800, 800);
        firstPlatform.setX(0);
        firstPlatform.setY(500);
        firstPlatform.setWidth(150);
        firstPlatform.setHeight(500);
        firstPlatform.setFill(Color.BLACK);

        secondPlatform.setX(getRandomOffset(firstPlatform.getWidth()));
        secondPlatform.setY(500);
        secondPlatform.setWidth(getRandomWidth());
        secondPlatform.setHeight(500);
        secondPlatform.setFill(Color.BLACK);
        root.getChildren().addAll(firstPlatform,secondPlatform);

        double stickX = firstPlatform.getX() + firstPlatform.getWidth() - 2;
        StickBridge stickBridge = new StickBridge(0);
        Rectangle stickRectangle = stickBridge.getStick();
        stickRectangle.setX(stickX);
        stickRectangle.setY(500);
        root.getChildren().add(stickRectangle);

        Text hold_score = new Text(String.valueOf(score));
        hold_score.setX(400);
        hold_score.setY(100);
        hold_score.setFont(Font.font(50));
        root.getChildren().add(hold_score);
        ImageView playerImageView = new ImageView("/stickhero_image.png");
        playerImageView.setFitWidth(60);
        playerImageView.setFitHeight(90);
        playerImageView.setY(410);
        playerImageView.setX(86);
        Player player = Player.getInstance();
        player.setPlayerImage(playerImageView);
        //Player player = new Player(100, 0, 0, 0, playerImageView);  --> backup
        root.getChildren().add(player.getPlayerImage());
        Cherry check = generateAndDisplayCherries(root);
        Swords check2 = new Swords();

        if(check == null){
            check2 = generateAndDisplaySwords(root);
        }
        else{
            check2 = null;
        }
        final Swords check2Final = check2;
        //System.out.println(check2);
        scene2.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {

                stickBridge.handleKeyPress(event.getCode());
                playermovenow=true;

            }else if (event.getCode() == KeyCode.DOWN) {
                // Rotate the player by 180 degrees when Enter key is pressed
                up_down_flag = true;
                player.getPlayerImage().setRotate(player.getPlayerImage().getRotate() + 180);
                playerImageView.setY(500);
                double playbackRate = 2.0;
                AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getClassLoader().getResource("rotate-sound-effect.wav")).toExternalForm());
                audioClip.setRate(playbackRate);
                audioClip.play();
            }
            else if(event.getCode() == KeyCode.UP){
                up_down_flag = false;
                player.getPlayerImage().setRotate(player.getPlayerImage().getRotate() + 180);
                player.getPlayerImage().setY(410);
                double playbackRate = 2.0;
                AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getClassLoader().getResource("rotate-sound-effect.wav")).toExternalForm());
                audioClip.setRate(playbackRate);
                audioClip.play();
            }
        });

        scene2.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                stickBridge.handleKeyRelease(event.getCode());
                if(stickX+ stickBridge.getStick().getHeight()<secondPlatform.getX()) {

                    int flag=0;
                    TranslateTransition playermove= new TranslateTransition();
                    if(playerImageView.getX()!=stickBridge.getStick().getHeight()){
                        playermove= new TranslateTransition(Duration.seconds(1), playerImageView);
                        playermove.setToX(stickBridge.getStick().getHeight());
                        playermove.play();
                        playermove.currentTimeProperty().addListener((observableValue, duration, t1) -> {
                            double playerX = playerImageView.getX()+playerImageView.getTranslateX();
                            if(!cherrycollected && check!=null){
                                checkCherryCollection(playerX, check.getX(), root,check);
                            }
                            checkSwordCollection(playerX, check2Final.getX(),root,check2Final);
                            if(killed && check2Final!=null){
                                Media media = new Media(Objects.requireNonNull(getClass().getResource("/kill-sound.mp3")).toExternalForm());
                                MediaPlayer mediaPlayer = new MediaPlayer(media);
                                mediaPlayer.setRate(mediaPlayer.getRate()*2);
                                mediaPlayer.play();
                                mediaPlayer.setOnEndOfMedia(()->{
                                    Scene scene3;
                                    try{
                                        scene3 = createScene3();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }

                                    Stage stage3 = new Stage();
                                    stage3.setResizable(false);
                                    stage3.setTitle("StickHero by Riya and Vimansh ");
                                    Image icon= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img.png")));
                                    stage3.getIcons().add(icon);
                                    stage3.setWidth(800);
                                    stage3.setHeight(800);
                                    stage3.setScene(scene3);
                                    stage.close();
                                    stage3.show();
                                });

                            }
                        });
                    }

                    playermove.setOnFinished(event2 -> {
                        TranslateTransition playerfall= new TranslateTransition();
                        playerfall= new TranslateTransition(Duration.seconds(1), playerImageView);
                        playerfall.setToY(800);
                        playerfall.play();
                        Media media = new Media(Objects.requireNonNull(getClass().getResource("/punch-soundeff.mp3")).toExternalForm());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.play();
                        // SHIFT TO SCENE 3
                        mediaPlayer.setOnEndOfMedia(()->{
                            Scene scene3;
                            try{
                                scene3 = createScene3();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            Stage stage3 = new Stage();
                            stage3.setResizable(false);
                            stage3.setTitle("StickHero by Riya and Vimansh ");
                            Image icon= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img.png")));
                            stage3.getIcons().add(icon);
                            stage3.setWidth(800);
                            stage3.setHeight(800);
                            stage3.setScene(scene3);
                            stage.close();
                            stage3.show();
                        });
                    });

                }
                else{
                    if(playermovenow){
                        TranslateTransition playermove= new TranslateTransition();
                        playermove= new TranslateTransition(Duration.seconds(3), playerImageView);
                        if(stickBridge.getStick().getHeight()>secondPlatform.getX()+secondPlatform.getWidth()- firstPlatform.getWidth()){
                            //          System.out.println("Shift to new scene");
                            TranslateTransition playermove2= new TranslateTransition();
                            if(playerImageView.getX()!=stickBridge.getStick().getHeight()){
                                playermove2= new TranslateTransition(Duration.seconds(1), playerImageView);
                                playermove2.setToX(stickBridge.getStick().getHeight());
                                playermove2.play();
                                playermove2.currentTimeProperty().addListener((observableValue, duration, t1) -> {
                                    double playerX = playerImageView.getX() + playerImageView.getTranslateX();
                                    if(!cherrycollected && check!= null){
                                        checkCherryCollection(playerX,check.getX(),root,check);

                                    }
                                    checkSwordCollection(playerX, check2Final.getX(),root,check2Final);
                                    if(killed && check2Final!=null){
                                        Media media = new Media(Objects.requireNonNull(getClass().getResource("/kill-sound.mp3")).toExternalForm());
                                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                                        mediaPlayer.play();
                                        mediaPlayer.setOnEndOfMedia(()->{
                                            Scene scene3;
                                            try{
                                                scene3 = createScene3();
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }

                                            Stage stage3 = new Stage();
                                            stage3.setResizable(false);
                                            stage3.setTitle("StickHero by Riya and Vimansh ");
                                            Image icon= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img.png")));
                                            stage3.getIcons().add(icon);
                                            stage3.setWidth(800);
                                            stage3.setHeight(800);
                                            stage3.setScene(scene3);
                                            stage.close();
                                            stage3.show();
                                        });

                                    }

                                });
                            }

                            playermove.setOnFinished(event2 -> {
                                TranslateTransition playerfall= new TranslateTransition();
                                playerfall= new TranslateTransition(Duration.seconds(1), playerImageView);
                                playerfall.setToY(800);
                                playerfall.play();
                                Media media = new Media(Objects.requireNonNull(getClass().getResource("/punch-soundeff.mp3")).toExternalForm());
                                MediaPlayer mediaPlayer = new MediaPlayer(media);
                                mediaPlayer.play();
                                mediaPlayer.setOnEndOfMedia(()->{
                                    Scene scene3;
                                    try{
                                        scene3 = createScene3();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }

                                    Stage stage3 = new Stage();
                                    stage3.setResizable(false);
                                    stage3.setTitle("StickHero by Riya and Vimansh ");
                                    Image icon= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img.png")));
                                    stage3.getIcons().add(icon);
                                    stage3.setWidth(800);
                                    stage3.setHeight(800);
                                    stage3.setScene(scene3);
                                    stage.close();
                                    stage3.show();
                                });

                            });
                            playermove.setToX(stickBridge.getStick().getHeight());
                            playermove.play();
                        }
                        else{

                            playermove.setToX(secondPlatform.getX()+secondPlatform.getWidth()-firstPlatform.getWidth());
                            playermove.play();
                            playermove.currentTimeProperty().addListener((observableValue, duration, t1) -> {
                                double playerX = playerImageView.getX() + playerImageView.getTranslateX();
                                if(!cherrycollected && check!=null){
                                    checkCherryCollection(playerX,check.getX(),root,check);
                                }
                                checkSwordCollection(playerX, check2Final.getX(),root,check2Final);
                                if(killed && check2Final!=null){
                                    Media media = new Media(Objects.requireNonNull(getClass().getResource("/kill-sound.mp3")).toExternalForm());
                                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                                    mediaPlayer.play();
                                    mediaPlayer.setOnEndOfMedia(()->{
                                        Scene scene3;
                                        try{
                                            scene3 = createScene3();
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }

                                        Stage stage3 = new Stage();
                                        stage3.setResizable(false);
                                        stage3.setTitle("StickHero by Riya and Vimansh ");
                                        Image icon= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img.png")));
                                        stage3.getIcons().add(icon);
                                        stage3.setWidth(800);
                                        stage3.setHeight(800);
                                        stage3.setScene(scene3);
                                        stage.close();
                                        stage3.show();
                                    });

                                }

                            });


                            playermove.setOnFinished(event2 -> {
                                if(playerImageView.getY()==410){
                                    //                    System.out.println("continue game");
                                    score+=1;
                                    stickBridge.resetStick();
                                    hold_score.setText(String.valueOf(score));
                                    //                      System.out.println("score updated");
                                    secondPlatform.setX(getRandomOffset(firstPlatform.getWidth()));
                                    secondPlatform.setWidth(getRandomWidth());
                                    stickBridge.getStick().setX(firstPlatform.getWidth()-5);
                                    TranslateTransition playerResetTransition = new TranslateTransition(Duration.seconds(0.1), playerImageView);
                                    resetPlayerPosition(playerResetTransition, playerImageView);
                                    stickBridge.getStick().setVisible(true);

                                    Scene gameScene = createScene2();
                                    stage.setScene(gameScene);

                                }
                                else{
                                    Scene scene3;
                                    try {
                                        scene3 = createScene3();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }

                                    Stage stage3 = new Stage();
                                    stage3.setResizable(false);
                                    stage3.setTitle("StickHero by Riya and Vimansh ");
                                    Image icon= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img.png")));
                                    stage3.getIcons().add(icon);
                                    stage3.setWidth(800);
                                    stage3.setHeight(800);
                                    stage3.setScene(scene3);
                                    stage.close();
                                    stage3.show();

                                }

                            });
                        }
                    }
                }
            }


        });
        return scene2;
    }
    private void resetPlayerPosition(TranslateTransition playerResetTransition, ImageView playerImageView) {
        playerResetTransition.setToX(10); // Set the X-coordinate to the original position
        //playerResetTransition.setToY(350); // Set the Y-coordinate to the original position
        playerResetTransition.play();
    }

    public static Scene createScene3() throws IOException {
        Group root = new Group();
        ImageView imageView = new ImageView("/background.jpg");
        imageView.setOpacity(0.5);
        imageView.setFitHeight(800);
        imageView.setFitWidth(800);
        root.getChildren().add(imageView);
        Scene scene3 = new Scene(root, 800, 800);
        BufferedReader reader= new BufferedReader(new FileReader("HighScore.txt"));
        String line = reader.readLine();
        if (line != null && !line.isEmpty()) {
            highScore = Integer.parseInt(line);
        }
        if(highScore<score){
            updateHighScore();
            Text newHS= new Text("New High Score!");
            newHS.setFont(Font.font("Verdana", 20));
            newHS.setFill(Color.RED);
            newHS.setLayoutX(200);
            newHS.setLayoutY(100);
            root.getChildren().add(newHS);

        }
        Text text1= new Text();
        text1.setText("GAME OVER! ");
        text1.setX(160);
        text1.setY(200);
        text1.setFont(Font.font("Verdana", 70));
        text1.setFill(Color.BLACK);
        root.getChildren().add(text1);

        StackPane stackPane = new StackPane();
        Rectangle bg = new Rectangle(400,220,Color.WHITE);
        bg.setTranslateX(180);
        bg.setTranslateY(250);
        bg.setArcHeight(20);
        bg.setArcWidth(20);
        Text text2 = new Text();
        text2.setX(300);
        text2.setY(300);
        text2.setFont(Font.font("Verdana",40));
        text2.setFill(Color.BLACK);
        String temp = String.valueOf(HelloApplication.score);
        text2.setText("SCORE\n    "+temp);

        Text text3 = new Text();
        text3.setX(300);
        text3.setY(400);
        text3.setFont(Font.font("Verdana",40));
        text3.setFill(Color.BLACK);
        text3.setText(" BEST\n    "+line);


        Button home = new Button();
        home.setText("HOME");
        home.setTranslateX(250);
        home.setTranslateY(500);
        home.setStyle("-fx-background-color: red; -fx-text-fill: black");
        home.setOnAction(event->{

            HelloApplication helloApplication = new HelloApplication();
            score = 0;
            helloApplication.createScene1();
        });

        BufferedWriter writer = new BufferedWriter(new FileWriter("CherryCount.txt"));
        writer.write(String.valueOf(Player.getNumTotalCherries()));
        writer.close();


        Image cherry = new Image("cherry.png");
        ImageView cherry_image = new ImageView(cherry);
        cherry_image.setFitWidth(25);
        cherry_image.setFitHeight(25);
        cherry_image.setY(50);
        cherry_image.setX(690);
        BufferedReader bufferedReader = new BufferedReader(new FileReader("CherryCount.txt"));

        String count = bufferedReader.readLine();
        if (count != null && !count.isEmpty()) {
            Player.setNumTotalCherries(Integer.parseInt(count));
        }
        Text cherry_count = new Text(String.valueOf(Player.getNumTotalCherries()));
        cherry_count.setY(75);
        cherry_count.setX(730);
        //int temp_store = Player.getNumTotalCherries();

        Button revive  = new Button();
        revive.setText("REVIVE");
        revive.setTranslateX(475);
        revive.setTranslateY(500);
        revive.setStyle("-fx-background-color: red; -fx-text-fill: black");
        Text disp = new Text();
        revive.setOnAction(event -> {
            if(Player.getNumTotalCherries() >= 5){
                Player.setNumTotalCherries(Player.getNumTotalCherries()-5);
                HelloApplication helloApplication = new HelloApplication();
                Scene scene = helloApplication.createScene2();
                stage.setScene(scene);
                stage.show();

            }
            else{
                disp.setText("NOT ENOUGH CHERRIES!");
                disp.setTranslateX(320);
                disp.setTranslateY(500);

            }
        });

        root.getChildren().addAll(bg,text2,text3,home,cherry_image,cherry_count,revive,disp);

        return scene3;
    }
    public Cherry generateAndDisplayCherries(Group root) {
        int flag = 0;
        int randomValue = (int) (Math.random() * 2); // Generate a random integer (0 or 1)
        Cherry cherry = null;
        if (randomValue == 1) {
            flag = 1;
            //System.out.println("hi");
            // If 1 is generated, spawn a cherry between two platforms
            cherry= new Cherry("/cherry.png");
            //double cherryX = firstPlatform.getOffset() + (secondPlatform.getOffset() - firstPlatform.getOffset()) * Math.random();
            //double cherryX = Math.random(firstPlatform.getOffset()+ firstPlatform.getWidth()+5, secondPlatform.getOffset());
            double num= Math.random();
            while(num<=0){
                num=Math.random();
            }
            double min= firstPlatform.getX()+ firstPlatform.getWidth()+20;
            double max= secondPlatform.getX()-20;
            double cherryX  = (num * (max - min + 1)) + min;
            double cherryY = 510;
            cherry.setX(cherryX);
            cherry.setY(cherryY);
            cherry.setFitHeight(20);
            cherry.setFitWidth(20);
            root.getChildren().add(cherry);
        }
        else{
            //System.out.println("bye");
            flag = 0;
        }
        return cherry;
    }

    private Swords generateAndDisplaySwords(Group root){
        int randomValue = (int)(Math.random()*2);
        Swords sword = null;
        if (randomValue == 1) {
            //System.out.println("hi sword");
            // If 1 is generated, spawn a cherry between two platforms
            sword= new Swords("/sword.png");
            //double cherryX = firstPlatform.getOffset() + (secondPlatform.getOffset() - firstPlatform.getOffset()) * Math.random();
            //double cherryX = Math.random(firstPlatform.getOffset()+ firstPlatform.getWidth()+5, secondPlatform.getOffset());
            double num= Math.random();
            while(num<=0){
                num=Math.random();
            }
            double min= firstPlatform.getX()+ firstPlatform.getWidth()+20;
            double max= secondPlatform.getX()-20;
            double swordX  = (num * (max - min + 1)) + min;
            double swordY = 470;
            sword.setX(swordX);
            sword.setY(swordY);
            sword.setFitHeight(20);
            sword.setFitWidth(20);
            root.getChildren().add(sword);
        }
        else{

        }
        return sword;
    }

    private static void updateHighScore() {
        // Update the high score in a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("HighScore.txt"))) {
            writer.write(Integer.toString(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    boolean cherrycollected=false;
    private void checkCherryCollection(double playerX, double cherryX,Group root,Cherry cherry) {
        double xRange = 20;

        if (Math.abs(playerX - cherryX) < xRange) {
            if (up_down_flag) {

                root.getChildren().remove(cherry);
                cherrycollected=true;

            }  // Incorrect collection
            //System.out.println("Incorrect collection incorrect");

        }
        if(cherrycollected){
            Player.setNumTotalCherries(Player.getNumTotalCherries()+1);
            //System.out.println("done");
            return;
        }
        else{
            //System.out.println("Incorrect collection");
        }
    }
    boolean killed = false;
    private void checkSwordCollection(double playerX, double swordX, Group root, Swords sword) {
        double xRange = 10; // Adjust this range based on your requirements


        if (Math.abs(playerX - swordX) < xRange) {
            if (!up_down_flag) {
                // Incorrect collection - Player touched the sword
                root.getChildren().remove(sword);
                killed = true;
                // Perform actions when sword is collected, e.g., end the game or decrease player live

            } else {
                // Correct collection - Player avoided the sword
                //System.out.println("Player successfully avoided the sword!");
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
