package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class FirstLineService extends Service<String> {
	private int delay = 0;

	public FirstLineService(String url, int delay) {
		super();
		this.delay = delay;
		this.setUrl(url);
	}

	private StringProperty url = new SimpleStringProperty();

	public final void setUrl(String value) {
		url.set(value);
	}

	public final String getUrl() {
		return url.get();
	}

	public final StringProperty urlProperty() {
		return url;
	}

	protected Task<String> createTask() {
		return new Task<String>() {
			@Override
			protected String call() throws IOException, MalformedURLException, InterruptedException {
				updateMessage("inicio: " + getUrl());
				BufferedReader in = new BufferedReader(new InputStreamReader(new URL(getUrl()).openStream()));
				Platform.runLater(new Runnable() {
                     @Override public void run() { setUrl("*" + getUrl()); }});
				Thread.sleep(delay);
				updateMessage("llega: " + getUrl());
				Platform.runLater(new Runnable() {
                    @Override public void run() { setUrl("*" + getUrl()); }});
				return in.readLine();
			}
		};
	}
}
