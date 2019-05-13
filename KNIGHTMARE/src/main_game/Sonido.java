package main_game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sonido {
	
	
	private Clip musica;//NO RECONOCE MP3, SOLO WAV...


	public Sonido(String fichero){
		
		try {
			
			musica=AudioSystem.getClip();
			musica.open(AudioSystem.getAudioInputStream(new File("./sonidos/"+fichero)));
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//reproducir un sonido
	public void play(){
		if(musica.isRunning()){
			musica.stop();
			musica.close();
			musica.start();
		}else{
			musica.start();
		}
	}
	
	public void detener(){
		musica.stop();
	}
	
	public void playLoop(){
		if(musica.isRunning()){
		}else{
			musica.loop(Clip.LOOP_CONTINUOUSLY);
		}
		
	}
	public void andar(){
		
	}
	public void atacar(){
		
	}
	public void voz(){
		
	}
	public void daño(){
		
	}

	//reproducir una musica de forma continua

}



