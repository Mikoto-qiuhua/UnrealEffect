package org.qiuhua.unrealeffect;

import org.bukkit.plugin.java.JavaPlugin;
import org.qiuhua.unrealeffect.listener.EntityEffectsListener;

public class Main extends JavaPlugin {
    private static Main mainPlugin;
    public static Main getMainPlugin(){
        return mainPlugin;
    }


    //启动时运行
    @Override
    public void onEnable(){
        mainPlugin = this;
        new EntityEffectsListener().register();
    }



    //关闭时运行
    @Override
    public void onDisable(){


    }
    //执行重载命令时运行
    @Override
    public void reloadConfig(){
    }


}