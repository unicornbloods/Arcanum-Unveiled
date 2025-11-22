# Contribution Guidelines

## Non-negotiable guidelines

- This mod WILL depend on [Thaumcraft4](https://www.curseforge.com/minecraft/mc-mods/thaumcraft). Removing that
  dependency will make the project break copyright.
- The existing API must remain intact for addons to still work.
    - You may extend upon the API, just do not break what exists.
- Avoid adding anything directly from Thaumcraft 7 showcases.

## General guidelines

- New features and changes should be able to be disabled individually by the end user via the config.
- Upstream your changes. The biggest goal of this project is to reduce the fractured community, don't be the dick that
  fractures it more.
- Let's have the changes and rewrite feel more connected with the existing modding environment. What does that look
  like:
    - Reinventing the wheel makes it feel disconnected. Banners for example, feel disconnected as we have a clear banner
      standard with [EFR](https://github.com/Roadhog360/Et-Futurum-Requiem) so use and extend EFR banners is a goal
      there.
- Contributions should not include asset files from ANY Thaumcraft version.
- Write your own code. Do not include anything from Thaumcraft itself, avoid looking at more than the Thaumcraft file
  names at all in fact. We have someone documenting the classes for us no need to look.
- If you need a mixin written to do stuff with Thaumcrft 4, ask me to do it please to avoid looking at the Thaumcraft 4
  source.
