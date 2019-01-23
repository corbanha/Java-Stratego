import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;

import java.io.*;

//import java.
public class AboutState extends GameState {
    
    BufferedImage backgroundImage;
    BufferedImage logoImage;
    
    private String[] aboutText = {"This game was written by Corban Anderson of Wilcox High School 2013-2014.", " ",
            "The Game State Manager and Key Managing Classes were built off from", 
        "original classes by ForiegnGuyMike. Check him out at http://neetlife2.blogspot.com/", " ", 
        "All the images in this game belong to their respected parties from the internet.", " ", "Version Beta 8.3.7", " ", "Dedicated to the children."};
    
    public AboutState(GameStateManager gsm){
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
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void update(){
        handleInput();
    }
    
    public void draw(Graphics2D g){
        g.setColor(GamePanel.defaultColor);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        
        g.drawImage(backgroundImage, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        g.drawImage(logoImage, GamePanel.WIDTH / 2 - logoImage.getWidth() / 2, GamePanel.HEIGHT / 2 - logoImage.getHeight() / 2 - GamePanel.HEIGHT / 4, null);
        
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        for(int i = 0; i < aboutText.length; i++){
            g.drawString(aboutText[i], GamePanel.WIDTH / 2 - 250, GamePanel.HEIGHT / 2 + i * 17);
        }
        
        
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.ITALIC, 24));
        g.drawString("Menu", GamePanel.WIDTH / 2 - 15, GamePanel.HEIGHT - 75);
    }
    
    public void handleInput(){
        if(Keys.isPressed(Keys.ENTER)){
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }
}