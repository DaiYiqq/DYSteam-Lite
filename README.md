# DYSteam Lite

<p align="center">
  <strong>把 SteamOS 体验带到你的 Android 手持设备。</strong><br>
  在 Adreno（高通骁龙）设备上运行 Valve 的 Steam 客户端（Big Picture 大屏模式），
  并通过 Valve 的 ARM64 Proton 运行 Windows 游戏。
</p>

<p align="center">
  <!-- 徽章占位：请替换为实际的构建状态、下载、Discord 等徽章 -->
  <img src="https://img.shields.io/badge/license-GPL--3.0-blue.svg" alt="License: GPL-3.0">
  <img src="https://img.shields.io/badge/platform-Android%209%2B-green.svg" alt="Platform: Android 9+">
  <img src="https://img.shields.io/badge/GPU-Adreno%20730%2B%20%7C%208xx-orange.svg" alt="GPU: Adreno 730+ / 8xx">
</p>

<p align="center"><em>Bring the SteamOS experience to Android: Valve's Steam client in Big Picture on your Adreno handheld, with Windows games through Valve's ARM64 Proton.</em></p>

> **说明 / Disclaimer**：本项目是基于开源项目 [DroidDeck](https://github.com/Droid-Deck/DroidDeck)（GPL-3.0）的**复刻与增强版**，由二次开发者 **戴亿（Dai Yi）和邓宇（DengYu）** 重新整理、汉化并升级。为兼容 15+ 个原生 JNI 符号，应用内部包名 / 命名空间仍保留为 `com.droiddeck.launcher`，这是**刻意为之**，并非遗漏。软件对外显示名称统一为 **DYSteam Lite**。

---

## 关于本项目 / About

**DYSteam Lite** 是一款在 Android 设备上运行的「SteamOS 体验」应用。它把完整的 Steam 客户端（Big Picture 大屏模式）搬到你的高通骁龙（Adreno）手持设备上，让你：

- 用 **Valve 官方 ARM64 Linux 版 Steam 客户端** 登录账号、逛商店、管理游戏库；
- 通过 **Valve 的 ARM64 Proton** 兼容层运行 **Windows 游戏**；
- 使用 **桌面模式**、**模拟器** 与多商店（Steam / GOG / Epic / Amazon / itch.io / Xbox）；
- 在 **智能眼镜 / XR** 上获得 3DoF / 6DoF 的虚拟大屏与 SteamVR 体验；
- 用手柄、键鼠、触控乃至**裸手手势 / 手部追踪**进行操控。

本项目是对原项目 DroidDeck 的复刻、二次开发与升级：在保持底层技术栈完整的前提下，将默认语言改为**简体中文**，扩展多语言支持，并补充署名与中文文档。

> 本项目默认语言为**简体中文**（`res/values/`），英文等其余语言已移入各自的 `values-*` 目录。支持 **33 种语言**，详见 [多语言说明](docs/LANGUAGES.md)。

---

## 致谢 / 鸣谢

**DYSteam Lite** 是站在巨人肩膀上的作品。它的一切基础都来自开源社区的努力，在此致以最诚挚的感谢。

- 感谢原项目 **[DroidDeck](https://github.com/Droid-Deck/DroidDeck)** 及整个 **Droid-Deck 团队**，他们开发并维护了这套完整的 Android 版 SteamOS 运行方案（GPL-3.0）。
- 感谢 **maxjivi05**（WinNative 与 Bannerlator 作者，运行时 / shim / 输入 / 手柄工作的基础）、**The412Banner**（DirectAudio、x86-64 客户端、夜间组件等多处贡献）、**phobos665**（Epic 与 GOG 商店支持所基于的 Legendary / gogdl 适配）等所有贡献者。
- 感谢为 DroidDeck 贡献代码、翻译、设备测试与 bug 报告的每一位社区成员（完整名单见 [docs/CREDITS.md](docs/CREDITS.md)）。

**二次开发者：戴亿（Dai Yi）** — 负责本项目的复刻、汉化、升级与社区共同维护。

- QQ 邮箱：`1703205607@qq.com`
- Gmail：`daiy91004@gmail.com`
- QQ：`1703205607`

> 完整致谢、第三方库清单与许可证说明见 [docs/CREDITS.md](docs/CREDITS.md)。

---

## 原理 / 工作原理

> 本节为概述；完整的技术架构、组件说明、启动流程与图形 / 音频 / 输入 / 存储栈见 [docs/PRINCIPLE.md](docs/PRINCIPLE.md)。

Android 默认不允许运行普通的桌面 Linux 程序，更无法直接运行 Steam 与 Windows 游戏。DYSteam Lite 通过一套精心编排的「用户态 Linux 运行时」绕过这些限制，其核心是：

1. **proot（用户态 chroot）**：在**无需 root** 的前提下，用 ptrace 系统调用翻译实现一个用户态根文件系统（Arch Linux），并补齐 Android 应用 seccomp 策略屏蔽的那些系统调用（`rseq`、`set_robust_list`、`faccessat2`、`openat2` 等）。它是整个会话的「容器」。
2. **box64 / FEX（x86-64 翻译）**：在 ARM64 设备上运行 x86-64 程序。FEX 作为 DLL 载入 Wine 进程内，把 x86-64 指令翻译为 ARM64，并通过 host thunks 把 Vulkan / GL 调用直通到 ARM64 的 Turnip / Mesa 驱动。
3. **Wine / Proton（兼容层）**：Windows 游戏通过 Valve 的 **ARM64 Proton**（Wine 的 arm64ec 构建）运行，把 Direct3D 调用转译为 Vulkan。
4. **DXVK / VKD3D-Proton（图形转译）**：DirectX 9/10/11 经 DXVK、DirectX 12 经 VKD3D-Proton 转译为 Vulkan，再由 **Turnip（Adreno 开源 Vulkan 驱动）** 执行。
5. **gamescope（合成器）**：SteamOS 同款微合成器，负责窗口合成、缩放、帧率限制与性能叠加层，使用两个 Xwayland 服务器分别承载 Steam 客户端与游戏。
6. **esync / fsync / ntsync（同步原语）**：多线程 Windows 游戏的同步机制加速，显著提升 CPU 密集场景的性能。
7. **PipeWire / DirectAudio（音频）**：Steam 客户端音频经 @The412Banner 的 DirectAudio PulseAudio 接收端输出；Steam 串流 / Remote Play 的视频画面经 PipeWire 采集并由手机硬件编码器编码。

简单地说：**DYSteam Lite = proot 用户态 Linux 运行时 + ARM64 Steam 客户端 + ARM64 Proton（内嵌 FEX）+ Turnip Vulkan 驱动 + gamescope 合成器**，共同把一台骁龙设备变成一台「掌上 Steam Deck」。

---

## 功能特性 / Features

- **Steam 大屏模式（Big Picture）**：完整的 Steam 客户端体验——登录、商店、库、社区、下载，全部在你的手持设备上原生呈现。
- **Windows 游戏（ARM64 Proton）**：通过 Valve 的 ARM64 Proton 运行 Windows 游戏，支持 DirectX 9–12（DXVK / VKD3D-Proton）。
- **桌面模式（Desktop）**：内置 KDE（KWin）桌面环境，可运行 Linux 桌面应用、浏览器与终端。
- **模拟器（Emulators）**：桌面与模拟器通过同一运行时安装（如 RetroArch、RPCS3 等）。
- **多商店支持**：Steam、GOG、Epic Games、Amazon Games、itch.io、Xbox（含 PC Game Pass），统一在游戏库中管理、下载、启动与云存档同步。
- **智能眼镜 / XR**：支持 VITURE、RayNeo、XREAL、Rokid 等智能眼镜的 3DoF 虚拟大屏，Luma Ultra 另提供实验性 6DoF 与双目辅助追踪；支持 SteamVR。
- **手柄 / 键鼠 / 触控**：虚拟 Steam Controller / Xbox 手柄模拟，支持实体手柄、键盘鼠标、可自定义的触控手势（单指 / 双指 / 三指拖拽、捏合缩放等）。
- **手部追踪**：基于 MediaPipe 的裸手手势（捏合、抓握等），可配合智能眼镜或摄像头使用。
- **帧生成（LSFG / DD-FG）**：可选帧生成（需自备 Lossless Scaling 副本），提升画面流畅度。
- **性能叠加层 / 帧时间图**：实时显示帧率、帧时间、GPU / 电池温度等。
- **开机动画（Boot movies）**：从 SteamDeckRepo 安装 Steam Deck 风格的开机动画。
- **多语言（33 种）**：默认简体中文，含中简 / 中繁 HK / 中繁 TW / 英 / 日 / 韩 / 德 / 法 / 西 / 俄 / 葡 / 意 / 阿 / 泰 / 越等。

> 以上功能的可用性与效果因设备而异；硬件验证范围有限。

---

## 系统要求与安装 / Requirements and Install

### 系统要求

- **系统**：Android 9 或更新版本。
- **GPU**：Adreno 730 或更新（或 8xx 系列）。Adreno 6xx 为**实验性**支持（DirectX 11 经 DXVK 2 可能可运行，DirectX 12 游戏仍可能崩溃）。
- **不支持**：Mali、Xclipse、PowerVR、Adreno 710。
- **Root**：无需 root。
- **存储**：运行时约 **3 GB**，桌面与模拟器额外约 **1.1 GB**。

### 安装步骤

1. **安装 APK**：下载并安装最新版 APK（构建产物位于 `app/build/outputs/apk/release/app-release.apk`，见 [从源码构建](docs/BUILDING.md)）。
2. **安装 Linux 运行时**：首次启动时按提示安装 Linux 运行时（约 3 GB）。
3. **登录 Steam**：运行时就绪后，点击 **Play** 启动 Steam 并登录账号。Steam 会在首次启动时自行下载。
4. **（Android 14+）关闭子进程限制**：在 **开发者选项** 中开启 **「停用子进程限制 / Disable child process restrictions」**，否则 Steam 无法启动。若 Android 12 / 13 设备上找不到该选项，Steam 首次启动时会提供 **「替我修复 / Fix it for me」** 按钮，帮助你自动完成设置。
5. **安装桌面（可选）**：如需使用桌面模式与模拟器，安装 **「桌面与应用 / Desktop & apps」**。

> 更详细的安装、使用与常见问题见 [docs/INSTALL.md](docs/INSTALL.md)。

---

## 多语言 / 默认语言

- **默认语言**：简体中文（`res/values/` 已设为简体中文，英文已移入 `values-en/`）。
- **支持语言**：33 种（中简、中繁 HK、中繁 TW、英、日、韩、德、法、西、俄、葡、意、阿、泰、越等）。
- **切换语言**：在应用内跟随系统语言，或于 **设置（Setup）** 中切换应用语言。
- **新增 / 修改语言**：见 [docs/LANGUAGES.md](docs/LANGUAGES.md)。

---

## 构建 / Build

> 完整构建说明见 [docs/BUILDING.md](docs/BUILDING.md)。

**准备依赖**：Docker、Java 17、Android SDK / NDK（`ndkVersion 27.3.13750724`）、Rust（rustup）、GitHub CLI、Python 3、`zstd`。

```bash
# 一键构建（Linux / macOS，经 Docker）
tools/build_local.sh
```

构建产物：`app/build/outputs/apk/release/app-release.apk`。

- **Windows**：运行 `tools/run_windows.ps1`（可选 `-Serial <adb-serial>`）。
- **单元测试**：`./gradlew -PskipRust=true :app:testDebugUnitTest`（无需准备原生库）。

### CI / 自动构建与发布

- **`ci.yml`**：每次 push 到 `main` 或提交 Pull Request 时，自动运行单元测试（无需原生库，用于快速校验）。
- **`release.yml`**：推送 `v*` 标签（如 `git tag v1.0.0 && git push origin v1.0.0`）或在 Actions 页面手动触发，执行完整原生构建，并自动创建 GitHub Release、附带 APK。
- **发布签名**：默认用 AOSP testkey 签名（仅侧载）；配置仓库 Secret 后自动改用自有密钥签名，详见 [docs/SIGNING.md](docs/SIGNING.md)。

---

## 二次开发 / 升级说明

本次复刻在保留 DroidDeck 完整技术栈的基础上，做了以下改动：

1. **改名 DYSteam Lite**：应用对外显示名称统一改为 **DYSteam Lite**。
2. **默认简体中文**：把 `res/values/` 设为简体中文，英文移入 `values-en/`。
3. **多语言**：保持并沿用 33 种语言支持。
4. **署名**：新增二次开发者 **戴亿（Dai Yi）** 的署名与联系方式（QQ 邮箱 / Gmail / QQ），并开启社区共同维护。
5. **中文文档**：补齐中文 README 与 `docs/` 下的技术文档（原理、安装、构建、多语言、致谢）。
6. **保留内部技术标识**：为兼容 15+ 个原生 JNI 符号，内部包名 / 命名空间仍保留为 `com.droiddeck.launcher`。

---

## 社区 / Community（共同维护）

本项目**欢迎社区共同维护**，向所有贡献者开放：

- **如何参与**：提交 Issue / Pull Request、翻译、设备测试报告、文档改进、打包与分发，详见 [CONTRIBUTING.md](CONTRIBUTING.md)。
- **联系方式**（二次开发者 戴亿）：
  - QQ 邮箱：`1703205607@qq.com`
  - Gmail：`daiy91004@gmail.com`
  - QQ：`1703205607`
- **开源协议**：本项目沿用 GPL-3.0，任何二次分发需同样开源。

---

## 许可证 / License

本项目沿用原项目的许可证：**GPL-3.0**（详见仓库根目录 [LICENSE](LICENSE)）。所有第三方库的许可证与致谢见 [docs/CREDITS.md](docs/CREDITS.md)。

> 本项目 100% 开源，不含广告、不含遥测。Steam、Proton 与游戏本体由 Valve 及各商店提供，不属于本项目。
