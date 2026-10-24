# 致谢与第三方库 / Credits & Third-Party Libraries

本文档完整记录 **DYSteam Lite** 的原项目、二次开发者与所依赖的第三方库 / 代码来源，并附许可证说明。DYSteam Lite 是原项目 [DroidDeck](https://github.com/Droid-Deck/DroidDeck) 的复刻与增强版，本致谢同样适用于 DYSteam Lite。

---

## 原项目 DroidDeck

- **项目**：[DroidDeck](https://github.com/Droid-Deck/DroidDeck)
- **团队**：Droid-Deck 团队
- **许可证**：GPL-3.0
- **描述**：把 SteamOS 体验带到 Android——在 Adreno 手持设备上运行 Valve 的 Steam 客户端（Big Picture），并通过 Valve 的 ARM64 Proton 运行 Windows 游戏。

DYSteam Lite 复刻并升级了该项目。原项目的完整贡献者名单与版本记录请见原仓库与 [docs/releases/](../releases/) 下的发布说明。

## 二次开发者

- **姓名**：戴亿（Dai Yi）
- **职责**：本项目的复刻、汉化与升级，以及社区共同维护。
- **QQ 邮箱**：`1703205607@qq.com`
- **Gmail**：`daiy91004@gmail.com`
- **QQ**：`1703205607`

---

## 核心作者与贡献者

以下为核心作者（详见原 README 的 Credits 段与 [docs/releases/0.6.0.md](../releases/0.6.0.md) 的完整贡献者名单）：

| 作者 / 贡献者 | 贡献 |
| --- | --- |
| **maxjivi05** | WinNative 与 Bannerlator 作者；运行时、shim、输入、手柄控制、性能优化、LSFG 移植等大量底层工作 |
| **The412Banner** | DirectAudio 音频接收端、x86-64 Steam 客户端、夜间 DXVK/VKD3D-Proton/FEX 组件、SD 卡游戏库等 |
| **phobos665** | 基于 [Legendary](https://github.com/legendary-gl/legendary) 与 [gogdl](https://github.com/Heroic-Games-Launcher/heroic-gogdl) 的 Epic Games / GOG 支持 |
| **xXJSONDeruloXx** | Xbox / Game Pass、商店改版、Remote Play、SteamVR 手柄类型、Rokid 眼镜、挂起与 zRAM、Decky、KWin 6.8 等 |
| **ovurrsl** | 文件管理器、手柄与触控修复、安全、无障碍、土耳其语翻译、云存档、设备冒烟测试 |
| **kingokksa** | itch.io 商店、游戏卸载、商店标签顺序、GOG 加速、Steam 触控布局 |
| 及其他大量社区贡献者 | 见 [docs/releases/0.6.0.md](../releases/0.6.0.md) 的「Community / New contributors」完整名单 |

---

## 第三方库与代码来源

### 运行时与兼容层

- **[WinNative](https://github.com/maxjivi05/WinNative)** 与 **[Bannerlator](https://github.com/maxjivi05)**（maxjivi05）——运行时、shim、输入与手柄控制工作的基础。
- **[Legendary](https://github.com/legendary-gl/legendary)** 与 **[gogdl](https://github.com/Heroic-Games-Launcher/heroic-gogdl)**——Epic Games 与 GOG 商店支持（经 phobos665 适配）。
- **[Heroic Games Launcher](https://github.com/Heroic-Games-Launcher/HeroicGamesLauncher)**（GPL-3.0）——GOG 游戏安装脚本（`goggame-<id>.script`）注册表项的读取方式。
- **[Steam Linux Runtime 4.0](https://gitlab.steamos.cloud/steamrt/steamrt)**——x86 程序在 FEX 下链接 OpenSSL 3 时使用的副本。
- **[deadlock-api-ingest](https://github.com/deadlock-api/deadlock-api-ingest)**——读取 Steam 保存令牌时使用的 `ConnectCache` 格式。

### 图形与帧生成

- **[Eden](https://eden-emu.dev)** 模拟器项目与 **Camille LaVey** 的工作、**[lsfg-vk](https://github.com/PancakeTAS/lsfg-vk)**——LSFG 帧生成（由 maxjivi05 移植到 WinNative 与 DroidDeck）。使用该功能需自备 [Lossless Scaling](https://store.steampowered.com/app/993090/) 副本，本项目不附带其着色器。
- **[uruntime](https://github.com/VHSgunzo/uruntime)**（VHSgunzo，MIT）——解包 x86 AppImages 及无法自行解包运行时的 AppImages。
- **[DirectAudio](https://github.com/The412Banner/directaudio)**（The412Banner，LGPL-2.1-or-later）——Steam 客户端音频的 PulseAudio 接收端（构建自 `tools/directaudio/sink`）。
- **[Flatpak](https://github.com/flatpak/flatpak)**（LGPL-2.1）——Store 安装并运行 Flathub 应用。
- **[zypak](https://github.com/refi64/zypak)**（refi64，BSD-3-Clause）——Flathub 的 Chromium / Electron 应用浏览器进程处理。

### 输入与控制

- **ribbit384**（[WinNative #345](https://github.com/WinNative-Emu/WinNative/pull/345)）——mmap 环形缓冲区手柄输入。

### 智能眼镜 / XR

- **[XRLinuxDriver](https://github.com/wheaney/XRLinuxDriver)**（wheaney）——VITURE 协议校验，含其随附的 VITURE 协议头。
- **[ar-drivers-rs](https://github.com/badicsalex/ar-drivers-rs)**（badicsalex）——VITURE 与 Rokid 协议参考。
- **[go-macos/iokit VITURE](https://pkg.go.dev/github.com/go-macos/iokit/viture)**——VITURE 协议参考。
- **VITURE SDK**——归 VITURE 所有，**不随本项目分发**（SDK 相关功能需在构建时自备，见 [viture-sdk](../development/viture-sdk.md)）。
- **[RayNeo-Air-Series-3DoF-Scaffold](https://github.com/informalTechCode/RayNeo-Air-Series-3DoF-Scaffold)**（informalTechCode，Apache-2.0）——RayNeo 支持。
- **[RayNeo-Air-3S-Pro-OpenVR](https://github.com/verncat/RayNeo-Air-3S-Pro-OpenVR)**（verncat，MIT）与 **[Monado](https://gitlab.freedesktop.org/monado/monado)**（BSL-1.0）——RayNeo 参考。
- **[xrealOneDeviceKit](https://github.com/wheaney/xrealOneDeviceKit)**、**[xreal_one_driver](https://github.com/rohitsangwan01/xreal_one_driver)**（rohitsangwan01，MIT）、**[xr-tools](https://github.com/0xcaff/xr-tools)**（0xcaff）——XREAL 1S 支持。
- **[MagicDesk investigation](https://github.com/mekhontsev/magicdesk/blob/main/docs/xr-resolution-fix.md)**（mekhontsev）与 **[decompiled Nubia kernel](https://github.com/Coding-BR/android_kernel_nubia_sm8...)**（Coding-BR）——REDMAGIC 眼镜显示研究。

### Joy-Con / VR 手柄

- **[Google VR SDK for Unity](https://github.com/googlevr/gvr-unity-sdk)**（Apache-2.0）——Joy-Con VR 手柄的手臂模型。
- **[OpenVR](https://github.com/ValveSoftware/openvr)** 的 `handskeletonsimulation` 示例（BSD-3-Clause）与 **[Monado](https://gitlab.freedesktop.org/monado/monado)** 的 `vive_poses.c`（BSL-1.0）——手部骨骼与 Index 手柄偏移。
- Linux 内核 **[hid-nintendo](https://github.com/torvalds/linux/blob/master/drivers/hid/hid-nintendo.c)**（Daniel J. Ogorchock）、**[Nintendo_Switch_Reverse_Engineering](https://github.com/dekuNukem/Nintendo_Switch_Reverse_Engineering)**（dekuNukem）、**[SDL](https://github.com/libsdl-org/SDL)**、AOSP——Joy-Con 行为研究。
- **[ALVR](https://github.com/alvr-org/ALVR)**、**[OpenComposite](https://gitlab.com/znixian/OpenOVR)**、**[PSMoveSteamVRBridge](https://github.com/HipsterSloth/PSMoveSteamVRBridge)**、**[JoyConVr](https://github.com/F8LEFT/JoyConVr)**（F8LEFT）、**[Daydream-Controller-SteamVR](https://github.com/krnelpan1c/Daydream-Controller-SteamVR)**（krnelpan1c）、**[joyconvr](https://github.com/yoyobuae/joyconvr)**（yoyobuae）、**[Moonlight](https://github.com/moonlight-stream/moonlight-android)**、**[Dolphin](https://github.com/dolphin-emu/dolphin)**——交叉校验与参考。

> Nintendo Switch 与 Joy-Con 是 Nintendo 的商标；本项目与 Nintendo 无关联。

### 手部追踪 / 摄像头

- **[MediaPipe](https://github.com/google-ai-edge/mediapipe)** Tasks Vision（Pose Landmarker (Lite)、BlazePose GHUM 3D、Hand Landmarker，均 Apache-2.0）——摄像头手部追踪。
- **[CameraX](https://developer.android.com/jetpack/androidx/releases/camera)**（Apache-2.0）——摄像头取帧。
- **[JoyConVr](https://github.com/F8LEFT/JoyConVr)** 摄像头应用（MIT）——设计参考。
- **[HaGRID](https://github.com/hukenovs/hagrid)** 手势集（CC BY-SA 4.0，未随附）——手势距离校验。
- **[ALVR](https://github.com/alvr-org/ALVR)** 的 `hand_gestures.rs`（MIT）与 Meta Quest 的菜单捏合——裸手手势参考。

### 商店 / 应用运行

- **[Flatpak](https://github.com/flatpak/flatpak)**（LGPL-2.1）——Store 安装并运行 Flathub 应用；其沙箱经本项目自有的 bubblewrap shim 运行。

---

## 许可证说明

- **DYSteam Lite 本体**：GPL-3.0（沿用原项目，见仓库根目录 [LICENSE](../LICENSE)）。
- 各第三方库遵循其各自的许可证（已在上述各处标注：MIT、Apache-2.0、BSD-3-Clause、BSD-3-Clause、BSL-1.0、LGPL-2.1 / LGPL-2.1-or-later、CC BY-SA 4.0 等）。
- 完整的三方许可证文本位于 `app/src/main/assets/licenses/`、`docs/third-party/` 及各 `LICENSE*` 文件中。

> 若你在 DYSteam Lite 基础上二次分发，请同时保留本项目（GPL-3.0）与上述各第三方库的许可证与致谢。
