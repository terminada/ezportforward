package terminada.ezportforward;

import com.dosse.upnp.UPnP;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ezportforward extends JavaPlugin {
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        config.addDefault("port", "auto");
        config.addDefault("tcp", true);
        config.addDefault("udp", true);
        config.options().copyDefaults(true);
        saveConfig();

        int port;
        if (config.getString("port").equalsIgnoreCase("auto")) {
            port = getServer().getPort();
        } else {
            port = config.getInt("port");
        }

        if (config.getBoolean("tcp")) {
            System.out.println("Attempting Minecraft TCP port forwarding...");
            if (UPnP.isUPnPAvailable()) { //is UPnP available?
                if (UPnP.isMappedTCP(port)) { //is the port already mapped?
                    System.out.println("UPnP port forwarding not enabled: port is already mapped!");
                } else if (UPnP.openPortTCP(port)) { //try to map port
                    System.out.println("Minecraft TCP port forwarding succeed!");
                } else {
                    System.out.println("UPnP port forwarding failed");
                }
            } else {
                System.out.println("UPnP is not available on your router!");
            }
        }

        if (config.getBoolean("udp")) {
            System.out.println("Attempting Minecraft UDP port forwarding...");
            if (UPnP.isUPnPAvailable()) { //is UPnP available?
                if (UPnP.isMappedTCP(port)) { //is the port already mapped?
                    System.out.println("UPnP port forwarding not enabled: port is already mapped!");
                } else if (UPnP.openPortTCP(port)) { //try to map port
                    System.out.println("Minecraft UDP port forwarding succeed!");
                } else {
                    System.out.println("UPnP port forwarding failed");
                }
            } else {
                System.out.println("UPnP is not available on your router!");
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {

    }
}
