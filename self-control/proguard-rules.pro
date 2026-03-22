# Self-Control Module ProGuard Rules

# Keep all public Skill classes and interfaces
-keep public class com.xiaolongxia.androidopenclaw.selfcontrol.** { *; }

# Keep all classes that implement Skill interface
-keep class * implements com.xiaolongxia.androidopenclaw.agent.tools.Skill { *; }

# Keep SelfControlRegistry
-keep class com.xiaolongxia.androidopenclaw.selfcontrol.SelfControlRegistry { *; }

# Keep all Skill execute methods (reflection may be used)
-keepclassmembers class * implements com.xiaolongxia.androidopenclaw.agent.tools.Skill {
    public *** execute(...);
}

# Keep SkillResult
-keep class com.xiaolongxia.androidopenclaw.agent.tools.SkillResult { *; }

# Keep tool definition classes
-keep class com.xiaolongxia.androidopenclaw.providers.** { *; }

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
