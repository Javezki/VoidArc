package com.javezki.Attributes;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class Attributes {
    
    public final Component createAttribute(String atr, int modifier)
    {
        Component comp = Component.text(atr + " +" + modifier, TextColor.color(135,206,235))
        .decoration(TextDecoration.ITALIC, false);

        return comp;
    }

}
