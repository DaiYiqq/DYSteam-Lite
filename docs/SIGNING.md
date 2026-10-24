# 发布签名 / Release Signing

本文说明如何让 DYSteam Lite 的 Release 构建使用**你自己的密钥**签名 APK，以便上架应用商店或作为正式发布版本分发。

> 默认情况下，`release.yml` 构建出的 APK 使用原项目自带的 **AOSP testkey** 签名（`keystore/testkey.p12`，密码 `android`）。该签名只能用于侧载安装，不能上架 Google Play。配置本文的 Secret 后，构建会自动改用你的密钥签名。

---

## 一、生成签名密钥

用 JDK 自带的 `keytool` 生成一个 keystore（`.jks` 或 `.p12`）：

```bash
keytool -genkeypair -v \
  -keystore release.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias dysteam
```

按提示设置密钥库密码、密钥密码与信息。**请务必妥善保管** keystore 文件与两个密码，丢失后无法为同一应用签名（无法覆盖升级）。

也可以用原项目的脚本：`tools/release/make-release-key.sh`。

---

## 二、在 GitHub 配置 4 个 Secret

在仓库 **Settings → Secrets and variables → Actions → New repository secret** 中依次添加：

| Secret 名 | 内容 | 说明 |
| --- | --- | --- |
| `DYSTEAM_RELEASE_KEYSTORE_BASE64` | keystore 文件的 base64 编码 | 见下方生成命令 |
| `DYSTEAM_RELEASE_STORE_PASSWORD` | 密钥库密码 | 生成 keystore 时设置的 |
| `DYSTEAM_RELEASE_KEY_ALIAS` | 密钥别名 | 如上例为 `dysteam` |
| `DYSTEAM_RELEASE_KEY_PASSWORD` | 密钥密码 | 生成 keystore 时设置的 |

### 生成 `DYSTEAM_RELEASE_KEYSTORE_BASE64`

Windows PowerShell：

```powershell
[Convert]::ToBase64String([IO.File]::ReadAllBytes("C:\path\to\release.jks"))
```

Linux / macOS：

```bash
base64 -w0 release.jks
```

把输出的**一整行** base64 字符串粘贴为 Secret 的值。

---

## 三、工作流行为

`release.yml` 里的「Sign with release key (optional)」步骤：

- **已配置** `DYSTEAM_RELEASE_KEYSTORE_BASE64` → 用你的 keystore 重新签名 APK（v1 + v2 + v3），再发布；
- **未配置** → 保持 AOSP testkey 签名（仅侧载分发）。

因此，即使暂不配置 Secret，Release 构建依然能正常产出并发布 APK；配置后即自动切换为正式签名。

---

## 四、签名一致性（重要）

- 一旦用某个 keystore 发布过正式版，**后续所有版本必须用同一 keystore 签名**，否则用户无法覆盖升级（Android 会提示签名不一致）。
- 请把 keystore 与密码备份到安全位置，并避免提交到 Git（仓库 `.gitignore` 已忽略 `*.jks` / `*.keystore` / `*.p12` / `*.pfx`）。
