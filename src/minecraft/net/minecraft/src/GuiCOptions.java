package net.minecraft.src;

public class GuiCOptions extends GuiScreen {
	private GuiScreen parentScreen;
	protected String screenTitle = "Client settings";
	private ClientSettings options;

	public GuiCOptions(GuiScreen var1, ClientSettings var2) {
		this.parentScreen = var1;
		this.options = var2;
	}

	public void initGui() {
		for(int var1 = 0; var1 < this.options.settings.size(); ++var1) {
			this.controlList.add(new GuiSmallButton(var1, this.width / 2 - 155 + var1 % 2 * 160, this.height / 6 + 24 * (var1 >> 1), this.options.settings.get(var1).name + ": " + this.options.settings.get(var1).value));
		}
		this.controlList.add(new GuiButton(100, this.width / 2 - 100, this.height / 6 + 168, "Done"));
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.enabled) {
			if(var1.id < 100) {
				int id = var1.id;
				ClientSetting setting = this.options.settings.get(id);
				switch (id) {
					case 0:
						String _ = String.valueOf(!Boolean.parseBoolean(String.valueOf(setting.value)));
						setting.value = _;
						this.options.saveOptions();
						var1.displayString = setting.name + ": " + setting.value;
						break;
					case 1:
						String _1 = String.valueOf(!Boolean.parseBoolean(String.valueOf(setting.value)));
						setting.value = _1;
						this.options.saveOptions();
						var1.displayString = setting.name + ": " + setting.value;
						break;
					case 2:
						String _2 = String.valueOf(!Boolean.parseBoolean(String.valueOf(setting.value)));
						setting.value = _2;
						this.options.saveOptions();
						var1.displayString = setting.name + ": " + setting.value;
						break;
					case 3:
						String _3 = String.valueOf(!Boolean.parseBoolean(String.valueOf(setting.value)));
						setting.value = _3;
						this.options.saveOptions();
						var1.displayString = setting.name + ": " + setting.value;
						break;
					case 4:
						String _4 = String.valueOf(!Boolean.parseBoolean(String.valueOf(setting.value)));
						setting.value = _4;
						this.options.saveOptions();
						var1.displayString = setting.name + ": " + setting.value;
						break;
					case 5:
						String _5 = String.valueOf(!Boolean.parseBoolean(String.valueOf(setting.value)));
						setting.value = _5;
						this.options.saveOptions();
						var1.displayString = setting.name + ": " + setting.value;
						break;
					case 6:
						String _6 = String.valueOf(!Boolean.parseBoolean(String.valueOf(setting.value)));
						setting.value = _6;
						this.options.saveOptions();
						var1.displayString = setting.name + ": " + setting.value;
						break;
					case 7:
						String _7 = String.valueOf(!Boolean.parseBoolean(String.valueOf(setting.value)));
						setting.value = _7;
						this.options.saveOptions();
						var1.displayString = setting.name + ": " + setting.value;
						break;
				}
			}

			if(var1.id == 100) {
				this.mc.displayGuiScreen(this.parentScreen);
			}

		}
	}
	
	protected void keyTyped(char var1, int var2) {
		if(var2 == 1) {
			for (Object lol : this.controlList) {
				GuiButton l = (GuiButton)(lol);
				if (l.id == 100) {
					this.actionPerformed(l);
				}
			}
		}
	}

	public void drawScreen(int var1, int var2, float var3) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 20, 0xFFFFFF);
		super.drawScreen(var1, var2, var3);
	}
}
