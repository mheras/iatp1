package edu.itba.ia.tp1.main;

import javax.swing.UIManager;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceBlue;

import edu.itba.ia.tp1.ui.MainFrame;


/**
 * Entry point to the application.
 * 
 * @author Martín A. Heras
 */
public class Main {

    public static void main(String args[]) {
    	
    	try {
    		Plastic3DLookAndFeel.setCurrentTheme(new ExperienceBlue());
			UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
		} catch (Exception e) {
			System.err.println("Could not apply jgoodies plastic look and feel. Applying default one.");
		}
    	
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }   
}
