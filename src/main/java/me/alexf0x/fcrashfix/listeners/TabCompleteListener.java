package me.alexf0x.fcrashfix.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.alexf0x.fcrashfix.FCrashFix;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.alexf0x.fcrashfix.FCrashFix.colored;

public class TabCompleteListener extends PacketAdapter {

    private final FCrashFix plugin;

    public TabCompleteListener(FCrashFix plugin) {
        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Client.TAB_COMPLETE);
        this.plugin = plugin;
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        String request = packet.getStrings().read(0);

        Player player = event.getPlayer();

        if (!plugin.isRequestBad(request)) return;

        event.setCancelled(true);

        plugin.sendConsole("&fИгрок &6" + player.getName() + " &fпопытался крашнуть сервер");

        Bukkit.getScheduler().runTask(plugin, () -> {
            player.kick(Component.text(colored(plugin.getConfig().getString("kick-message"))));
        });
    }
}
