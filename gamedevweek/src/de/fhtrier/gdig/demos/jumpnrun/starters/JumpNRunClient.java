package de.fhtrier.gdig.demos.jumpnrun.starters;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.util.logging.LogManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.pbuffer.GraphicsFactory;

import de.fhtrier.gdig.demos.jumpnrun.client.ClientGame;
import de.fhtrier.gdig.demos.jumpnrun.identifiers.Constants;
import de.fhtrier.gdig.demos.jumpnrun.identifiers.Settings;

public class JumpNRunClient {

	/**
	 * @param args
	 * @throws SlickException 
	 */
	public static void main(String[] args) throws SlickException {

		if (Constants.Debug.forceNoFBO)
			GraphicsFactory.setUseFBO(false);
		
		System.setProperty("java.util.logging.config.file",
				"content/logging.properties");
		try {
			LogManager.getLogManager().readConfiguration();
		} catch (Exception e1) {}
		
		boolean fullscreen = false;
		
		if (args.length > 0 && args[0].equals("--fullscreen"))
		{
			DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice().getDisplayMode();
	
			if (dm.getWidth() >= dm.getHeight()*1.7f)
			{
				Settings.SCREENWIDTH = 1280;
				Settings.SCREENHEIGHT = 720;
			}
			else if (dm.getWidth() >= dm.getHeight()*1.5f)
			{
				Settings.SCREENWIDTH = 1280;
				Settings.SCREENHEIGHT = 800;
			}
			else if (dm.getWidth() == 1280 && dm.getHeight() == 1024)
			{
				Settings.SCREENWIDTH = 1280;
				Settings.SCREENHEIGHT = 1024;
			}
			else if (dm.getWidth() >= 1280)
			{
				Settings.SCREENWIDTH = 1280;
				Settings.SCREENHEIGHT = 960;
			}
			
			fullscreen = true;
		}
		
		ClientGame clientGame = new ClientGame();
		
		// initialize (gfx) settings depending on game type
		if (clientGame != null) {

			try {

				AppGameContainer gc = new AppGameContainer(clientGame);
				gc.setDisplayMode(Settings.SCREENWIDTH, Settings.SCREENHEIGHT, fullscreen);
				gc.setVSync(true);
				gc.setSmoothDeltas(true);
				gc.setAlwaysRender(true);
				gc.setUpdateOnlyWhenVisible(false);
				gc.setMaximumLogicUpdateInterval(30);
				gc.setTargetFrameRate(60);
				gc.setShowFPS(Constants.Debug.showDebugOverlay);
				gc.start();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}

}
