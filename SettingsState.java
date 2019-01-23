import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;

import java.io.*;

//import java.
public class SettingsState extends GameState {

    BufferedImage backgroundImage;
    BufferedImage logoImage;
    BufferedImage miniMap;

    private String[] options = {"Player Color", "Computer Color", "Show Grid", "Show Avaliable Moves", "Background", "Map"};

    private String[] maps = {"SnowflakeMap.png", "CloudMap.png", "DesertMap.png", "SewerMap.png"};
    private String[] backgrounds = {"SandBackground.png", "GrassBackground.png", "GrayBackground.png", "BlackBackground.png", "PurpleBackground.png",
    		"PeachBackground.png", "BlueBackground.png"};

    private int currentChoice = 0;

    private Color[] colors;

    private int playerSelectedColor = 0;
    private int computerSelectedColor = 1;
    private int mapChoice = 0;
    private int backgroundChoice = 0;

    public SettingsState(GameStateManager gsm){
        super(gsm);
        init();
    }

    public void init(){
        try{
        	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        	InputStream rawBackground = classloader.getResourceAsStream(GamePanel.getGameBackground());
            backgroundImage = ImageIO.read(rawBackground);
            
            InputStream rawLogo = classloader.getResourceAsStream(GamePanel.getGameLogo());
            logoImage = ImageIO.read(rawLogo);
            
            InputStream rawMap = ClassLoader.getSystemResourceAsStream(GamePanel.getMap());
            miniMap = ImageIO.read(rawMap);
        }catch(Exception e){
            e.printStackTrace();
        }

        colors = new Color[10];
        colors[0] = new Color(51, 74, 222);
        colors[1] = new Color(237, 81, 64);
        colors[2] = new Color(237, 162, 64);
        colors[3] = new Color(234, 242, 78);
        colors[4] = new Color(157, 242, 78);
        colors[5] = new Color(78, 242, 176);
        colors[6] = new Color(78, 240, 242);
        colors[7] = new Color(78, 154, 242);
        colors[8] = new Color(157, 78, 242);
        colors[9] = new Color(242, 78, 229);
    }

    public void update(){
        handleInput();
        if(currentChoice < 0){
            currentChoice = options.length;
        }
        if(currentChoice > options.length){
            currentChoice = 0;
        }
    }

    public void draw(Graphics2D g){
        g.setColor(GamePanel.defaultColor);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        g.drawImage(backgroundImage, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        g.drawImage(logoImage, GamePanel.WIDTH / 2 - logoImage.getWidth() / 2, GamePanel.HEIGHT / 2 - logoImage.getHeight() / 2 - GamePanel.HEIGHT / 4, null);

        for(int i = 0; i < options.length; i++){
            if(currentChoice == i){
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.ITALIC, 24));
            }else{
                g.setColor(GamePanel.defaultColor);
                g.setFont(new Font("Arial", Font.PLAIN, 20));
            }
            g.drawString(options[i], GamePanel.WIDTH / 5, GamePanel.HEIGHT / 2 - GamePanel.HEIGHT / 12 + i * 35);
        }

        g.setColor(GamePanel.getPlayerColor());
        if(currentChoice == 0){
            g.fillRect(GamePanel.WIDTH - GamePanel.WIDTH / 4, GamePanel.HEIGHT / 2 - 12 - GamePanel.HEIGHT / 12, 35, 19);
        }else{
            g.fillRect(GamePanel.WIDTH - GamePanel.WIDTH / 4, GamePanel.HEIGHT / 2 - 10 - GamePanel.HEIGHT / 12, 30, 15);
        }

        g.setColor(GamePanel.getComputerColor());
        if(currentChoice == 1){
            g.fillRect(GamePanel.WIDTH - GamePanel.WIDTH / 4, GamePanel.HEIGHT / 2 - 12 + 35 - GamePanel.HEIGHT / 12, 35, 19);
        }else{
            g.fillRect(GamePanel.WIDTH - GamePanel.WIDTH / 4, GamePanel.HEIGHT / 2 - 10 + 35 - GamePanel.HEIGHT / 12, 30, 15);
        }

        g.setColor(GamePanel.defaultColor);

