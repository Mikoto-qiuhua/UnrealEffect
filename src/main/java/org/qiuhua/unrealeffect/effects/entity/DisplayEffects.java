package org.qiuhua.unrealeffect.effects.entity;

import com.daxton.unrealcore.application.UnrealCoreAPI;
import com.daxton.unrealcore.been.entity.base.EntityDisplayBeen;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.ITargetedEntitySkill;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillResult;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class DisplayEffects implements ITargetedEntitySkill {

    private final Boolean isDisplay;
    public DisplayEffects(MythicLineConfig mlc){
        this.isDisplay = mlc.getBoolean("isDisplay");
    }

    @Override
    public SkillResult castAtEntity(SkillMetadata skillMetadata, AbstractEntity abstractEntity) {
        Entity entity = abstractEntity.getBukkitEntity();
        UUID uuid = entity.getUniqueId();

        EntityDisplayBeen entityDisplayBeen = new EntityDisplayBeen(uuid.toString(), this.isDisplay);
        UnrealCoreAPI.inst().getEntityHelper().setEntityDisplay(entityDisplayBeen);
        return null;
    }
}
