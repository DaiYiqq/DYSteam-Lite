# 多语言说明 / Localization

本文档说明 **DYSteam Lite** 的多语言目录约定、默认语言、如何在应用内切换语言，以及如何新增 / 修改语言。DYSteam Lite 是 [DroidDeck](https://github.com/Droid-Deck/DroidDeck) 的复刻与增强版，本次复刻将默认语言改为简体中文。

---

## 一、默认语言

- **默认语言**：简体中文。
- 实现方式：把 `res/values/`（Android 的默认资源目录）设为简体中文，英文等其余语言移入各自的 `values-*` 目录。
- 当系统语言无法匹配任何 `values-*` 目录时，应用会回退到 `res/values/`（即简体中文）。

> 这一改动是在 DroidDeck 基础上进行的复刻 / 升级改动之一。

---

## 二、目录约定 / Directory Layout

字符串资源位于 `app/src/main/res/`，按 Android 语言限定符命名：

```
app/src/main/res/
├── values/              默认（简体中文，zh-CN）
├── values-en/           英文
├── values-ar/           阿拉伯语
├── values-b+es+419/     西班牙语（拉丁美洲）
├── values-bg/           保加利亚语
├── values-cs/           捷克语
├── values-da/           丹麦语
├── values-de/           德语
├── values-el/           希腊语
├── values-es/           西班牙语
├── values-fi/           芬兰语
├── values-fr/           法语
├── values-hi/           印地语
├── values-hu/           匈牙利语
├── values-in/           印度尼西亚语
├── values-it/           意大利语
├── values-ja/           日语
├── values-ko/           韩语
├── values-ms/           马来语
├── values-nb/           挪威语（博克马尔）
├── values-nl/           荷兰语
├── values-pl/           波兰语
├── values-pt/           葡萄牙语
├── values-pt-rPT/       葡萄牙语（葡萄牙）
├── values-ro/           罗马尼亚语
├── values-ru/           俄语
├── values-sv/           瑞典语
├── values-th/           泰语
├── values-tr/           土耳其语
├── values-uk/           乌克兰语
├── values-vi/           越南语
├── values-zh-rHK/       中文（繁体，香港）
└── values-zh-rTW/       中文（繁体，台湾）
```

共 **33 种语言**（含默认简体中文）。每个语言目录下主要包含：

- `strings.xml` —— 界面字符串；
- `gestures.xml` —— 触控手势的本地化描述。

---

## 三、在应用内切换语言

1. 打开应用的 **设置（Setup）**。
2. 找到 **语言 / Language** 相关选项（应用语言默认跟随系统）。
3. 选择目标语言。切换后界面即随之更新。

> 商店（GOG / Epic）的游戏安装语言默认跟随应用语言，也可在商店设置中单独固定，见 [stores](../development/stores.md)。

---

## 四、如何修改默认语言 / Changing the Default

默认语言由 `res/values/` 目录决定（因为它是 Android 无语言限定符时的回退目录）。

- **改为简体中文（当前默认）**：把简体中文字符串放在 `res/values/strings.xml`，英文放入 `res/values-en/strings.xml`。
- **改回英文等其他默认**：把目标语言字符串放回 `res/values/`，原默认语言移入对应的 `values-*` 目录。

> 注意：切换默认语言只影响「未命中任何语言目录时的回退」，不影响已经单独提供的 `values-*` 语言。

---

## 五、如何新增一种语言 / Adding a Language

以新增「示例语言 `xx`」为例：

1. 新建目录 `app/src/main/res/values-xx/`。
2. 复制 `res/values/strings.xml` 与 `res/values/gestures.xml` 到新目录。
3. 逐条翻译字符串（保留 `name` 属性不变，只改元素文本）。
4. 处理**复数形式**（`<plurals>`）：确保该语言需要的复数形式齐全。
5. 清理**漏译的英文残留**：翻译后检查是否还有未翻译的英文文本。
6. 重新构建并测试该语言在应用内的显示。

---

## 六、翻译约定 / Translation Notes

- 所有 `name` 键必须与默认语言（`res/values/`）保持一致。
- 占位符（如 `%1$d`、`%s`）必须原样保留，顺序与类型不得改变。
- 复数形式（`<plurals>` 的 `one` / `other` / `few` / `many` 等）按各语言语法补齐。
- 无障碍文案（屏幕阅读器朗读内容）也应一并翻译；按钮、磁贴、列表行等需在屏幕阅读器中可正确朗读。
- 提交翻译时，保持文件为 UTF-8 编码、无 BOM。

---

## 七、参考

- 语言目录结构以 `app/src/main/res/` 为准；更新语言时请同步本文档的目录清单。
- 原项目 DroidDeck 的翻译贡献者名单见 [docs/releases/0.6.0.md](../releases/0.6.0.md) 的「Community」节。
