package me.mr9te.basiccommands.data;

import org.bukkit.entity.Player;

public class TPAData {

    private Player source;
    private Player target;
    private TypeOfTP type;

    public TPAData(Player source, Player target, TypeOfTP type) {
        this.source = source;
        this.target = target;
        this.type = type;
    }

    public Player getSource() {
        return source;
    }

    public void setSource(Player source) {
        this.source = source;
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public TypeOfTP getType() {
        return type;
    }

    public void setType(TypeOfTP type) {
        this.type = type;
    }
}
