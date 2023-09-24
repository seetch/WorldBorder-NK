package me.seetch.worldborder;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.event.player.PlayerTeleportEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.ConfigSection;
import me.seetch.format.Format;
import me.seetch.worldborder.util.Border;

import java.util.List;

public class WorldBorder extends PluginBase implements Listener {

    private static Border worldBorder;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        ConfigSection border = this.getConfig().getSection("border");
        boolean borderEnabled = border.getBoolean("enabled", true);

        if (borderEnabled) {
            List<Integer> pos = border.getIntegerList("positions");
            worldBorder = new Border(pos.get(0), pos.get(1), pos.get(2), pos.get(3));
            this.getServer().getPluginManager().registerEvents(this, this);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (!worldBorder.isVectorInside(player)) {
            player.sendTip(Format.YELLOW.colorize("\uE113", "Вы достигли границы мира"));
            event.setCancelled();
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (!worldBorder.isVectorInside(event.getTo())) {
            event.getPlayer().sendTip(Format.YELLOW.colorize("\uE113", "Вы достигли границы мира"));
            event.setCancelled();
        }
    }
}
