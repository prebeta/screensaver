
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class AudioControl{

	AudioFormat audioFormat;
	TargetDataLine targetDataLine;
	AudioInputStream audioInputStream;

	public AudioControl(){
		try{
			audioFormat = getAudioFormat();
			DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
			targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
			targetDataLine.open(audioFormat);
			targetDataLine.start();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}//end catch
	}
	//This method captures audio input
	// from a microphone and saves it in
	// a ByteArrayOutputStream object.
	public int getAudio(){
		int level = 0;
		try{
			byte tempBuffer[] = new byte[100];
			int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
			if(cnt > 0)
				level = calculateRMSLevel(tempBuffer);
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}//end catch
		return level;
	}

	private AudioFormat getAudioFormat(){
		float sampleRate = 11025.0F;
		//8000,11025,16000,22050,44100
		int sampleSizeInBits = 8;
		//8,16
		int channels = 1;
		//1,2
		boolean signed = true;
		//true,false
		boolean bigEndian = true;
		//true,false
		return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed,bigEndian);
	}//end getAudioFormat
	//===================================//

	public int calculateRMSLevel(byte[] audioData)
	{ // audioData might be buffered data read from a data line
		long lSum = 0;
		for(int i=0; i<audioData.length; i++)
			lSum = lSum + audioData[i];

		double dAvg = lSum / audioData.length;

		double sumMeanSquare = 0d;
		for(int j=0; j<audioData.length; j++)
			sumMeanSquare = sumMeanSquare + Math.pow(audioData[j] - dAvg, 2d);

		double averageMeanSquare = sumMeanSquare / audioData.length;
		return (int)(Math.pow(averageMeanSquare,0.5d) + 0.5);
	}
}
