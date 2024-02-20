package fun.raccoon.bunyedit.command.action.actions;

import fun.raccoon.bunyedit.command.action.ISelectionAction;
import fun.raccoon.bunyedit.data.PlayerData;
import fun.raccoon.bunyedit.data.Selection;
import fun.raccoon.bunyedit.util.ChatString;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.net.command.CommandError;
import net.minecraft.core.net.command.CommandSender;

public class GetSelectionAction implements ISelectionAction {
    public boolean apply(
        I18n i18n, CommandSender sender, EntityPlayer player,
        PlayerData playerData, Selection selection, String[] argv
    ) {
        if (argv.length > 0)
            throw new CommandError(i18n.translateKey("bunyedit.cmd.err.toomanyargs"));

        sender.sendMessage(String.format("%s: %s (%s)",
            i18n.translateKey("bunyedit.selection"),
            ChatString.gen(playerData.selection)));

        return true;
    }
}