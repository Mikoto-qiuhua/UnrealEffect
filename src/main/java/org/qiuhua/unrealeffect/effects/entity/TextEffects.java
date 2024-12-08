package org.qiuhua.unrealeffect.effects.entity;

import com.daxton.unrealcore.application.UnrealCoreAPI;
import com.daxton.unrealcore.been.common.type.RenderingType;
import com.daxton.unrealcore.been.effects.entity.EntityEffectsImageBeen;
import com.daxton.unrealcore.been.effects.entity.EntityEffectsTextBeen;
import com.daxton.unrealcore.been.effects.remove.EntityEffectsRemoveBeen;
import com.daxton.unrealcore.been.world.been.LocationBeen;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.ITargetedEntitySkill;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillResult;
import io.lumine.mythic.api.skills.placeholders.PlaceholderInt;
import io.lumine.mythic.api.skills.placeholders.PlaceholderString;
import org.bukkit.entity.Entity;
import org.qiuhua.unrealeffect.Main;

import java.util.UUID;

public class TextEffects implements ITargetedEntitySkill {
    private final String type;
    private final String mark;
    private final PlaceholderString textPlaceholder;
    private final PlaceholderInt removePlaceholder;
    private final String renderingType;
    private final PlaceholderString pXPlaceholder;
    private final PlaceholderString pYPlaceholder;
    private final PlaceholderString pZPlaceholder;
    private final PlaceholderString rXPlaceholder;
    private final PlaceholderString rYPlaceholder;
    private final PlaceholderString rZPlaceholder;
    private final PlaceholderString sizePlaceholder;

    public TextEffects(MythicLineConfig mlc){
        //标签
        this.mark = mlc.getString("mark", "UnrealEffect");
        //操作类型
        this.type = mlc.getString("type", "clear");
        //文本
        this.textPlaceholder = mlc.getPlaceholderString("text", "");

        //移除时间
        this.removePlaceholder = mlc.getPlaceholderInteger("remove", 0);
        //渲染类型
        this.renderingType = mlc.getString("renderingType", "DEFAULT");

        //处理位置信息
        this.pXPlaceholder = mlc.getPlaceholderString("px", "0");
        this.pYPlaceholder = mlc.getPlaceholderString("py", "1");
        this.pZPlaceholder = mlc.getPlaceholderString("pz", "0");
        this.rXPlaceholder = mlc.getPlaceholderString("rx", "0");
        this.rYPlaceholder = mlc.getPlaceholderString("ry", "0");
        this.rZPlaceholder = mlc.getPlaceholderString("rz", "0");
        this.sizePlaceholder = mlc.getPlaceholderString("size", "1");
    }

    @Override
    public SkillResult castAtEntity(SkillMetadata skillMetadata, AbstractEntity abstractEntity) {
        Entity entity = abstractEntity.getBukkitEntity();
        UUID uuid = entity.getUniqueId();
        switch (this.type){
            case "clear":
                UnrealCoreAPI.inst().getEntityEffectsHelper().clearText(uuid.toString());
                break;
            case "remove":
                EntityEffectsRemoveBeen entityEffectsRemoveBeen = new EntityEffectsRemoveBeen(uuid.toString(), this.mark);
                UnrealCoreAPI.inst().getEntityEffectsHelper().removeText(entityEffectsRemoveBeen);
                break;
            case "add":
                int remove = this.removePlaceholder.get(skillMetadata, abstractEntity);
                String text = this.textPlaceholder.get(skillMetadata, abstractEntity);
                String px = this.pXPlaceholder.get(skillMetadata, abstractEntity);
                String py = this.pYPlaceholder.get(skillMetadata, abstractEntity);
                String pz = this.pZPlaceholder.get(skillMetadata, abstractEntity);
                String rx = this.rXPlaceholder.get(skillMetadata, abstractEntity);
                String ry = this.rYPlaceholder.get(skillMetadata, abstractEntity);
                String rz = this.rZPlaceholder.get(skillMetadata, abstractEntity);
                String size = this.sizePlaceholder.get(skillMetadata, abstractEntity);
                LocationBeen locationBeen = new LocationBeen(px,py,pz,rx,ry,rz,size);
                EntityEffectsTextBeen entityEffectsTextBeen = new EntityEffectsTextBeen(uuid.toString(), this.mark, text, "0.2", true, locationBeen);
                entityEffectsTextBeen.setTickRemove(remove);
                entityEffectsTextBeen.setRenderingType(RenderingType.valueOf(this.renderingType));
                UnrealCoreAPI.inst().getEntityEffectsHelper().addText(entityEffectsTextBeen);
                break;
        }
        return SkillResult.SUCCESS;
    }
}
