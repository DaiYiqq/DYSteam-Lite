# 从源码构建 / Building from Source

本文档说明如何从源码构建 **DYSteam Lite**。DYSteam Lite 是 [DroidDeck](https://github.com/Droid-Deck/DroidDeck) 的复刻与增强版，构建流程与原项目一致。

---

## 一、环境准备 / Dependencies

构建需要以下依赖：

| 依赖 | 说明 |
| --- | --- |
| **Docker** | 用于与 CI 一致的构建环境（Linux / macOS 必需） |
| **Java 17** | 可通过 mise 或 SDKMAN 安装 |
| **Android SDK / NDK** | NDK 版本 `ndkVersion 27.3.13750724` |
| **Rust** | 通过 `rustup` 安装（用于商店下载引擎等原生库） |
| **GitHub CLI** | `gh`，用于拉取组件 / 产物 |
| **Python 3** | 构建脚本依赖 |
| **zstd** | 压缩 / 解压工具 |

> Linux / macOS 上一键构建经 Docker 完成，可免去本机手工配置 SDK/NDK/Rust 的繁琐。

---

## 二、一键构建 / One-shot Build

### Linux / macOS

```bash
tools/build_local.sh
```

该脚本会准备与 CI 相同的原生库、固定版本的组件与音频接收端，然后校验 APK 的 assets、JNI 导出与依赖。构建产物：

```
app/build/outputs/apk/release/app-release.apk
```

常用环境变量：

| 变量 | 作用 |
| --- | --- |
| `DROIDDECK_PA13_SOURCE_DIR` | 指向已有的 PulseAudio 13.0 源码目录，跳过下载 |
| `DROIDDECK_SIGNING_ENV` | 指向签名环境文件，用于在 release 构建上覆盖安装（相对 keystore 路径相对该文件解析） |
| `DROIDDECK_SKIP_RUST=1` | 跳过可选的商店下载引擎，构建不含商店引擎的 APK（同时省略缓存的引擎库） |

### Windows

Windows 上运行：

```powershell
./tools/run_windows.ps1
# 或指定设备序列号
./tools/run_windows.ps1 -Serial <adb-serial>
```

> Windows 需要先准备好运行时输入。运行 `python tools/prepare_windows_runtime.py` 可从固定的 CI APK、本地重编译的预加载库与校验和固定的 Gamescope 归档恢复本检出的已审计运行时输入（产物位于 `build/windows-setup/`）。启动前需先启动 **Docker Desktop** 的 Linux 引擎。

---

## 三、部署到设备 / Deploy

```bash
# 部署到已连接的设备
tools/deploy_local.sh
```

或在 **Android Studio** 中选择 **DroidDeck XR** 运行配置，把 `app` 模块运行到已连接的手机上。

`local.properties`（已忽略提交）中的机器相关选项：

| 属性 | 作用 |
| --- | --- |
| `droiddeck.python` | 指定 Python 可执行文件 |
| `droiddeck.skipRust=true` | 省略可选的商店下载引擎 |
| `droiddeck.debugApplicationIdSuffix=.xr` | 安装 **DroidDeck XR Dev**（与签名 release 并存，拥有独立运行时与 Steam 登录） |

本地 Java 选择记录在 `.gradle/config.properties`。

---

## 四、单元测试 / Unit Tests

无需准备原生库即可运行单元测试：

```bash
./gradlew -PskipRust=true :app:testDebugUnitTest
```

回归与预加载测试套件（Linux / macOS 经 Docker 运行）：

```bash
bash tools/test_local.sh
```

设备冒烟测试（经普通 adb 在真机检查构建）：

```bash
tools/device-smoke.sh
```

---

## 五、CI 与组件打包 / CI & Components

- CI 使用 Docker 准备与发布**相同的原生库、固定组件与音频接收端**。
- 组件（DXVK、VKD3D-Proton、FEX、Turnip 等）由 **Droid-Deck/DroidDeck-Components** 仓库构建与发布；应用的 Auto 驱动选择来自 **Drivers-CI** 的清单，因此默认驱动可在不升级应用的情况下变更。
- 夜间 DXVK / VKD3D-Proton / FEX 组件在组件管理器中提供 **Stable | Nightly** 切换。
- FEX 的构建与发布细节见 [x86-64-steam-client](../development/x86-64-steam-client.md)。

---

## 六、打包 APK / APK Output

构建产物：

```
app/build/outputs/apk/release/app-release.apk
```

- 准备完成后，普通的 `./gradlew :app:assembleRelease` 可复用已验证的输入重新构建。
- 若原生源码、组件固定版本或暂存文件发生变化，打包会在需要时停止并提示重新运行辅助脚本。
- 生成的音频产物位于 `app/build/` 下；被跟踪的基础包永远不会被修改。
- 使用 `DROIDDECK_SKIP_RUST=1 tools/build_local.sh` 可构建不含商店引擎的 APK。

---

## 七、全平台说明 / Platform Notes

| 平台 | 方式 | 说明 |
| --- | --- | --- |
| **Linux** | `tools/build_local.sh` | 经 Docker，与 CI 一致 |
| **macOS** | `tools/build_local.sh` | 经 Docker（Linux），避免用 macOS 头文件编译 Linux 预加载库 |
| **Windows** | `tools/run_windows.ps1` + `python tools/prepare_windows_runtime.py` | 需 Docker Desktop 的 Linux 引擎；支持 `-Serial` 指定设备 |

> 保存好被忽略的预构建产物（`app/build/windows-runtime-provenance.json` 等）；`clean` 会移除已准备的 assets 及其校验清单。

---

## 八、参考

- 环境准备、依赖与 CI 细节，参见仓库根目录 [README.md](../README.md) 的「Build」节。
- 智能眼镜 SDK（可选 VITURE Android SDK 2.4.0）的构建接入见 [viture-sdk](../development/viture-sdk.md)。
