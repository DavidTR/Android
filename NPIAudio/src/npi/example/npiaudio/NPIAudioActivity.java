package npi.example.npiaudio;

import java.io.IOException;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.media.MediaPlayer;

public class NPIAudioActivity extends Activity {
	
	private RadioGroup radioG;
	private TextView comprobante;
	private MediaPlayer reproductor;
	private Button reproducir, detener;
	private boolean pausado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_npiaudio);
		
        // Variables con las que podremos acceder a los elementos de la aplicaci�n.
		radioG = (RadioGroup) findViewById(R.id.radioGroup1);
    
        comprobante = (TextView) findViewById(R.id.textView2);
        reproducir = (Button) findViewById (R.id.button1);
        detener = (Button) findViewById (R.id.button2);
        
        // La variable "pausado" se usar� para que, cuando le demos al bot�n Play/Pause, la canci�n no empiece de nuevo (no se haga reset, prepare...).
        pausado = false;
        
        // Creamos el reproductor, para luego s�lo cambiar la canci�n que se ha elegido.
        reproductor = MediaPlayer.create(NPIAudioActivity.this, R.raw.guns_n_roses);
       
        // M�todo que controla el click que se produce en el bot�n PLAY. Si ya se est� reproduciendo una canci�n, se muestra un mensaje.
        reproducir.setOnClickListener(new View.OnClickListener() {
        	
        	@Override
            public void onClick(View v) {
        		        	    
               // Perform action on click
            	if (radioG.getCheckedRadioButtonId() == R.id.radio0) {
            		
            		if (reproductor.isPlaying()) {
            			reproductor.pause();
            			comprobante.setText("PAUSE");
            			pausado = true;
            		}
            		else {
                	   
            			try {
            				if (!pausado) {
	            				AssetFileDescriptor afd = NPIAudioActivity.this.getResources().openRawResourceFd( R.raw.guns_n_roses);
	
	                 			reproductor.reset();
	                 			               			
								reproductor.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
	                 			reproductor.prepare();
            				}
	                 		
            				reproductor.start();            	
	     	    	    	comprobante.setText("PLAY");
	     	    	    	pausado = false;	     	    	    	
            			}
					    catch (IllegalArgumentException e)
					    {
					        Log.e("001", "Excepci�n en el argumento: " + e.getMessage(), e);
					    }
					    catch (IllegalStateException e)
					    {
					        Log.e("002", "Excepci�n en el estado: " + e.getMessage(), e);
					    }
					    catch (IOException e)
					    {
					        Log.e("003", "Excepci�n de entrada/salida: " + e.getMessage(), e);
					    }
            		}
    	       	} else if (radioG.getCheckedRadioButtonId() == R.id.radio1) {
            		
            		if (reproductor.isPlaying()) {
            			reproductor.pause();
            			comprobante.setText("PAUSE");
            			pausado = true;
            		}
            		else {
            			try {
            				if (!pausado) {
	            				AssetFileDescriptor afd = NPIAudioActivity.this.getResources().openRawResourceFd( R.raw.u2);
	
	                			reproductor.reset();
	
								reproductor.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
	                			reproductor.prepare();
            				}
            				
	                		reproductor.start();
    	    	    		comprobante.setText("PLAY");
    	    	    		pausado = false;
            			}
            			catch (IllegalArgumentException e)
  					    {
  					        Log.e("001", "Excepci�n en el argumento: " + e.getMessage(), e);
  					    }
  					    catch (IllegalStateException e)
  					    {
  					        Log.e("002", "Excepci�n en el estado: " + e.getMessage(), e);
  					    }
  					    catch (IOException e)
  					    {
  					        Log.e("003", "Excepci�n de entrada/salida: " + e.getMessage(), e);
  					    }
            		}           		
            	}
            }

				
        });
        
        
        // M�todo que controla el click que se produce en el bot�n STOP.
        detener.setOnClickListener (new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				comprobante.setText("STOP");
				reproductor.stop();
				pausado = false;
			}
        	
        });
         
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.npiaudio, menu);
		return true;
	}
	
}
