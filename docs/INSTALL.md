# 安装与使用详解 / Install & Usage

本文档提供 **DYSteam Lite** 的完整安装、配置与使用说明，并附常见问题（FAQ）。DYSteam Lite 是 [DroidDeck](https://github.com/Droid-Deck/DroidDeck) 的复刻与增强版，使用方法与原项目基本一致。

---

## 一、系统要求

| 项目 | 要求 |
| --- | --- |
| **系统** | Android 9 或更新版本 |
| **GPU** | Adreno 730 或更新（或 8xx 系列） |
| **实验性 GPU** | Adreno 6xx（DirectX 11 经 DXVK 2 可能可运行，DirectX 12 游戏仍可能崩溃） |
| **不支持的 GPU** | Mali、Xclipse、PowerVR、Adreno 710 |
| **Root** | 无需 root |
| **存储** | 运行时约 3 GB；桌面与模拟器额外约 1.1 GB |

> 兼容性与性能因设备而异，硬件验证范围有限。

---

## 二、安装步骤

### 1. 安装 APK

下载最新版 APK 并安装到设备。构建产物位于：

```
app/build/outputs/apk/release/app-release.apk
```

> 注意：DYSteam Lite 没有独立官网。请勿点击任何声称代表本项目团队的网站上的下载链接。自行构建方式见 [从源码构建](BUILDING.md)。

### 2. 安装 Linux 运行时

首次启动后，按提示安装 **Linux 运行时**（约 3 GB）。这一步会下载并解压用户态 Linux 根文件系统（Arch Linux 基础镜像）。

### 3. 登录 Steam

运行时就绪后，点击 **Play** 启动 Steam 并登录你的账号。Steam 会在首次启动时自行下载客户端文件。

### 4. Android 14+ 关闭「子进程限制」

在 **Android 14 及更新版本** 上，启动 Steam **之前**必须开启开发者选项中的 **「停用子进程限制 / Disable child process restrictions」**：

1. 进入 **设置 → 关于手机**，连续点击「版本号」多次以开启**开发者选项**；
2. 进入 **设置 → 系统 → 开发者选项**；
3. 找到并开启 **「停用子进程限制 / Disable child process restrictions」**。

若在 **Android 12 / 13** 设备上找不到该选项，Steam 首次启动时会显示 **「替我修复 / Fix it for me」** 按钮，帮助你自动完成设置。

### 5. 安装桌面与模拟器（可选）

如需使用桌面模式、模拟器与 Linux 应用：

- 安装 **「桌面与应用 / Desktop & apps」**（额外约 1.1 GB）。
- 安装 **Store**（Setup › Launcher › Linux apps）后，可从 Flathub 安装 ARM64 Linux 应用与游戏（Flatpak），也可安装 AppImages。

---

## 三、日常使用

### 启动 Steam

安装完成后，回到主界面点击 **Play**，即可进入 Steam Big Picture 大屏模式。默认情况下，离开应用会**暂停**会话；返回后会从原处继续。

### 运行 Windows 游戏

在 Steam 库中直接启动游戏即可。Windows 游戏会自动经 ARM64 Proton 运行；如需为单个游戏指定 Proton / FEX / DXVK / VKD3D-Proton 组件或环境变量，使用 **游戏环境编辑器**（Games → Game environment）。

### 使用多商店

在 **Setup › Stores › Show Stores in the rail** 中开启商店栏后，可登录 GOG、Epic Games、Amazon Games、itch.io、Xbox（含 PC Game Pass），下载并统一管理游戏。

### 智能眼镜 / XR

在 **Setup › Smart glasses** 中配置智能眼镜（VITURE、RayNeo、XREAL、Rokid 等）的虚拟大屏、3DoF / 6DoF 追踪与 SteamVR。支持的设备、连接与限制见 [智能眼镜支持矩阵](smart-glasses-support.md)。

### 切换语言

应用语言跟随系统，或于 **设置（Setup）** 中切换。默认语言为**简体中文**。详见 [多语言说明](LANGUAGES.md)。

### 分享日志 / 报告问题

在 **Setup** 或会话抽屉中点击 **Share logs**，可打包最新一次会话（含商店与工具日志）为一个脱敏 zip，用于报告问题。

---

## 四、常见问题 / FAQ

### Q1：为什么应用内部包名还是 `com.droiddeck.launcher`，而不是 DYSteam Lite？

为了兼容 **15+ 个原生 JNI 符号**，本项目刻意保留了原项目 DroidDeck 的内部包名 / 命名空间 `com.droiddeck.launcher`。对外显示名称统一为 **DYSteam Lite**，这不影响功能。

### Q2：Steam 无法启动 / 停在黑屏？

- 确认系统为 Android 9+，GPU 为受支持的 Adreno 型号；
- **Android 14+**：务必在开发者选项中开启「停用子进程限制」；
- 确认 Linux 运行时已完整安装、磁盘空间充足（约 3 GB）；
- 首次启动会下载 Steam，请保持网络畅通并耐心等待。

### Q3：Steam 下载失败或提示网络 / VPN 拦截？

Steam 客户端下载可能被网络或 VPN 阻断。关闭 VPN / 代理后重试，或在提示时点击 **Retry**。

### Q4：游戏画面闪烁 / 花屏 / 绿屏？

- Samsung One UI 设备（骁龙 8 Gen 2，Galaxy S23 / Tab S9）的历史闪烁问题已在后续版本修复，请升级到最新版；
- 尝试调整会话分辨率（Setup › Display › Resolution）；
- 确认使用的是与设备匹配的 GPU 驱动（Auto）。

### Q5：DirectX 12 游戏崩溃？

Adreno 6xx 上 DirectX 12 可能崩溃（实验性支持）。请使用 Adreno 730+ 或 8xx 设备。也可在游戏环境编辑器中调整 `VKD3D_FEATURE_LEVEL` / `VKD3D_SHADER_MODEL`，但「强制 12_2 / 6_9」仅改变报告的能力，无法实现缺失的 Vulkan 特性。

### Q6：如何卸载桌面模式？

从 **Open desktop** 旁卸载桌面（包括中断的桌面安装）。游戏、Steam、已添加应用与设置会被保留。详见 [desktop-uninstall](desktop-uninstall.md)。

### Q7：如何报告 bug？

请附上 **Share logs** 打包的日志 zip。DYSteam Lite 是 DroidDeck 的复刻，社区问题可参考原项目 [DroidDeck Discord](https://discord.gg/JRGAvawjsm) 的 **#bug-reports** 论坛。

---

## 五、已知限制

- 桌面合成的软件渲染（Desktop compositing uses software rendering）。
- proot 下 Firefox 沙箱被减弱。
- 日志保存在应用私有目录（`files/logs/`，保留最近 30 次会话）。
- 兼容性与性能因设备而异。
