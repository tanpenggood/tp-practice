package com.aden;

import com.aden.biz.impl.GitBeiJingImpl;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.nio.file.Paths;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-18 13:53
 * @version: 1.0.0
 */
//public class Application extends javafx.application.Application {
public class Application {


    public static void main(String[] args) {
        // launch(args);
        new GitBeiJingImpl().template();
    }

    //@Override
    public void start(Stage primaryStage) throws Exception {
        //窗口的标题
        primaryStage.setTitle("My Application");

        primaryStage.show();
    }
}
