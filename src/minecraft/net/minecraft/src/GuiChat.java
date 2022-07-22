package net.minecraft.src;

import org.lwjgl.input.Keyboard;

public class GuiChat extends GuiScreen {
	private String message = "";
	private int updateCounter = 0;
	public boolean old;

	public void initGui() {
		old = this.mc.shouldSpeed;
		this.mc.coptions.settings.get(2).value = "false";
		this.mc.coptions.saveOptions();
		Keyboard.enableRepeatEvents(true);
	}

	public void onGuiClosed() {
		this.mc.shouldSpeed = old;
		this.mc.coptions.settings.get(2).value = String.valueOf(old);
		this.mc.coptions.saveOptions();
		Keyboard.enableRepeatEvents(false);
	}

	public void updateScreen() {
		++this.updateCounter;
	}
	
	public void giveItem(ItemStack item) {
		if (this.mc.thePlayer.inventory.addItemStackToInventory(item)) {
			System.out.println("success");
		}
	}

	protected void keyTyped(char var1, int var2) {
		if(var2 == 1) {
			this.mc.displayGuiScreen((GuiScreen)null);
		} else if(var2 == 28) {
			String var3 = this.message.trim();
			if(var3.length() > 0 && !var3.startsWith(".")) {
				this.mc.thePlayer.sendChatMessage(this.message.trim());
			}
			this.mc.displayGuiScreen((GuiScreen)null);
			if (var3.startsWith(".")) {
				if (var3.startsWith(".kit give ")) {
					if (var3.equalsIgnoreCase(".kit give obsidian")) {
						giveItem(new ItemStack(Item.obsidianArmor0, 1));
						giveItem(new ItemStack(Item.obsidianArmor1, 1));
						giveItem(new ItemStack(Item.obsidianArmor2, 1));
						giveItem(new ItemStack(Item.obsidianArmor3, 1));
						giveItem(new ItemStack(Item.obsidianPick, 1));
						giveItem(new ItemStack(Item.obsidianAxe, 1));
						giveItem(new ItemStack(Item.obsidianShovel, 1));
						giveItem(new ItemStack(Item.obsidianHoe, 1));
						giveItem(new ItemStack(Item.obsidianSword, 1));
						giveItem(new ItemStack(Item.obsidianIngot, 64));
						giveItem(new ItemStack(Block.obsidian, 64));
					}
					else if (var3.equalsIgnoreCase(".kit give grief")) {
						giveItem(new ItemStack(Item.bucketLava, 1));
						giveItem(new ItemStack(Block.tnt, 64));
					}
					else {
						this.mc.ingameGUI.addChatMessage("§cKit " + var3.substring(".kit give ".length()) + " doesn't exist! Run the .kits command for a full list.");
					}
				}
				if (var3.startsWith(".say ")) {
					this.mc.thePlayer.sendChatMessage(var3.substring(".say ".length()));
				}
				/*if (var3.startsWith(".spoofname ")) {
					this.mc.thePlayer.username = var3.substring(".spoofname ".length());
				}*/
				if (var3.equalsIgnoreCase(".kits")) {
					this.mc.ingameGUI.addChatMessage("§eKits: §robsidian, grief");
				}
				if (var3.equalsIgnoreCase(".help")) {
					this.mc.ingameGUI.addChatMessage("§esex kaxz client commands:");
					this.mc.ingameGUI.addChatMessage(".help - shows this message");
					this.mc.ingameGUI.addChatMessage(".kit give [name] - gives a kit");
					this.mc.ingameGUI.addChatMessage(".kits - lists all the kits");
					this.mc.ingameGUI.addChatMessage(".say [message] - says a message");
					this.mc.ingameGUI.addChatMessage(".players - shows all the players in a world");
				}
				if (var3.equalsIgnoreCase(".players")) {
					String plrVal = "Players (" + this.mc.theWorld.playerEntities.size() + "): ";
					int x = 0;
					for (Object oplr : this.mc.theWorld.playerEntities) {
						EntityPlayer plr = (EntityPlayer)(oplr);
						if (x == this.mc.theWorld.playerEntities.size()-1) {
							plrVal += plr.username;
						}else {
							plrVal += plr.username + ", ";
						}
						x++;
					}
					this.mc.ingameGUI.addChatMessage(plrVal);
				}
			}
		} else {
			if(var2 == 14 && this.message.length() > 0) {
				this.message = this.message.substring(0, this.message.length() - 1);
			}

			if(" !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_\'abcdefghijklmnopqrstuvwxyz{|}~\u2302\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb".indexOf(var1) >= 0 && this.message.length() < 100) {
				this.message = this.message + var1;
			}

		}
	}

	public void drawScreen(int var1, int var2, float var3) {
		this.drawRect(2, this.height - 14, this.width - 2, this.height - 2, Integer.MIN_VALUE);
		if (this.message.startsWith(".")) {
            this.drawRect(1, this.height - 14, 2, this.height - 1, this.mc.rainbowColor);
            this.drawRect(this.width - 2, this.height - 14, this.width - 1, this.height - 1, this.mc.rainbowColor);
            this.drawRect(2, this.height - 14, this.width - 2, this.height - 13, this.mc.rainbowColor);
            this.drawRect(2, this.height - 2, this.width - 2, this.height - 1, this.mc.rainbowColor);
        }
		this.drawString(this.fontRenderer, "> ", 4, this.height - 12, this.mc.rainbowColor);
		this.drawString(this.fontRenderer, "  " + this.message + (this.updateCounter / 6 % 2 == 0?"_":""), 4, this.height - 12, 14737632);
	}

	protected void mouseClicked(int var1, int var2, int var3) {
		if(var3 == 0 && this.mc.ingameGUI.testMessage != null) {
			if(this.message.length() > 0 && !this.message.endsWith(" ")) {
				this.message = this.message + " ";
			}

			this.message = this.message + this.mc.ingameGUI.testMessage;
			byte var4 = 100;
			if(this.message.length() > var4) {
				this.message = this.message.substring(0, var4);
			}
		}

	}
}
