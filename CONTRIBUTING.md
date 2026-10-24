# 参与贡献 / Contributing

感谢你愿意参与 **DYSteam Lite** 的社区共同维护！本项目欢迎任何形式的贡献。DYSteam Lite 是基于 [DroidDeck](https://github.com/Droid-Deck/DroidDeck)（GPL-3.0）的复刻与增强版，你的每一份贡献都会让更多人能在手机上畅玩 Steam 游戏。

> *Thank you for helping maintain DYSteam Lite! This project welcomes all contributions. DYSteam Lite is a fork of [DroidDeck](https://github.com/Droid-Deck/DroidDeck) (GPL-3.0).*

---

## 你可以如何参与 / Ways to Contribute

- **报告问题（Bug Report）**：在 Issues 中描述问题，附上设备型号、Android 版本、复现步骤，以及「分享日志 / Share logs」打包的日志压缩包。
- **提交代码（Pull Request）**：修复 bug、优化性能、新增功能。
- **翻译（Translation）**：完善 33 种语言的翻译，或新增语言（见 [docs/LANGUAGES.md](docs/LANGUAGES.md)）。
- **设备测试（Device Testing）**：在更多 Adreno 设备上测试兼容性并反馈结果。
- **文档（Documentation）**：改进 README、`docs/` 下的文档与翻译。
- **打包与分发（Packaging）**：帮助构建各平台安装包（见 [docs/BUILDING.md](docs/BUILDING.md)）。

---

## 提交规范 / Submission Guidelines

1. **先沟通**：大改动请先开 Issue 讨论，避免重复劳动。
2. **一个 PR 做一件事**：保持改动聚焦、可审阅。
3. **保持风格一致**：遵循现有代码风格；注释应简洁、只解释非显而易见的约束。
4. **不要改动内部技术标识**：包名 `com.droiddeck.launcher`、原生 JNI 符号、`droiddeck-*` 脚本、`DroidDeck-Proton` 等为兼容性保留项，请勿重命名。
5. **测试**：逻辑改动请补充/更新单元测试，并运行 `./gradlew -PskipRust=true :app:testDebugUnitTest`。
6. **签名**：请用 `Developer Certificate of Origin`（DCO）风格的 `Signed-off-by` 提交。

---

## 本地开发 / Local Development

构建依赖与步骤见 [docs/BUILDING.md](docs/BUILDING.md)：

```bash
# 单元测试（无需准备原生库）
./gradlew -PskipRust=true :app:testDebugUnitTest

# 完整构建（需 Docker + Android SDK/NDK + Rust）
tools/build_local.sh
```

---

## 语言与文档 / Language & Docs

- 默认语言为**简体中文**，英文及多语言资源见 `app/src/main/res/`。
- 新增/修改语言请参考 [docs/LANGUAGES.md](docs/LANGUAGES.md)。
- 文档以中文为主、关键小节附英文。

---

## 联系方式 / Contact

二次开发者：**戴亿（Dai Yi）**

- QQ 邮箱：`1703205607@qq.com`
- Gmail：`daiy91004@gmail.com`
- QQ：`1703205607`

---

## 许可证 / License

本项目沿用 **GPL-3.0**。你提交的贡献默认以相同许可证发布；任何二次分发同样必须开源。第三方库的许可证与致谢见 [docs/CREDITS.md](docs/CREDITS.md)。
