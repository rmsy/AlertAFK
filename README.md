# AlertAFK
### What is AlertAFK?

**AlertAFK allows players to designate themselves as AFK, so that other
players talking to them in chat are notified that they are not
available.**

* Players can designate a custom AFK message, which will be displayed to users mentioning them (and optionally broadcast to the server when they go AFK, if broadcastGlobally is enabled)
* Players are automatically set back to available when they return to the game - there’s no need to remember to run a command
* Several command aliases are available, should there be any incompatibilities with existing plugins.

AlertAFK was built as light-weight as possible, but this is still my
first relatively comprehensive plugin (code-wise; the user interaction
is, of course, extremely simple :)), so it may be inefficient in some
aspects. Please feel free to (suggest changes)[https://github.com/rmsy/AlertAFK/pulls] if you think something
could be better - in fact, I encourage you to. (What fun is open-source,
community-driven software if only one person is writing it?)

AlertAFK is entirely (open-source)[https://github.com/rmsy/AlertAFK], and is licensed under the GNU
General Public License version 3. Here’s what that means for you:

* Feel free to do **whatever you want** with AlertAFK
* Feel free to share AlertAFK with **whoever you want**
but:
* Don’t sell it.
* Don’t license anything using AlertAFK with a more restricting license.

**I believe in open software, and I believe in freedom of information.
Let’s use it responsibly and be an example to the world.**

### Usage

This is the usage for AlertAFK:
`/<command> [optional AFK message] That’s it.
These are the commands:

* /afk
* /a
* /away

### Configuration

There are currently only two values in AlertAFK’s configuration file.
There is **broadcastGlobally**, defaulting to false, which defines
whether or not to broadcast to the entire server when a user goes AFK or
becomes available again, and **defaultAfkMessage**, which is the default
AFK message to be provided, should the user not provide one. The
configuration file will be generated on first-run, you do not need to
create it manually.

**Note:** AlertAFK makes use of Hidendra’s (Griefcraft Metrics)[http://metrics.griefcraft.com/]. These
statistics are entirely anonymous, and do not affect performance in any
way. **These statistics help me to get an idea of who’s using AlertAFK
and how, but I respect your right to privacy - should you wish to
disable these usage statistics, please opt-out in the
PluginMetrics/config.yml file, generated on first-run.**
