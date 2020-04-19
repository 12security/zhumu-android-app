package com.zipow.videobox;

interface ISIPService {
	void sendMessage(in byte[] message);
	boolean toggleSpeakerPhone(boolean on);
	boolean isSpeakerPhoneOn();
	void startPlayout();
	void stopPlayout();
}
