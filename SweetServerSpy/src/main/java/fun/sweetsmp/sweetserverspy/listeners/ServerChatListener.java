package fun.sweetsmp.sweetserverspy.listeners;

import fun.sweetsmp.sweetserverspy.SweetServerSpy;
import fun.sweetsmp.sweetserverspy.utils.ChatUtils;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerChatListener implements Listener {

    private SweetServerSpy core;
    private String replaceString;
    public ServerChatListener(SweetServerSpy core){
        this.core = core;
        this.replaceString = core.getConfig().getString("format");
    }

    @EventHandler
    public void onChat(ChatEvent event){
        if(!(event.getSender() instanceof ProxiedPlayer)){
            return;
        }

        final ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        final Server server = player.getServer();

        String format = replace(replaceString, player, server, event.getMessage());

        for(ProxiedPlayer pl : core.getProxy().getPlayers()){
            if(!core.getSpies().contains(pl)) { return; }

            pl.sendMessage(new TextComponent(format));
        }
    }

    private String replace(String format, ProxiedPlayer player, Server server, String message){
        format = format.replace("%server_name%", server.getInfo().getName());
        format = format.replace("%user%", player.getDisplayName());
        format = format.replace("%message%", message);
        format = ChatUtils.translate(format);

        return format;
    }

}
