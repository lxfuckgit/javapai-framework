package com.javapai.framework.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * StageManager场景管理类.<br>
 * 
 * @author pooja
 *
 */
public final class StageManager {
	/**
	 * 存放所有的Stage实例.<br>
	 */
	private Map<String, Stage> stageMap = new HashMap<>();
	
	/**
	 * 存放所有的Control实例.<br>
	 */
	private Map<String, Object> controlMap = new HashMap<>();

	/**
	 * 从容器中取出一个指定Stage。<br>
	 * 
	 * @param stageId
	 * @return
	 */
	public Stage getStage(String stageId) {
		return stageMap.get(stageId);
	}
	
	/**
	 * 向容器中增加一个Stage对象.<br>
	 * 
	 * @param stage
	 * 
	 * @deprecated 现情况是Stage对象中没有唯一标识stageId来纳入容器管理，单纯利用其stage.title又过于片面且存在覆盖风险；于此前提，决定过期此方法。<br>
	 * {@link 参见StageManager.setStage(String stageId,Stage stage);}
	 */
	public boolean setStage(Stage stage) {
		System.out.println("--------->method deprecated!please user setStage(String stageId, Stage stage)!");
		return false;
	};
	
	/**
	 * 将指定Stage放置到容器。<br>
	 * 如果当前容器中存在一个已有的tageId，则返回false,代表设置不成功，已免有覆盖风险。<br>
	 * 
	 * @param stageId
	 * @return
	 */
	public boolean setStage(String stageId, Stage stage) {
		if (null != getStage(stageId)) {
			return false;
		} else {
			stageMap.put(stageId, stage);
			return true;
		}
	}
	
	/**
	 * 将指定Stage移出(销毁)容器。<br>
	 * 
	 * @param stageId
	 * @return
	 */
	public boolean removeStage(String stageId) {
		stageMap.remove(stageId);
		return true;
	}
	
	/**
	 * 
	 * @param closeStageId
	 * @param showStageId
	 */
	public void switchStage(String closeStageId, String showStageId) {
		switchStage(stageMap.get(closeStageId), stageMap.get(showStageId));
	}
	
	/**
	 * 切换容器中两个已存在的Stage的显示状态。<br>
	 * 
	 * 对fromStage进行隐藏操作，对toStage进行显示操作。<br>
	 * 
	 * @param fromStage
	 * @param toStage
	 */
	public void switchStage(Stage fromStage, Stage toStage) {
		fromStage.close();//		close() == hide();
		toStage.show();
	}
	
	/**
	 * 指定当前Stage为主窗口.<br>
	 * 注意：默认stage标识为"RootStage".<br>
	 * 
	 * @param stage
	 */
	public void appointPrimaryStage(Stage stage) {
		appointPrimaryStage("RootStage", stage);
	}

	/**
	 * 指定当前Stage为主窗口.<br>
	 * 
	 * @param stageKey
	 * @param stage
	 */
	public void appointPrimaryStage(String stageKey, Stage stage) {
		setStage(stageKey, stage);
	}
	
	/**
	 * 
	 * @param stageId stage唯一标识.<br>
	 * @param stageLayout stage布局文件.<br>
	 * @param styles stage布局风格.<br>
	 * @return
	 */
	public Stage loadAndAddStage(String stageId, String stageLayout) {
		return loadAndAddStage(stageId, stageLayout, StageStyle.DECORATED);
//		Stage stage = loadStage(stageLayout);
//		this.setStage(stageId, stage);
//		return stage;
	}
	
	/**
	 * 
	 * @param stageId
	 * @param stageLayout
	 * @param styles
	 * @return
	 */
	public Stage loadAndAddStage(String stageId, String stageLayout, StageStyle... styles) {
		FXMLLoader loader = getFXMLLoader(stageLayout);
		System.out.println("---------->[" + stageId + ":" + stageLayout + "]加载完毕!");
		
		/* level 1 */
		Stage stage = new Stage();
		try {
			/* level 2 */
			Scene tempScene = new Scene(loader.load());
			stage.setScene(tempScene);

			/* level 3 */
			for (StageStyle style : styles) {
				stage.initStyle(style);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		/* step4 */
		this.setStage(stageId, stage);
		
		/* step5 */
		this.controlMap.put(stageId, loader.getController());
		
		/* step6 */
//		https://blog.csdn.net/u012487272/article/details/79324536
//		https://www.jianshu.com/p/e81281fbc62d
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				System.out.println("---------->[" + stageId + "]已关闭窗口!");
				removeStage(stageId);
				controlMap.remove(stageId);
				
//				stage.close();//注意!stage.close()=stage.hide(),舞台并没有销毁。
				
//				System.exit(0);//整个应用退出并关闭.
//				javafx.application.Platform.exit();//整个应用退出并关闭.
			}
		});
		
		return stage;
	}
	
	/**
	 * 根据条件生成Stage.<br>
	 * 
	 * @param stageLayout
	 *            stage布局文件.<br>
	 * @param styles
	 *            stage风格设定.<br>
	 * @return
	 */
	public Stage loadStage(String stageLayout, StageStyle... styles) {
		/* level 1 */
		Stage stage = new Stage();

		FXMLLoader loader = getFXMLLoader(stageLayout);
		try {
			/* level 2 */
			Scene tempScene = new Scene(loader.load());
			stage.setScene(tempScene);

			/* level 3 */
			for (StageStyle style : styles) {
				stage.initStyle(style);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return stage;
	}
	
	/**
	 * 读取项目resources目录下指定文件.<br>
	 * 
	 * @param layout
	 * @return
	 */
	public FXMLLoader getFXMLLoader(String layout) {
		URL url = Thread.currentThread().getContextClassLoader().getResource(layout);
		return new FXMLLoader(url);
	}
	
	/**
	 * 
	 * @param layout
	 * @return
	 */
	public <T> T getFXMLLayout(String layout) {
		try {
			return getFXMLLoader(layout).load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param stageId
	 * @return
	 */
	public Object getController(String stageId) {
		return this.controlMap.get(stageId);
	}
}