        if(currentChoice == 2){
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("" + GamePanel.getShowGrid(), GamePanel.WIDTH - GamePanel.WIDTH / 4, GamePanel.HEIGHT / 2 + 80 - GamePanel.HEIGHT / 12);
        }else{
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("" + GamePanel.getShowGrid(), GamePanel.WIDTH - GamePanel.WIDTH / 4, GamePanel.HEIGHT / 2 + 80 - GamePanel.HEIGHT / 12);
        }
        
        if(currentChoice == 3){
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("" + GamePanel.getShowAvaliableMoves(), GamePanel.WIDTH - GamePanel.WIDTH / 4, GamePanel.HEIGHT / 2 + 105 - GamePanel.HEIGHT / 12);
        }else{
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("" + GamePanel.getShowAvaliableMoves(), GamePanel.WIDTH - GamePanel.WIDTH / 4, GamePanel.HEIGHT / 2 + 105 - GamePanel.HEIGHT / 12);
        }

        if(currentChoice == 4){
            //do nothing
        }

        if(currentChoice == 5){
            g.drawImage(miniMap, GamePanel.WIDTH - GamePanel.WIDTH / 4, GamePanel.HEIGHT / 2 + 140 - GamePanel.HEIGHT / 12, 175, 175, null);
        }else{
            g.drawImage(miniMap, GamePanel.WIDTH - GamePanel.WIDTH / 4, GamePanel.HEIGHT / 2 + 140 - GamePanel.HEIGHT / 12, 40, 40, null);
        }

        if(currentChoice == options.length){
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.ITALIC, 24));
        }else{
            g.setColor(GamePanel.defaultColor);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
        }
        g.drawString("Menu", GamePanel.WIDTH / 2 - 15, GamePanel.HEIGHT - 75);
    }

    public void handleInput(){
        if(Keys.isPressed(Keys.ENTER)){
            if(currentChoice == 0){
                playerSelectedColor++;
                while(playerSelectedColor >= colors.length || playerSelectedColor == computerSelectedColor){
                    playerSelectedColor++;
                    if(playerSelectedColor >=colors.length){
                        playerSelectedColor = 0;
                    }
                }

                GamePanel.setPlayerColor(colors[playerSelectedColor]);
            }
            if(currentChoice == 1){
                computerSelectedColor++;
                while(computerSelectedColor >= colors.length || playerSelectedColor == computerSelectedColor){
                    computerSelectedColor++;
                    if(computerSelectedColor >=colors.length){
                        computerSelectedColor = 0;
                    }
                }

                GamePanel.setComputerColor(colors[computerSelectedColor]);
            }
            if(currentChoice == 2){
                GamePanel.setShowGrid(!GamePanel.getShowGrid());
            }
            if(currentChoice == 3){
                GamePanel.setShowAvaliableMoves(!GamePanel.getShowAvaliableMoves());
            }
            if(currentChoice == 4){
                backgroundChoice++;
                if(backgroundChoice >= backgrounds.length){
                    backgroundChoice = 0;
                }
                GamePanel.setGameBackground(backgrounds[backgroundChoice]);
                try{
                	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                	InputStream rawBackground = classloader.getResourceAsStream(GamePanel.getGameBackground());
               
                    backgroundImage = ImageIO.read(rawBackground);
                }catch(Exception e){
                    e.printStackTrace();
                }
                if(GamePanel.getGameBackground().equals("BlackBackground.png")){
                    GamePanel.setDefaultColor(Color.LIGHT_GRAY);
                }else{
                    GamePanel.setDefaultColor(Color.BLACK);
                }
            }
            if(currentChoice == 5){
                mapChoice++;
                if(mapChoice >= maps.length){
                    mapChoice = 0;
                }
                GamePanel.setMap(maps[mapChoice]);
                try{
                	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                	InputStream rawMap = classloader.getResourceAsStream(GamePanel.getMap());
                    miniMap = ImageIO.read(rawMap);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(currentChoice == options.length){
                gsm.setState(GameStateManager.MENUSTATE);
            }
        }
        if(Keys.isPressed(Keys.UP)){
            currentChoice--;
        }
        if(Keys.isPressed(Keys.DOWN)){
            currentChoice++;
        }
    }
}