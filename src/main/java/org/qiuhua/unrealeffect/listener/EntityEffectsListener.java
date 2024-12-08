package org.qiuhua.unrealeffect.listener;

import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.qiuhua.unrealeffect.Main;
import org.qiuhua.unrealeffect.effects.entity.BBModelEffects;
import org.qiuhua.unrealeffect.effects.entity.DisplayEffects;
import org.qiuhua.unrealeffect.effects.entity.ImgEffects;
import org.qiuhua.unrealeffect.effects.entity.TextEffects;

public class EntityEffectsListener implements Listener {
    public void register(){
        Bukkit.getPluginManager().registerEvents(this, Main.getMainPlugin());
    }

    @EventHandler
    public void onMythicMechanicLoad(MythicMechanicLoadEvent e){
        if(e.getMechanicName().equalsIgnoreCase("ucbbe")){
            e.register(new BBModelEffects(e.getConfig()));
        }
        if(e.getMechanicName().equalsIgnoreCase("ucimge")){
            e.register(new ImgEffects(e.getConfig()));
        }
        if(e.getMechanicName().equalsIgnoreCase("uctexte")){
            e.register(new TextEffects(e.getConfig()));
        }
        if(e.getMechanicName().equalsIgnoreCase("ucdisplay")){
            e.register(new DisplayEffects(e.getConfig()));
        }
    }



}
