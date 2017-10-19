package ControlPanel;

import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;

public class ControlPanelTooltip extends Tooltip {
	public ControlPanelTooltip(String title)
	{
		setText(title);
		setFont(new Font(24));
	}
}
