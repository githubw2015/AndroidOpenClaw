# AndroidOpenClaw Releases

Pre-built APK packages, ready to use.

**📥 Latest Release**: [v1.3.0](https://github.com/SelectXn00b/AndroidOpenClaw/releases/tag/v1.3.0)

## 📦 Latest Version: v1.3.0 (2026-03-22)

### Installation Steps

1. **Download APK**
   - Download the APK files above (browser is optional)

2. **Install Apps**
   ```bash
   # Install via ADB
   adb install androidopenclaw-v2.4.3-release.apk
   adb install androidopenclaw-accessibility-v2.4.3-release.apk
   adb install BClaw-universal-release.apk  # Optional

   # Or install directly on phone
   ```

3. **Configure API**

   Create config file `/sdcard/AndroidOpenClaw/openclaw.json`:

   ```json
   {
     "version": "1.0.0",
     "agent": {
       "name": "androidopenclaw",
       "defaultModel": "claude-opus-4-6",
       "maxIterations": 50
     },
     "models": {
       "mode": "merge",
       "providers": {
         "anthropic": {
           "baseUrl": "https://api.anthropic.com/v1",
           "apiKey": "YOUR_ANTHROPIC_API_KEY",
           "api": "anthropic",
           "models": [
             {
               "id": "claude-opus-4-6",
               "name": "Claude Opus 4.6",
               "reasoning": true,
               "contextWindow": 200000
             }
           ]
         }
       }
     }
   }
   ```

   Push config to device:
   ```bash
   adb push openclaw.json /sdcard/AndroidOpenClaw/openclaw.json
   ```

4. **Grant Permissions**

   - Open **S4Claw** app and enable:
     - ✅ **Accessibility Service** - Required for device control
     - ✅ **Media Projection** - Required for screenshots
   - Open **Main app** and grant:
     - ✅ **Display Over Apps** - Required for floating window

5. **Configure Channels** (Optional)

   Configure Feishu or Discord in `/sdcard/AndroidOpenClaw/openclaw.json`:

   ```json
   {
     "gateway": {
       "enabled": true,
       "port": 8080,
       "feishu": {
         "enabled": true,
         "appId": "YOUR_APP_ID",
         "appSecret": "YOUR_APP_SECRET"
       },
       "discord": {
         "enabled": true,
         "token": "YOUR_DISCORD_BOT_TOKEN"
       }
     }
   }
   ```

6. **Start Using**

   - Send messages in Feishu or Discord
   - Or use the in-app chat interface
   - AI will automatically control your phone!

## 📋 System Requirements

- **Android**: 8.0+ (API 26+)
- **Permissions**: Accessibility Service, Display Over Apps, Media Projection
- **Network**: Internet connection for LLM API (Claude Opus 4.6 recommended)

## 🔧 Signing Info

All APKs are signed with unified keystore for compatibility:
- **Keystore**: keystore.jks
- **Signature**: Production signing
- **Main Package**: `com.xiaolongxia.androidopenclaw`
- **Accessibility Package**: `com.xiaolongxia.androidopenclaw.accessibility`

## 📝 Release Notes

### v2.4.3 (2026-03-09)

**🎉 New Features:**
- ✅ **Full ClawHub integration** - skills.search, skills.install, skills.status
- 🌐 **Browser Tool improvements** - Fixed port configuration (8765), added Baidu search example
- 📚 **Complete documentation** - [CLAWHUB_GUIDE.md](../CLAWHUB_GUIDE.md)

**🔧 Technical Improvements:**
- 🔐 **Unified signing configuration** - All APKs use same keystore.jks
- ✅ **Release build auto-signing** - No manual signing needed
- 🎨 **UI optimizations** - ConfigActivity Skills page, ChatScreen improvements

**⚠️ Important:**
- ClawHub API (clawhub.ai) is fully operational, even if website (clawhub.com) shows 404
- BrowserForClaw uses port 8765 (not 8766)
- Don't use open_app for browser operations, use browser tool directly

**📥 Download:** [GitHub Releases](https://github.com/SelectXn00b/AndroidOpenClaw/releases/tag/v2.4.3)

**🔗 Related Docs:**
- [CLAWHUB_GUIDE.md](../CLAWHUB_GUIDE.md) - ClawHub integration guide
- [CLAUDE.md](../CLAUDE.md) - Development guide
- [ARCHITECTURE.md](../ARCHITECTURE.md) - Architecture design

## 🐛 Troubleshooting

Having issues?

1. Check [Documentation](../docs/README.md)
2. Search [Known Issues](https://github.com/SelectXn00b/AndroidOpenClaw/issues)
3. Submit [New Issue](https://github.com/SelectXn00b/AndroidOpenClaw/issues/new)

## 📚 More Resources

- [Full Documentation](../README.md)
- [Quick Start](../README.md#-quick-start)
- [ClawHub Guide](../CLAWHUB_GUIDE.md)
- [Contributing](../CONTRIBUTING.md)

---

**AndroidOpenClaw** - Give AI the power to use phones 🦞📱

*Inspired by [OpenClaw](https://github.com/openclaw/openclaw)*
