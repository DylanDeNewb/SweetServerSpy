package fun.sweetsmp.sweetserverspy.commands;

import fun.sweetsmp.sweetserverspy.SweetServerSpy;
import fun.sweetsmp.sweetserverspy.utils.ChatUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ServerSpyCommand extends Command {

    private SweetServerSpy core;
    public ServerSpyCommand(SweetServerSpy core) {
        super("serverspy", "sweetserverspy.use");
        this.core = core;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof ProxiedPlayer)){ return; }
        ProxiedPlayer player = (ProxiedPlayer) sender;

        if(core.getSpies().contains(player)){
            core.getSpies().remove(player);
            player.sendMessage(new TextComponent(ChatUtils.translate("&c&l( ! ) &8► &7ServerSpy toggled &c&noff")));
            return;
        }

        core.getSpies().add(player);
        player.sendMessage(new TextComponent(ChatUtils.translate("&a&l( ! ) &8► &7ServerSpy toggled &a&non")));
    }
}
