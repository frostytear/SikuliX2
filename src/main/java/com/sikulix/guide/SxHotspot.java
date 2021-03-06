/*
 * Copyright (c) 2010-2017, sikuli.org, sikulix.com - MIT license
 */

/**
 *
 */
package com.sikulix.guide;

import com.sikulix.api.Element;

import javax.swing.*;
import java.awt.*;

public class SxHotspot extends SxClickable {

  Font f = new Font("sansserif", Font.BOLD, 18);
  JLabel label;
  Guide guide;
  SxSpotlight spotlight;
  Visual text;
  JLabel symbol;
  SxCircle circle;

  public SxHotspot(Element region, Visual text, Guide g) {
    super(region);
    guide = g;
    spotlight = new SxSpotlight(region);
    spotlight.setShape(SxSpotlight.CIRCLE);

    Rectangle bounds = spotlight.getBounds();
    bounds.grow(10, 10);
    spotlight.setBounds(bounds);

    this.text = text;
    text.setLocationRelativeToComponent(this, Layout.RIGHT);
    //this.text.setLocationRelativeToRegion(new Region(bounds), Visual.RIGHT);

    // draw a question mark centered on the region
    Font f = new Font("sansserif", Font.BOLD, 18);
    symbol = new JLabel("?");
    symbol.setFont(f);
    Dimension size = symbol.getPreferredSize();
    symbol.setSize(size);
    symbol.setForeground(Color.white);
    symbol.setLocation(region.x + region.w / 2 - size.width / 2,
            region.y + region.h / 2 - size.height / 2);

    // draw a circle around the question mark
    Rectangle cc = new Rectangle(symbol.getBounds());
    cc.grow(7, 0);
    circle = new SxCircle(new Element(cc));
    circle.setForeground(Color.white);
    circle.setShadow(5, 2);

    g.content.add(symbol);
    g.addToFront(circle);

    g.addToFront(spotlight);
    g.addToFront(text);

    text.setVisible(false);
    spotlight.setVisible(false);
  }

  @Override
  public void globalMouseEntered() {
    circle.setVisible(false);
    symbol.setVisible(false);
    spotlight.setVisible(true);
    text.setVisible(true);
    guide.repaint();
  }

  @Override
  public void globalMouseExited() {
    circle.setVisible(true);
    symbol.setVisible(true);
    spotlight.setVisible(false);
    text.setVisible(false);
    guide.repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    if (mouseOver) {
      g2d.setColor(new Color(0, 0, 0, 0));
    } else {
      g2d.setColor(normalColor);
    }
  }
}
