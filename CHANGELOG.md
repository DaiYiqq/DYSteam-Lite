# 更新日志 / Changelog

本文件记录 **DYSteam Lite** 的版本变更。DYSteam Lite 是基于 [DroidDeck](https://github.com/Droid-Deck/DroidDeck)（GPL-3.0）的复刻与增强版；上游 DroidDeck 的版本历史见 [docs/releases/](docs/releases/)。

## 1.0.0（DYSteam Lite 首发）

> 基于 DroidDeck `0.6.0`（commit `476642b`）复刻，作为 DYSteam Lite 的独立起点重新编号。

### 改名与品牌

- 软件显示名称统一改为 **DYSteam Lite**（应用启动器名称、全 33 种语言的用户可见文案，共 2109 处）。
- 内部包名 / 命名空间 `com.droiddeck.launcher`、原生 JNI 符号、`droiddeck-*` 脚本与 `DroidDeck-Proton` 等技术标识**刻意保留**，以保证 15+ 个原生 JNI 符号可编译、运行时兼容。

### 本地化 / 多语言

- 默认语言改为**简体中文**：`res/values/` 设为简体中文，英文移入 `values-en/`。
- 补齐原中文翻译缺失的 **65 条**字符串，中英文资源现均为 2927 条、完全对齐。
- 保留 33 种语言（中简 / 中繁 HK / 中繁 TW / 英 / 日 / 韩 / 德 / 法 / 西 / 俄 / 葡 / 意 / 阿 / 泰 / 越等）。

### 新功能

- 新增应用内「关于」对话框（更新页 ⓘ 图标）：显示版本、二次开发者、联系方式、致谢与许可证。

### 署名与社区

- 二次开发者：**戴亿（Dai Yi）**。
- 联系方式：QQ 邮箱 `1703205607@qq.com`、Gmail `daiy91004@gmail.com`、QQ `1703205607`。
- 感谢原项目 DroidDeck 团队与 maxjivi05、The412Banner、phobos665 等所有贡献者（详见 [docs/CREDITS.md](docs/CREDITS.md)）。
- 新增 `CONTRIBUTING.md`，开启**社区共同维护**。

### 文档

- 重写中英文 `README.md`（原理、功能、安装使用、构建、多语言、升级说明）。
- 新增 `docs/PRINCIPLE.md`（原理）、`docs/INSTALL.md`（安装使用 / FAQ）、`docs/BUILDING.md`（构建）、`docs/LANGUAGES.md`（多语言）、`docs/CREDITS.md`（致谢）。

### 版本与许可

- 版本号独立重编为 `1.0.0`（`versionCode 1`）。
- 许可证沿用 **GPL-3.0**。

---

## 版本命名约定

- 后续以 DYSteam Lite 自身的 `x.y.z` 版本号发布；对上游 DroidDeck 的合并会在每条版本说明中注明来源 commit。
