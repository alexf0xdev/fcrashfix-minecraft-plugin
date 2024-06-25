package me.alexf0x.fcrashfix;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.alexf0x.fcrashfix.listeners.TabCompleteListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class FCrashFix extends JavaPlugin {

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("ProtocolLib") == null) {
            sendConsole("&cProtocolLib не найден. Плагин отключается...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        saveDefaultConfig();

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();

        manager.addPacketListener(new TabCompleteListener(this));

        sendConsole("&aПлагин успешно включен");
        sendConsole("&7Developed by github.com/alexf0xdev");
    }

    public void sendConsole(String message) {
        getServer().getConsoleSender().sendMessage(colored("[" + getName() + "] " + message));
    }

    public boolean isRequestBad(String request) {
        return request.contains("@") && request.contains("[");
    }

    public static String colored(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
