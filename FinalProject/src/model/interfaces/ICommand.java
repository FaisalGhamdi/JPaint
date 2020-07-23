package model.interfaces;

import model.ShapeShadingType;

public interface ICommand {
	void run(int x, int y, int pointX, int pointY, IApplicationState appState);
}
