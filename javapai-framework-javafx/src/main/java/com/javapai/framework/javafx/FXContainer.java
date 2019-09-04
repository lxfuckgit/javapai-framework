package com.javapai.framework.javafx;

import javafx.application.Platform;

/**
 * 容器实例.<br>
 * 
 * @author pooja
 *
 */
public enum FXContainer {
	INSTANCE;
	
	private StageManager stageHome = new StageManager();

	public StageManager getStageManager() {
		return stageHome;
	}
	
	/**
	 * 
	 * @param task
	 * 
	 * @deprecated 原因：建议用户直接 Planform.runLater(()->{ you code});我觉得没必要这样封一层。
	 */
	public void runTaskInFxThread(Runnable task){
		Platform.runLater(task);
	}

}
