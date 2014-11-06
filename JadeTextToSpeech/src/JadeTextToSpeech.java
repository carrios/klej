

import java.util.Locale;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

public class JadeTextToSpeech implements TextToSpeech.OnInitListener{
    TextToSpeech tts;
    String[] text;
    Context context;
    int iterator;
    public JadeTextToSpeech(Context context){
        // Do poprawy
        tts=new TextToSpeech(context, this);
        this.context=context;
        clearText();
    }

    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub
        if(status ==TextToSpeech.SUCCESS){
            tts.setLanguage(Locale.US);
        }
        else if (status == TextToSpeech.ERROR) {
            Toast.makeText(context, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }


    }
    public void pause(){
        tts.stop();

    }

    public void stop(){

        iterator = 0;
        if (isWhatToSpeak())
            clearText();


    }

    private void clearText() {
        text = new String[]{" "};
    }

    public void speakWord(String s){
        tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
    }
    public void speakText(){
        while(isWhatToSpeak()||iterator>0||!isPaused()){
            speakWord(text[iterator]);
            iterator++;
        }
        if(!isWhatToSpeak())
            stop();
    }


    public void jadeSpeak(String sentence){
        text=sentence.split(" ");
        iterator=text.length;
        speakText();

    }

    public void ttsResume(){
        speakText();

    }
    public boolean isWhatToSpeak(){
        return (text[0].equals(" "))? false:true;

    }
    public void repeatText(){
        iterator=text.length;
        speakText();
    }


    public boolean isPaused(){
        //TODO połaczyc z Jade aby łapało kiedy ma zapauzować
        return false;
    }
    public boolean isStopped(){
        //TODO połaczyc z Jade aby łapało kiedy ma zastopowac
        return false;
    }



}

