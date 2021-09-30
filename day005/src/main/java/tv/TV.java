package tv;

import java.util.Map;

public interface TV {
	
	public Map<String, String> getMap();

	public void setMap(Map<String, String> map);

	public void setRm(Remote rm);

	public void setNum(int num);

	void turnOn();

	void channelUp();

	void volumeUp();

	void volumeDown();

	void channelDown();

	void turnOff();
}
