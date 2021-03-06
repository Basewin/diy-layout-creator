package org.diylc.swing.gui.editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import org.diylc.common.PropertyWrapper;
import org.diylc.swingframework.FontChooserComboBox;
import org.diylc.utils.Constants;

public class FontEditor extends FontChooserComboBox {

  private static final long serialVersionUID = 1L;

  private Color oldBg = getBackground();

  private final PropertyWrapper property;

  public FontEditor(final PropertyWrapper property) {
    this.property = property;
    ((JTextField) getEditor().getEditorComponent()).setBorder(BorderFactory.createCompoundBorder(
        ((JTextField) getEditor().getEditorComponent()).getBorder(), BorderFactory.createEmptyBorder(0, 2, 0, 0)));
    setSelectedItem(((Font) property.getValue()).getName());
    addItemListener(new ItemListener() {

      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          property.setChanged(true);
          setBackground(oldBg);
          Font oldFont = (Font) FontEditor.this.property.getValue();
          Font newFont = new Font(getSelectedItem().toString(), oldFont.getStyle(), oldFont.getSize());
          FontEditor.this.property.setValue(newFont);
        }
      }
    });
    if (!property.isUnique()) {
      setBackground(Constants.MULTI_VALUE_COLOR);
    }
  }
}
