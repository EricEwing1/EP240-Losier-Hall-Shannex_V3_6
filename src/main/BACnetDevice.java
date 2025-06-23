package main;

public class BACnetDevice {
	PointManager pointManager;
	int deviceId;
	
	public BACnetDevice(final PointManager pointManager, final int deviceId) {
		this.pointManager = pointManager;
		this.deviceId = deviceId;
	}
	public int getDeviceId () {
		return deviceId;
	}	
	public PointManager getPointManager () {
		return pointManager;
	}
}
