# PermissionSecurity
PermissionSecurity listens to console and player commands. Flagged commands will require an extra layer of validation.

[Download from Spigot](https://www.spigotmc.org/resources/permissionsecurity.20815/)

### What is it?
PermissionSecurity listens to player and console commands, looking for your configured "flagged commands". When a flagged command is detected, PermissionSecurity will check to see if the required security word is present at the end. If it's not, the command will be rejected.

### Who is this for?
This is for servers that run many public plugins. With the increase of malicious plugins that execute commands on behalf of console, this attempts to protect you from damage from said plugins.

**It's important to note,** this is no substitute to being careful with what you install. Never install plugins from sources you don't trust.

### Installation
On first run, PermissionSecurity will guard against the following commands:
* pex
* user
* group
* op
* deop

... and it will generate a random string as the security phrase. If you wish to modify this, stop your server and visit the **config.yml** file.

### How should I type my commands?
It's very simple. For demonstration purposes, let's pretend that you set your security word to "pizza".

If you wanted to OP Jimmy, instead of typing...

#### /op Jimmy

... you would instead type ...

#### /op Jimmy pizza

This goes for all commands. Because we watch for commands before they're processed by plugins, the normal command functions shouldn't have any issues. PermSecurity will remove the extra space and your security word, so the command your typing will never know it was there.

If the command is missing the security word, or it was typed incorrectly, this message will be shown:
![Example](https://s3.amazonaws.com/f.cl.ly/items/351O07171I3d3I1i4411/Screen%20Shot%202016-03-28%20at%202.12.38%20AM.png?v=99c86ae7)

... or if it's run from console:
![Example](https://s3.amazonaws.com/f.cl.ly/items/182A0v023T0y052S0a37/Screen%20Shot%202016-03-28%20at%202.13.23%20AM.png?v=fc672854)

... but appending the security word will make it work as normal:
![Example](https://s3.amazonaws.com/f.cl.ly/items/1Z2h020f1Z0r3K2g2t0I/Screen%20Shot%202016-03-28%20at%202.27.15%20AM.png?v=d794141b)
